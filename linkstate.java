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

  public static int amtOfNodes;
  public static Integer infiniti = Integer.MAX_VALUE;


  public static void main(String[] args) throws IOException {

    // check for filename argument
    if (args.length != 1) {
      System.err.println("java linkstate <file name>");
      System.exit(0);
    }

    // read from file
    String filename = args[0].replaceAll("\\s+", "");
    String fileContents = new String(Files.readAllBytes(Paths.get(filename))).replaceAll("\\s+", "");
    String[] fileSplit = fileContents.trim().split("\\.");

    // find size of 2d array
    amtOfNodes = fileSplit.length - 1;
    System.out.println("amt of nodes: " + amtOfNodes);

    // put the values into a 2d array of strings
    String[][] nodeStrings = new String[amtOfNodes][amtOfNodes];
    for (int i = 0; i < amtOfNodes; i++) {
      nodeStrings[i] = fileSplit[i].split("\\,");
    }
    // for (int i = 0; i < amtOfNodes; i++) {
    //   for (int j = 0; j < amtOfNodes; j++) {
    //     System.out.print(nodeStrings[i][j] + " ");
    //   }
    //   System.out.println();
    // }

    // convert the strings to integers
    int[][] nodeInts = new int[amtOfNodes][amtOfNodes];
    for (int i = 0; i < amtOfNodes; i++) {
      for (int j = 0; j < amtOfNodes; j++) {
        if (nodeStrings[i][j].equals("N")) {
          nodeInts[i][j] = infiniti;
        } else {
          nodeInts[i][j] = Integer.parseInt(nodeStrings[i][j]);
        }
      }
    }

    // print 2d array of integers
    for (int i = 0; i < amtOfNodes; i++) {
      for (int j = 0; j < amtOfNodes; j++) {
        if (nodeInts[i][j] == infiniti) {
          System.out.print("N.");
        } else {
          System.out.print(nodeInts[i][j] + " ");
        }
      }
      System.out.println();
    }

  }

  public static void dottedLine() {
    System.out.println("--------------------------------------------------------------------------------");
  }

}
