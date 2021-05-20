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
  //File name: Animation.java
  //Compile : javac Animation.java
  //This is the top level module.  This module activates the user interface.
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Animation extends JPanel
{
  /// EARTH ///
  private final double earthRadius = 7.0;
  private final double earthDiameter = 2.0 * earthRadius;
  private double earthDeltaX;
  private double earthDeltaY;
  private double earthSpeed;
  private double earthCenterX;
  private double earthCenterY;
  private double earthUpperCornerX;
  private double earthUpperCornerY;
  private Position earthUpperCornerPos;
  private double radians;
  /// ORBIT ///
  private double tee;
  private double earthOrbitRadius; //initialized later to 200
  private double speed_to_delta;
  ///  SUN  ///
  private final double sunRadius = 14.0;
  private final double sunDiameter = 2.0 * sunRadius;
  private double sunUpperCornerX;
  private double sunUpperCornerY;
  private double sunCenterX;
  private double sunCenterY;
  private Position sunUpperCornerPos;

  public Animation()
  {
    setBounds(0, 100, 1920, 780);
    setBackground(new Color(85, 86, 89));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }
  public void paint(Graphics lines)
  {
    Graphics2D bases = (Graphics2D) lines;
    bases.setColor(new Color(85, 86, 89));
    bases.fillRect(0, 0, 1920, 880); //repaints the panel background

    //repaints Earth
    bases.setColor(new Color(78, 179, 62));
    bases.drawOval(earthUpperCornerPos.getX(), earthUpperCornerPos.getY(), (int)Math.round(earthDiameter),(int)Math.round(earthDiameter));
    bases.fillOval(earthUpperCornerPos.getX(), earthUpperCornerPos.getY(),
                        (int)Math.round(earthDiameter),(int)Math.round(earthDiameter));

    //repaints Sun
    bases.setColor(new Color(252, 235, 48));
    bases.drawOval(sunUpperCornerPos.getX(), sunUpperCornerPos.getY(), (int)Math.round(sunDiameter),(int)Math.round(sunDiameter));
    bases.fillOval(sunUpperCornerPos.getX(), sunUpperCornerPos.getY(),
                          (int)Math.round(sunDiameter),(int)Math.round(sunDiameter));
  }
  public void initializeEarth()
  {
    //sets the center of the Sun to the center of the panel
    tee = 0;
    sunCenterX = 1920/2;
    sunCenterY =  780/2;

    sunUpperCornerX = sunCenterX - sunRadius;
    sunUpperCornerY = sunCenterY - sunRadius;
    sunUpperCornerPos = new Position((int)Math.round(sunUpperCornerX), (int)Math.round(sunUpperCornerY));

    //sets the Earth to the left of the Sun
    //lengthOfLine = Math.sqrt(Math.pow((mouseCenterX - catCenterX), 2) + Math.pow((mouseCenterY - catCenterY), 2));

    earthCenterX = sunCenterX;
    earthCenterY =  sunCenterY-200;
    earthUpperCornerX = earthCenterX - earthRadius;
    earthUpperCornerY = earthCenterY - earthRadius;
    earthUpperCornerPos = new Position((int)Math.round(earthUpperCornerX), (int)Math.round(earthUpperCornerY));

    earthOrbitRadius = Math.sqrt(Math.pow((sunCenterX - earthCenterX), 2) + Math.pow((sunCenterY - earthCenterY), 2));

  }
  public void updateEarth()
  { //changes the direction of the ball and it hits the boarder of the panel
    tee = getspeed_to_delta()+tee;
    earthDeltaX = (-Math.cos(tee));
    earthDeltaY = (Math.sin(tee));

    earthCenterX += earthSpeed*earthDeltaX;
    earthCenterY += earthSpeed*earthDeltaY;
    //
    earthUpperCornerX = earthCenterX - earthRadius;
    earthUpperCornerY = earthCenterY - earthRadius;

    //earthCenterX = (earthOrbitRadius*earthDeltaX)+sunCenterX;
    //earthCenterY = (earthOrbitRadius*earthDeltaY)+sunCenterY;


    earthUpperCornerPos.setX((int)Math.round(earthUpperCornerX));
    earthUpperCornerPos.setY((int)Math.round(earthUpperCornerY));
  }
  public double getspeed_to_delta()
  {
    speed_to_delta = earthSpeed/earthOrbitRadius;
    return speed_to_delta;
  }
  public void setEarthSpeed(double earthSpeed)
  {
    this.earthSpeed = earthSpeed/98;
  }
  public double getEarthCenterX()
  {
    return earthCenterX;
  }
  public double getEarthCenterY()
  {
    return earthCenterY;
  }
}
class Position
{//simple position class to store an obejects coordinates as integers
  private int x;
  private int y;

  public Position(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  public void setX(int x)
  {
    this.x = x;
  }
  public void setY(int y)
  {
    this.y = y;
  }
}
