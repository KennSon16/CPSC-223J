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
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: gui.java
  //Compile : javac gui.java
  //Purpose: This class defines the user interface.
  //This module (class) is called from the main class.

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame; //frame of the window
import javax.swing.JButton; // adds buttons
import javax.swing.JTextField;  //adds a text box
import javax.swing.JLabel; // adds labels on the panels
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.BorderFactory;

// These imports are saved here incase of any fails withj other imports
//import java.awt.FlowLayout;
//import java.awt.BorderLayout;
//import javax.swing.Timer;
//import java.awt.Insets;

public class gui extends JFrame
{
  private JLabel mainTitle; // Business name
  private JLabel subtitle; // subtitle
  private JLabel displayName; // "Employee name:"
  private JTextField textboxName; // name
  private JLabel displayHours; // "Hours worked:"
  private JTextField textboxHours; //hours
  private JLabel displayRates; // "Hourly pay rate:"
  private JTextField textboxRate; // rate
  private JLabel displayName2; // "Name of Employee"
  private JLabel firstNameOnly; // put first name
  private JLabel displayRegularPay; // "Regular pay"
  private JLabel regularPayment; // regularPay()
  private JLabel displayOvertimePay; // "Overtime pay"
  private JLabel overtimePayment; // overtimePay()
  private JLabel displayGrossPay; // "Gross pay"
  private JLabel grossPayment; // grossPay()
  private JButton clearButton;
  private JButton computeButton;
  private JButton quitButton;
  private JPanel titlePanel;
  private JPanel inputPanel;
  private JPanel outputPanel;
  private JPanel buttonPanel;


  public gui() //constructor
  {//setting the dimensions and layout for the frame
    super("Program 1");
    setLayout(new GridLayout(4,1));

    //CREATING PANELS
    //first panel red
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(245,88,64));
    titlePanel.setLayout(new GridLayout(2,1));

    mainTitle = new JLabel();
    mainTitle.setText("Kenn\'s Designs Company");
    mainTitle.setHorizontalAlignment(JLabel.CENTER);
    subtitle = new JLabel();
    subtitle.setText("Payroll Calculator");
    subtitle.setHorizontalAlignment(JLabel.CENTER);



    titlePanel.add(mainTitle);
    titlePanel.add(subtitle);

    // //second panel yellow
    inputPanel = new JPanel();
    inputPanel.setBackground(new Color(235,218,127));
    inputPanel.setLayout(new GridLayout(3,2));
    displayName = new JLabel();
    displayName.setText("Employee\'s Name:");
    displayName.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(displayName);
    textboxName = new JTextField();
    textboxName.setHorizontalAlignment(JLabel.CENTER);
    inputPanel.add(textboxName);
    displayHours = new JLabel();
    displayHours.setText("Hours worked:");
    displayHours.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(displayHours);
    textboxHours = new JTextField();
    textboxHours.setHorizontalAlignment(JLabel.CENTER);
    inputPanel.add(textboxHours);
    displayRates = new JLabel();
    displayRates.setText("Hourly pay rate:");
    displayRates.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(displayRates);
    textboxRate = new JTextField();
    textboxRate.setHorizontalAlignment(JLabel.CENTER);
    inputPanel.add(textboxRate);

    // //third panel green
    outputPanel = new JPanel();
    outputPanel.setLayout(new GridLayout(4,2));
    outputPanel.setBackground(new Color(155,235,127));
    displayName2 = new JLabel("Name of employee:");
    displayName2.setHorizontalAlignment(JLabel.RIGHT);
    firstNameOnly = new JLabel("");
    firstNameOnly.setHorizontalAlignment(JLabel.CENTER);
    displayRegularPay = new JLabel("Regular pay:");
    displayRegularPay.setHorizontalAlignment(JLabel.RIGHT);
    regularPayment = new JLabel("");
    regularPayment.setHorizontalAlignment(JLabel.CENTER);
    displayOvertimePay = new JLabel("Overtime pay:");
    displayOvertimePay.setHorizontalAlignment(JLabel.RIGHT);
    overtimePayment = new JLabel("");
    overtimePayment.setHorizontalAlignment(JLabel.CENTER);
    displayGrossPay = new JLabel("Gross pay:");
    displayGrossPay.setHorizontalAlignment(JLabel.RIGHT);
    grossPayment = new JLabel("");
    grossPayment.setHorizontalAlignment(JLabel.CENTER);

    outputPanel.add(displayName2);
    outputPanel.add(firstNameOnly);
    outputPanel.add(displayRegularPay);
    outputPanel.add(regularPayment);
    outputPanel.add(displayOvertimePay);
    outputPanel.add(overtimePayment);
    outputPanel.add(displayGrossPay);
    outputPanel.add(grossPayment);


    // //fourth panel blue
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.setBackground(new Color(95,170,232));
    clearButton = new JButton("Clear");
    computeButton = new JButton("Compute");
    quitButton = new JButton("Quit");
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    buttonPanel.add(clearButton);
    buttonPanel.add(computeButton);
    buttonPanel.add(quitButton);

    buttonhandler myButtons = new buttonhandler();
    clearButton.addActionListener(myButtons);
    computeButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    add(titlePanel);
    add(inputPanel);
    add(outputPanel);
    add(buttonPanel);



  }// end of constructor
  private class buttonhandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == clearButton)
      {
        textboxHours.setText("");
        textboxName.setText("");
        textboxRate.setText("");
        firstNameOnly.setText("");
        regularPayment.setText("");
        overtimePayment.setText("");
        grossPayment.setText("");

      }
      else if (event.getSource() == computeButton)
      { //initializes everything to 0;
        Payroll money = new Payroll();
        String employeeName = textboxName.getText();
        String firstName;
        double employeeHours;
        double employeeRate;
        try
        { // if user inputs anything that is not a number then it would go to catch
          if (!textboxHours.getText().contains(".")) //checks for a double
          {
            throw new Exception("Number was an integer.");
          }
          employeeHours = (Double.valueOf(textboxHours.getText()));
        } // catch makes the value 0
        catch (Exception e)
        {
          employeeHours = 0.00;
        }
        try
        { // if user inputs anything that is not a number then it would go to catch
          if (!textboxRate.getText().contains(".")) //checks for a double
          {
            throw new Exception("Number was an integer.");
          }
          employeeRate = (Double.valueOf(textboxRate.getText()));
        }
        catch (Exception e)
        {
            employeeRate = 0.00;
        }
        if (employeeHours < 0) // if employeeHours is a negative number then it becomes 0
        {
          employeeHours = 0.00;
        }
        if (employeeRate < 0) // if employeeRate is a negative number then it becomes 0
        {
          employeeRate = 0.00;
        }
        double regPay = money.regularPay(employeeHours, employeeRate);
        double overPay = money.overtime(employeeHours, employeeRate);
        double grossPay = money.grossPay(regPay, overPay);
        if (employeeName.equals(""))
        {
          firstName = "No Name";
        } // this if statement defaults to No Name if left empty
        else if (employeeName.contains(" "))
        { // assumes if the string has a space the charaters before the space is the first name
          String[] nameParts = employeeName.split(" ");
          firstName = nameParts[0];
        }
        else
        {
          firstName = employeeName;
        }
        firstNameOnly.setText(firstName);
        regularPayment.setText(String.format("%.2f", regPay)); //rounding the decimals places
        overtimePayment.setText(String.format("%.2f", overPay));
        grossPayment.setText(String.format("%.2f", grossPay));
        firstNameOnly.repaint(); //refreshes the labels
        regularPayment.repaint();
        overtimePayment.repaint();
        grossPayment.repaint();

      } // takes only the first name if given a full name
      else if (event.getSource() == quitButton)
      {
        System.exit(0);
      } // implements the quit button
      else
      {
        System.out.println("Unknown error");
      } // if its not any button then its an Error.
    }// End of actionPerformed
  }// End of buttonhandler class
}// End of gui class
