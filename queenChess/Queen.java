import java.util.*;

public class Queen {
   static int numCells;

   private static void markBoard(Integer[][] board,
                                 int r,
                                 int c)
   {
      for (int i = 0; i < numCells; i++) {
         if (board[r][i] == 0) {
            board[r][i] = 1;
         }
      }

      for (int i = 0; i < numCells; i++) {
         if (board[i][c] == 0) {
            board[i][c] = 1;
         }
      }

      // Diag
      int dr = r + 1;
      int dc = c + 1;

      while (dr < numCells && dc < numCells) {
         if (board[dr][dc] == 0) {
            board[dr][dc] = 1;
         }
         dr++;
         dc++;
      }

      dr = r - 1;
      dc = c - 1;
      while (dr >= 0 && dc >= 0) {
         if (board[dr][dc] == 0) {
            board[dr][dc] = 1;
         }
         dr--;
         dc--;
      }

      dr = r + 1;
      dc = c - 1;
      while (dr < numCells && dc >= 0) {
         if (board[dr][dc] == 0) {
            board[dr][dc] = 1;
         }
         dr++;
         dc--;
      }

      dr = r - 1;
      dc = c + 1;
      while (dr >= 0 && dc < numCells) {
         if (board[dr][dc] == 0) {
            board[dr][dc] = 1;
         }
         dr--;
         dc++;
      }
   }

   public static Integer[][] deepCopyIntMatrix(Integer[][] input) {
      if (input == null)
        return null;
      Integer[][] result = new Integer[input.length][];
      for (int r = 0; r < input.length; r++) {
        result[r] = new Integer[input[r].length];
        for (int c = 0; c < input[r].length; c++) {
           result[r][c] = input[r][c];
        }
      }
      return result;
   }

   private static void printBoard(Integer[][] board) {
      System.out.println("=============");
      for (Integer[] arr: board) {
         System.out.println(Arrays.toString(arr));
      }
      System.out.println("=============");
   }

   private static void nextQueenPos(Integer[][] board,
                                    int curRow,
                                    int numQueens)
   {
      if (curRow >= numCells) {
         System.out.println("_____________" + numQueens);
         if (numQueens == numCells || true) {
            System.out.println("_____________");
            for (Integer[] arr: board) {
               System.out.println(Arrays.toString(arr));
            }
         }
         return;
      }

      for (int c = 0; c < numCells; c++) {
         if (board[curRow][c] == 0) {
            Integer[][] newBoard = deepCopyIntMatrix(board);
            newBoard[curRow][c] = 2;
            markBoard(newBoard, curRow, c);
            nextQueenPos(newBoard, curRow + 1, numQueens + 1);
         } else {
            //printBoard(board);
         }
      }
   }

   public static void main(String[] args)
   {
      ArrayList<Integer[][]> results;

      if (args.length != 1) {
         System.out.println("Enter the size of chess board");
         System.exit(1);
      }
      numCells = new Integer(args[0]);

      Integer[][] board = new Integer[numCells][numCells];

      int r, c;

      for (r = 0; r < numCells; r++) {
         for (c = 0; c < numCells; c++) {
            board[r][c] = 0;
         }
      }

      nextQueenPos(board, 0, 0);
   }
}
