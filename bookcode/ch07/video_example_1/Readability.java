 import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Readability
{
   public static void main(String[] args)
   {
      String filename;
      if (args.length == 0)
      {
         Scanner console = new Scanner(System.in);
         System.out.print("Input file: ");
         filename = console.nextLine();
      }
      else
      {
         filename = args[0];
      }
      try
      {

         Scanner in = new Scanner(new File(filename));
         readabilityIndex(in);
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("File not found: " + filename);
      }
   }

   /**
      Checks whether a character is a vowel.
      @param ch a string of length 1
      @return true if ch is a vowel
   */
   public static boolean isVowel(String ch)
   {
      return "aeiouy".contains(ch.toLowerCase());
   }

   /**
      Checks whether a character is a punctuation mark denoting the end of
      a sentence.
      @param ch a string of length 1
      @return true if ch ends a sentence
   */
   public static boolean isSentenceEnd(String ch)
   {
      return ".:;?!".contains(ch);
   }

   /**
      Computes and displays the readability index of a file.
      @param in a scanner for the file
   */
   public static void readabilityIndex(Scanner in)
   {
      int words = 0;
      int syllables = 0;
      int sentences = 0;
      while (in.hasNext())
      {
         String word = in.next();
         words++;
         syllables = syllables + countSyllables(word);
         if (isSentenceEnd(word.substring(word.length() - 1)))
         { 
            sentences++; 
         }
      }
      double index = 0;
      if (sentences > 0)
      {
         index = 206.835 - 84.6 * syllables / words 
            - 1.015 * words / sentences;
      }
      System.out.println("Words: " + words);
      System.out.println("Syllables: " + syllables);
      System.out.println("Sentences: " + sentences);
      System.out.println("Index: " + index);
   }

   /**
      Counts the syllables in a word.
      @param word a word
      @return the number of syllables in the word
   */
   public static int countSyllables(String word)
   {
      int count = 0;
      boolean inVowelGroup = false;
      for (int i = 0; i < word.length(); i++)
      {
         String letter = word.substring(i, i + 1);
         if (isVowel(letter))
         {
            if (!inVowelGroup)
            {
               inVowelGroup = true;
               count++;
            }
         }
         else
         {
            inVowelGroup = false;
         }
      }
      if (word.endsWith("e")) { count--; }
      if (count == 0) { count = 1; }
      return count;
   }
}
