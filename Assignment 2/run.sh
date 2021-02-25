#!/bin/bash

#Program name: Baseball Runner
#Author: Kenn Son
#Email: kenneki@csu.fullerton.edu
#File name:  run.sh
#Preconditions:
#   1.  All source files are in one directory
#   2.  This file, run.sh, is in that same directory
#   3.  The shell is currently active in that same directory
#How to execute: Enter "sh run.sh" without the quotes.

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Computations.java
javac Computations.java

echo Compile Quad.java
javac Quad.java

echo Compile BaseballUI.java
javac BaseballUI.java

echo Compile Diamond.java
javac Diamond.java

echo Execute the Baseball Runner program
java Diamond.java

echo End of execution.  Have a nice day.
