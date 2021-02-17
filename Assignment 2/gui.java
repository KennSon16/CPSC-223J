//****************************************************************************************************************************
//Program name: "Baseball Runner".  This program shows the speed of a baseball runner running to each base with the inputed  *
//              speed.                                                                                                       *
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
  //Program name: Baseball Runner
  //Programming language: Java
  //Files: main.java, gui.java, math.java, run.sh
  //Date project began: 2021-February-8.
  //Date of last update: 2021-February-8.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input a speed to show a baseball player move from base to base,
  //         shown through an animation. Two buttons are added to start and stop the baseball player.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: gui.java
  //Compile : javac gui.java
  //This is the top level module.  This module activates the user interface.

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton; // adds buttons
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;  //adds a text box

public class gui extends JFrame {

private JPanel titlePanel;
private quadPanel movePanel;
private JPanel controlPanel;
// private JLabel titleLabel;
// private JLabel speedLabel;
private JTextField speedInput;
private JButton startButton;
private JButton pauseButton;
private JButton quitButton;

  public gui() //constructor
  {//setting the dimensions and layout for the frame
    super("Program 2");
    setLocationRelativeTo(null);
    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(91, 193, 245));
    titlePanel.setBounds(0, 0 , 800, 100);

    //********ANIMATION PANEL********//
    //128, 235, 52 RGB color looks like grass!
    //**********MARCO PANEL**********//
    controlPanel = new JPanel();
    controlPanel.setBackground(new Color(250, 206, 130));
    titlePanel.setBounds(0, 500 , 800, 100);
    //250, 206, 130

    add(titlePanel);
    add(controlPanel);
  }
}
