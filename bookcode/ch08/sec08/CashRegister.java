/**
   A simulated cash register that tracks the item count and
   the total amount due.
*/
public class CashRegister
{
   private int itemCount;
   private double totalPrice;
   private double taxableTotal;
   private double taxRate;

   /**
      Constructs a cash register with cleared item count and total.
      @param aTaxRate the tax rate to use with this cash register.
   */
   public CashRegister(double aTaxRate)
   {
      itemCount = 0;
      totalPrice = 0;
      taxableTotal = 0;
      taxRate = aTaxRate;
   }

   /**
      Adds an item to this cash register.
      @param price the price of this item
      @param taxable true if this item is taxable
   */
   public void addItem(double price, boolean taxable)
   {
      itemCount++;
      totalPrice = totalPrice + price;
      if (taxable) 
      {
         taxableTotal = taxableTotal + price;
      }
   }

   /**
      Gets the price of all items in the current sale.
      @return the total amount
   */
   public double getTotal()
   {
      return totalPrice + taxableTotal * taxRate / 100; 
   }
   
   /**
      Gets the number of items in the current sale.
      @return the item count
   */
   public int getCount()
   {
      return itemCount; 
   }

   /**
      Clears the item count and the total.
   */
   public void clear()
   {
      itemCount = 0;
      totalPrice = 0;
      taxableTotal = 0;
   }
}


