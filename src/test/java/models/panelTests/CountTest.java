package models.panelTests;

import models.decorators.PointCounter;
import org.junit.Assert;
import org.junit.Test;

public class CountTest extends PointCounter {

   @Test
   public void shouldCountPoints1() {
      int[][] panel = new int[][]{
        {0, 0, 0, 2, 1, 0, 0},
        {0, 0, 0, 2, 1, 0, 0},
        {0, 0, 0, 2, 1, 1, 1},
        {0, 2, 0, 2, 1, 0, 0},
        {0, 0, 2, 1, 0, 1, 1},
        {0, 0, 2, 1, 1, 0, 0},
        {0, 0, 2, 1, 0, 0, 0}
      };

      Assert.assertArrayEquals(new int[]{12, 17}, count(panel));
   }

   @Test
   public void shouldCountPoints2() {
      int[][] panel = new int[][]{
        {0, 1, 0, 0, 1, 2, 0},
        {1, 0, 1, 1, 1, 2, 0},
        {0, 1, 2, 2, 2, 0, 0},
        {0, 0, 1, 1, 2, 0, 0},
        {0, 1, 1, 2, 0, 2, 0},
        {1, 0, 1, 2, 2, 0, 0},
        {0, 1, 2, 2, 0, 0, 0}
      };

      Assert.assertArrayEquals(new int[]{10, 13}, count(panel));
   }
}
