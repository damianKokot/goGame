package models.structures;

import org.junit.Test;

import java.util.Random;

public class UnionTestObject extends Union {
   private static Random random = new Random();

   public static Union[] getUnions(int count) {
      Union[] unions = new Union[count];

      for(int i = 0; i < count; ++i) {
         unions[i] = new Union();
      }

      return unions;
   }

   public static int[] createRandomConnections(int count) {
      int[] connections = new int[count];

      int i;
      for(i = 0; i < count; connections[i] = i++) {
      }

      for(i = 1; i < count; ++i) {
         connections[i] = random.nextInt(i);
      }

      return connections;
   }

   public static int countChildes(Union union) {
      return union.edges.size();
   }
}
