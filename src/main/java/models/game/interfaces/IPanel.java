package models.game.interfaces;

import models.game.exceptions.PushException;

public interface IPanel {

  Boolean isValid(int x, int y, int playerIndex);
  void push(int x, int y, int playerIndex) throws PushException;
  void print();
  void setSize();
}
