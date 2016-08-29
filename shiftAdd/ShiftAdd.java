import java.util.*;

class Result {
   int value;
   int carry;

   public Result() {
      value = 0;
      carry = 0;
   }
}

public class ShiftAdd {

   static Result shiftAdd(int num1,
                          int num2,
                          int bit,
                          Result result)
   {
      if (bit >= 32) {
         return result;
      }

      int numOnes = 0;
      final int mask = 0x1;
      int n1 = (num1 >>> bit) & mask;
      int n2 = (num2 >>> bit) & mask;

      if (n1 == 1) { numOnes++;}
      if (n2 == 1) { numOnes++;}
      if (result.carry == 1) { numOnes++;}

      int resultBit = (n1 ^ n2) ^ result.carry;
      result.value |= (resultBit << bit);
      result.carry = (numOnes > 1) ? 1 : 0;

      return shiftAdd(num1, num2, bit + 1, result);
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the first number: ");
      int n1 = in.nextInt();

      System.out.println("Enter the second number: ");
      int n2 = in.nextInt();

      System.out.format("The two numbers are %d, %d\n", n1, n2);

      Result result = new Result();
      result = shiftAdd(n1, n2, 0, result);

      System.out.format("%d + %d = %d, expected value %d\n",
                        n1, n2, result.value, n1 + n2);
   }
}
