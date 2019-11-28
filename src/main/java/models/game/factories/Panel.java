package models.game.factories;

import models.game.interfaces.IPanel;
import models.structures.Union;

abstract class Panel implements IPanel {
   protected Union[][] board;

   protected Panel() {
      setSize();
      resetBoard();
   }

   @Override
   public int[][] getPositions() {
      int[][] out = new int[getSize()][getSize()];
      for (int i = 0; i < getSize(); i++) {
         for (int j = 0; j < getSize(); j++) {
            out[i][j] = board[i][j].getValue();
         }
      }
      return out;
   }

   public void resetBoard() {
      for(int i = 0; i < board.length; ++i) {
         for(int j = 0; j < board[i].length; ++j) {
            int conditionCounter = 0;

            if(i == 0 || i == board.length - 1){
               conditionCounter++;
            }
            if(j == 0 || j == board[i].length - 1) {
               conditionCounter++;
            }

            board[i][j] = new Union();
            if(conditionCounter == 0) {
               board[i][j].setBreaths(4);
            } else if(conditionCounter == 1) {
               board[i][j].setBreaths(3);
            } else if(conditionCounter == 2) {
               board[i][j].setBreaths(2);
            } else {
               throw new ExceptionInInitializerError("Wrong counter: " + conditionCounter);
            }
         }
      }
   }
}
