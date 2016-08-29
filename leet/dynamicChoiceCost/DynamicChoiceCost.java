import java.util.*;
import java.lang.*;

public class DynamicChoiceCost {
   static Map<Integer, Map<Integer, Integer>> cache;
   static int numInserts = 0;
   static int numLookups = 0;

   public static void addToCache(int lower,
                                 int upper,
                                 int cost)
   {
      if (!cache.containsKey(lower)) {
         cache.put(lower, new HashMap<Integer, Integer>());
         cache.get(lower).put(upper, 0);
      }
      cache.get(lower).put(upper, cost);
      numInserts += 1;
      return;
   }

   public static int getCached(int lower,
                               int upper)
   {
      if (!cache.containsKey(lower)) {
         return -1;
      }
      if (!cache.get(lower).containsKey(upper)) {
         return -1;
      }
      numLookups += 1;
      return cache.get(lower).get(upper);
   }

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

      int cached = getCached(lower, upper);
      if (cached != -1) {
         return cached;
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
      addToCache(lower, upper, minMax);
      return minMax;
   }

   public static void main(String[] args) {
      if (args.length != 1) {
         System.out.println("Enter the number n");
         System.exit(1);
      }
      cache = new HashMap<Integer, Map<Integer, Integer>>();
      System.out.println("Max money required is: " + maxCost(1, new Integer(args[0])));
      System.out.println("Num inserts: " + numInserts + " , Num lookups: " + numLookups);
   }

}
