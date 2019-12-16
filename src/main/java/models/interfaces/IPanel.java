package models.interfaces;

public interface IPanel {

  void push(int x, int y, int playerIndex);
  void setSize();
  int[][] getPositions();
}
