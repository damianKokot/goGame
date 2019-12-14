package models;

import models.exceptions.UserExistsException;
import models.exceptions.PushException;
import models.interfaces.IPanel;

public class GoGame {
   private int idPlayer1;  //Black
   private int idPlayer2;  //White
   private IPanel panel;

   public GoGame(int idPlayer1, IPanel panel) {
      this.idPlayer1 = idPlayer1;
      this.panel = panel;
   }

   public void setOpponent(int idPlayer2) throws UserExistsException {

   }

   public void push(int idUser, int x, int y) throws PushException {
	   System.out.println(idUser+" "+x+" "+y);
   }

   public int[][] getGameStatus() {
	   
	   int[][] tab= new int[][] {
		   {2,1,3,7},
		   {6,9,6,9},
		   {2,1,3,7},
		   {6,9,6,9}
	   };
	   
      //return panel.getPositions();
	   return tab;
   }

   public int[] getUsers() {
      return new int[]{idPlayer1, idPlayer2};
   }

   public int[] getScores() {
      return new int[]{0, 0};
   }
}
