import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.*;

public class linkstate {

  // D(v): cost of the least-cost path from the source node to destination v as of this iteration of the algorithm.
  // p(v): previous node (neighbor of v) along the current least-cost path from the source to v.
  // N: subset of nodes; v is in N  if the least-cost path from the source to v is definitively known.

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

    // puts the numbers in a 2d array
    for (int i = 0; i < fileSplit.length; i++) {
        // System.out.println("line " + i + ": " + fileSplit[i]);
        String[] graphString = fileSplit[i].split("\\,");
        int[][] graph = new int[fileSplit.length][graphString.length];
        for (int j = 0; j < graphString.length; j++) {
          int item = -1;
          try {
            item = Integer.parseInt(graphString[j]);
          } catch (NumberFormatException e) {
            item = -1;
          }
          graph[i][j] = item;
          // System.out.println(graph[i][j]);
        }
    }

    printTitle();

  }

  public static void dottedLine() {
    System.out.println("--------------------------------------------------------------------------------");
  }

  public static void printTitle() {
    dottedLine();
    System.out.println("Step  N'      D(v),p(v)     D(w),p(w)    D(x),p(x)     D(y),p(y)      D(z),p(z) ");
    dottedLine();
  }

}
