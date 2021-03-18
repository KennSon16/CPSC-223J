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
  //Files: Diamond.java, BaseballUI.java, Computations.java, Quad.java, run.sh
  //Date project began: 2021-February-8.
  //Date of last update: 2021-February-8.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input a speed to show a baseball player move from base to base,
  //         shown through an animation. Two buttons are added to start and stop the baseball player.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: Quad.java
  //Compile : javac Quad.java
  //This is the top level module.  This module activates the user interface.


import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Quad extends JPanel
{
  /// BASES ///
  private Position northBase;
  private Position westBase;
  private Position eastBase;
  private Position southBase;
  public  Position[] orderOfBases;
  private int i = 0;
  /// BALL/RUNNER ///
  private final double ballRadius = 7.0;
  private final double ballDiameter = 2.0 * ballRadius;
  private double deltaX;
  private double deltaY;
  private double ballCenterX;
  private double ballCenterY;
  private double ballUpperCornerX;
  private double ballUpperCornerY;
  private double distanceBetween;
  private double distanceMovedInOneTick;
  private Position ballUpperCornerPos;
  private boolean showField = false;
  private boolean successfulMove = true;
  private boolean fullLoop = false;
  private double speed;


  public Quad()
  {
    northBase = new Position(1000, 30);
    westBase = new Position(200, 400);
    eastBase = new Position(1820, 530);
    southBase = new Position(800, 850);
    orderOfBases = new Position[4];
    //put the bases in a specific order that the ball will travel
    orderOfBases[0] = southBase;
    orderOfBases[1] = eastBase;
    orderOfBases[2] = northBase;
    orderOfBases[3] = westBase;
    setBounds(0, 100, 1920, 880);
    setBackground(new Color(1, 145, 56));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    //(x,y) N(960, 30) E(1820, 440) S(960, 850) W(100, 440) this are coordinates for a perfect diamond

  }
  public void paint(Graphics lines)
  {
    Graphics2D bases = (Graphics2D) lines;
    bases.setColor(new Color(1, 145, 56));
    bases.fillRect(0, 0, 1920, 880); //repaints the panel background

    bases.setPaint(new Color(230, 230, 230));
    bases.setStroke(new BasicStroke(6));
    bases.drawLine(southBase.getX(), southBase.getY(), eastBase.getX(), eastBase.getY()); //south to east
    bases.drawLine(eastBase.getX(), eastBase.getY(), northBase.getX(), northBase.getY()); //east to north
    bases.drawLine(northBase.getX(), northBase.getY(), westBase.getX(), westBase.getY()); //north to west
    bases.drawLine(westBase.getX(), westBase.getY(), southBase.getX(), southBase.getY()); //west to south
    //repaints ball or runner
    bases.setColor(new Color(255, 43, 110));
    bases.drawOval(ballUpperCornerPos.getX(), ballUpperCornerPos.getY(), (int)Math.round(ballDiameter),(int)Math.round(ballDiameter));
    bases.fillOval(ballUpperCornerPos.getX(), ballUpperCornerPos.getY(),
                        (int)Math.round(ballDiameter),(int)Math.round(ballDiameter));
  }
  public void initializeRunner()
  {
    showField = true;
    fullLoop = false;
    //sets the center of the ball to the south base
    ballCenterX = southBase.getX();
    ballCenterY = southBase.getY();
    i = 0; //reinitializes the array counter;

    ballUpperCornerX = ballCenterX - ballRadius;
    ballUpperCornerY = ballCenterY - ballRadius;
    ballUpperCornerPos = new Position((int)Math.round(ballUpperCornerX), (int)Math.round(ballUpperCornerY));

    distanceBetween
      = Math.sqrt(Math.pow(ballCenterX - orderOfBases[getNextIndex()].getX(),2) + Math.pow(ballCenterY - orderOfBases[getNextIndex()].getY(), 2));
  }

  public boolean updateRunner()
  {
    successfulMove = true;
    if(distanceBetween > speed*distanceMovedInOneTick)
    {//This is the case where the destination is further away than a single step can accomplish.
      ballCenterX += speed*deltaX;
      ballCenterY += speed*deltaY;
      // System.out.println("x:" + ballCenterX + ", y:" + ballCenterY); //Debug
    }
    else
    {//This is the case where the ball needs exactly one short hop to reach its destination.
      ballCenterX = orderOfBases[getNextIndex()].getX();
      ballCenterY = orderOfBases[getNextIndex()].getY();
      //System.out.println("x:" + ballCenterX + ", y:" + ballCenterY); //Debug
      // System.out.println("yeet"); //Debug
      successfulMove = false;
    }
    ballUpperCornerX = ballCenterX - ballRadius;
    ballUpperCornerY = ballCenterY - ballRadius;
    ballUpperCornerPos.setX((int)Math.round(ballUpperCornerX));
    ballUpperCornerPos.setY((int)Math.round(ballUpperCornerY));
    distanceBetween
        = Math.sqrt(Math.pow(ballCenterX - orderOfBases[getNextIndex()].getX(),2) + Math.pow(ballCenterY - orderOfBases[getNextIndex()].getY(), 2));

    //System.out.println(distanceBetween); //Debug
    return successfulMove;
  }//End of updateRunner

  public void smartCounter()
  {
    this.i = (this.i + 1)%4;
  }
  public int getNextIndex()
  {
    int num = this.i + 1;
    if (num > 3)
    {
      return 0;
    }
    return num;
  }
  public void updateDelta(double deltaX, double deltaY)
  {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
    distanceMovedInOneTick = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    distanceBetween
        = Math.sqrt(Math.pow(ballCenterX - orderOfBases[getNextIndex()].getX(),2) +
          Math.pow(ballCenterY - orderOfBases[getNextIndex()].getY(), 2));
  }
  public boolean isFullLoop()
  {
    boolean temp = fullLoop;
    return temp;
  }
  public void toggleFullLoop()
  {
    if(fullLoop)
    {
      fullLoop = false;
    }
    else
    {
      fullLoop = true;
    }
  }
  public void setSpeed(double speed)
  {
    this.speed = speed;
  }

}//End of class Quad
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
