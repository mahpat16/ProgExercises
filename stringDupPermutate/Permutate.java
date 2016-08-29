import java.util.*;

public class Permutate {
   public static ArrayList<String> permutate(String s) {
      HashMap<Character, Boolean> done = new HashMap<Character, Boolean>();
      ArrayList<String> result = new ArrayList<String>();
      if (s.length() == 1) {
         result.add(s);
         return result;
      }

      for (int i = 0; i < s.length(); i++) {
         ArrayList<String> rtn;
         char cur = s.charAt(i);
         if (done.containsKey(cur)) {
            continue;
         }
         done.put(cur, true);
         String remain;
         if (i == 0) {
            remain = s.substring(i + 1);
         } else if (i == (s.length() - 1)) {
            remain = s.substring(0, i);
         } else {
            remain = s.substring(0, i);
            remain = remain + s.substring(i + 1, s.length());
         }
         rtn = permutate(remain);
         for (String str: rtn) {
            result.add(cur + str);
         }
      }
      return result;
   }

   public static void main(String[] args) {
      ArrayList<String> permutations;
      if (args.length != 1) {
         System.out.println("Enter a string");
         System.exit(1);
      }
      permutations = permutate(args[0]);
      for (String s: permutations) {
         System.out.println(s);
      }
   }
}

