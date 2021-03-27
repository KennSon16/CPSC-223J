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
  //File name: Animation.java
  //Compile : javac Animation.java
  //This is the top level module.  This module activates the user interface.


import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Animation extends JPanel
{

  /// BALL ///
  private final double ballRadius = 7.0;
  private final double ballDiameter = 2.0 * ballRadius;
  private double deltaX;
  private double deltaY;
  private double ballCenterX;
  private double ballCenterY;
  private double ballUpperCornerX;
  private double ballUpperCornerY;
  private double distanceMovedInOneTick;
  private Position ballUpperCornerPos;
  private boolean successfulMove = true;
  private double speed;


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

    //repaints ball or runner
    bases.setColor(new Color(255, 43, 110));
    bases.drawOval(ballUpperCornerPos.getX(), ballUpperCornerPos.getY(), (int)Math.round(ballDiameter),(int)Math.round(ballDiameter));
    bases.fillOval(ballUpperCornerPos.getX(), ballUpperCornerPos.getY(),
                        (int)Math.round(ballDiameter),(int)Math.round(ballDiameter));
  }
  public void initializeBall()
  {
    //sets the center of the ball to the center of the panel
    ballCenterX = 1920/2;
    ballCenterY =  780/2;

    ballUpperCornerX = ballCenterX - ballRadius;
    ballUpperCornerY = ballCenterY - ballRadius;
    ballUpperCornerPos = new Position((int)Math.round(ballUpperCornerX), (int)Math.round(ballUpperCornerY));


  }

  public boolean updateBall()
  {
    // successfulMove = true;
    // if(distanceBetween > speed*distanceMovedInOneTick)
    // {//This is the case where the destination is further away than a single step can accomplish.
    //   ballCenterX += speed*deltaX;
    //   ballCenterY += speed*deltaY;
    //   // System.out.println("x:" + ballCenterX + ", y:" + ballCenterY); //Debug
    // }
    // else
    // {//This is the case where the ball needs exactly one short hop to reach its destination.
    // }
    // ballUpperCornerX = ballCenterX - ballRadius;
    // ballUpperCornerY = ballCenterY - ballRadius;
    // ballUpperCornerPos.setX((int)Math.round(ballUpperCornerX));
    // ballUpperCornerPos.setY((int)Math.round(ballUpperCornerY));

    if ( (ballUpperCornerPos.getY() + speed*deltaY) <= 0)
    {
      if (deltaX == 0)
      {
        ballCenterY = 0 + ballRadius;
        deltaY *= -1.0;
      }
      else
      {
        ballCenterX = ballCenterX + (0 + ballRadius)*(deltaX/deltaY);
        ballCenterY = 0 + ballRadius;
        deltaY *= -1.0;
      }
    }
    else if( (ballUpperCornerPos.getY() + speed*deltaY) >= 780 - ballDiameter)
    {
      if (deltaX == 0)
      {
        ballCenterY = 0 + ballRadius;
        deltaY *= -1.0;
      }
      else
      {
        ballCenterX = (ballCenterX + (780 - ballRadius)*(deltaX/deltaY));
        ballCenterY = 0 + ballRadius;
        deltaY *= -1.0;
      }
    }
    else if( (ballUpperCornerPos.getX() + speed*deltaX) <= 0)
    {
      if (deltaX == 0)
      {
        ballCenterY = 0 + ballRadius;
        deltaX *= -1.0;
      }
      else
      {
        ballCenterX = 0 + ballRadius;
        ballCenterY = ballCenterY + (780 - ballRadius)*(deltaY/deltaX);
        deltaX *= -1.0;
      }
    }
    else if( (ballUpperCornerPos.getX() + speed*deltaX) >= 1920 - ballDiameter)
    {
      if (deltaX == 0)
      {
        ballCenterY = 0 + ballRadius;
        deltaX *= -1.0;
      }
      else
      {
        ballCenterX =
        ballCenterY = ballCenterY + (780 - ballRadius)*(deltaY/deltaX);
        deltaX *= -1.0;
      }
    }
    else
    {
      ballCenterX += speed*deltaX;
      ballCenterY += speed*deltaY;
    }
    ballUpperCornerX = ballCenterX - ballRadius;
    ballUpperCornerY = ballCenterY - ballRadius;
    ballUpperCornerPos.setX((int)Math.round(ballUpperCornerX));
    ballUpperCornerPos.setY((int)Math.round(ballUpperCornerY));
  }//End of updateRunner

  public void updateDelta(double deltaX, double deltaY)
  {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  public void setSpeed(double speed)
  {
    this.speed = speed;
  }

  public double getCenterX()
  {
    return ballCenterX;
  }
  public double getcenterY()
  {
    return ballCenterY;
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
