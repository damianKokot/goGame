package models.factories;

import java.util.ArrayList;

import models.exceptions.PushException;
import models.interfaces.IPanel;
import models.structures.Union;

abstract class Panel implements IPanel {
   protected Union[][] board;
   private ArrayList<String> logs = new ArrayList<>();
   private static ArrayList<int[]> vectors;

   Panel() {
      setSize();
      resetBoard();
   }

   static {
      vectors = new ArrayList<>();
      vectors.add(new int[]{-1, 0});
      vectors.add(new int[]{0, 1});
      vectors.add(new int[]{1, 0});
      vectors.add(new int[]{0, -1});
   }

   @Override
   public int[][] getPositions() {
      int[][] out = new int[getSize()][getSize()];

      for(int i = 0; i < getSize(); ++i) {
         for(int j = 0; j < getSize(); ++j) {
            out[i][j] = board[i][j].getValue();
         }
      }

      return out;
   }

   @Override
   public int[][] getBreaths() {
      int[][] out = new int[getSize()][getSize()];

      for(int i = 0; i < getSize(); ++i) {
         for(int j = 0; j < getSize(); ++j) {
            out[i][j] = board[i][j].getBreaths();
         }
      }

      return out;
   }

   @Override
   public IPanel copy() {
      IPanel panel = this.getNewInstance();
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
      return this.logs;
   }

   protected void addLog(String log) {
      this.logs.add(log);
   }

   public void resetLogs() {
      this.logs.clear();
   }

   protected int countNeighbours(int x, int y, int value) {
      int count = 0;
      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];

         if (idX >= 0 && idY >= 0 && idX < board.length && idY < board.length && board[idY][idX].getValue() == value) {
            ++count;
         }
      }

      return count;
   }

   protected void resetBoard() {
      int i;
      int j;
      for(i = 0; i < board.length; ++i) {
         for(j = 0; j < board[i].length; ++j) {
            board[i][j] = new Union();
            board[i][j].x = j;
            board[i][j].y = i;
         }
      }

      for(i = 0; i < board.length; ++i) {
         for(j = 0; j < board[i].length; ++j) {
            board[i][j].setBreaths(this.countNeighbours(i, j, 0));
         }
      }

   }

   ArrayList<Union> getNeighbours(int x, int y) {
      ArrayList<Union> neighbours = new ArrayList<>();

      for (int[] vector : vectors) {
         int idX = x + vector[0];
         int idY = y + vector[1];
         if (idX >= 0 && idY >= 0 && idX < board.length && idY < board.length) {
            neighbours.add(board[idY][idX]);
         }
      }

      return neighbours;
   }
}
