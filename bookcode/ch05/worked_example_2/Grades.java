import java.util.Scanner;

/**
   This program reads inputs that contain four grades per line and displays the
   average grade for each input line.
*/
public class Grades
{
   public static void main(String[] args)
   {
      while (processLine())
      {
      }
   }

   /**
      Processes one line of input.
      @return true if the sentinel was not encountered
   */
   public static boolean processLine()
   {
      System.out.print("Enter four grades or Q to quit: ");
      Scanner in = new Scanner(System.in);

      // Read four grades

      String g1 = in.next();
      if (g1.equals("Q")) { return false; }
      String g2 = in.next();
      String g3 = in.next();
      String g4 = in.next();

      // Compute and print their average

      double x1 = gradeToNumber(g1);
      double x2 = gradeToNumber(g2);
      double x3 = gradeToNumber(g3);
      double x4 = gradeToNumber(g4);
      double xlow = min4(x1, x2, x3, x4);
      double avg = (x1 + x2 + x3 + x4 - xlow) / 3;
      System.out.println(numberToGrade(avg));
      return true;
   }

   /**
      Converts a letter grade to a number.
      @param grade a letter grade (A+, A, A-, ..., D-, F)
      @return the equivalent number grade
   */
   public static double gradeToNumber(String grade)
   {
      double result = 0;
      String first = grade.substring(0, 1);
      if (first.equals("A")) { result = 4; }
      else if (first.equals("B")) { result = 3; }
      else if (first.equals("C")) { result = 2; }
      else if (first.equals("D")) { result = 1; }
      if (grade.length() > 1)
      {
         String second = grade.substring(1, 2);
         if (second.equals("+"))
         {
            result = result + 0.3;
         }
         else if (second.equals("-"))
         {
            result = result - 0.3;
         }
      }
      return result;
   }
   
   /**
      Converts a number to the nearest letter grade.
      @param x a number between 0 and 4.3
      @return the nearest letter grade
   */
   public static String numberToGrade(double x)
   {
      if (x >= 4.15) { return "A+"; }
      if (x >= 3.85) { return "A"; }
      if (x >= 3.5) { return "A-"; }
      if (x >= 3.15) { return "B+"; }
      if (x >= 2.85) { return "B"; }
      if (x >= 2.5) { return "B-"; }
      if (x >= 2.15) { return "C+"; }
      if (x >= 1.85) { return "C"; }
      if (x >= 1.5) { return "C-"; }
      if (x >= 1.15) { return "D+"; }
      if (x >= 0.85) { return "D"; }
      if (x >= 0.5) { return "D-"; }
      return "F";
   }

   /**
      Returns the smallest of four numbers.
      @param a a number
      @param b a number
      @param c a number
      @param d a number
      @return the smallest of a, b, c, and d
   */
   public static double min4(double a, double b, double c, double d) 
   { 
      return Math.min(Math.min(a, b), Math.min(c, d));
   }
}
