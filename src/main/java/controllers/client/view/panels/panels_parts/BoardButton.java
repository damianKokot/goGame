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
      setPreferredSize(new Dimension(10, 10));
      setBackground(Color.LIGHT_GRAY);
   }

   public void changeMember(int occupied) {
      System.out.println("x: " + x + " y: " + y + " o: " + occupied);
      if(occupied == 0) {
         setBackground(Color.lightGray);
      } else if(occupied == 1) {
         setBackground(Color.WHITE);
      } else if(occupied == 2){
         setBackground(Color.BLACK);
      }
   }

   private void setActionListener() {
      addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            player.doMove(x, y);
         }
      });
   }
}
