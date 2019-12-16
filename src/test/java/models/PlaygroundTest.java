package models;

import models.exceptions.UserExistsException;
import models.factories.ConcreteFactory.PanelNormal;
import org.junit.Assert;
import org.junit.Test;

public class PlaygroundTest {
   @Test
   public void shouldThrowUserError() {
      int p1 = 112313;
      GoGame game = new GoGame(p1, new PanelNormal());
      try {
         game.setOpponent(p1);
         Assert.fail();
      } catch (UserExistsException e) {
         Assert.assertTrue(true);
      }
   }
}
