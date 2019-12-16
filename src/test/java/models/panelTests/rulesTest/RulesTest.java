package models.panelTests.rulesTest;

import java.util.Random;
import models.GoGameTest;
import models.GoGame;
import models.exceptions.PushException;
import models.exceptions.UserExistsException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class RulesTest {
   public RulesTest() {
   }

   @Test
   public void shouldViolateKoRule() {
      GoGame game = new GoGame(123, new PanelNormal());

      try {
         game.setOpponent(456);
      } catch (UserExistsException e) {
         e.printStackTrace();
      }

      try {
         game.push(123, 1, 0);
         game.push(123, 0, 1);
         game.push(123, 2, 1);
         game.push(456, 0, 2);
         game.push(456, 2, 2);
         game.push(456, 1, 3);
         game.push(456, 1, 1);
         game.push(123, 1, 2);
      } catch (PushException e) {
         Assert.assertEquals(e.getMessage(), "KO rule violated");
      }

   }

   @Test
   public void shouldCheckPanelCopy() {
      for(int i = 0; i < 1; ++i) {
         GoGameTest game = this.createRandomGame();
         int[][] panel1 = game.getGameStatus();
         int[][] panel2 = game.getStatusCopiedArray();
         if (!this.compareArrays(panel1, panel2)) {
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
      } catch (UserExistsException var11) {
         var11.printStackTrace();
      }

      for(int i = 0; i < 100; ++i) {
         int x = rand.nextInt(13);
         int y = rand.nextInt(13);
         int userId = rand.nextInt() % 2 == 0 ? p1 : p2;

         try {
            game.push(userId, x, y);
         } catch (PushException ignored) {
         }
      }

      return game;
   }

   private boolean compareArrays(int[][] arr1, int[][] arr2) {
      if (arr1.length != arr2.length) {
         return false;
      } else {
         for(int i = 0; i < arr1.length; ++i) {
            for(int j = 0; j < arr1[i].length; ++j) {
               if (arr1[i][j] != arr2[i][j]) {
                  System.out.println("Error for:\nx = " + j + ",\ny = " + i + "\n");
                  return false;
               }
            }
         }

         return true;
      }
   }
}
