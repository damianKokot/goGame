package models.factories.ConcreteFactory;

import models.factories.PanelRules;
import models.interfaces.IPanel;
import models.structures.Union;

public class PanelNormal extends PanelRules {

   @Override
   public void setSize() {
      super.board = new Union[13][13];
   }

   @Override
   public int getSize() {
      return 13;
   }

   @Override
   public IPanel getNewInstance() {
      return new PanelNormal();
   }
}
