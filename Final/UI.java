//****************************************************************************************************************************
//Program name: "Earth".  This program shows the speed of Earth revolving around the sun.                                    *
//                                                                                                                           *
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
  //Program name: Earth
  //Programming language: Java
  //Files: Earth.java, UI.java, Animation.java, run.sh
  //Date project began: 2021-May-19.
  //Date of last update: 2021-May-19.
  //Status: Completed.
  //Purpose: This program shows a simple user-interface that allows the user to input speed of Earth
  //          and see an animation of the earth revolving around the Sun.
  //Nice feature: If no values are entered into the direction input box then zero is assumed to be the input.
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
  private JLabel earthSpeedLabel;
  private JTextField earthInput;
  private double earthSpeed;
  private JLabel xLabel;
  private JLabel yLabel;
  private JLabel xCoords;
  private JLabel yCoords;
  private JButton startButton;
  private JButton pauseButton;
  private JButton quitButton;
  ////CLOCK SPEED OBJECTS////
  private double refreshRate;
  private Timer refreshClock;
  private Timer earthClock;
  private int earth_clock_delay_interval;
  private final double earth_speed_pix_per_second = 88.435;
  private final double motion_clock_rate = 99.873;
  private final double millisecondpersecond = 1000.0;
  private Clockhandlerclass clockhandler;

  public UI()
  {
    super("Final Program");
    setLocationRelativeTo(null);
    setLayout(null);

    //**********TITLE PANEL**********//
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(47, 163, 186));
    titlePanel.setBounds(0, 0 , 1920, 100);
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    titleLabel = new JLabel();
    titleLabel.setText("<html>Earth Animation<br/> by Kenn Son");
    titlePanel.add(titleLabel);
    //********ANIMATION PANEL********//
    movePanel = new Animation();
    movePanel.initializeEarth();
    //**********CONTROL PANEL**********//
    controlPanel = new JPanel();
    controlPanel.setBackground(new Color(250, 206, 130));
    controlPanel.setBounds(0, 880 , 1920, 200);
    controlPanel.setLayout(null);
    controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    earthSpeedLabel = new JLabel();
    earthSpeedLabel.setText("Speed (pix/sec):");
    earthSpeedLabel.setBounds(160, 20, 200, 40);
    earthSpeedLabel.setBackground(new Color(64, 209, 36));
    earthSpeedLabel.setOpaque(true);

    earthInput = new JTextField();
    earthInput.setText("100");
    earthInput.setBounds(160, 70, 200, 40);

    xLabel = new JLabel();
    xLabel.setText("X:");
    xLabel.setBounds(900, 20, 20, 30);
    xLabel.setBackground(new Color(59, 185, 235));
    xLabel.setOpaque(true);

    yLabel = new JLabel();
    yLabel.setText("Y:");
    yLabel.setBounds(900, 50, 20, 30);
    yLabel.setBackground(new Color(235, 80, 56));
    yLabel.setOpaque(true);

    xCoords = new JLabel();
    xCoords.setText( String.valueOf( movePanel.getEarthCenterX() ) );
    xCoords.setBounds(920, 20, 200, 30);
    xCoords.setBackground(new Color(59, 185, 235));
    xCoords.setOpaque(true);

    yCoords = new JLabel();
    yCoords.setText( String.valueOf( movePanel.getEarthCenterY() ) );
    yCoords.setBounds(920, 50, 200, 30);
    yCoords.setBackground(new Color(235, 80, 56));
    yCoords.setOpaque(true);

    startButton = new JButton();
    startButton.setText("Start");
    startButton.setBounds(460, 20, 100, 40);

    pauseButton = new JButton();
    pauseButton.setText("Pause");
    pauseButton.setBounds(1400, 20, 100, 40);

    quitButton = new JButton();
    quitButton.setText("Quit");
    quitButton.setBounds(1620, 20, 100, 40);

    controlPanel.add(earthSpeedLabel);
    controlPanel.add(earthInput);
    controlPanel.add(xLabel);
    controlPanel.add(yLabel);
    controlPanel.add(xCoords);
    controlPanel.add(yCoords);

    controlPanel.add(startButton);
    controlPanel.add(pauseButton);
    controlPanel.add(quitButton);

    buttonhandler myButtons = new buttonhandler();
    startButton.addActionListener(myButtons);
    pauseButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    add(titlePanel);
    add(movePanel);
    add(controlPanel);

    clockhandler = new Clockhandlerclass();
    refreshClock = new Timer((int)Math.round(1000/120), clockhandler);
  }
  private class buttonhandler implements ActionListener
  {
    boolean active = false;
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == startButton)
      {
        try
        {
          earthSpeed = (Double.valueOf( earthInput.getText() ) );
          earth_clock_delay_interval = (int)Math.round(1000/motion_clock_rate);
        }
        catch(Exception e)
        {
          if(e instanceof NumberFormatException) {
            System.out.println("Speed of the textbox was not defined! Please input into textbox.");
          }
          System.out.println("Error: " + e);
        }
        try
        {
          if(!active)
          {
            earthClock = new Timer(earth_clock_delay_interval, clockhandler);
            movePanel.setEarthSpeed(earthSpeed);
            active = true;
            refreshClock.start();
            earthClock.start();
          }
        }
        catch(Exception e)
        {
          System.out.println("Error: " + e);
        }
      }
      else if(event.getSource() == pauseButton)
      {
        if(active)
        {
          refreshClock.stop();
          earthClock.stop();
          active = false;
        }
        else
        {
          refreshClock.start();
          earthClock.start();
          active = true;
        }
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
      if(event.getSource() == refreshClock)
      {
        movePanel.repaint();
      }//End of if(event.getSource() == refreshClock)
      else if(event.getSource() == earthClock)
      {
        movePanel.updateEarth();
        xCoords.setText( String.valueOf( movePanel.getEarthCenterX() ) );
        yCoords.setText( String.valueOf( movePanel.getEarthCenterY() ) );
      }//End of if(event.getSource() == earthClock)
      else
      {
        System.out.printf("%s\n","There is a bug in one of the clocks.");
      }
     }//End of method actionPerformed
  }//End of Clockhandlerclass
}
