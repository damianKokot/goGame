package controllers.client.view.panels;

import controllers.client.player.PlayerClient;
import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.boards.Board_13x13;
import controllers.client.view.panels.boards.Board_19x19;
import controllers.client.view.panels.boards.Board_9x9;
import controllers.client.view.panels.panels_parts.PanelState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseSizePanel extends JPanel implements PanelState {
   private PlayerPanel playerPanel;

   public ChoseSizePanel(PlayerPanel panel) {
      this.playerPanel = panel;

      JButton small = new JButton("9x9");
      JButton medium = new JButton("13x13");
      JButton big = new JButton("19x19");

      small.addActionListener(getActionListener(9));
      medium.addActionListener(getActionListener(13));
      big.addActionListener(getActionListener(19));

      add(small);
      add(medium);
      add(big);

      //TODO Set message to "Chose panel size"
   }

   @Override
   public PanelState next(int value) {
      if (value == 9) {
         return new Board_9x9(playerPanel);
      } else if(value == 13) {
         return new Board_13x13(playerPanel);
      } else {
         return new Board_19x19(playerPanel);
      }
   }

   @Override
   public void refresh(int x, int y, int playerId) {

   }

   private ActionListener getActionListener(final int value) {
      return new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            playerPanel.setPlane(value);
            System.out.println("Button value: " + value);
         }
      };
   }
   
   @Override
   public JPanel getPanel() {
   	// TODO Auto-generated method stub
   	return this;
   }
}
