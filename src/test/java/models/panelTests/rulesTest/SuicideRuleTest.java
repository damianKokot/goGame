package models.panelTests.rulesTest;

import models.GoGame;
import models.exceptions.PushException;
import models.exceptions.UserExistsException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class SuicideRuleTest {
   public SuicideRuleTest() {
   }

   @Test
   public void shouldThrowError() {
      GoGame game = new GoGame(123, new PanelNormal());

      try {
         game.setOpponent(456);
      } catch (UserExistsException e) {
         e.printStackTrace();
      }

      try {
         game.push(123, 1, 0);
         game.push(123, 0, 1);
         game.push(456, 0, 0);
         Assert.fail();
      } catch (PushException e) {
         Assert.assertEquals(e.getMessage(), "Suicide move");
      }

   }

   @Test
   public void shouldNotThrowError() {
      GoGame game = new GoGame(123, new PanelNormal());

      try {
         game.setOpponent(456);
      } catch (UserExistsException var4) {
         var4.printStackTrace();
      }

      try {
         game.push(123, 1, 0);
         game.push(123, 0, 1);
         game.push(456, 0, 2);
         game.push(456, 1, 1);
         game.push(456, 2, 0);
         game.push(456, 0, 0);
      } catch (PushException e) {
         Assert.fail();
      }

   }

   @Test
   public void shouldThrowErrorGroup() {
      GoGame game = new GoGame(123, new PanelNormal());

      try {
         game.setOpponent(456);
      } catch (UserExistsException e) {
         e.printStackTrace();
      }

      try {
         game.push(123, 1, 1);
         game.push(456, 0, 0);
         game.push(456, 1, 0);
         game.push(456, 2, 1);
         game.push(456, 1, 2);
         game.push(456, 0, 2);
         game.push(123, 0, 1);
         Assert.fail();
      } catch (PushException e) {
         Assert.assertEquals(e.getMessage(), "Suicide move");
      }

   }
}
