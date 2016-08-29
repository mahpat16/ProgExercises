import java.util.*;


public class QuickSort {
   static void swap(int[] arr,
                    int ndx1,
                    int ndx2)
   {
      int tmp = arr[ndx1];
      arr[ndx1] = arr[ndx2];
      arr[ndx2] = tmp;
   }

   static int partition(int[] arr,
                        int begin,
                        int end)
   {
      int left = begin;
      int right = end;
      int mid = (begin + end)/2;
      int pivot = arr[mid];
      swap(arr, left, mid);
      left = left + 1;

      while (left <= right) {
         while (arr[right] > pivot) {
            right--;
         }

         while (left <= right && arr[left] <= pivot) {
            left++;
         }

         if (left <= right) {
            swap(arr, left, right);
            left++;
            right--;
         }
      }

      swap(arr, begin, right);
      return right;
   }

   static void quickSort(int[] arr,
                        int begin,
                        int end)
   {
      if (begin > end) {
         return;
      }
      int pivot = partition(arr, begin, end);
      quickSort(arr, begin, pivot - 1);
      quickSort(arr, pivot + 1, end);
   }

   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the unsorted space separated array");
      String[] s = in.nextLine().trim().split(" ");
      int[] arr = new int[s.length];
      for (int i = 0; i < s.length; i++) {
         arr[i] = new Integer(s[i]);
      }
      System.out.println("Unsorted array: " + Arrays.toString(arr));
      quickSort(arr, 0, arr.length - 1);
      System.out.println("Sorted array: " + Arrays.toString(arr));
   }
}

