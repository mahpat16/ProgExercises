import java.util.*;

public class AllSets {

   private static void getNextSet(ArrayList<ArrayList<String>> result,
                                  int maxLen,
                                  ArrayList<String> root,
                                  ArrayList<String> set) {
      if (root.size() == maxLen) {
         ArrayList<String> newSet = new ArrayList<String>();
         for (String elem: root) {
            newSet.add(elem);
         }
         result.add(newSet);
         return;
      }

      if (set.size() == 0) {
         return;
      }

      ArrayList<String> remainderSet = new ArrayList<String>(set);
      for (String elem: set) {
         ArrayList<String> newRoot = new ArrayList<String>(root);

         newRoot.add(elem);
         remainderSet.remove(elem);

         getNextSet(result, maxLen, newRoot, remainderSet);
      }
   }

   public static void main(String[] args) {
      ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
      ArrayList<String> inputSet = new ArrayList<String>();

      System.out.println("Hello World");

      for (String element : args) {
         inputSet.add(element);
      }

      for (int i = 1; i <= inputSet.size(); i++) {
         getNextSet(result, i, new ArrayList<String>(), inputSet);
      }

      System.out.println("Number of sets is: " + result.size());
      for (ArrayList<String> set: result) {
         System.out.println("Next set is => ");
         for (String elem: set) {
            System.out.println("  " + elem);
         }
      }
   }
}
