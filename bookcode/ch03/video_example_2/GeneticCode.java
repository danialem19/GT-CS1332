import java.util.Scanner;

public class GeneticCode
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Codon: ");
      String codon = in.next();

      char first = codon.charAt(0);
      char second = codon.charAt(1);
      char third = codon.charAt(2);
      boolean thirdIsUC = third == 'U' || third == 'C';
      boolean thirdIsAG = third == 'A' || third == 'G';
      String result = "";
      
      if (first == 'U')
      {
         if (second == 'U')
         {
            if (thirdIsUC) { result = "Phe"; } 
            else if (thirdIsAG) { result = "Leu"; }
         }
         else if (second == 'C') { result = "Ser"; }
         else if (second == 'A')
         {
            if (thirdIsUC) { result = "Tyr"; } 
            else if (thirdIsAG) { result = "Stop"; }
         }
         else if (second == 'G')
         {
            if (thirdIsUC) { result = "Cys"; } 
            else if (third == 'A') { result = "Stop"; }
            else if (third == 'G') { result = "Trp"; }
         }      
      }
      else if (first == 'C')
      {
         if (second == 'U')  { result = "Leu"; }
         else if (second == 'C') { result = "Pro"; }
         else if (second == 'A')
         {
            if (thirdIsUC) { result = "His"; } 
            else if (thirdIsAG) { result = "Gln"; }
         }
         else if (second == 'G') { result = "Arg"; }
      }
      else if (first == 'A')
      {
         if (second == 'U')
         { 
            if (third == 'U' || third == 'C' || third == 'A')
            {
               result = "Ile"; 
            }
            else { result = "Met"; }
         }
         else if (second == 'C') { result = "Thr"; }
         else if (second == 'A')
         {
            if (thirdIsUC) { result = "Asn"; } 
            else if (thirdIsAG) { result = "Lys"; }
         }
         else if (second == 'G')
         {
            if (thirdIsUC) { result = "Ser"; } 
            else if (thirdIsAG) { result = "Arg"; }
         }
      }
      else if (first == 'G')
      {
         if (second == 'U')  { result = "Val"; }
         else if (second == 'C') { result = "Ala"; }
         else if (second == 'A')
         {
            if (thirdIsUC) { result = "Asp"; } 
            else if (thirdIsAG) { result = "Glu"; }
         }
         else if (second == 'G') { result = "Gly"; }
      }
      System.out.println("Amino acid: " + result);
   }
}
