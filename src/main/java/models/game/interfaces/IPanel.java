package models.game.interfaces;

import models.game.exceptions.PushException;

import java.util.ArrayList;

public interface IPanel {

  void checkIfValid(int x, int y, int playerIndex) throws PushException;
  void push(int x, int y, int playerIndex) throws PushException;
  void setSize();
  int getSize();
  int[][] getPositions();
  int[][] getBreaths();

  ArrayList<String> getLogs();

  IPanel copy();
  IPanel getNewInstance();
}
