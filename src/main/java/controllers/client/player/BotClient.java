package controllers.client.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;

public class BotClient extends PlayerClient {
   private int[][] coordinates;

   public BotClient() {
      connectToServer();
   }

   @Override
   protected void gameUpdate(JsonObject command) {
      try {
         sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      Gson gson = new Gson();
      int[][] arr = gson.fromJson(command.get("plane").getAsString(), int[][].class);

      boolean opponentPassed = true;
      for (int i = 0; i < plane.length; i++) {
         for (int j = 0; j < plane.length; j++) {
            if (arr[i][j] != plane[i][j]) {
               opponentPassed = false;
               break;
            }
         }
      }
      if(opponentPassed) {
         skipRound();
      }


      ArrayList<int[]> path = reachablePath(arr, coordinates);
      while (coordinates == null || path == null) {
         coordinates = getRandomPoints();
         path = reachablePath(arr, coordinates);
      }

      boolean moveMade = false;
      for(int[] point:path) {
         if(plane[point[1]][point[0]] == 0) {
            moveMade = true;
            doMove(point[0], point[1]);
         }
      }
      if(!moveMade) {
         coordinates = getRandomPoints();
         gameUpdate(command);
      }

      this.plane = arr;
   }

   protected ArrayList<int[]> reachablePath(int[][] panel, int[][] coordinates) {
      int[][] visited = new int[panel.length][panel.length];
      int[][] vectors = {{0,1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

      if(panel[coordinates[0][1]][coordinates[0][0]] == 1) {
         return null;
      }

      Queue<int[]> queue = new LinkedList<>();
      queue.add(coordinates[0]);
      int counter = 2;
      visited[coordinates[0][1]][coordinates[0][0]] = 1;

      while(!queue.isEmpty()) {
         for (int[] vector: vectors) {
            assert queue.peek() != null;
            int x = queue.peek()[0] + vector[0];
            int y = queue.peek()[1] + vector[1];

            if(x < 0 || x >= panel.length || y < 0 || y >= panel.length) {
               continue;
            }

            if(visited[y][x] == 0 && panel[y][x] != 1) {
               queue.add(new int[]{x, y});
               visited[y][x] = counter;
            }
         }
         counter++;

         queue.remove();
      }

      if(visited[coordinates[1][1]][coordinates[1][0]] != 0 ) {
         ArrayList<int[]> backtrace = new ArrayList<>();
         int x = coordinates[1][0];
         int y = coordinates[1][1];
         backtrace.add(new int[]{x, y});
         while (x != coordinates[0][0] || y != coordinates[0][1]) {
            int smallestNeighbourX = x;
            int smallestNeighbourY = y;
            for (int[] vector:vectors) {
               int neighbourX = x + vector[0];
               int neighbourY = y + vector[1];

               if(neighbourX < 0 || neighbourX >= panel.length || neighbourY < 0 || neighbourY >= panel.length) {
                  continue;
               }

               if(visited[neighbourY][neighbourX] != 0 &&
                 visited[neighbourY][neighbourX] < visited[smallestNeighbourY][smallestNeighbourX]) {
                  smallestNeighbourX = neighbourX;
                  smallestNeighbourY = neighbourY;
               }
            }
            x = smallestNeighbourX;
            y = smallestNeighbourY;

            backtrace.add(new int[]{x, y});
         }

         return backtrace;
      } else {
         return null;
      }
   }

   private int[][] getRandomPoints() {
      Random random = new Random();

      int[][] coordinates = new int[2][2];
      for (int i = 0; i < 2; i++) {
         coordinates[i][0] = random.nextInt(plane.length);
         coordinates[i][1] = random.nextInt(plane.length);
      }

      return coordinates;
   }





   @Override
   public void refresh(int x, int y, int value) {

   }

   @Override
   public void setMessage(String message) {

   }

   @Override
   public void nextStage(int value) {

   }

   @Override
   public void serverStatus(boolean state) {

   }
}
