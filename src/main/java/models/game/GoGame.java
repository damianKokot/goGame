package models.game;

import java.util.ArrayList;
import models.game.exceptions.PushException;
import models.game.exceptions.UserExistsException;
import models.game.interfaces.IPanel;

public class GoGame {
   private int idPlayer1;
   private int idPlayer2;
   private IPanel panel;
   private ArrayList<String> history;

   public GoGame(int idPlayer1, IPanel panel) {
      this.idPlayer1 = idPlayer1;
      this.panel = panel;
      this.history = new ArrayList();
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
      } else {
         this.idPlayer2 = idPlayer2;
      }
   }

   public int[][] getGameStatus() {
      return this.panel.getPositions();
   }

   public int[][] getGameBreaths() {
      return this.panel.getBreaths();
   }

   public int[] getUsers() {
      return new int[]{this.idPlayer1, this.idPlayer2};
   }

   public int[] getScores() {
      return new int[]{0, 0};
   }

   protected GoGame createSubGame() {
      GoGame subGame = new GoGame(this.idPlayer1, this.panel.copy());

      try {
         subGame.setOpponent(this.idPlayer2);
      } catch (UserExistsException var3) {
         var3.printStackTrace();
      }

      return subGame;
   }
}
