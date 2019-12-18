package models;

import controllers.client.player.BotClient;
import org.junit.Test;

import java.util.ArrayList;

public class BotTest extends BotClient {
   @Test
   public void shouldCheckIfPathReachable() {
      int[][] panel = new int[][]{
         {0, 0, 1, 0, 0, 0, 0},
         {1, 0, 1, 0, 1, 1, 0},
         {1, 0, 1, 0, 1, 0, 1},
         {0, 1, 1, 0, 1, 1, 0},
         {1, 0, 1, 0, 1, 0, 1},
         {0, 1, 1, 0, 1, 1, 0},
         {0, 0, 0, 0, 1, 0, 0}
      };

      int[][] coordinates = new int[][]{{0, 0}, {6, 6}};

      ArrayList<int[]> points = reachablePath(panel, coordinates);

      for (int[] point: points) {
         System.out.println(point[0] + ", " + point[1]);
      }
   }

}
