package models.factories.ConcreteFactory;

import models.factories.PanelRules;
import models.interfaces.IPanel;
import models.structures.Union;

public class PanelLarge  extends PanelRules {
   @Override
   public void setSize() {
      super.board = new Union[19][19];
   }

   @Override
   public int getSize() {
      return 19;
   }

   @Override
   public IPanel getNewInstance() {
      return new PanelLarge();
   }
}
