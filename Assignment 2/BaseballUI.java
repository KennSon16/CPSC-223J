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
  //Files: Diamond.java, BaseballUI.java, Computations.java, Quad.java run.sh
  //Date project began: 2021-February-8.
  //Date of last update: 2021-February-8.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input a speed to show a baseball player move from base to base,
  //         shown through an animation. Two buttons are added to start and stop the baseball player.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: BaseballUI.java
  //Compile : javac BaseballUI.java
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
  private JTextField speedInput;
  private JButton startButton;
  private JButton pauseButton;
  private JButton quitButton;
  ////RUNNER SPEED THINGS ////
  private Clockhandlerclass clockhandler;
  private double speed;
  private Timer refreshclock;
  private Timer motionclock;
  private Computations toBase1;

  public BaseballUI() //constructor
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
    movePanel = new Quad();

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

    clockhandler = new Clockhandlerclass();
    //250, 206, 130

    add(titlePanel);
    add(movePanel);
    add(controlPanel);

    movePanel.initializeRunner();

    toBase1 = new Computations(movePanel.orderOfBases[0].getX(), movePanel.orderOfBases[1].getX(),
                                movePanel.orderOfBases[0].getY(), movePanel.orderOfBases[1].getY());
    movePanel.updateDelta(toBase1.getDeltaX(), toBase1.getDeltaY());

  }// end of constructor
  private class buttonhandler implements ActionListener
  {
    boolean active;
    Computations toBase1;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {
        speed = (Double.valueOf(speedInput.getText()));
          refreshclock = new Timer((int)Math.round(1000/120.47), clockhandler);
          motionclock = new Timer(((int)Math.round(1000/speed)), clockhandler);
        movePanel.setSpeed(speed);
        active = true;
        refreshclock.start();
        motionclock.start();
        startButton.setVisible(false); //replaces startButton with pauseButton
        pauseButton.setVisible(true);
      }
      else if(event.getSource() == pauseButton)
      {
        if(active)
        {
          refreshclock.stop();
          motionclock.stop();
          startButton.setVisible(true); //replaces pauseButton with startButton
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
    //boolean base1 = false;
    boolean base2 = false;
    boolean base3 = false;
    boolean home  = false;
    //Computations toBase1;
    Computations toBase2;
    Computations toBase3;
    Computations toHomeBase;
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
        if(!animation_continues && !movePanel.isFullLoop())
        {
          if(!base2)
          {
            //refreshclock.stop();
            //motionclock.stop();
            toBase2 = new Computations(movePanel.orderOfBases[1].getX(), movePanel.orderOfBases[2].getX(),
                                        movePanel.orderOfBases[1].getY(), movePanel.orderOfBases[2].getY());
            movePanel.smartCounter();
            movePanel.updateDelta(toBase2.getDeltaX(), toBase2.getDeltaY());
            //refreshclock.start();
            //motionclock.start();
            base2 = true;
          }
          else if(!base3)
          {
            //refreshclock.stop();
            //motionclock.stop();
            toBase3 = new Computations(movePanel.orderOfBases[2].getX(), movePanel.orderOfBases[3].getX(),
                                        movePanel.orderOfBases[2].getY(), movePanel.orderOfBases[3].getY());
            movePanel.smartCounter();
            movePanel.updateDelta(toBase3.getDeltaX(), toBase3.getDeltaY());
            //refreshclock.start();
            //motionclock.start();
            base3 = true;
          }
          else if(!home)
          {
            //refreshclock.stop();
            //motionclock.stop();
            toHomeBase = new Computations(movePanel.orderOfBases[3].getX(), movePanel.orderOfBases[0].getX(),
                                          movePanel.orderOfBases[3].getY(), movePanel.orderOfBases[0].getY());
            movePanel.smartCounter();
            movePanel.updateDelta(toHomeBase.getDeltaX(), toHomeBase.getDeltaY());
            //refreshclock.start();
            //motionclock.start();
            movePanel.toggleFullLoop();
            home = true;
          }
        }
        //coordinates_of_center_of_ball.setText("(" + special_edition.format(u) + " , " +  special_edition.format(v) + ")");
        else if(!animation_continues && home)
        {
          motionclock.stop();
          refreshclock.stop();
          movePanel.smartCounter();
          movePanel.toggleFullLoop();
          base2 = false;
          base3 = false;
          home  = false;
          movePanel.initializeRunner();
          movePanel.repaint();
          movePanel.updateDelta(toBase1.getDeltaX(), toBase1.getDeltaY());
          startButton.setVisible(true); //replaces pauseButton with startButton
          pauseButton.setVisible(false);
        }
        // else
        // {
        //   System.out.println("Error with bases");
        // }
      }//End of if(event.getSource() == motionclock)
      else
         System.out.printf("%s\n","There is a bug in one of the clocks.");
     }//End of method actionPerformed
  }//End of Clockhandlerclass
}//end of class
