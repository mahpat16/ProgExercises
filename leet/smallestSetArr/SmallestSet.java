import java.util.*;
import java.io.Console;

class Solution {

   private int[] progressTop;
   private int[] progressBottom;
   private int[] a1;
   private int[] a2;

   private int findBottomMin(int end,
                             int curRow,
                             int curCol,
                             int curMin,
                             int[] result) {
      int i;
      if (curRow >= a1.length && curCol >= a2.length) {
         return curMin;
      }

      if (curCol < a2.length && progressBottom[curCol] == (a1.length - 1)) {
         return findBottomMin(end, curRow + 1, curCol + 1, curMin, result);
      }

      // Optimization
      if (curCol < a2.length && progressBottom[curCol] != -1) {
         i = progressBottom[curCol] + 1;
      } else {
         i = curRow;
      }

      for (; i <= end; i++) {
         int min;
         int rtnMin;

         if (i >= a1.length) {
            return curMin;
         }

         if (curCol >= a2.length) {
            return curMin;
         }

         if (progressBottom[curCol] != -1 && progressBottom[curCol] >= i) {
            continue;
         }

         min = a1[i] + a2[curCol];
         if (curMin <= min) {
            return findBottomMin(end, curRow + 1, curCol + 1, curMin, result);
         }

         result[0] = a1[i];
         result[1] = a2[curCol];

         rtnMin = findBottomMin(end, curRow + 1, curCol + 1, min, result);
         if (rtnMin == min) {
            progressBottom[curCol] = i;
         }
         return rtnMin;
      }
      return curMin;
   }

   private int findTopMin(int end,
                          int curRow,
                          int curCol,
                          int curMin,
                          int[] result) {
      int i;
      if (curCol >= a2.length && curRow >= a1.length) {
         return curMin;
      }

      if (curRow < a1.length && progressTop[curRow] == (a2.length - 1)) {
         return findTopMin(end, curRow + 1, curCol + 1, curMin, result);
      }

      // Optimization
      if (curRow < a1.length && progressTop[curRow] != -1) {
         i = progressTop[curRow] + 1;
      } else {
         i = curCol;
      }

      for (; i <= end; i++) {
         int min;
         int rtnMin;

         if (curRow >= a1.length) {
            return curMin;
         }

         if (i >= a2.length) {
            return curMin;
         }

         if (progressTop[curRow] != -1 && progressTop[curRow] >= i) {
            continue;
         }

         min = a1[curRow] + a2[i];
         if (curMin <= min) {
            return findTopMin(end, curRow + 1, curCol + 1, curMin, result);
         }

         result[0] = a1[curRow];
         result[1] = a2[i];

         rtnMin = findTopMin(end, curRow + 1, curCol + 1, min, result);
         if (rtnMin == min) {
            progressTop[curRow] = i;
         }
         return rtnMin;
      }
      return curMin;
   }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
       List<int[]> result = new ArrayList<int[]>();
       int initMin = 0x7fffffff;
       int bottomMin = initMin;
       int topMin = initMin;
       int[] topResult = new int[2];
       int[] bottomResult = new int[2];

       progressTop = new int[nums1.length];
       progressBottom = new int[nums2.length];
       a1 = nums1;
       a2 = nums2;

       for (int n = 0; n < progressTop.length; n++) {
          progressTop[n] = -1;
       }

       for (int n = 0; n < progressBottom.length; n++) {
          progressBottom[n] = -1;
       }

       int found = 0;
       int n = 0;
       while (found < k) {
          if (topMin == initMin) {
             topMin = findTopMin(n, 0, 0, initMin, topResult);
          }

          if (bottomMin == initMin) {
             bottomMin = findBottomMin(n, 1, 0, initMin, bottomResult);
          }

          if (topMin != initMin && (bottomMin == initMin || topMin <= bottomMin)) {
             result.add(topResult.clone());
             topMin = initMin;
             found++;
          } else {
             if (bottomMin != initMin) {
                result.add(bottomResult.clone());
                bottomMin = initMin;
                found++;
             } else {
                break;
             }
          }
          n++;
       }

       return result;
    }
}

public class SmallestSet {

   public static void main(String[] args)
   {
      Console c = System.console();
      int[] a1;
      int[] a2;
      List<int[]> result;

      if (c == null) {
         System.err.println("No console.");
         System.exit(1);
      }

      int n = new Integer(c.readLine("Enter the number of elements in array 1: "));
      a1 = new int[n];
      for (int i = 0; i < n; i++) {
         String elem = c.readLine("Next element: ");
         a1[i] = new Integer(elem);
      }

      n = new Integer(c.readLine("Enter the number of elements in array 2: "));
      a2 = new int[n];
      for (int i = 0; i < n; i++) {
         String elem = c.readLine("Next element: ");
         a2[i] = new Integer(elem);
      }

      int k = new Integer(c.readLine("Enter the number of smallest sets to find: "));

      Solution sol = new Solution();
      result = sol.kSmallestPairs(a1, a2, k);

      for (int[] set: result) {
         System.out.println("Sets are: [ " + set[0] + ", " + set[1] + " ]");
      }
   }
}
