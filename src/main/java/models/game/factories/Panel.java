package models.game.factories;

import models.game.exceptions.PushException;
import models.game.interfaces.IPanel;
import models.structures.Union;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Panel implements IPanel {
   protected Union[][] board;
   private ArrayList<String> logs = new ArrayList();
   private static ArrayList<int[]> vectors = new ArrayList();

   Panel() {
      setSize();
      resetBoard();
   }

   static {
      vectors.add(new int[]{-1, 0});
      vectors.add(new int[]{0, 1});
      vectors.add(new int[]{1, 0});
      vectors.add(new int[]{0, -1});
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

   //TODO: Delete before release
   @Override
   public int[][] getBreaths() {
      int[][] out = new int[this.getSize()][this.getSize()];

      for(int i = 0; i < this.getSize(); ++i) {
         for(int j = 0; j < this.getSize(); ++j) {
            out[i][j] = this.board[i][j].getBreaths();
         }
      }

      return out;
   }

   @Override
   public IPanel copy() {
      IPanel panel = getNewInstance();
      int[][] positions = this.getPositions();

      for(int i = 0; i < positions.length; ++i) {
         for(int j = 0; j < positions.length; ++j) {
            if (positions[i][j] != 0) {
               try {
                  panel.push(j, i, positions[i][j]);
               } catch (PushException e) {
                  e.printStackTrace();
               }
            }
         }
      }

      return panel;
   }

   @Override
   public ArrayList<String> getLogs() {
      return logs;
   }

   protected void addLog(String log) {
      logs.add(log);
   }

   public void resetLogs() {
      logs.clear();
   }

   protected int countNeighbours(int x, int y, int value) {
      int count = 0;
      for(int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX >= 0 && idY >= 0 && idX < board.length && idY < board.length &&
           board[idY][idX].getValue() == value) {
            count++;
         }
      }
      return count;
   }


   public void resetBoard() {
      for(int i = 0; i < board.length; ++i) {
         for(int j = 0; j < board[i].length; ++j) {
            board[i][j] = new Union();
            board[i][j].x = j;
            board[i][j].y = i;
         }
      }

      for(int i = 0; i < board.length; ++i) {
         for(int j = 0; j < board[i].length; ++j) {
            board[i][j].setBreaths(countNeighbours(i, j, 0));
         }
      }
   }
   protected ArrayList<Union> getNeighbours(int x, int y) {
      ArrayList<Union> neighbours = new ArrayList<>();
      for(int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];
         if (idX >= 0 && idY >= 0 && idX < board.length && idY < board.length) {
            neighbours.add(board[idY][idX]);
         }
      }
      return neighbours;
   }
}
