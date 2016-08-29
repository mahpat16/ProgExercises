import java.util.*;

public class HeapA {

   static int getChild(int ndx)
   {
      return 2 * ndx + 1;
   }

   static int getParent(int ndx)
   {
      if (ndx == 0) {
         return -1;
      }

      return (ndx - 1)/2;
   }

   static void swap(int[] n,
                    int s,
                    int d)
   {
      int tmp = n[s];
      n[s] = n[d];
      n[d] = tmp;
   }

   static void siftUp(int[] n,
                      int parent,
                      int length)
   {
      if (parent < 0) {
         return;
      }

      int c1 = getChild(parent);
      int c2 = c1 + 1;
      int swapChild = parent;

      if (c1 < length && n[c1] > n[swapChild]) {
         swapChild = c1;
      }

      if (c2 < length && n[c2] > n[swapChild]) {
         swapChild = c2;
      }

      if (swapChild == parent) {
         return;
      }

      swap(n, parent, swapChild);
      siftUp(n, getParent(parent), length);
   }

   static void pushDown(int[] n,
                        int parent,
                        int length)
   {
      int c1 = getChild(parent);
      int c2 = c1 + 1;
      int swapChild = parent;

      if (c1 < length && n[c1] > n[swapChild]) {
         swapChild = c1;
      }

      if (c2 < length && n[c2] > n[swapChild]) {
         swapChild = c2;
      }

      if (swapChild == parent) {
         return;
      }

      swap(n, parent, swapChild);
      pushDown(n, swapChild, length);

   }

   static void heapify(int[] n,
                       int length)
   {
      for (int i = getParent(length - 1); i >= 0; i--) {
         pushDown(n, i, length);
      }
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);

      System.out.println("Enter space separated array of numbers");
      String[] s = in.nextLine().trim().split(" ");
      int[] n = new int[s.length * 4];
      for (int i = 0; i < s.length; i++) {
         n[i] = new Integer(s[i]);
      }

      System.out.println("Array is: " + Arrays.toString(n));
      heapify(n, s.length);
      System.out.println("Heapified array is: " + Arrays.toString(n));

      /*
      System.out.println("Now plucking one large value after another");

      for (int i = 0; i < n.length; i++) {
         System.out.println("- " + n[0]);
         n[0] = n[n.length - (i + 1)];

         pushDown(n, 0, n.length - (i + 1));
      }
      */

      int curLength = s.length;
      while(true) {
         System.out.println("Next element: ");
         int x = in.nextInt();
         n[curLength] = x;
         siftUp(n, getParent(curLength), curLength + 1);
         curLength++;
         System.out.println("Array is: " + Arrays.toString(n));
      }
   }
};

