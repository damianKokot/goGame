package models.game;

import java.util.ArrayList;
import models.game.exceptions.PushException;
import models.game.exceptions.UserExistsException;
import models.game.interfaces.IPanel;

public class GoGame {
   private int idPlayer1;
   private int idPlayer2;
   private IPanel panel;

   public GoGame(int idPlayer1, IPanel panel) {
      this.idPlayer1 = idPlayer1;
      this.panel = panel;
   }

   public void push(int idUser, int x, int y) throws PushException {
      if (x < 0 || x >= panel.getSize()) {
         throw new PushException("x: " + x);
      }
      if (y < 0 || y >= panel.getSize()) {
         throw new PushException("y: " + y);
      }

      int userIdx = idUser == idPlayer1? 1 : 2;

      panel.checkIfValid(x, y, userIdx);
      panel.push(x, y, userIdx);
   }

   public void setOpponent(int idPlayer2) throws UserExistsException {
      if (idPlayer1 == idPlayer2) {
         throw new UserExistsException("User " + idPlayer1 + "actually is in the game!");
      } else {
         this.idPlayer2 = idPlayer2;
      }
   }

   public int[][] getGameStatus() {
      return this.panel.getPositions();
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