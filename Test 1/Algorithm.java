//Kenn Son
//CPSC223J Test 1

public class Algorithm
{
  public static double compute_hypotenuse(double s1, double s2)
  {
    if (s1 == 0 || s2 == 0) {
      return 0;
    }
    double cSquared = (s1*s1) + (s2*s2);
    return Math.sqrt(cSquared);
  }

  public static double compute_area(double side1,double side2)
  {
    return (side1*side2)/2.0;
  }
}
