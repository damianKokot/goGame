package controllers.client.view;

public class GoPanelListener extends Thread{
	
	private GoPanel display;
	
	public GoPanelListener(GoPanel display) {
		this.display=display;
	}
	
	public int waitForSize() {
		
		while(true) {
			
			if(display.planeSize!=0)
				return display.planeSize;
			
			Thread.yield();
		}
	}
	
    public int[] waitForMove() {
		
    	display.wasMoveMade=false;
    	
		while(true) {
			
			if(display.wasMoveMade)
				return display.getCoordinates();
				
			try {
				Thread.yield();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void generateBoard(int board[][]) {
		display.drawPlane(board);
	}

}
