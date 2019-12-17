package controllers.client.view.panels.panels_parts;

import controllers.client.player.PlayerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardButton extends JButton {
   private int x;
   private int y;
   private PlayerClient player;

   public BoardButton(PlayerClient player, int x, int y) {
      this.x=x;
      this.y=y;
      this.player = player;
      setActionListener();
      setForeground(Color.GRAY);
      //setVisible(false);
   }

   public void changeMember(int occupied) {
      //setVisible(true);
      if(player.getMyId() == 1) {
         setForeground(Color.WHITE);
      } else {
         setForeground(Color.BLACK);
      }
   }

   private void setActionListener() {
      /*
      this.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseEntered(java.awt.event.MouseEvent evt) {
            setBackground(Color.GREEN);
         }

         public void mouseExited(java.awt.event.MouseEvent evt) {
            setBackground(Color.WHITE);
         }
      });
      */
      addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Clicked x:" + x + " y: "+ y);
            player.doMove(x, y);
         }
      });
   }
}
