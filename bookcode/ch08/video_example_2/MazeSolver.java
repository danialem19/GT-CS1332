public class MazeSolver
{
   public static void main(String[] args)
   {
      Maze maze = new Maze(
         new String[] {
            "*****************************",
            "** ***                      *",
            "** *** * ********************",
            "** *** *         *          *",
            "** *** * *******   **** *****",
            "**     * ************** *****",
            "****** ******* *******  *****",
            "******         ******* ******",
            "*      ******* ******* ******",
            "* **** ******* **           *",
            "*    ********* ******* ******",
            "* ****         ***     ******",
            "************** **************"});

      Robot fred = new Robot(maze, 5, 8);
      
      while (!fred.seesExit())
      {
         if (fred.seesWall())
         {
            fred.turnLeft();
         }
         else 
         {
            fred.move();
            fred.turnRight();
            if (fred.seesWall()) 
            {
               fred.turnLeft(); // Turn back to original position
            }
            else 
            {
               fred.move();
            }
         }
         System.out.println(fred.toString());
      }
   }
}
