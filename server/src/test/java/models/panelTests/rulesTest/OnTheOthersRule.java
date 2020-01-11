package models.panelTests.rulesTest;

import models.GoGame;
import models.exceptions.PushException;
import models.exceptions.UserExistsException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class OnTheOthersRule {
   public OnTheOthersRule() {
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
         game.push(123, 0, 0);
         game.push(123, 0, 0);
         Assert.fail();
      } catch (PushException var3) {
         Assert.assertEquals(var3.getMessage(), "There is someone else rock in this place");
      }

   }
}
