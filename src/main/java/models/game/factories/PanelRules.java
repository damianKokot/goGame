package models.game.factories;

import java.util.ArrayList;
import java.util.Iterator;
import models.game.exceptions.PushException;
import models.game.rulesDecorators.RulesChecker;
import models.game.rulesDecorators.concreteRules.KoRule;
import models.game.rulesDecorators.concreteRules.OnTheOtherRule;
import models.game.rulesDecorators.concreteRules.SuicideRule;
import models.structures.Union;

public abstract class PanelRules extends Panel {
   private ArrayList<RulesChecker> rules = new ArrayList<>();

   public PanelRules() {
      rules.add(new OnTheOtherRule(this));
      rules.add(new SuicideRule(this));
      rules.add(new KoRule(this));
   }

   @Override
   public void checkIfValid(int x, int y, int playerIndex) throws PushException {
      for(RulesChecker rule : rules) {
         rule.check(x, y, playerIndex);
      }
   }

   @Override
   public void push(int x, int y, int playerIndex) throws PushException {
      if (playerIndex >= 1 && playerIndex <= 2) {
         resetLogs();
         board[y][x].setValue(playerIndex);

         unify(x, y);
         refreshBreaths(x, y, false);
         killNeighbours(x, y);
      } else {
         throw new PushException("Invalid username: " + playerIndex);
      }
   }

   private void refreshBreaths(int X, int Y, boolean lastStep) {
      int counter = 0;

      for (Union neighbour : board[Y][X].getSet()) {
         counter += countNeighbours(neighbour.x, neighbour.y, 0);
      }
      board[Y][X].setBreaths(counter);

      if (!lastStep) {
         for (Union neighbour : getNeighbours(X, Y)) {
            if (neighbour.getValue() != board[Y][X].getValue()) {
               refreshBreaths(neighbour.x, neighbour.y, true);
            }
         }
      }
   }

   private void unify(int X, int Y) {
      int x, y;
      for (Union neighbour : getNeighbours(X, Y)) {
         x = neighbour.x;
         y = neighbour.y;

         if (board[y][x].getValue() != 0 && board[y][x].getValue() == board[Y][X].getValue()) {
            board[y][x].union(board[Y][X]);
         }
      }
   }

   private void killNeighbours(int X, int Y) {
      if (board[Y][X].getValue() != 0) {
         ArrayList<Union> neighbours = getNeighbours(X, Y);
         neighbours.add(board[Y][X]);

         for (Union neighbour : neighbours) {
            int x = neighbour.x;
            int y = neighbour.y;

            if (board[y][x].getValue() != 0 && board[y][x].getBreaths() == 0) {
               kill(x, y, X, Y);
            }
         }
      }
   }

   private void kill(int X, int Y, int killX, int killY) {
      ArrayList<Union> group = board[Y][X].getSet();

      for (Union member : group) {
         member.resetParameters();
      }
      for (Union member : group) {
         addLog("deleted " + member.x + " " + member.y + " with " + killX + " " + killY);
         refreshBreaths(member.x, member.y, false);
      }
   }
}
