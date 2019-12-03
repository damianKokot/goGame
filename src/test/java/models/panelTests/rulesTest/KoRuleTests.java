package models.panelTests.rulesTest;

import models.game.GoGame;
import models.game.exceptions.PushException;
import models.game.exceptions.UserExistsException;
import models.game.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class KoRuleTests {
   public KoRuleTests() {
   }

   @Test
   public void shouldViolateKoRule() {
      GoGame game = this.preparePositions();

      try {
         game.push(456, 1, 1);
         game.push(123, 1, 2);
         game.push(456, 1, 1);
         Assert.fail();
      } catch (PushException e) {
         Assert.assertEquals(e.getMessage(), "KO rule violated");
      }

   }

   @Test
   public void shouldNotViolateKoRule() {
      GoGame game = preparePositions();

      try {
         game.push(456, 1, 1);
         game.push(123, 1, 2);
         game.push(456, 2, 0);
         game.push(123, 1, 1);
      } catch (PushException e) {
         Assert.fail();
      }

   }

   private GoGame preparePositions() {
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

      } catch (PushException e) {
         e.printStackTrace();
      }

      return game;
   }
}
