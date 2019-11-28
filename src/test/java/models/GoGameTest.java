package models;

import models.game.GoGame;
import models.game.interfaces.IPanel;

public class GoGameTest extends GoGame {
   public GoGameTest(int idPlayer1, IPanel panel) {
      super(idPlayer1, panel);
   }

   public int[][] getStatusCopiedArray() {
      return createSubGame().getGameStatus();
   }
}
