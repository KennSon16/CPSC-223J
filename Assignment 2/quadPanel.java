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
  //Files: main.java, gui.java, math.java, quadPanel.java, run.sh
  //Date project began: 2021-February-8.
  //Date of last update: 2021-February-8.
  //Status: Creating the gui.
  //Purpose: This program shows a simple user-interface that allows the user to input a speed to show a baseball player move from base to base,
  //         shown through an animation. Two buttons are added to start and stop the baseball player.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: quadPanel.java
  //Compile : javac quadPanel.java
  //This is the top level module.  This module activates the user interface.


import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class quadPanel extends JPanel
{
  private Position northBase;
  private Position westBase;
  private Position eastBase;
  private Position southBase;
  public  Position runner;

  public quadPanel()
  {
    northBase = new Position(960, 30);
    westBase = new Position(100, 440);
    eastBase = new Position(1820, 440);
    southBase = new Position(960, 850);
    runner = new Position(southBase.getX(), southBase.getY());
    setBounds(0, 100, 1920, 880);
    setBackground(new Color(159, 255, 148));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    //(x,y) N(250, 150) E(450, 200) S(250, 450) W(50, 200)

  }
  public void paint(Graphics lines)
  {
    Graphics2D bases = (Graphics2D) lines;
    bases.setColor(new Color(159, 255, 148));
    bases.fillRect(0, 0, 1920, 880);

    bases.setPaint(new Color(230, 230, 230));
    bases.setStroke(new BasicStroke(6));
    bases.drawLine(southBase.getX(), southBase.getY(), eastBase.getX(), eastBase.getY()); //south to east
    bases.drawLine(eastBase.getX(), eastBase.getY(), northBase.getX(), northBase.getY()); //east to north
    bases.drawLine(northBase.getX(), northBase.getY(), westBase.getX(), westBase.getY()); //north to west
    bases.drawLine(westBase.getX(), westBase.getY(), southBase.getX(), southBase.getY()); //west to south

  }
  // public void updateRunner(Postion nextPos) {
  //
  // }

}
class Position
{
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
