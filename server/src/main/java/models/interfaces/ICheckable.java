package models.interfaces;

import models.exceptions.PushException;

public interface ICheckable {
   void check(int x, int y, int playerIdx) throws PushException;
}
