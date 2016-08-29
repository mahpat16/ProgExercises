import java.util.*;
import java.lang.*;


public class Multiply {

   static private int mult(int n, int multiplier) {
      int lowPowerOf2 = Integer.highestOneBit(n);
      int numShift = Integer.numberOfTrailingZeros(lowPowerOf2);

      if (n == 0) {
         return 0;
      }

      if (n == 1) {
         return multiplier;
      }


      return (multiplier << numShift) +  mult(n - lowPowerOf2, multiplier);
   }

   public static void main(String[] args) {
      int num1 = 0;
      int num2 = 0;
      if (args.length != 2) {
         System.out.println("Enter 2 numbers");
         return;
      }

      num1 = new Integer(args[0]);
      num2 = new Integer(args[1]);
      System.out.println(num1 + " x " + num2 + " = " + mult(num1, num2));
   }
}
