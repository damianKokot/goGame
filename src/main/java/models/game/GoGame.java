package models.game;

import models.game.exceptions.ForbiddenMoveException;
import models.game.exceptions.PushException;
import models.game.exceptions.UserExistsException;
import models.game.interfaces.IPanel;

import java.util.ArrayList;

public class GoGame {
   private int idPlayer1;  //Black
   private int idPlayer2;  //White
   private int turn;
   private IPanel panel;
   private String message;

   private ArrayList<String> history;


   public GoGame(int idPlayer1, IPanel panel) {
      this.idPlayer1 = idPlayer1;
      this.panel = panel;
      history = new ArrayList<>();
      message = null;
   }

   public void push(int idUser, int x, int y) throws PushException, ForbiddenMoveException {
      if(x < 0 || x > panel.getSize())
         throw new ForbiddenMoveException("x: " + x);
      if(y < 0 || y > panel.getSize()) {
         throw new ForbiddenMoveException("y: " + y);
      }

      if(!panel.isValid(x, y, turn == idPlayer1? 1 : 2) && !KoRule())
         throw new ForbiddenMoveException("You can't leave that here");
      panel.push(x, y, ( turn == idPlayer1? 1 : 2));
   }

   public void setOpponent(int idPlayer2) throws UserExistsException {
      if(idPlayer1 == idPlayer2) {
         throw new UserExistsException("You have chosen yourself");
      }
      this.idPlayer2 = idPlayer2;
   }

   public int[][] getGameStatus() {
      return panel.getPositions();
   }

   private int getUserId() {

   }

   protected GoGame createSubGame() {
      int[][] status = panel.getPositions();

      GoGame subGame = new GoGame(idPlayer1, panel.getInstance());

      for (int i = 0; i < status.length; i++) {
         for (int j = 0; j < status.length; j++) {
            if(status[i][j] != 0) {
               try {
                  subGame.push(status[i][j], j, i);
               } catch (PushException | ForbiddenMoveException e) {
                  e.printStackTrace();
               }
            }
         }
      }
      return subGame;
   }


   private boolean KoRule() {
      //TODO: Implement
      return false;
   }

   void addLog(String message) {
      history.add(message);
   }
}
