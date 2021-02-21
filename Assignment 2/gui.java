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

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class gui extends JFrame {

private JPanel titlePanel;
private JLabel titleLabel;
private quadPanel movePanel;
private JPanel controlPanel;
private JLabel speedLabel;
private JTextField speedInput;
private JButton startButton;
private JButton pauseButton;
private JButton quitButton;

  public gui() //constructor
  {//setting the dimensions and layout for the frame
    super("Program 2");
    setLocationRelativeTo(null);
    setLayout(null);
    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(91, 193, 245));
    titlePanel.setBounds(0, 0 , 1920, 100);
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    titleLabel = new JLabel();
    titleLabel.setText("<html>Diamond Animation <br/> by Kenn Son");
    titlePanel.add(titleLabel);

    //********ANIMATION PANEL********//
    //128, 235, 52 RGB color looks like grass!
    movePanel = new quadPanel();

    //**********MARCO PANEL**********//
    controlPanel = new JPanel();
    controlPanel.setBackground(new Color(250, 206, 130));
    controlPanel.setBounds(0, 980 , 1920, 100);
    controlPanel.setLayout(null);
    controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    startButton = new JButton();
    startButton.setText("Start");
    startButton.setBackground(new Color(9, 222, 12));
    startButton.setBounds(200, 20, 80, 30);

    pauseButton = new JButton();
    pauseButton.setText("Pause");
    pauseButton.setBackground(new Color(255, 48, 48));
    pauseButton.setVisible(false);
    pauseButton.setBounds(200, 20, 80, 30);

    speedLabel = new JLabel();
    speedLabel.setText("Speed:");
    speedLabel.setBackground(new Color(9, 232, 91));
    speedLabel.setBorder(BorderFactory.createBevelBorder(0));
    speedLabel.setOpaque(true);
    speedLabel.setBounds(900, 20, 60, 30);
    controlPanel.add(speedLabel);

    speedInput = new JTextField();
    speedInput.setBounds(960, 20, 70, 30);
    controlPanel.add(speedInput);

    quitButton = new JButton();
    quitButton.setText("Quit");
    quitButton.setBackground(new Color(255, 48, 48));
    quitButton.setBounds(1720, 20, 80, 30);

    controlPanel.add(startButton);
    controlPanel.add(pauseButton);
    controlPanel.add(quitButton);


    buttonhandler myButtons = new buttonhandler();
    startButton.addActionListener(myButtons);
    pauseButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    //250, 206, 130

    add(titlePanel);
    add(movePanel);
    add(controlPanel);
  }// end of constructor
  private class buttonhandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {
        //start();
        startButton.setVisible(false); //replaces startButton with pauseButton
        pauseButton.setVisible(true);
      }
      else if (event.getSource() == pauseButton)
      {
        startButton.setVisible(true); //replaces pauseButton with startButton
        pauseButton.setVisible(false);
      }
      else if(event.getSource() == quitButton)
      {
        System.exit(0);
      }// implements the quit button
      else
      {
        System.out.println("Button error");
      }
    }
  }//end of button class
}//end of class
