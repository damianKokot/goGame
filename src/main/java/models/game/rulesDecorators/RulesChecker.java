package models.game.rulesDecorators;

import models.game.factories.PanelRules;
import models.game.interfaces.ICheckable;

public abstract class RulesChecker implements ICheckable {
   protected PanelRules panel;

   protected RulesChecker(PanelRules panel) {
      this.panel = panel;
   }
}
