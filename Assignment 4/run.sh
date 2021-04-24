#!/bin/bash

#Program name: Ricochet Ball
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

echo Compile Animation.java
javac Animation.java

echo Compile UI.java
javac UI.java

echo Compile CatAndMouse.java
javac CatAndMouse.java

echo Execute the CatAndMouse program
java CatAndMouse.java

echo End of execution.  Have a nice day.
