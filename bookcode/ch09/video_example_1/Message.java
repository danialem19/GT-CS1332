/**
   A discussion board message.
*/
public class Message
{
   private User author;
   private String title;
   private String text;

   /**
      Constructs a message.
      @param author the message author
      @param title the message title
      @param text the message text
   */
   public Message(User author, String title, String text)
   {
      this.author = author;
      this.title = title;
      this.text = text;
   }

   /**
      Gets the message title.
      @return the title
   */
   public String getTitle()
   {
      return title;
   }

   /**
      Displays the message.
   */
   public void display()
   {
      System.out.println("From: " + author.getName());
      System.out.println("Title: " + getTitle());
      System.out.print(text);
   }
}
