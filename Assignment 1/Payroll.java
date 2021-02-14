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
  //Date of last update: 2021-February-13.
  //Status: In progress; testing completed.
  //Purpose: This program shows a simple user-interface that allows the user to input a name, hours that person has worked and
  //         the hourly payrate in order to calculate the gross pay, overtime pay, and regular pay for that person.
  //Nice features: If no values are entered into the input boxes then zero is assumed to be the input.
  //               If given a first and last name will only print out the first name.
  //               If no name is entered into the textbox then will be print "No Name"
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
    //File name: Payroll.java
    //Compile: Payroll.java
    //This module is invoked from the gui class
    //Purpose: Dectect and calculate the gross pay for a person.

    //Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**
public class Payroll
{
  //theres no need for private variables.
  public static double regularPay(double hours, double hourlyRate)
  {
    return hours * hourlyRate;
  }

  public static double overtime(double hours, double hourlyRate)
  {
    //overtime is 0 by default;
    if (hours > 40.0)
    {
      return (hours - 40) * 1.5 * hourlyRate;
    }
    return 0.0;
  }

  public static double grossPay(double regularPay, double overtimePay)
  {
    return regularPay + overtimePay;
  }
}
