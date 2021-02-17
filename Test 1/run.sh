#Kenn Son
#CPSC223J Test 1

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Algorithm.java
javac Algorithm.java

echo Compile ui.java
javac ui.java

echo Compile driver.java
javac driver.java

echo Execute the CPSC223J Test 1 program
java driver.java

echo End of execution.  Have a nice day.
