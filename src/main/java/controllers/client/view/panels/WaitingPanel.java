package controllers.client.view.panels;

import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.panels_parts.PanelState;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingPanel extends JPanel implements PanelState {
   private PlayerPanel panel;

   public WaitingPanel(PlayerPanel panel) {
      this.panel = panel;
      this.setLayout(new FlowLayout(FlowLayout.CENTER));

      add(getContent());
   }

   private JPanel getContent() {
      JPanel container = new JPanel(new BorderLayout());

      JLabel label = new JLabel("Wait for server...");
      JButton button = new JButton("Multiplayer");
      JButton botButton = new JButton("Singleplayer");
      button.addActionListener(getActionListener());
      botButton.addActionListener(getActionBotListener());
      
      container.add(label, BorderLayout.NORTH);
      container.add(botButton);
      container.add(button, BorderLayout.SOUTH);
      
      return container;
   }

   private ActionListener getActionListener() {
	      return new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent actionEvent) {
	            panel.connectToServer();
	         }
	      };
   }
   
   private ActionListener getActionBotListener() {
	      return new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent actionEvent) {
	            panel.connectBotToServer();
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
   public JPanel getPanel() {
   	return this;
   }

   @Override
   public void refresh(int x, int y, int playerId) {

   }
}
