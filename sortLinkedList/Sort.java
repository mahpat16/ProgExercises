import java.util.*;

class ListNode {
   int val;
   ListNode next;

   public ListNode(int val) {
      this.val = val;
      next = null;
   }

   public String toString() {
      return Integer.toString(val);
   }
}

public class Sort {
   static ListNode merge(ListNode left,
                         ListNode right)
   {
      ListNode head = new ListNode(0);
      ListNode cur = head;

      while (left != null && right != null) {
         if (left.val < right.val) {
            cur.next = left;
            cur = left;
            left = left.next;
         } else {
            cur.next = right;
            cur = right;
            right = right.next;
         }
      }

      if (left != null) {
         cur.next = left;
      }

      if (right != null) {
         cur.next = right;
      }
      return head.next;
   }

   static ListNode mergeSort(ListNode head)
   {
      ListNode slowPrev = null;
      ListNode slow = head;
      ListNode fast = head;

      if (head == null) {
         return null;
      }

      while (fast != null && fast.next != null) {
         slowPrev = slow;
         slow = slow.next;
         fast = fast.next.next;
      }

      if (slowPrev == null) {
         return head;
      }

      // Split the list into 2
      slowPrev.next = null;

      ListNode left = mergeSort(head);
      ListNode right = mergeSort(slow);

      return merge(left, right);
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.format("Enter the numbers (space separated): ");
      String[] sArr = in.nextLine().trim().split(" ");
      int[] nArr = new int[sArr.length];
      ListNode head = null;
      ListNode prev = null;

      for (int i = 0; i < sArr.length; i++) {
         ListNode n = new ListNode(new Integer(sArr[i]));
         if (head == null) {
            head = n;
         } else {
            prev.next = n;
         }
         prev = n;
      }

      System.out.println("Unsorted array");
      ListNode tmp = head;
      while (tmp != null) {
         System.out.println(tmp);
         tmp = tmp.next;
      }

      head = mergeSort(head);

      System.out.println("Sorted array");
      tmp = head;
      while (tmp != null) {
         System.out.println(tmp);
         tmp = tmp.next;
      }
   }
}
