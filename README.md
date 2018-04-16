## To Compile and Run Code in OSX and Unix Environments
1. `javac linkstate.java`
2. `java linkstate network.txt`

### Code Structure
First, my code reads in the arguments from the command line to verify that there is a file name specified. It then reads from the file and creates a 2D Array from the data that represents the node graph. Next, dijkstra's algorithm is run on a loop and the node distances and previous nodes are kept track of and updated. At the end of each loop iteration, the print function is called and you can see the current state.

### Execution Results
