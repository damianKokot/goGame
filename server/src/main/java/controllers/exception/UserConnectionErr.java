package controllers.exception;

public class UserConnectionErr extends Exception{
	public UserConnectionErr() {
	      super("Cannot connect user!");
	}
}
