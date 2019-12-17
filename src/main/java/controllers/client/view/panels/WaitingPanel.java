package controllers.client.view.panels;

import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.panels_parts.PanelState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingPanel extends JPanel implements PanelState {
   private PlayerPanel panel;

   public WaitingPanel(PlayerPanel panel) {
      this.panel = panel;
      setLayout(new BorderLayout());
      JLabel label = new JLabel("Wait for server...");
      JButton button = new JButton("Connect to game");
      
      button.addActionListener(getActionListener());
      
      add(label, BorderLayout.CENTER);
     // add(button);
   }
   
   private ActionListener getActionListener() {
	      return new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent actionEvent) {
	           
	         }
	      };
   }
   @Override
   public PanelState next(int value) {
      if(value == 1) {
         return new ChoseSizePanel(this.panel);
      } else {
         return new WaitingForOpponentPanel(this.panel);
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
