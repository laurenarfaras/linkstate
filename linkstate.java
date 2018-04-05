import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.*;

public class linkstate {

  public static void main(String[] args) throws IOException {

    // check for filename argument
    if (args.length != 1) {
      System.err.println("java linkstate <file name>");
      System.exit(0);
    }

    // read from file
    String filename = args[0].replaceAll("\\s+", "");
    String fileContents = new String(Files.readAllBytes(Paths.get(filename))).replaceAll("\\s+", "");
    String[] fileSplit = fileContents.split("\\.");

    for (int i = 0; i < fileSplit.length; i++) {
        System.out.println("line " + i + ": " + fileSplit[i]);
    }

  }

}
