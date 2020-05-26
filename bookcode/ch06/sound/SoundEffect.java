import java.awt.Color;
import java.awt.Graphics;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
   This program processes a 16 bit uncompressed mono .WAV file.
*/
public class SoundEffect
{
   /**
      Processes the sound samples. Modify this function to change
      the way the sound is processed.
      @param samples the sound samples in the sound file
      @param size the number of samples
      @param sampleRate the number of samples per second
   */
   public static void process(int samples[], int sampleRate)
   {
      // Here, we make the sound three times as loud
      // In this example, we don't need the sample rate
      
      for (int i = 0; i < samples.length; i++)
      {
         samples[i] = 3 * samples[i];
      }
   }

   // -----------------------------------------------------------------

   /*
     The code below processes a file in the WAV format.
     You can use this program to manipulate sound files without 
     reading or understanding the code below. 
     
     The code uses Java features that are introduced in chapter 19, 
     as well as the internals of the WAV format 
     (https://ccrma.stanford.edu/courses/422/projects/WaveFormat/)
   */

   /**
      Gets an unsigned 4-byte integer from a random access file.
      @param in the file
      @return the integer 
   */
   public static int getUnsignedInt4(RandomAccessFile in) 
      throws IOException
   {
      int result = 0;
      int base = 1;
      for (int i = 0; i < 4; i++)
      {
         result = result + in.read() * base;
         base = base * 256;
      }
      return result;
   }

   /**
      Gets an unsigned 2-byte integer from a random access file.
      @param in the file
      @return the integer
   */
   public static int getUnsignedInt2(RandomAccessFile in)
      throws IOException
   {
      int lo = in.read();
      int hi = in.read();
      return lo + 256 * hi;
   }

   /**
      Gets a signed 2-byte integer from a random access file.
      @param in the file
      @return the integer
   */
   public static int getSignedInt2(RandomAccessFile in)
      throws IOException
   {
      int lo = in.read();
      int hi = in.read();
      int result = lo + 256 * hi;
      if (result >= 32768) { result = result - 65536; }
      return result;
   }

   /**
      Puts a signed 2-byte integer to a random access file.
      @param out the file
      @param value the integer to put
   */
   public static void putSignedInt2(RandomAccessFile out, int value)
      throws IOException
   {
      if (value < 0) { value = value + 65536; }
      out.write(value % 256);
      out.write(value / 256);
   }

   public static void main(String[] args) throws Exception
   {
      JFileChooser chooser = new JFileChooser(".");
      if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) 
      {
         System.out.println("No sound file selected.");
         System.exit(1);
      }

      RandomAccessFile file = new RandomAccessFile(
         chooser.getSelectedFile(), "rw");

      // Check that we can handle this file
      file.seek(20);
      int formatType = getUnsignedInt2(file);
      if (formatType != 1)
      {
         System.out.println("Not an uncompressed sound file.");
         System.exit(1);
      }
      int numChannels = getUnsignedInt2(file);
      if (numChannels != 1)
      {
         System.out.println("Not a mono sound file.");
         System.exit(1);
      }

      final int sampleRate = getUnsignedInt2(file);

      file.seek(34);
      int bitsPerSample = getUnsignedInt2(file);
      if (bitsPerSample != 16)
      {
         System.out.println("Not a 16 bit sound file.");
         System.exit(1);
      }

      // Read data size and allocate data array
      file.seek(40);
      int dataSize = getUnsignedInt4(file) / 2; // 2 bytes per data
      final int[] samples = new int[dataSize];

      // Read sound data
      for (int i = 0; i < dataSize; i++) 
      {
         samples[i] = getSignedInt2(file);
      }

      final int[] original = Arrays.copyOf(samples, dataSize);

      // Process sound data
      process(samples, sampleRate);

      // Write sound data
      file.seek(44);
      for (int i = 0; i < dataSize; i++)
      {
         putSignedInt2(file, samples[i]);
      }

      file.close();

      // This is the end of the file processing. 

      // Next, we show the original and processed wave forms

      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 800;
      final int FRAME_HEIGHT = 200;

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JComponent component = new JComponent()
      {
          public void paintComponent(Graphics graph)
          {
             int increment = samples.length / getWidth();

             // Find the largest value for scaling the displayed values
             int largest = 0;
             for (int i = 0; i < samples.length; i = i + increment)
             {
                int sample = Math.abs(samples[i]);
                int orig = Math.abs(original[i]);
                if (sample > largest) { largest = sample; } 
                if (orig > largest) { largest = orig; }
             }

             int x = 0;
             for (int i = 0; i < samples.length; i = i + increment)
             {
                int height = getHeight() / 4;
                int y = height - original[i] * height / largest;
                graph.drawLine(x, y, x, height);
                y = 3 * height - samples[i] * height / largest;
                graph.drawLine(x, y, x, 3 * height);
                x++;
             }
          }
      };
      
      frame.add(component);
      frame.setVisible(true);

      // Now play the sound

      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(
            chooser.getSelectedFile()));
      clip.start(); 
   }
}
