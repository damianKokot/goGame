package models;

import models.game.GoGame;
import models.game.exceptions.UserExistsException;
import models.game.factories.PanelNormal;
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
