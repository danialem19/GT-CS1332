/**
   This program tests the CashRegister class.
*/
public class CashRegisterTester
{
   public static void main(String[] args)
   {
      CashRegister register1 = new CashRegister();
      register1.addItem(1.95);
      register1.addItem(0.95);
      register1.addItem(2.50);

      Coin dollar = new Coin(1.0, "Dollar");
      Coin quarter = new Coin(0.25, "Quarter");

      register1.enterPayment(6, dollar);
      register1.enterPayment(3, quarter);

      System.out.printf("Change: %.2f%n", register1.giveChange());
   }
}
