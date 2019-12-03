package models.game.interfaces;

import models.game.exceptions.PushException;

public interface ICheckable {
   void check(int x, int y, int playerIdx) throws PushException;
}
