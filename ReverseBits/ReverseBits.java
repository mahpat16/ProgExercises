import java.util.*;

public class ReverseBits {

   static int reverseBits(int n)
   {
      int result = 0;
      int lo = 0;
      int hi = Integer.SIZE - 1;
      System.out.format("lo: %d, hi: %d\n", lo, hi);

      for (int i = 0; i < Integer.SIZE/2; i++) {
         if (((n >>> lo) & 0x1) != ((n >>> hi) & 0x1)) {
            // Swap lo and hi
            n ^= ((1 << hi) | (1 << lo));
         }
         lo++;
         hi--;
      }
      return n;
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);

      System.out.println("Enter the number to reverse\n");
      int n = in.nextInt();

      System.out.format("Number is %s\n", Integer.toBinaryString(n));

      System.out.format("Reversed number is %s\n", Integer.toBinaryString(reverseBits(n)));
   }
}
