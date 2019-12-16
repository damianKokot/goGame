package models.factories.ConcreteFactory;

import models.factories.PanelRules;
import models.interfaces.IPanel;
import models.structures.Union;

public class PanelSmall extends PanelRules {
   @Override
   public void setSize() {
      super.board = new Union[9][9];
   }

   @Override
   public int getSize() {
      return 9;
   }

   @Override
   public IPanel getNewInstance() {
      return new PanelSmall();
   }
}
