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
    public static int infiniti = Integer.MAX_VALUE;

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
      // System.out.println("amt of nodes: " + amtOfNodes);

      // put the values into a 2d array of strings
      String[][] nodeStrings = new String[amtOfNodes][amtOfNodes];
      for (int i = 0; i < amtOfNodes; i++) {
        nodeStrings[i] = fileSplit[i].split("\\,");
      }

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

      dijkstra(nodeInts);

    }

    public static void dijkstra(int nodeInts[][]) {
        // keeps track of the shortest distance
        int distance[] = new int[amtOfNodes];
        // keeps track of the previous node to find the
        // shortest distance to the node at the corresponding index
        String prevNode[] = new String[amtOfNodes];
        // subset of nodes with definitively known least-cost paths
        boolean located[] = new boolean[amtOfNodes];

        // set all distances = infiniti and located[] as false
        for (int i = 0; i < amtOfNodes; i++) {
            distance[i] = infiniti;
            located[i] = false;
        }

        // string of located nodes
        String locNodes = "";
        int numLocated = 0;

        // set distance to self = 0
        distance[0] = 0;

        printTitle();

        for (int i = 0; i < amtOfNodes; i++) {
            // finds the node that is closest to current node
            int min = infiniti;
            int index = -1;
            for (int a = 0; a < amtOfNodes; a++) {
              if (!located[a] && distance[a] <= min) {
                  min = distance[a];
                  index = a;
              }
            }
            // current node is located
            located[index] = true;
            if (i == 0) {
              locNodes = locNodes + Integer.toString(index + 1);
            } else {
              locNodes = locNodes + "," + Integer.toString(index + 1);
            }
            numLocated++;

            for (int b = 0; b < amtOfNodes; b++) {
              // if node b is not yet located
              // and if node b knows the distance to the index
              // and if the distance for the index is known,
              // and if the cumulative distance to node b is less than the current distance to node b
              if (!located[b] && nodeInts[index][b]!= infiniti && distance[index] != infiniti &&
                  distance[index]+nodeInts[index][b] < distance[b]) {
                    distance[b] = distance[index] + nodeInts[index][b];
                    prevNode[b] = Integer.toString(index + 1);
              }
            }
            // print the constructed distance array
            output(i, located, locNodes, numLocated, distance, prevNode, amtOfNodes);
        }

    }

    // print output
    public static void output(int step, boolean located[], String locNodes, int numLocated, int distance[], String prevNode[], int n) {
        System.out.print(step + "       ");
        int spaceCount = 0;
        boolean firstprint = true;
        int amtOfSpaces = located.length - numLocated;
        String spaces = "";
        for (int h = 0; h < amtOfSpaces; h++) {
          spaces = spaces + "  ";
        }
        System.out.print(locNodes + spaces);
        for (int i = 0; i <= spaceCount; i++) {
          System.out.print("  ");
        }
        for (int k = 1; k < amtOfNodes; k++) {
          if (distance[k] == infiniti) {
            System.out.print("N         ");
          } else if (located[k]) {
            System.out.print("          ");
          } else {
            System.out.print( distance[k] + "," + prevNode[k] + "       ");
          }
        }
        System.out.println();
        dottedLine();
    }

    public static void printTitle() {
      System.out.print("Step    N'          ");
      for (int c = 1; c < amtOfNodes; c++) {
        System.out.print("D(" + (c+1) + "),p(" + (c+1) + ")   ");
      }
      System.out.println();
      dottedLine();
    }

    public static void dottedLine() {
     System.out.println("-------------------------------------------------------------------------");
    }

}
