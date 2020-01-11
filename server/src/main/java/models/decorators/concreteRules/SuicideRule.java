package models.decorators.concreteRules;

import models.exceptions.PushException;
import models.factories.PanelRules;
import models.interfaces.IPanel;
import models.decorators.RulesChecker;

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
