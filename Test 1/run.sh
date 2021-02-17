#Kenn Son
#CPSC223J Test 1

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Algorithm.java
javac Algorithm.java

echo Compile gui.java
javac gui.java

echo Compile main.java
javac main.java

echo Execute the CPSC223J Test 1 program
java main.java

echo End of execution.  Have a nice day.
