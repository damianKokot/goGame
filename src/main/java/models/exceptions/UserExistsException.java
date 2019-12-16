package models.exceptions;

public class UserExistsException extends Exception {
   public UserExistsException(String message) {
      super(message);
   }
}
