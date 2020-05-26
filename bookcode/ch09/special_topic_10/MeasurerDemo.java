/**
   This program demonstrates the string measurer.
*/
public class MeasurerDemo
{
   public static void main(String[] args)
   {
      String[] words = { "Mary", "had", "a", "little", "lamb" };
      Measurer lengthMeasurer = new StringMeasurer();
      System.out.println("Average length: " 
         + average(words, lengthMeasurer));
   }

   /**
      Computes the average of the measures of the given objects.
      @param objects an array of objects
      @param meas the measurer
      @return the average of the measures
   */
   public static double average(Object[] objects, Measurer meas)
   {
      if (objects.length == 0) { return 0; }
      double sum = 0;
      for (Object obj : objects)
      {
         sum = sum + meas.measure(obj);
      }
      return sum / objects.length;
   }
}
