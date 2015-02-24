NOTE: The program uses JDK 8
In order to find a Knight's tour of a N dimension chess board you can follow either of the two options. In either case you have to install JDK 8 and set your JAVA_HOME variable appropriately.

1. Use Maven
If you have maven installed execute the commands:
a. mvn compile (In case you are running the code for the first time). mvn test(In case you are interested in running unit tests) 
b. mvn exec:exec -Ddimension={dimension} -DstartRow={startRow} -DstartColumn={startColumn}. Note that all 3 have to be non-zero positive integers and startRow  and startColumn have to greater than 0 and less than or equal to dimension
If you omit the -D parameters and just run mvn exec:exec then the program will run for a chessboard of size 5 and start from position 3,3. Also Note if you enter invalid values for any of the above params then you will see a message
[INFO] --- exec-maven-plugin:1.3.2:exec (default-cli) @ chessproblems ---
Please enter valid input of the form [dimension of board] [start column] [start
row]. For eg. 8 5 1

2. Use java on the command line
a. Use javac or your favorite IDE to compile the code
b. Go to the output directory where the classes have been compiled. Execute the command java -Djava.util.logging.config.file={absolute or relative path to logging.properties} com.salil.chessproblems.KnightsTour {dimension} {startRow} {startColumn}.
For eg. 
java -Djava.util.logging.config.file=logging.properties com.salil.chessproblems.KnightsTour 5 3 3 to find a knights tour for a board of size 5 starting from 3,3

The output will be seen on the console and the file ${user.home}/knightstour-{uniqueId}.txt

Found 64 ways to do  Knights Tour of a 5 * 5 sized chessboard with knight starting at : 3,3 in 0.907 seconds

3,3 -> 5,4 -> 3,5 -> 1,4 -> 2,2 -> 4,1 -> 5,3 -> 4,5 -> 2,4 -> 1,2 -> 3,1 -> 5,2 -> 4,4 -> 2,5 -> 1,3 -> 2,1 -> 4,2 -> 3,4 -> 5,5 -> 4,3 -> 5,1 -> 3,2 -> 1,1 -> 2,3 -> 1,5
3,3 -> 5,4 -> 3,5 -> 1,4 -> 2,2 -> 4,1 -> 5,3 -> 4,5 -> 2,4 -> 1,2 -> 3,1 -> 5,2 -> 4,4 -> 2,5 -> 1,3 -> 2,1 -> 4,2 -> 3,4 -> 1,5 -> 2,3 -> 1,1 -> 3,2 -> 5,1 -> 4,3 -> 5,5