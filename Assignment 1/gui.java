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
  //File name: gui.java
  //Compile : javac gui.java
  //Purpose: This class defines the user interface.
  //This module (class) is called from the main class.

//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame; //frame of the window
import javax.swing.JButton; // adds buttons
import javax.swing.JTextField;  //adds a text box for
import javax.swing.JLabel; // adds labels on the panels
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
//import javax.swing.Timer;
//import java.awt.Insets;
import javax.swing.BorderFactory;
//import java.awt.BorderLayout;

public class gui extends JFrame
{
  //private final int framewidth = 300;
  //private final int frameheight = 500;
  private int buttonWidth = 75;
  private int buttonHeight = 45;
  private Dimension buttonDimension;

  private JLabel title1; // Business name
  private JLabel title2; // subtitle
  private JLabel name1; // "Employee name:"
  private JTextField textName1; // name
  private JLabel hours1; // "Hours worked:"
  private JTextField textHour1; //hours
  private JLabel rates1; // "Hourly pay rate:"
  private JTextField textRate1; // rate
  private JLabel name2; // "Name of Employee"
  private JLabel name3; // put first name
  private JLabel rpay1; // "Regular pay"
  private JLabel rpay2; // regularPay()
  private JLabel opay1; // "Overtime pay"
  private JLabel opay2; // overtimePay()
  private JLabel gpay1; // "Gross pay"
  private JLabel gpay2; // grossPay()
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
    //setSize(framewidth,frameheight);
    // setLocationRelativeTo(null); // opens the window on the middle of the screen

    //CREATING PANELS
    //first panel red
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(245,88,64));
    titlePanel.setLayout(new GridLayout(2,1));

    title1 = new JLabel();
    title1.setText("Kenn\'s Designs Company");
    title1.setHorizontalAlignment(JLabel.CENTER);
    title2 = new JLabel();
    title2.setText("Payroll Calculator");
    title2.setHorizontalAlignment(JLabel.CENTER);



    titlePanel.add(title1);
    titlePanel.add(title2);

    // //second panel yellow
    inputPanel = new JPanel();
    inputPanel.setBackground(new Color(235,218,127));
    inputPanel.setLayout(new GridLayout(3,2));
    name1 = new JLabel();
    name1.setText("Employee\'s Name:");
    name1.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(name1);
    textName1 = new JTextField();
    inputPanel.add(textName1);
    hours1 = new JLabel();
    hours1.setText("Hours worked:");
    hours1.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(hours1);
    textHour1 = new JTextField();
    inputPanel.add(textHour1);
    rates1 = new JLabel();
    rates1.setText("Hourly pay rate:");
    rates1.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(rates1);
    textRate1 = new JTextField();
    inputPanel.add(textRate1);

    // //third panel green
    outputPanel = new JPanel();
    outputPanel.setLayout(new GridLayout(4,2));
    outputPanel.setBackground(new Color(155,235,127));
    name2 = new JLabel("Name of employee:");
    name2.setHorizontalAlignment(JLabel.RIGHT);
    name3 = new JLabel("");
    name3.setHorizontalAlignment(JLabel.CENTER);
    rpay1 = new JLabel("Regular pay:");
    rpay1.setHorizontalAlignment(JLabel.RIGHT);
    rpay2 = new JLabel("");
    rpay2.setHorizontalAlignment(JLabel.CENTER);
    opay1 = new JLabel("Overtime pay:");
    opay1.setHorizontalAlignment(JLabel.RIGHT);
    opay2 = new JLabel("");
    opay2.setHorizontalAlignment(JLabel.CENTER);
    gpay1 = new JLabel("Gross pay:");
    gpay1.setHorizontalAlignment(JLabel.RIGHT);
    gpay2 = new JLabel("");
    gpay2.setHorizontalAlignment(JLabel.CENTER);

    outputPanel.add(name2);
    outputPanel.add(name3);
    outputPanel.add(rpay1);
    outputPanel.add(rpay2);
    outputPanel.add(opay1);
    outputPanel.add(opay2);
    outputPanel.add(gpay1);
    outputPanel.add(gpay2);


    // //fourth panel blue
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.setBackground(new Color(95,170,232));
    clearButton = new JButton("Clear");
    computeButton = new JButton("Compute");
    quitButton = new JButton("Quit");
    buttonDimension = new Dimension(buttonWidth, buttonHeight);
    clearButton.setSize(buttonDimension);
    computeButton.setSize(buttonDimension);
    quitButton.setSize(buttonDimension);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    buttonPanel.add(clearButton);
    buttonPanel.add(computeButton);
    buttonPanel.add(quitButton);

    buttonhandler myhandler = new buttonhandler();
    clearButton.addActionListener(myhandler);
    computeButton.addActionListener(myhandler);
    quitButton.addActionListener(myhandler);

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
        textHour1.setText("");
        textName1.setText("");
        textRate1.setText("");
        name3.setText("");
        rpay2.setText("");
        opay2.setText("");
        gpay2.setText("");

      }
      else if (event.getSource() == computeButton)
      { //initializes everything to 0;
        Payroll money = new Payroll();
        String employeeName = textName1.getText();
        String firstName;
        double employeeHours;
        double employeeRate;
        try
        {
          employeeHours = (Double.valueOf(textHour1.getText()));
        }
        catch (Exception e) {
          employeeHours = 0.00;
        }
        try
        {
          employeeRate = (Double.valueOf(textRate1.getText()));
        }
        catch (Exception e) {
            employeeRate = 0.00;
        }
        double regPay = money.regularPay(employeeHours, employeeRate);
        double overPay = money.overtime(employeeHours, employeeRate);
        double grossPay = money.grossPay(regPay, overPay);
        if (employeeName.equals(""))
        {
          firstName = "No Name";
        } // this if statement defaults to No Name if left empty
        else if (employeeName.contains(" ")) {
          String[] nameParts = employeeName.split(" ");
          firstName = nameParts[0];
        }
        else
        {
          firstName = employeeName;
        }
        name3.setText(firstName);
        rpay2.setText(String.format("%.2f", regPay));
        opay2.setText(String.format("%.2f", overPay));
        gpay2.setText(String.format("%.2f", grossPay));
        name3.repaint();
        rpay2.repaint();
        opay2.repaint();
        gpay2.repaint();

      } // takes only the first name if given a full name
      else if (event.getSource() == quitButton){
        System.exit(0);
      } // implements the quit button
      else
      {
        System.out.println("Unknown error");
      } // if its not any button then its an Error.
    }// End of actionPerformed
  }// End of buttonhandler class
}// End of gui class
