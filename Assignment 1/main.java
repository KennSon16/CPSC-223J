//****************************************************************************************************************************
//Program name: "Payroll Calculator".  This program calculates basic hourly pay included with a simple overtime bonus rate   *
//Copyright (C) 2021 Kenn Son                                                                                                *
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.                                                                    *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author information:
  //Author: Kenn Son
  //E-mail: kenneki@csu.fullerton.edu

//Program information:
  //Program name: Payroll Calculator
  //Programming language: Java
  //Files: main.java, gui.java, Payroll.java, run.sh
  //Date project began: 2021-January-27.
  //Date of last update: 2021-January-27.
  //Status: In progress; testing completed.
  //Purpose: This program shows a simple user-interface that allows the user to input a name, hours that person has worked and
  //         the hourly payrate in order to calculate the gross pay for that person.
  //Special feature: The program shows how to colorize individual characters with a string.  The program has a built-in 3.5
  //second delay after clicking on the Exit button.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: main.java
  //Compile : javac main.java
  //This is the top level module.  This module activates the user interface.

import javax.swing.JFrame;
public class main
{
  public static void main(String[] args)
  {
    gui myframe = new gui();
    myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myframe.setSize(350,535);
    myframe.setResizable(false);
    myframe.setVisible(true);
  }//End of main
}//End of class main
