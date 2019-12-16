package models.game.decorators.concreteRules;

import models.game.exceptions.PushException;
import models.game.factories.PanelRules;
import models.game.interfaces.IPanel;
import models.game.decorators.RulesChecker;

public class SuicideRule extends RulesChecker {
   public SuicideRule(PanelRules rules) {
      super(rules);
   }

   public void check(int x, int y, int playerIndex) throws PushException {
      IPanel subPanel = panel.copy();
      subPanel.push(x, y, playerIndex);

      for (String log : subPanel.getLogs()) {
         if(log.contains("deleted " + x + " " + y)) {
            throw new PushException("Suicide move");
         }
      }
   }
}
