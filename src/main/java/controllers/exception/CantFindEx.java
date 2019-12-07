package controllers.exception;

public class CantFindEx extends Exception{
	public CantFindEx() {
	      super("Cannot find game/client!");
	}
}
