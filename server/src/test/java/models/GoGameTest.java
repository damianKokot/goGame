package models;

import models.exceptions.UserExistsException;
import models.interfaces.IPanel;

public class GoGameTest extends GoGame {
   public GoGameTest(int idPlayer1, IPanel panel) {
      super(idPlayer1, panel);
   }

   public int[][] getStatusCopiedArray() {
      return this.createSubGame().getGameStatus();
   }

   protected GoGame createSubGame() {
      GoGame subGame = new GoGame(1, panel.copy());

      try {
         subGame.setOpponent(2);
      } catch (UserExistsException e) {
         e.printStackTrace();
      }

      return subGame;
   }
}
