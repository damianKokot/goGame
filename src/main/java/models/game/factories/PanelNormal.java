package models.game.factories;

import models.structures.Union;

public class PanelNormal extends PanelRules {

   @Override
   public void setSize() {
      super.board = new Union[13][13];
   }
}
