import java.util.*;

public class Coins {

   private static ArrayList<ArrayList<Integer>> addUp(int target,
                                                      int curVal) {
      ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
      final int coinList[] = { 1, 5, 10, 25 };

      if (curVal == target) {
         ArrayList<Integer> coins = new ArrayList<Integer>();
         result.add(coins);
         return result;
      }

      for (int c: coinList) {
         ArrayList<ArrayList<Integer>> rtnResult;
         if ((c + curVal) > target) {
            continue;
         }

         rtnResult = addUp(target, curVal + c);
         for (ArrayList<Integer> coins : rtnResult) {
            coins.add(c);
            result.add(coins);
         }
      }
      return result;
   }

   private static ArrayList<ArrayList<Integer>> addUpUniq(int target,
                                                          int curVal,
                                                          int curCoin) {
      ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
      final int coinList[] = { 1, 5, 10, 25 };

      if (curVal == target) {
         ArrayList<Integer> coins = new ArrayList<Integer>();
         result.add(coins);
         return result;
      }

      for (int c: coinList) {
         ArrayList<ArrayList<Integer>> rtnResult;

         if ((c + curVal) > target) {
            continue;
         }

         if (c < curCoin) {
            continue;
         }

         rtnResult = addUpUniq(target, curVal + c, c);
         for (ArrayList<Integer> coins : rtnResult) {
            coins.add(c);
            result.add(coins);
         }
      }
      return result;
   }

   public static void main(String[] args) {
      ArrayList<ArrayList<Integer>> result;
      if (args.length != 1) {
         System.out.println("Enter the value to add up");
         System.exit(1);
      }

      System.out.println("Value to add up is: " + args[0]);
      result = addUpUniq(new Integer(args[0]), 0, 0);
      for (ArrayList<Integer> coins : result) {
         System.out.println(coins);
      }
   }
}
