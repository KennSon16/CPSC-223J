//Kenn Son
//CPSC223J Test 1

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame; //frame of the window
import javax.swing.JButton; // adds buttons
import javax.swing.JTextField;  //adds a text box
import javax.swing.JLabel; // adds labels on the panels
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class gui extends JFrame
{
  private JLabel mainTitle;
  private JLabel subtitle;
  private JLabel inputTitle1;
  private JTextField textboxInput1;
  private JLabel inputTitle2;
  private JTextField textboxInput2;
  private JTextField textboxRate;
  private JLabel hypotenuseLabel;
  private JLabel calculatedHypotenuse;
  private JLabel areaLabel;
  private JLabel calculatedArea;
  private JButton clearButton;
  private JButton computeButton;
  private JButton quitButton;
  private JPanel titlePanel;
  private JPanel inputPanel;
  private JPanel outputPanel;
  private JPanel buttonPanel;


  public gui()
  {
    super("CPSC223J Test 1");
    setLayout(new GridLayout(4,1)); // used 4 panels but made it look like three when ran for spacing

    //CREATING PANELS
    //first panel orange
    titlePanel = new JPanel();
    titlePanel.setBackground(new Color(252, 161, 3));
    titlePanel.setLayout(new GridLayout(2,1));

    mainTitle = new JLabel();
    mainTitle.setText("<html> Welcome to Triangle Computations <br/> Programmed by Kenn Son <html>");
    mainTitle.setHorizontalAlignment(JLabel.CENTER);
    subtitle = new JLabel();
    subtitle.setText("All triangles are right triangle");
    subtitle.setHorizontalAlignment(JLabel.CENTER);

    titlePanel.add(mainTitle);
    titlePanel.add(subtitle);

    // //second panel green
    inputPanel = new JPanel();
    inputPanel.setBackground(new Color(155,235,127));
    inputPanel.setLayout(new GridLayout(2,2));
    inputTitle1 = new JLabel();
    inputTitle1.setText("Input side 1:");
    inputTitle1.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(inputTitle1);
    textboxInput1 = new JTextField();
    textboxInput1.setHorizontalAlignment(JLabel.CENTER);
    inputPanel.add(textboxInput1);
    inputTitle2 = new JLabel("Input side 2:");
    textboxInput2 = new JTextField();
    inputTitle2.setHorizontalAlignment(JLabel.RIGHT);
    inputPanel.add(inputTitle2);
    textboxInput2.setHorizontalAlignment(JLabel.CENTER);
    inputPanel.add(textboxInput2);

    // //third panel green
    outputPanel = new JPanel();
    outputPanel.setLayout(new GridLayout(2,2));
    outputPanel.setBackground(new Color(155,235,127));
    hypotenuseLabel = new JLabel("Hypotenuse is:");
    hypotenuseLabel.setHorizontalAlignment(JLabel.RIGHT);
    calculatedHypotenuse = new JLabel("");
    calculatedHypotenuse.setHorizontalAlignment(JLabel.CENTER);
    areaLabel = new JLabel("Area is:");
    areaLabel.setHorizontalAlignment(JLabel.RIGHT);
    calculatedArea = new JLabel("");
    calculatedArea.setHorizontalAlignment(JLabel.CENTER);

    outputPanel.add(hypotenuseLabel);
    outputPanel.add(calculatedHypotenuse);
    outputPanel.add(areaLabel);
    outputPanel.add(calculatedArea);


    // //fourth panel red
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.setBackground(new Color(255, 87, 71));
    computeButton = new JButton("Compute");
    clearButton = new JButton("Clear");
    quitButton = new JButton("Quit");
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    buttonPanel.add(computeButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(quitButton);

    buttonhandler myButtons = new buttonhandler();
    clearButton.addActionListener(myButtons);
    computeButton.addActionListener(myButtons);
    quitButton.addActionListener(myButtons);

    add(titlePanel);
    add(inputPanel);
    add(outputPanel);
    add(buttonPanel);



  }// end of constructor
  private class buttonhandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == clearButton)
      {
        textboxInput1.setText("");
        textboxInput2.setText("");
        calculatedHypotenuse.setText("");
        calculatedArea.setText("");
      }
      else if (event.getSource() == computeButton)
      { //initializes everything to 0;
        Algorithm trigMath = new Algorithm();

        double userInput1;
        double userInput2;
        try
        { // if user inputs anything that is not a number then it would go to catch
          userInput1 = (Double.valueOf(textboxInput1.getText()));
        } // catch makes the value 0
        catch (Exception e)
        {
          userInput1 = 0.00;
        }
        try
        { // if user inputs anything that is not a number then it would go to catch
          userInput2 = (Double.valueOf(textboxInput2.getText()));
        }
        catch (Exception e)
        {
            userInput2 = 0.00;
        }
        if (userInput1 < 0) // if employeeHours is a negative number then it becomes 0
        {
          userInput1 = 0.00;
        }
        if (userInput2 < 0) // if userInput2 is a negative number then it becomes 0
        {
          userInput2 = 0.00;
        }
        double newHypotenuse = trigMath.compute_hypotenuse(userInput1, userInput2);
        double newArea = trigMath.compute_area(userInput1, userInput2);

        if (newHypotenuse == 0.00)
        {
          calculatedHypotenuse.setText("Error");
        }
        else
        {
          calculatedHypotenuse.setText(String.format("%.2f", newHypotenuse));
        }
        if (newArea == 0.00)
        {
          calculatedArea.setText("Error");
        }
        else
        {
          calculatedArea.setText(String.format("%.2f", newArea));
        }
        calculatedHypotenuse.repaint(); //refreshes the labels
        calculatedArea.repaint();
      }
      else if (event.getSource() == quitButton)
      {
        System.exit(0);
      } // implements the quit button
      else
      {
        System.out.println("Unknown error");
      } // if its not any button then its an Error.
    }// End of actionPerformed
  }// End of buttonhandler class
}// End of gui class
