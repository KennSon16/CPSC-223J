//****************************************************************************************************************************
//Program name: "Cat and Mouse".  This program shows the speed of a mouse running while being chased by a cat represented    *
//               with a big and small circle                                                                                 *
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
  //Program name: CatAndMouse
  //Programming language: Java
  //Files: CatAndMouse.java, UI.java, Animation.java, run.sh
  //Date project began: 2021-Apirl-23.
  //Date of last update: 2021-Apirl-24.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input speed of a cat and mouse
  //          and see an animation of the cat chasing a mouse running at a given direction.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: UI.java
  //Compile : javac UI.java
  //This is the top level module.  This module activates the user interface.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UI extends JFrame
{
  ////TITLE PANELS OBJECTS////
  private JPanel titlePanel;
  private JLabel titleLabel;
  ////ANIAMTION OBJECTS////
  private Animation movePanel;
  ////CONTROL PANELS OBJECTS////
  private JPanel controlPanel;
  private JLabel mouseSpeedLabel;
  private JLabel catSpeedLabel;
  private JLabel directionLabel;
  private JLabel doneLabel;
  private JLabel compareLabel;
  private JLabel distanceLabel;
  private JTextField mouseInput;
  private JTextField catInput;
  private JTextField directionInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton clearButton;
  private JButton resumeButton;
  private JButton quitButton;
  ////RUNNER SPEED OBJECTS////
  private Clockhandlerclass clockhandler;
  private double mouseSpeed;
  private double catSpeed;
  private double refreshRate;
  private double direction;
  private Timer refreshClock;
  private Timer mouseClock;
  private Timer catClock;
  private int cat_clock_delay_interval;
  private int mouse_clock_delay_interval;
  public UI()
  {
    super("Program 3");
    setLocationRelativeTo(null);
    setLayout(null);
    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(47, 163, 186));
    titlePanel.setBounds(0, 0 , 1920, 100);
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    titleLabel = new JLabel();
    titleLabel.setText("<html>Cat and Mouse Animation<br/> by Kenn Son");
    titlePanel.add(titleLabel);
    //********ANIMATION PANEL********//
    //128, 235, 52 RGB color looks like grass!
    movePanel = new Animation();
    movePanel.initializeCatAndMouse();


    //**********MARCO PANEL**********//
    controlPanel = new JPanel();
    controlPanel.setBackground(new Color(250, 206, 130));
    controlPanel.setBounds(0, 880 , 1920, 200);
    controlPanel.setLayout(null);
    controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    clearButton = new JButton();
    clearButton.setText("Clear");
    clearButton.setBounds(200, 20, 100, 40);

    startButton = new JButton();
    startButton.setText("Start");
    startButton.setBounds(760, 20, 100, 40);

    resumeButton = new JButton();
    resumeButton.setText("Resume");
    resumeButton.setVisible(false);
    resumeButton.setBounds(760, 20, 100, 40);

    pauseButton = new JButton();
    pauseButton.setText("Pause");
    pauseButton.setVisible(false);
    pauseButton.setBounds(760, 20, 100, 40);

    doneLabel = new JLabel();
    doneLabel.setText("<html>Mouse has been caught!!!<br/>Press clear to reset!");
    doneLabel.setVisible(false);
    doneLabel.setBounds(740, 20, 180, 60);

    quitButton = new JButton();
    quitButton.setText("Quit");
    quitButton.setBounds(1320, 20, 100, 40);

    catSpeedLabel = new JLabel();
    catSpeedLabel.setText("Speed (Cat)");
    catSpeedLabel.setBackground(new Color(9, 232, 91));
    catSpeedLabel.setOpaque(true);
    catSpeedLabel.setBounds(175, 80, 150, 20);

    mouseSpeedLabel = new JLabel();
    mouseSpeedLabel.setText("Speed (Mouse)");
    mouseSpeedLabel.setBackground(new Color(9, 232, 91));
    mouseSpeedLabel.setOpaque(true);
    mouseSpeedLabel.setBounds(735, 80, 150, 20);

    directionLabel = new JLabel();
    directionLabel.setText("Mouse Direction");
    directionLabel.setBackground(new Color(9, 232, 91));
    directionLabel.setOpaque(true);
    directionLabel.setBounds(1295, 80, 150, 20);

    distanceLabel = new JLabel();
    distanceLabel.setText("Distance in between");
    distanceLabel.setBackground(new Color(9, 232, 91));
    distanceLabel.setOpaque(true);
    distanceLabel.setBounds(1650, 80, 200, 20);

    compareLabel = new JLabel();
    compareLabel.setText( String.valueOf(movePanel.getDistance()));
    compareLabel.setBackground(new Color(9, 232, 91));
    compareLabel.setOpaque(true);
    compareLabel.setBounds(1650, 100, 200, 20);

    catInput = new JTextField();
    catInput.setBounds(175, 105, 150, 30);

    mouseInput = new JTextField();
    mouseInput.setBounds(735, 105, 150, 30);

    directionInput = new JTextField();
    directionInput.setBounds(1295, 105, 150, 30);

    buttonhandler myButtons = new buttonhandler();
    startButton.addActionListener(myButtons);
    resumeButton.addActionListener(myButtons);
    clearButton.addActionListener(myButtons);
    pauseButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    controlPanel.add(clearButton);
    controlPanel.add(startButton);
    controlPanel.add(resumeButton);
    controlPanel.add(pauseButton);
    controlPanel.add(quitButton);
    controlPanel.add(doneLabel);
    controlPanel.add(catSpeedLabel);
    controlPanel.add(mouseSpeedLabel);
    controlPanel.add(directionLabel);
    controlPanel.add(mouseInput);
    controlPanel.add(catInput);
    controlPanel.add(directionInput);
    controlPanel.add(distanceLabel);
    controlPanel.add(compareLabel);

    add(titlePanel);
    add(movePanel);
    add(controlPanel);

    clockhandler = new Clockhandlerclass();
    refreshClock = new Timer((int)Math.round(1000/120), clockhandler);

  }
  private class buttonhandler implements ActionListener
  {
    boolean active;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {//makes sure all textFields are filled
        try
        {
          mouseSpeed = (Double.valueOf(mouseInput.getText()));
          catSpeed = (Double.valueOf(catInput.getText()));
          direction = (Double.valueOf(directionInput.getText()));

          cat_clock_delay_interval = (int)Math.round(1000/catSpeed);
          mouse_clock_delay_interval = (int)Math.round(1000/mouseSpeed);

          if (mouseSpeed < 0 || catSpeed < 0)
          {
            throw new Exception("Speed cannot be negative!");
          }
        }
        catch(Exception e)
        {
          if(e instanceof NumberFormatException) {
            System.out.println("One of the text boxes was not defined! Please input into ALL box.");
          }
          System.out.println("Error: " + e);
        }
        try
        {
          catClock = new Timer(cat_clock_delay_interval, clockhandler);
          mouseClock = new Timer(mouse_clock_delay_interval, clockhandler);
          movePanel.setMouseSpeed(mouseSpeed);
          movePanel.setCatSpeed(catSpeed);
          movePanel.updateMouseDelta(direction);
          active = true;
          refreshClock.start();
          mouseClock.start();
          catClock.start();
          startButton.setVisible(false); //replaces startButton with pauseButton
          pauseButton.setVisible(true);
          doneLabel.setVisible(false);
        }
        catch(Exception e)
        {
          System.out.println("Error: " + e);
        }
      }
      else if(event.getSource() == resumeButton)
      {
        if(!active)
        {
          try
          {
            mouseSpeed = (Double.valueOf(mouseInput.getText()));
            catSpeed = (Double.valueOf(catInput.getText()));
            direction = (Double.valueOf(directionInput.getText()));

            cat_clock_delay_interval = (int)Math.round(1000/catSpeed);
            mouse_clock_delay_interval = (int)Math.round(1000/mouseSpeed);

            if (catSpeed < 0 || mouseSpeed < 0)
            {
              throw new Exception("Speed cannot be negative!");
            }
          }
          catch(Exception e)
          {
            if(e instanceof NumberFormatException) {
              System.out.println("One of the text boxes was not defined! Please input into ALL box.");
            }
            System.out.println("Error: " + e);
          }
          try
          {
            catClock = new Timer(cat_clock_delay_interval, clockhandler);
            mouseClock = new Timer(mouse_clock_delay_interval, clockhandler);

            movePanel.setMouseSpeed(mouseSpeed);
            movePanel.setCatSpeed(catSpeed);
            movePanel.updateMouseDelta(direction);
            active = true;
            refreshClock.start();
            mouseClock.start();
            catClock.start();
            resumeButton.setVisible(false); //replaces resumeButton with pauseButton
            pauseButton.setVisible(true);
            doneLabel.setVisible(false);
          }
          catch(Exception e)
          {
            System.out.println("Error: " + e);
          }
        }
      }
      else if(event.getSource() == pauseButton)
      {
        if(active)
        {
          refreshClock.stop();
          mouseClock.stop();
          catClock.stop();
          resumeButton.setVisible(true); //replaces pauseButton with resumeButton
          pauseButton.setVisible(false);
          doneLabel.setVisible(false);
          active = false;
        }
        else
        {
          refreshClock.start();
          mouseClock.start();
          catClock.start();
          active = true;
        }
      }
      else if(event.getSource() == clearButton)
      {
        refreshClock.stop();
        mouseClock.stop();
        catClock.stop();
        active = false;
        startButton.setVisible(true);
        resumeButton.setVisible(false);
        pauseButton.setVisible(false);
        doneLabel.setVisible(false);
        movePanel.initializeCatAndMouse();
        mouseInput.setText("");
        catInput.setText("");
        directionInput.setText("");
        movePanel.repaint();
      }
      else if(event.getSource() == quitButton)
      {
        System.exit(0);
      }
      else
      {
        System.out.println("Button error");
      }
    }
  }
  private class Clockhandlerclass implements ActionListener
  {
    double distance;
    boolean caught = false;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == refreshClock)
      {
        movePanel.repaint();
      }
      else if(event.getSource() == mouseClock)
      {
        movePanel.updateMouse();
      }//End of if(event.getSource() == mouseClock)
      else if(event.getSource() == catClock)
      {
        caught = movePanel.updateCat();
        distance = movePanel.getDistance();
        compareLabel.setText( String.valueOf(distance));
        if(caught)
        {
          compareLabel.setText( String.valueOf(0));
          mouseClock.stop();
          catClock.stop();

          refreshClock.stop();
          resumeButton.setVisible(false);
          pauseButton.setVisible(false);
          doneLabel.setVisible(true);
        }
      }
      else
         System.out.printf("%s\n","There is a bug in one of the clocks.");
     }//End of method actionPerformed
  }//End of Clockhandlerclass
}
