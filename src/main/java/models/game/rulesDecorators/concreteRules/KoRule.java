package models.game.rulesDecorators.concreteRules;

import models.game.exceptions.PushException;
import models.game.factories.PanelRules;
import models.game.interfaces.IPanel;
import models.game.rulesDecorators.RulesChecker;

import java.util.ArrayList;

public class KoRule extends RulesChecker {
   public KoRule(PanelRules rules) {
      super(rules);
   }

   public void check(int x, int y, int playerIndex) throws PushException {
      IPanel subPanel = panel.copy();
      ArrayList<String> logs = panel.getLogs();
      if (logs.size() != 1) {
         return;
      }

      String flippedLog = flipLog(logs.get(0));

      logs = getLogsFromPush(subPanel, x, y, playerIndex);
      if (logs.size() != 1) {
         return;
      }
      if (logs.get(0).equals(flippedLog)) {
         throw new PushException("KO rule violated");
      }
   }

   private ArrayList<String> getLogsFromPush(IPanel subPanel, int x, int y, int playerIndex) {
      try {
         subPanel.push(x, y, playerIndex);
      } catch (PushException e) {
         e.printStackTrace();
      }

      return subPanel.getLogs();
   }

   private String flipLog(String log) {
      String[] args = log.split(" ");
      return "deleted " + args[4] + " " + args[5] + " with " + args[1] + " " + args[2];
   }
}
