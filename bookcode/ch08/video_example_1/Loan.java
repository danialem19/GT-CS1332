public class Loan
{
   private double interestRate;
   private double balance;
   private double interestPaid;

   /**
      Constructs a loan for a given amount at a given interest rate.
      @param amount the amount being loaned
      @param rate the interest rate in percent to be applied at
      every payment period.
   */
   public Loan(double amount, double rate)
   {
      balance = amount;
      interestRate = rate;
   }

   /**
      Get the total amount of interest paid so far.
      @return the interest paid
   */
   public double getInterestPaid() 
   {
      return interestPaid;
   }

   /**
      Get the balance of the principal that still must be repaid.
      @return the principal balance
   */
   public double getBalance()
   {
      return balance;
   }

   /**
      Make one payment. 
      @param payment the amount of the payment
   */
   public void makePayment(double payment)
   {
      double interest = balance * interestRate / 100;
      interestPaid = interestPaid + interest;
      balance = balance - (payment - interest);
   }
}
