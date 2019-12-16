package models.game.decorators.concreteRules;

import models.game.exceptions.PushException;
import models.game.factories.PanelRules;
import models.game.decorators.RulesChecker;

public class OnTheOtherRule extends RulesChecker {
   public OnTheOtherRule(PanelRules rules) {
      super(rules);
   }

   public void check(int x, int y, int playerIndex) throws PushException {
      int[][] positions = panel.getPositions();
      if (positions[y][x] != 0) {
         throw new PushException("There is someone else rock in this place");
      }
   }
}
