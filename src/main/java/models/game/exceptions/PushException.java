package models.game.exceptions;

public class PushException extends Exception {
   public PushException() {
      super("Incorrect data while trying to push");
   }
}
