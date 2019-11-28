package models.game.exceptions;

public class ForbiddenMoveException extends Exception {
   public ForbiddenMoveException(String message) {
      super(message);
   }
}
