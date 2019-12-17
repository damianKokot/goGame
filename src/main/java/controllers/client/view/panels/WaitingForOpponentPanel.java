package controllers.client.view.panels;

import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.panels_parts.PanelState;
import controllers.client.view.panels.boards.Board_13x13;
import controllers.client.view.panels.boards.Board_19x19;
import controllers.client.view.panels.boards.Board_9x9;

import javax.swing.*;
import java.awt.*;

public class WaitingForOpponentPanel extends JPanel implements PanelState {
   private PlayerPanel panel;

   public WaitingForOpponentPanel(PlayerPanel panel) {
      this.panel = panel;
      setLayout(new BorderLayout());
      JLabel label = new JLabel("Wait for server...");

      add(label, BorderLayout.CENTER);
   }

   @Override
   public PanelState next(int value) {
	   
	   System.out.println(value);
	   
      if (value == 9) {
         return new Board_9x9(panel);
      } else if(value == 13) {
         return new Board_13x13(panel);
      } else {
         return new Board_19x19(panel);
      }
   }

   @Override
   public void refresh(int x, int y, int playerId) {

   }
   
   @Override
   public JPanel getPanel() {
   	// TODO Auto-generated method stub
   	return this;
   }
}
