package models.panelTests;

import models.GoGameTest;
import models.exceptions.PushException;
import models.exceptions.UserExistsException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class RulesTest {
   @Test
   public void shouldCheckPanelCopy() {

      for (int i = 0; i < 1000; i++) {
         GoGameTest game = createRandomGame();
         int[][] panel1 = game.getGameStatus();
         int[][] panel2 = game.getStatusCopiedArray();

         if(!compareArrays(panel1, panel2)) {
            Assert.fail();
         }
      }
      Assert.assertTrue(true);
   }

   private GoGameTest createRandomGame() {
      Random rand = new Random();
      int p1 = rand.nextInt();
      int p2 = rand.nextInt();

      GoGameTest game = new GoGameTest(p1, new PanelNormal());
      try {
         game.setOpponent(p2);
      } catch (UserExistsException e) {
         e.printStackTrace();
      }

      for (int i = 0; i < 100; i++) {
         int x = rand.nextInt(13);
         int y = rand.nextInt(13);
         int userId = (rand.nextInt() % 2 == 0)? p1 : p2;

         try {
            game.push(userId, x, y);
         } catch (PushException ignored) {

         }
      }
      return game;
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


   private void printArray(int[][] arr) {
      System.out.print("{\n");
      for (int i = 0; i < arr.length; i++) {
         System.out.print("{");
         for (int j = 0; j < arr[i].length; j++) {
            System.out.print(arr[i][j] + ", ");
         }
         System.out.print("}\n");
      }
      System.out.print("}\n");
   }
}
