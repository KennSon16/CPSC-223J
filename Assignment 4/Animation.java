//****************************************************************************************************************************
//Program name: "Cat and Mouse".  This program shows the mouseSpeed of a mouse running while being chased by a cat represented    *
//               with a big and small circle                                                                                 *
//              mouseSpeed.                                                                                                       *
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
  //Date project began: 2021-March-17.
  //Date of last update: 2021-March-28.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input speed of a cat and mouse
  //          and see an animation of the cat chasing a mouse running at a given direction.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
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

  /// MOUSE ///
  private final double mouseRadius = 7.0;
  private final double mouseDiameter = 2.0 * mouseRadius;
  private double radians;
  private double mouseDeltaX;
  private double mouseDeltaY;
  private double mouseCenterX;
  private double mouseCenterY;
  private double mouseUpperCornerX;
  private double mouseUpperCornerY;
  private double mouseSpeed;
  private Position mouseUpperCornerPos;

  /// CAT ///
  private final double catRadius = 10.0;
  private final double catDiameter = 2.0 * catRadius;
  private double catDeltaX;
  private double catDeltaY;
  private double catCenterX;
  private double catCenterY;
  private double catUpperCornerX;
  private double catUpperCornerY;
  private double catSpeed;
  private Position catUpperCornerPos;
  private double lengthOfLine;
  private double catMovePerTick;
  private boolean catCaughtMouse;

  public Animation()
  {
    setBounds(0, 100, 1920, 780);
    setBackground(new Color(1, 145, 56));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }
  public void paint(Graphics lines)
  {
    Graphics2D bases = (Graphics2D) lines;
    bases.setColor(new Color(1, 145, 56));
    bases.fillRect(0, 0, 1920, 880); //repaints the panel background

    //repaints mouse
    bases.setColor(new Color(255, 43, 110));
    bases.drawOval(mouseUpperCornerPos.getX(), mouseUpperCornerPos.getY(), (int)Math.round(mouseDiameter),(int)Math.round(mouseDiameter));
    bases.fillOval(mouseUpperCornerPos.getX(), mouseUpperCornerPos.getY(),
                        (int)Math.round(mouseDiameter),(int)Math.round(mouseDiameter));

    //repaints cat
    bases.setColor(new Color(77, 76, 75));
    bases.drawOval(catUpperCornerPos.getX(), catUpperCornerPos.getY(), (int)Math.round(catDiameter),(int)Math.round(catDiameter));
    bases.fillOval(catUpperCornerPos.getX(), catUpperCornerPos.getY(),
                          (int)Math.round(catDiameter),(int)Math.round(catDiameter));
  }

  public void initializeCatAndMouse()
  {
    //sets the center of the mouse to the center of the panel
    mouseCenterX = 1920/2;
    mouseCenterY =  780/2;

    mouseUpperCornerX = mouseCenterX - mouseRadius;
    mouseUpperCornerY = mouseCenterY - mouseRadius;
    mouseUpperCornerPos = new Position((int)Math.round(mouseUpperCornerX), (int)Math.round(mouseUpperCornerY));

    //sets the cat to the top left corner of the PANEL
    catCenterX = 10;
    catCenterY =  10;
    catCaughtMouse = false;
    catUpperCornerX = catCenterX - catRadius;
    catUpperCornerY = catCenterY - catRadius;
    catUpperCornerPos = new Position((int)Math.round(catUpperCornerX), (int)Math.round(catUpperCornerY));
  }

  public void updateMouse()
  { //changes the direction of the ball and it hits the boarder of the panel
    mouseCenterX += mouseSpeed*mouseDeltaX;
    mouseCenterY += mouseSpeed*mouseDeltaY;
    if (mouseCenterX <= 0 + mouseRadius || mouseCenterX >= 1920 - mouseRadius)
    {
      mouseDeltaX = mouseDeltaX * -1.0;
    }
    if (mouseCenterY <= 0 + mouseRadius || mouseCenterY >= 780 - mouseRadius)
    {
      mouseDeltaY = mouseDeltaY * -1.0;
    }
    mouseUpperCornerX = mouseCenterX - mouseRadius;
    mouseUpperCornerY = mouseCenterY - mouseRadius;
    mouseUpperCornerPos.setX((int)Math.round(mouseUpperCornerX));
    mouseUpperCornerPos.setY((int)Math.round(mouseUpperCornerY));
  }

  public boolean updateCat()
  {
    lengthOfLine = Math.sqrt(Math.pow((mouseCenterX - catCenterX), 2) + Math.pow((mouseCenterY - catCenterY), 2));
    catDeltaX = (mouseCenterX - catCenterX) / lengthOfLine;
    catDeltaY = (mouseCenterY - catCenterY) / lengthOfLine;
    catMovePerTick = Math.sqrt(catDeltaX*catDeltaX + catDeltaY*catDeltaY);
    if(lengthOfLine > catSpeed * catMovePerTick)
    {
      catCenterY += catSpeed * catDeltaY;
      catCenterX += catSpeed * catDeltaX;
    }
    else
    {
      catCenterY = mouseCenterX;
      catCenterX = mouseCenterY;
      catCaughtMouse = true;
    }

    catUpperCornerX = catCenterX - catRadius;
    catUpperCornerY = catCenterY - catRadius;
    catUpperCornerPos.setX((int)Math.round(catUpperCornerX));
    catUpperCornerPos.setY((int)Math.round(catUpperCornerY));

    return catCaughtMouse;
  }

  public void updateMouseDelta(double direction)
  {
    radians = Math.toRadians(-direction);
    this.mouseDeltaX = Math.cos(radians);
    this.mouseDeltaY = Math.sin(radians);
  }

  public void setMouseSpeed(double mouseSpeed)
  {
    this.mouseSpeed = mouseSpeed;
  }

  public double getMouseCenterX()
  {
    return mouseCenterX;
  }
  public double getMouseCenterY()
  {
    return mouseCenterY;
  }

  public void setCatSpeed(double catSpeed)
  {
    this.catSpeed = catSpeed;
  }

  public double getCatCenterX()
  {
    return catCenterX;
  }
  public double getCatCenterY()
  {
    return catCenterY;
  }
  public double getDistance()
  {
    return lengthOfLine;
  }

}//End of class Animation

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
