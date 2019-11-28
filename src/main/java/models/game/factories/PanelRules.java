package models.game.factories;

import models.game.exceptions.PushException;
import models.game.interfaces.LambdaFunctionVoid;

import java.util.ArrayList;

public abstract class PanelRules extends Panel {
   private ArrayList<int[]> vectors = new ArrayList<>();

   PanelRules() {
      vectors.add(new int[]{-1, 0});
      vectors.add(new int[]{0, 1});
      vectors.add(new int[]{1, 0});
      vectors.add(new int[]{0, -1});
   }

   @Override
   public Boolean isValid(int x, int y, int playerIndex) {
      if(board[y][x].getValue() != 0) {
         return false;
      }
      if(board[y][x].getBreaths() == 0) {
         return checkIfSurrounded(x, y, playerIndex);
      }
      return true;
   }

   @Override
   public void push(int x, int y, int playerIndex) throws PushException {
      if(playerIndex < 1 || playerIndex > 2) {
         throw new PushException();
      }
      board[y][x].setValue(playerIndex);

      refreshBreaths(x, y);
      unify(x, y);
   }

   private void refreshBreaths(int X, int Y) {
      forEachNeighbour(X, Y, (int x, int y, int idX, int idY) -> board[idY][idX].substrateBreath());
   }

   private void unify(int X, int Y) {
      forEachNeighbour(X, Y, (int x, int y, int idX, int idY) -> {
         if(board[idY][idX].getValue() != 0 &&
           board[idY][idX].getValue() == board[y][x].getValue())
            board[idY][idX].union(board[y][x]);
      });
   }

   private Boolean checkIfSurrounded(int x, int y, int playerIndex) {
      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX < 0 || idY < 0 || idX >= board.length || idY >= board.length) {
            continue;
         }
         if(board[idY][idX].getValue() != playerIndex)
            return false;
      }
      return true;
   }

   private void forEachNeighbour(int x, int y, LambdaFunctionVoid operation) {
      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX < 0 || idY < 0 || idX >= board.length || idY >= board.length) {
            continue;
         }
         operation.start(x, y, idX, idY);
      }
   }
}
