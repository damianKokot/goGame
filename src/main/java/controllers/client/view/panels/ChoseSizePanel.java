package controllers.client.view.panels;

import controllers.client.player.PlayerClient;
import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.boards.Board_13x13;
import controllers.client.view.panels.boards.Board_19x19;
import controllers.client.view.panels.boards.Board_9x9;
import controllers.client.view.panels.panels_parts.PanelState;
import controllers.client.view.panels.panels_parts.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseSizePanel extends JPanel implements PanelState {
   private PlayerPanel playerPanel;

   public ChoseSizePanel(PlayerPanel panel) {
      this.playerPanel = panel;

      JLabel label = new JLabel("Wybierz rozmiar planszy");
      JButton small = new JButton("9x9");
      JButton medium = new JButton("13x13");
      JButton big = new JButton("19x19");

      small.addActionListener(getActionListener(9));
      medium.addActionListener(getActionListener(13));
      big.addActionListener(getActionListener(19));

      Box box = new Box(BoxLayout.Y_AXIS);
      box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
      box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
      box.setPreferredSize(new Dimension(100, 140));
      box.add(Box.createVerticalGlue());

      JPanel actions = new JPanel(new GridLayout(4, 1));

      actions.add(label);
      actions.add(small);
      actions.add(medium);
      actions.add(big);

      box.add(actions);
      box.add(Box.createVerticalGlue());
      add(box, BorderLayout.CENTER);
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
   	return this;
   }

   @Override
   public void refresh(int x, int y, int playerId) {

   }
}
