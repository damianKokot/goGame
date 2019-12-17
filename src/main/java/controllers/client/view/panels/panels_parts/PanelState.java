package controllers.client.view.panels.panels_parts;

import javax.swing.*;

public interface PanelState {
   PanelState next(int value);
   void refresh(int x, int y, int playerId);
   JPanel getPanel();
}
