import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Stream;

public class sortFilesByDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to organize: ");
        String pathString = scanner.nextLine();

        // get the path and list of files in the specified directory
        Path path = Paths.get(pathString);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.out.println("Invalid path.");
            return;
        }
        Stream<Path> files = null;
        try {
            files = Files.list(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // iterate through the files and move them to the appropriate year and month folder
        files.forEach(file -> {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTimeInMillis(Files.getLastModifiedTime(file).toMillis());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);

            // create the year and month folders if they don't exist
            Path yearPath = path.resolve(String.valueOf(year));
            if (!Files.exists(yearPath)) {
                try {
                    Files.createDirectory(yearPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Path monthPath = yearPath.resolve(String.format("%02d", month + 1));
            if (!Files.exists(monthPath)) {
                try {
                    Files.createDirectory(monthPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // move the file to the appropriate month folder
            try {
                Files.move(file, monthPath.resolve(file.getFileName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
