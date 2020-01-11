package models;

import java.util.ArrayList;

import models.decorators.PointCounter;
import models.exceptions.PushException;
import models.exceptions.UserExistsException;
import models.interfaces.IPanel;

public class GoGame {
   protected int idPlayer1;
   protected IPanel panel;

   public GoGame(int idPlayer1, IPanel panel) {
      this.idPlayer1 = idPlayer1;
      this.panel = panel;
   }

   public void push(int idUser, int x, int y) throws PushException {
      if (x >= 0 && x <= this.panel.getSize()) {
         if (y >= 0 && y <= this.panel.getSize()) {
            int userIdx = idUser == this.idPlayer1 ? 1 : 2;
            this.panel.checkIfValid(x, y, userIdx);
            this.panel.push(x, y, userIdx);
         } else {
            throw new PushException("y: " + y);
         }
      } else {
         throw new PushException("x: " + x);
      }
   }

   public void setOpponent(int idPlayer2) throws UserExistsException {
      if (this.idPlayer1 == idPlayer2) {
         throw new UserExistsException("User " + this.idPlayer1 + "actually is in the game!");
      }
   }

   public int[][] getGameStatus() {
      return this.panel.getPositions();
   }

   public int[] getScores() {
      return new PointCounter().count(getGameStatus());
   }
}
