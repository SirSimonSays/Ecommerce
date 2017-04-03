package panelli;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public abstract class DefaultPanel extends JPanel implements ActionListener{

	protected HandlePanel handlepanel;
	protected MenuBar mainMb;
	private Menu infoMenu;
	
	public DefaultPanel(HandlePanel handlePanel){
		
		this.handlepanel = handlePanel;
		
		mainMb = new MenuBar();
		infoMenu = new Menu("File");
		MenuItem m1 = new MenuItem("Logout");
		MenuItem m2 = new MenuItem("Exit");
		
		infoMenu.add(m1);
		infoMenu.add(m2);
		mainMb.add(infoMenu);
		
		handlePanel.setMenuBar(mainMb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("logout")){
			
		}else if(e.getActionCommand().equals("exit")){
			
		}
		
	}
	
}
