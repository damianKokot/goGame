package controllers.client.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.*;

public class BotClient extends PlayerClient {
   private int[][] coordinates;
   String message;
   int updates = 0;

   public BotClient() {
      connectToServer();
   }

   @Override
   public void setMessage(String message) {
      if(message.equals("It is your move!")) {
         if(this.message == null) {
            doStrategy();
         }
         messageToServer(this.message);
         this.message = null;
      }
   }

   @Override
   public void doMove(int x, int y) {
      this.message = commander.makeMove(x, y).toString();
   }

   @Override
   protected void gameUpdate(JsonObject command) {
      updates++;
      Gson gson = new Gson();
      int[][] arr = gson.fromJson(command.get("plane").getAsString(), int[][].class);
      if (updates < 2) {
         plane = arr;
         return;
      }


      boolean opponentPassed = true;
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr.length; j++) {
            if (arr[i][j] != plane[i][j]) {
               opponentPassed = false;
               break;
            }
         }
      }
      if(opponentPassed) {
         skipRound();
         return;
      }

      ArrayList<int[]> path = reachablePath(arr, coordinates);
      while (coordinates == null || path == null) {
         coordinates = getRandomPoints();
         path = reachablePath(arr, coordinates);
      }

      boolean moveMade = false;
      for(int i = 0; i < path.size(); ++i) {
         if(plane[path.get(i)[1]][path.get(i)[0]] == 0) {
            moveMade = true;
            doMove(path.get(i)[0], path.get(i)[1]);
            break;
         }
      }
      if(!moveMade) {
         coordinates = getRandomPoints();
         gameUpdate(command);
      }

      this.plane = arr;
   }

   protected void doStrategy() {
      ArrayList<int[]> path = reachablePath(plane, coordinates);
      while (coordinates == null || path == null) {
         coordinates = getRandomPoints();
         path = reachablePath(plane, coordinates);
      }

      boolean moveMade = false;
      for(int i = 0; i < path.size(); ++i) {
         if(plane[path.get(i)[1]][path.get(i)[0]] == 0) {
            moveMade = true;
            doMove(path.get(i)[0], path.get(i)[1]);
            break;
         }
      }
      if(!moveMade) {
         coordinates = getRandomPoints();
         doStrategy();
      }
   }

   protected ArrayList<int[]> reachablePath(int[][] panel, int[][] coordinates) {
      int[][] visited = new int[panel.length][panel.length];
      int[][] vectors = {{0,1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};


      if(coordinates == null || panel[coordinates[0][1]][coordinates[0][0]] == 1) {
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
      int[] startOrEnd = new int[]{0, plane.length - 1};

      int axis = random.nextInt(2);
      coordinates[0][axis] = random.nextInt(plane.length);
      coordinates[0][Math.abs(axis - 1)] = startOrEnd[random.nextInt(2)];

      axis = random.nextInt(2);
      coordinates[1][axis] = random.nextInt(plane.length);
      coordinates[1][Math.abs(axis - 1)] = startOrEnd[random.nextInt(2)];


      return coordinates;
   }


   @Override
   public void refresh(int x, int y, int value) {

   }

   @Override
   public void nextStage(int value) {

   }

   @Override
   public void serverStatus(boolean state) {

   }
}
