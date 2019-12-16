package models.game.decorators.concreteRules;

import java.util.ArrayList;
import models.game.exceptions.PushException;
import models.game.factories.PanelRules;
import models.game.interfaces.IPanel;
import models.game.decorators.RulesChecker;

public class KoRule extends RulesChecker {
   public KoRule(PanelRules rules) {
      super(rules);
   }

   public void check(int x, int y, int playerIndex) throws PushException {
      IPanel subPanel = this.panel.copy();
      ArrayList<String> logs = this.panel.getLogs();
      if (logs.size() == 1) {
         String flippedLog = this.flipLog((String)logs.get(0));
         logs = this.getLogsFromPush(subPanel, x, y, playerIndex);
         if (logs.size() == 1) {
            if (((String)logs.get(0)).equals(flippedLog)) {
               throw new PushException("KO rule violated");
            }
         }
      }
   }

   private ArrayList<String> getLogsFromPush(IPanel subPanel, int x, int y, int playerIndex) {
      try {
         subPanel.push(x, y, playerIndex);
      } catch (PushException var6) {
         var6.printStackTrace();
      }

      return subPanel.getLogs();
   }

   private String flipLog(String log) {
      String[] args = log.split(" ");
      return "deleted " + args[4] + " " + args[5] + " with " + args[1] + " " + args[2];
   }
}
