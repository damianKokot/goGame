package models.decorators;

import models.factories.PanelRules;
import models.interfaces.ICheckable;

public abstract class RulesChecker implements ICheckable {
   protected PanelRules panel;

   protected RulesChecker(PanelRules panel) {
      this.panel = panel;
   }
}
