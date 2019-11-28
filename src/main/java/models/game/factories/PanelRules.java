package models.game.factories;

import models.game.exceptions.PushException;

import java.util.ArrayList;

public abstract class PanelRules extends Panel {
   ArrayList<int[]> vectors = new ArrayList<>();

   PanelRules() {
      vectors.add(new int[]{-1, 0});
      vectors.add(new int[]{0, 1});
      vectors.add(new int[]{1, 0});
      vectors.add(new int[]{0, -1});
   }

   public Boolean isValid(int x, int y) {
      //TODO: implement
      return null;
   }

   public void push(int x, int y, int playerIndex) throws PushException {
      if(playerIndex < 1 || playerIndex > 2) {
         throw new PushException();
      }
      //try {
      board[y][x].setValue(playerIndex);
      //} catch (Out)

      refreshBreaths(x, y);
      unify(x, y);
   }

   private void refreshBreaths(int x, int y) {
      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX < 0 || idY < 0 || idX >= board.length || idY >= board.length) {
            continue;
         }
         board[idY][idX].substrateBreath();
      }
   }

   private void unify(int x, int y) {
      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX < 0 || idY < 0 || idX >= board.length || idY >= board.length) {
            continue;
         }
         if(board[idY][idX].getValue() != 0 &&
           board[idY][idX].getValue() == board[y][x].getValue()) {
            board[idY][idX].union(board[y][x]);
         }
      }
   }
}
