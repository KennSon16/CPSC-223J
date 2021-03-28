//****************************************************************************************************************************
//Program name: "Ricochet Ball".  This program shows the speed of a baseball runner running to each base with the inputed  *
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
  //Program name: RicochetBall
  //Programming language: Java
  //Files: RicochetBall.java, RicochetUI.java, Algorithm.java, Animation.java, run.sh
  //Date project began: 2021-March-17.
  //Date of last update: 2021-March-28.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input a speed to show a ball ricochet from the side of the panel,
  //         shown through an animation. Two buttons are added to start and stop the ball.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: RicochetUI.java
  //Compile : javac RicochetUI.java
  //This is the top level module.  This module activates the user interface.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RicochetUI extends JFrame
{
  ////TITLE PANELS OBJECTS////
  private JPanel titlePanel;
  private JLabel titleLabel;
  ////ANIAMTION OBJECTS////
  private Animation movePanel;
  ////CONTROL PANELS OBJECTS////
  private JPanel controlPanel;
  private JPanel whereBallPanel;
  private JLabel speedLabel;
  private JLabel refreshLabel;
  private JLabel directionLabel;
  private JLabel ballLocateLabel;
  private JLabel xCoordsLabel;
  private JLabel yCoordsLabel;
  private JLabel xLabel;
  private JLabel yLabel;
  private JTextField speedInput;
  private JTextField refreshInput;
  private JTextField directionInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton clearButton;
  private JButton resumeButton;
  private JButton quitButton;
  ////RUNNER SPEED OBJECTS////
  private Clockhandlerclass clockhandler;
  private double speed;
  private double refreshRate;
  private double direction;
  private Timer refreshclock;
  private Timer motionclock;
  private Algorithm math;

  public RicochetUI() //constructor
  {//setting the dimensions and layout for the frame
    super("Program 3");
    setLocationRelativeTo(null);
    setLayout(null);
    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(47, 163, 186));
    titlePanel.setBounds(0, 0 , 1920, 100);
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    titleLabel = new JLabel();
    titleLabel.setText("<html>Ricochet Ball Animation<br/> by Kenn Son");
    titlePanel.add(titleLabel);

    //********ANIMATION PANEL********//
    //128, 235, 52 RGB color looks like grass!
    movePanel = new Animation();
    movePanel.initializeBall();

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

    quitButton = new JButton();
    quitButton.setText("Quit");
    quitButton.setBounds(1320, 20, 100, 40);

    refreshLabel = new JLabel();
    refreshLabel.setText("Refresh Rate (Hz)");
    refreshLabel.setBackground(new Color(9, 232, 91));
    refreshLabel.setOpaque(true);
    refreshLabel.setBounds(175, 80, 150, 20);

    speedLabel = new JLabel();
    speedLabel.setText("Speed (pix/sec)");
    speedLabel.setBackground(new Color(9, 232, 91));
    speedLabel.setOpaque(true);
    speedLabel.setBounds(735, 80, 150, 20);

    directionLabel = new JLabel();
    directionLabel.setText("Direction (pix/sec)");
    directionLabel.setBackground(new Color(9, 232, 91));
    directionLabel.setOpaque(true);
    directionLabel.setBounds(1320-25, 80, 150, 20);

    speedInput = new JTextField();
    speedInput.setBounds(175, 105, 150, 30);
    refreshInput = new JTextField();
    refreshInput.setBounds(735, 105, 150, 30);
    directionInput = new JTextField();
    directionInput.setBounds(1295, 105, 150, 30);

    ////// OBJECTS FOR SMALL PANEL ON THE CONROLPANEL ////////
    whereBallPanel = new JPanel();
    whereBallPanel.setBorder(BorderFactory.createBevelBorder(0));
    whereBallPanel.setLayout(null);
    whereBallPanel.setBounds(1620, 20, 280, 130);

    ballLocateLabel  = new JLabel();
    ballLocateLabel.setText("Ball Location");
    ballLocateLabel.setBounds(5, 5, 270, 20);
    xCoordsLabel = new JLabel();
    xCoordsLabel.setText("X =");
    xCoordsLabel.setBounds(10, 40, 50, 20);
    yCoordsLabel = new JLabel();
    yCoordsLabel.setText("Y =");
    yCoordsLabel.setBounds(10, 70, 50, 20);
    xLabel = new JLabel();
    xLabel.setText(String.valueOf(movePanel.getCenterX()));
    xLabel.setBounds(70, 40, 50, 20);
    yLabel = new JLabel();
    yLabel.setText(String.valueOf(movePanel.getCenterY()));
    yLabel.setBounds(70, 70, 50, 20);

    whereBallPanel.add(ballLocateLabel);
    whereBallPanel.add(xCoordsLabel);
    whereBallPanel.add(yCoordsLabel);
    whereBallPanel.add(xLabel);
    whereBallPanel.add(yLabel);
    ////// END OF OBJECTS FOR SMALL PANEL ON THE CONROLPANEL ////////

    controlPanel.add(startButton);
    controlPanel.add(pauseButton);
    controlPanel.add(quitButton);
    controlPanel.add(resumeButton);
    controlPanel.add(clearButton);
    controlPanel.add(refreshLabel);
    controlPanel.add(speedLabel);
    controlPanel.add(directionLabel);
    controlPanel.add(speedInput);
    controlPanel.add(refreshInput);
    controlPanel.add(directionInput);
    controlPanel.add(whereBallPanel);
    
    add(titlePanel);
    add(movePanel);
    add(controlPanel);

    buttonhandler myButtons = new buttonhandler();
    startButton.addActionListener(myButtons);
    resumeButton.addActionListener(myButtons);
    clearButton.addActionListener(myButtons);
    pauseButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    clockhandler = new Clockhandlerclass();

  } //end of constructor
  private class buttonhandler implements ActionListener
  {
    boolean active;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {
        try //makes sure all textFields are filled
        {
          speed = (Double.valueOf(speedInput.getText())/1000)*99.873;
          refreshRate = (Double.valueOf(refreshInput.getText()));
          direction = (Double.valueOf(directionInput.getText()));
          if (speed < 0)
          {
            throw new Exception("Speed cannot be negative!");
          }
          if (refreshRate < 0)
          {
            throw new Exception("refreshRate cannot be negative!");
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
          math = new Algorithm(direction);
          refreshclock = new Timer((int)Math.round(1000/refreshRate), clockhandler);
          motionclock = new Timer(((int)Math.round(1000/99.78)), clockhandler);
          movePanel.updateDelta(math.getDeltaX(), math.getDeltaY());
          movePanel.setSpeed(speed);
          active = true;
          refreshclock.start();
          motionclock.start();
          startButton.setVisible(false); //replaces startButton with pauseButton
          pauseButton.setVisible(true);
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
          try //makes sure all textFields are filled
          {
            speed = (Double.valueOf(speedInput.getText())/1000)*99.873;
            refreshRate = (Double.valueOf(refreshInput.getText()));
            direction = (Double.valueOf(directionInput.getText()));
            if (speed < 0)
            {
              throw new Exception("Speed cannot be negative!");
            }
            if (refreshRate < 0)
            {
              throw new Exception("Refresh Rate cannot be negative!");
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
            math = new Algorithm(direction);
            refreshclock = new Timer((int)Math.round(1000/refreshRate), clockhandler);
            motionclock = new Timer(((int)Math.round(1000/99.873)), clockhandler);

            movePanel.setSpeed(speed);
            movePanel.updateDelta(math.getDeltaX(), math.getDeltaY());
            active = true;
            refreshclock.start();
            motionclock.start();
            startButton.setVisible(false); //replaces resumeButton with pauseButton
            pauseButton.setVisible(true);
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
          refreshclock.stop();
          motionclock.stop();
          resumeButton.setVisible(true); //replaces pauseButton with startButton
          pauseButton.setVisible(false);
          active = false;
        }
        else
        {
          refreshclock.start();
          motionclock.start();
          active = true;
        }
      }
      else if(event.getSource() == clearButton)
      {
        refreshclock.stop();
        motionclock.stop();
        active = false;
        startButton.setVisible(true);
        resumeButton.setVisible(false);
        pauseButton.setVisible(false);
        movePanel.initializeBall();
        speedInput.setText("");
        refreshInput.setText("");
        directionInput.setText("");
        movePanel.repaint();
        xLabel.setText(String.valueOf(movePanel.getCenterX()));
        yLabel.setText(String.valueOf(movePanel.getCenterY()));
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
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == refreshclock)
      {
        movePanel.repaint();
      }
      else if(event.getSource() == motionclock)
      {
        movePanel.updateBall();
        xLabel.setText(String.valueOf(movePanel.getCenterX()));
        yLabel.setText(String.valueOf(movePanel.getCenterY()));
      }//End of if(event.getSource() == motionclock)
      else
         System.out.printf("%s\n","There is a bug in one of the clocks.");
     }//End of method actionPerformed
  }//End of Clockhandlerclass

} //end of RicochetUI class
