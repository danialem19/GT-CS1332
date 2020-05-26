/**
   This class measures the length of a string.
*/
public class StringMeasurer implements Measurer
{
   public double measure(Object obj)
   {
      String str = (String) obj;
      return str.length();
   }
}
