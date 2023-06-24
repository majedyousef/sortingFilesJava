import java.io.File;
import java.util.Arrays;

public class FileOrganizer {

  public static void main(String[] args) {
    // Specify the directory path
    String directoryPath = "/path/to/directory";

    // Get the directory as a File object
    File directory = new File(directoryPath);

    // Check if the directory exists and is a directory
    if (directory.exists() && directory.isDirectory()) {
      // Get the list of files in the directory
      File[] files = directory.listFiles();

      // Sort the list of files by name
      Arrays.sort(files, (f1, f2) -> f1.getName().compareTo(f2.getName()));

      // Print the sorted list of files
      for (File file : files) {
        System.out.println(file.getName());
      }
    } else {
      System.out.println("Error: The specified directory does not exist or is not a directory.");
    }
  }
}
