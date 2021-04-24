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
  //File name: CatAndMouse.java
  //Compile : javac CatAndMouse.java
  //This is the top level module.  This module activates the user interface.

  import javax.swing.JFrame;
  public class CatAndMouse
  {
    public static void main(String[] args)
    {
      UI myframe = new UI();
      myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myframe.setSize(1920,1080);
      myframe.setResizable(false);
      myframe.setVisible(true);
    }//End of CatAndMouse
  }//End of class CatAndMouse
