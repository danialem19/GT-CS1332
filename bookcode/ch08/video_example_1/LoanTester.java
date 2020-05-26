public class LoanTester
{
   public static void main(String[] args)
   {
      // 5 year 12% per year fixed rate, monthly payments
      double loanAmount = 20000;
      double annualRate = 12;
      int MONTHS_PER_YEAR = 12;
      Loan carLoan = new Loan(loanAmount, annualRate / MONTHS_PER_YEAR);
      int count = 0;
      double payment = 444.89;
      while (carLoan.getBalance() > 0)
      {
         carLoan.makePayment(payment);
         count++;
      }
      System.out.println("Number of payments: " + count);
      System.out.printf("Interest paid: %.2f\n", carLoan.getInterestPaid());
      System.out.printf("Total amount paid: %.2f\n", count * payment);
      System.out.println();

      // 30 year 6% per year fixed rate, monthly payments
      Loan mortgage = new Loan(200000, 6.0 / MONTHS_PER_YEAR);
      count = 0;
      payment = 1200;
      while (mortgage.getBalance() > 0)
      {
         mortgage.makePayment(payment);
         count++;
      }
      System.out.println("Number of payments: " + count);
      System.out.printf("Interest paid: %.2f\n", mortgage.getInterestPaid());
      System.out.printf("Total amount paid: %.2f\n", count * payment);
   }
}
