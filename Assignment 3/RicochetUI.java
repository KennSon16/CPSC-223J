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

public class BaseballUI extends JFrame
{
  ////TITLE PANELS OBJECTS////
  private JPanel titlePanel;
  private JLabel titleLabel;
  ////ANIAMTION OBJECTS////
  private Quad movePanel;
  ////CONTROL PANELS OBJECTS////
  private JPanel controlPanel;
  private JLabel speedLabel;
  private JLabel refreshLabel;
  private JLabel directionLabel;
  private JLabel ballLocateLabel;
  private JLabel xCoordsLabel;
  private JLabel yCoordsLabel;
  private JLabel xLabel;
  private JLabel yLabel;
  private JTextField speedInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton clearButton;
  private JButton resumeButton;
  private JButton quitButton;
  ////RUNNER SPEED OBJECTS////
  private Clockhandlerclass clockhandler;
  private double speed;
  private Timer refreshclock;
  private Timer motionclock;
  private Computations toBase1;

  public BaseballUI() //constructor
  {//setting the dimensions and layout for the frame
    super("Program 3");
    setLocationRelativeTo(null);
    setLayout(null);
    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(47, 163, 186);
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
    clearButton.setBounds(200, 20, 80, 30);

    startButton = new JButton();
    startButton.setText("Start");
    startButton.setBounds(200, 20, 80, 30);

    resumeButton = new JButton();
    resumeButton.setText("Resume");
    resumeButton.setVisible(false);
    resumeButton.setBounds(200, 20, 80, 30);

    pauseButton = new JButton();
    pauseButton.setText("Pause");
    pauseButton.setVisible(false);
    pauseButton.setBounds(200, 20, 80, 30);

    quitButton = new JButton();
    quitButton.setText("Quit");
    quitButton.setBounds(1720, 20, 80, 30);

    refreshLabel = new JLabel();
    refreshLabel.setText("Refresh Rate (Hz)");
    refreshLabel.setBackground(new Color(9, 232, 91));

    refreshLabel.setOpaque(true);
    refreshLabel.setBounds(900, 20, 60, 30);

    speedLabel = new JLabel();
    speedLabel.setText("Speed (pix/sec)");
    speedLabel.setBackground(new Color(9, 232, 91));
    speedLabel.setOpaque(true);
    speedLabel.setBounds(900, 20, 60, 30);

    directionLabel = new JLabel();
    directionLabel.setText("Direction (pix/sec)");
    directionLabel.setBackground(new Color(9, 232, 91));
    directionLabel.setOpaque(true);
    directionLabel.setBounds(900, 20, 60, 30);

    whereBallPanel = new JPanel();
    whereBallPanel.setBorder(BorderFactory.createBevelBorder(0));

    whereBallPanel.add(ballLocateLabel);
    whereBallPanel.add(xCoordsLabel);
    whereBallPanel.add(yCoordsLabel);
    //
    // speedInput = new JTextField();
    // speedInput.setBounds(960, 20, 70, 30);
    // controlPanel.add(speedInput);
    //
    //
    controlPanel.add(startButton);
    controlPanel.add(pauseButton);
    controlPanel.add(quitButton);
    controlPanel.add(resumeButton);
    controlPanel.add(clearButton);
    controlPanel.add(refreshLabel);
    controlPanel.add(speedLabel);
    controlPanel.add(directionLabel);
    //
    buttonhandler myButtons = new buttonhandler();
    startButton.addActionListener(myButtons);
    pauseButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    clockhandler = new Clockhandlerclass();

    add(titlePanel);
    add(movePanel);
    add(controlPanel);
    //
    // toBase1 = new Computations(movePanel.orderOfBases[0].getX(), movePanel.orderOfBases[1].getX(),
    //                             movePanel.orderOfBases[0].getY(), movePanel.orderOfBases[1].getY());
    // movePanel.updateDelta(toBase1.getDeltaX(), toBase1.getDeltaY());

  }// end of constructor
  private class buttonhandler implements ActionListener
  {
    boolean active;
    Computations toBase1;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {
        try
        {
          speed = (Double.valueOf(speedInput.getText()));
          if (speed < 0)
          {
            throw new Exception("Speed cannot be negative!");
          }
          refreshclock = new Timer((int)Math.round(1000/60.47), clockhandler);
          motionclock = new Timer(((int)Math.round(1000/speed)), clockhandler);
          movePanel.setSpeed(speed);
          active = true;
          refreshclock.start();
          motionclock.start();
          startButton.setVisible(false); //replaces startButton with pauseButton
          pauseButton.setVisible(true);
        }
        catch(Exception e)
        {
          if(e instanceof NumberFormatException) {
            System.out.println("Speed was not defined! Please input the speed.");
          }
          System.out.println("Error: " + e);
        }
      }
      else if(event.getSource() == resumeButton)
      {
        if(!active)
        {
          refreshclock.start();
          motionclock.start();
          resumeButton.setVisible(false); //replaces pauseButton with startButton
          pauseButton.setVisible(true);
          active = true;
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

  private class Clockhandlerclass implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      boolean animation_continues = false;
      if(event.getSource() == refreshclock)
      {
        movePanel.repaint();
      }
      else if(event.getSource() == motionclock)
      {
        animation_continues = movePanel.updateRunner();
      }//End of if(event.getSource() == motionclock)
      else
         System.out.printf("%s\n","There is a bug in one of the clocks.");
     }//End of method actionPerformed
  }//End of Clockhandlerclass
}//end of class
