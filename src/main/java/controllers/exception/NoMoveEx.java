package controllers.exception;

public class NoMoveEx extends Exception {
	public NoMoveEx() {
		super("Unknown command!");
	}
}
