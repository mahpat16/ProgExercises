import java.util.*;
import java.lang.*;

public class ChoiceCost {
   public static int maxCost(int lower,
                             int upper) {
      int minMax = -1;
      if ((upper - lower) == 2) {
         return lower + 1;
      }

      if (lower > upper) {
         return 0;
      }

      if (upper - lower == 1) {
         return lower;
      }

      if (upper == lower) {
         return 0;
      }

      for (int i = lower; i <= upper; i++) {
         int curMax = 0;
         curMax = maxCost(lower, i - 1) + i;
         curMax = Math.max(maxCost(i + 1, upper) + i, curMax);

         if (minMax == -1) {
            minMax = curMax;
         } else {
            minMax = Math.min(curMax, minMax);
         }
      }
      return minMax;
   }

   public static void main(String[] args) {
      if (args.length != 1) {
         System.out.println("Enter the number n");
         System.exit(1);
      }
      System.out.println("Max money required is: " + maxCost(1, new Integer(args[0])));
   }

}
