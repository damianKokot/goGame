package models.decorators;

import java.util.LinkedList;
import java.util.Queue;

public class PointCounter {
   private int[][] panel;
   private int[][] visited;

   public int[] count(int[][] panel) {
      this.panel = panel;
      visited = new int[panel.length][panel.length];

      int[] points = new int[]{0, 0};

      for (int i = 0; i < panel.length; i++) {
         for (int j = 0; j < panel.length; j++) {
            if(panel[i][j] == 0 && visited[i][j] == 0) {
               int[] territory = getTerritory(j, i);
               if (territory.length != 1) {
                  points[territory[1]-1] += territory[0];
               }
            }
         }
      }
      return points;
   }

   private int[] getTerritory(int x, int y) {
      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[]{x, y});
      visited[y][x] = 1;

      int counter = 1;
      boolean isWhite = false;
      boolean isBlack = false;


      int[][] vectors = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};
      while(!queue.isEmpty()) {
         for (int[] vector: vectors) {
            assert queue.peek() != null;
            int X = queue.peek()[0] + vector[0];
            int Y = queue.peek()[1] + vector[1];

            if(X < 0 || X >= panel.length || Y < 0 || Y >= panel.length) {
               continue;
            }

            if(visited[Y][X] == 0) {
               if(panel[Y][X] == 0) {
                  visited[Y][X] = 1;
                  counter++;
                  queue.add(new int[]{X, Y});
               } else if(panel[Y][X] == 1) {
                  isWhite = true;
               } else if(panel[Y][X] ==2) {
                  isBlack = true;
               }
            }
         }
         queue.remove();
      }
      if(isBlack == isWhite) {
         return new int[]{0};
      } else if(isWhite){
         return new int[]{counter, 1};
      } else {
         return new int[]{counter, 2};
      }
   }
}
