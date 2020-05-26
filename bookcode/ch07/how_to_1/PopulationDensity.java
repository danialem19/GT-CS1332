import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
   This program reads data files of country populations and areas and prints the
   population density for each country.
*/
public class PopulationDensity
{
   public static void main(String[] args) throws FileNotFoundException
   {
      // Construct Scanner objects for input files

      Scanner in1 = new Scanner(new File("worldpop.txt"));
      Scanner in2 = new Scanner(new File("worldarea.txt"));

      // Construct PrintWriter for the output file

      PrintWriter out = new PrintWriter("world_pop_density.txt");

      // Read lines from each file

      while (in1.hasNextLine() && in2.hasNextLine())
      {
         String line1 = in1.nextLine();
         String line2 = in2.nextLine();

         // Extract country and associated value
         String country = extractCountry(line1);
         double population = extractValue(line1);
         double area = extractValue(line2);

         // Compute and print the population density
         double density = 0;
         if (area != 0) // Protect against division by zero
         {
            density = population / area;
         }
         out.printf("%-40s%15.2f%n", country, density);
      }

      in1.close();
      in2.close();
      out.close();
   }

   /**
      Extracts the country from an input line.
      @param line a line containing a country name, followed by a number
      @return the country name
   */
   public static String extractCountry(String line)
   {
      int i = 0; // Locate the start of the first digit
      while (!Character.isDigit(line.charAt(i))) { i++; }
      return line.substring(0, i).trim(); // Extract the country name
   }

   /**
      Extracts the value from an input line.
      @param line a line containing a country name, followed by a value
      @return the value associated with the country
   */
   public static double extractValue(String line)
   {
      int i = 0; // Locate the start of the first digit
      while (!Character.isDigit(line.charAt(i))) { i++; }
      // Extract and convert the value
      return Double.parseDouble(line.substring(i).trim());
   }
}
