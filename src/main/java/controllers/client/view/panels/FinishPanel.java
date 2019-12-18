package controllers.client.view.panels;

import javax.swing.JPanel;

import controllers.client.view.PlayerPanel;
import controllers.client.view.panels.panels_parts.PanelState;

public class FinishPanel extends JPanel implements PanelState {
	
	private PlayerPanel panel;
	
	
	
	
	
	

	@Override
	public PanelState next(int value) {
		
		return null;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
	
	@Override
	public void refresh(int x, int y, int playerId) {}

}
