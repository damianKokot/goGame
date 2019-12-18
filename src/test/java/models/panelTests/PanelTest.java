package models.panelTests;

import models.GoGame;
import models.exceptions.PushException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class PanelTest extends PanelNormal {

   @Test
   public void shouldCreatePanelWithGoodBreaths() {
      int[][] breaths = getBreathsState();
      int[][] pattern = new int[][] {
        {2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2}
      };

      Assert.assertTrue(compareArrays(breaths, pattern));
   }

   @Test
   public void shouldMakeCorrectBreaths() {
      //printTestingCommands();
      try {
         push(6, 2, 1);
         push(0, 12, 1);
         push(1, 12, 1);
         push(2, 12, 1);
         push(3, 12, 1);
         push(4, 12, 1);
         push(5, 12, 1);

         push(2, 2, 1);
         push(1, 3, 1);
         push(2, 3, 1);
         push(3, 3, 1);
         push(2, 4, 1);
      } catch (PushException e) {
         e.printStackTrace();
      }

      int[][] breaths = getBreathsState();
      int[][] pattern = new int[][] {
        {2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2},
        {3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4, 4, 3},
        {3, 2, 12, 2, 4, 3, 4, 3, 4, 4, 4, 4, 3},
        {2, 12, 12, 12, 3, 4, 3, 4, 4, 4, 4, 4, 3},
        {3, 2, 12, 2, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
        {2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3},
        {7, 7, 7, 7, 7, 7, 2, 3, 3, 3, 3, 3, 2}
      };
      Assert.assertTrue(compareArrays(breaths, pattern));
   }

   private int[][] getBreathsState() {
      int[][] out = new int[board.length][board.length];
      for (int i = 0; i < board.length; i++) {
         for(int j = 0; j < board.length; ++j) {
            out[i][j] = board[i][j].getBreaths();
         }
      }
      return out;
   }


   private void printArray(int[][] arr) {
      System.out.print("{\n");
      for (int i = 0; i < arr.length; i++) {
         System.out.print("{");
         for (int j = 0; j < arr[i].length; j++) {
            System.out.print(arr[i][j]);
            if(j != arr.length - 1) {
               System.out.print(", ");
            }
         }
         System.out.print("}");
         if(i != arr.length - 1) {
            System.out.print(",\n");
         } else {
            System.out.print("\n");
         }

      }
      System.out.print("}\n");
   }

   private Boolean compareArrays(int[][] arr1, int[][] arr2) {
      if(arr1.length != arr2.length) {
         return false;
      }
      for (int i = 0; i < arr1.length; i++) {
         for (int j = 0; j < arr1[i].length; j++) {
            if(arr1[i][j] != arr2[i][j]) {
               System.out.println("Error for:\nx = " + j + ",\ny = " + i + "\n");
               return false;
            }
         }
      }
      return true;
   }
   @Test
   public void printTestingCommands() {
      int[][] pattern = new int[][] {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      };

      for (int i = 0; i < pattern.length; i++) {
         for (int j = 0; j < pattern.length; j++) {
            if(pattern[i][j] != 0) {
               try {
                  push(j, i, pattern[i][j]);
               } catch (PushException e) {
                  e.printStackTrace();
               }
               System.out.println("push(" + j + ", " + i + ", " + pattern[i][j] + ");");
            }
         }
      }
      printArray(getPositions());
      printArray(getBreathsState());

      resetBoard();
      Assert.assertTrue(true);
   }
}
