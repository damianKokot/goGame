package controllers.exception;

public class ConnectionErr extends Exception{
	public ConnectionErr() {
		super("Something went wrong!");
	}
}
