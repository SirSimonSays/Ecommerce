package panelli;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public abstract class DefaultPanel extends JPanel implements ActionListener{

	private JFrame f;
	private MenuBar mainMb;
	private Menu infoMenu;
	
	public DefaultPanel(){
		
		super();
		f.setTitle("Ecommerce");
		f.setBounds(200, 100, 1000, 600);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLayout(new CardLayout());
		mainMb = new MenuBar();
		infoMenu = new Menu("File");
	
		MenuItem m1 = new MenuItem("Logout");
		MenuItem m2 = new MenuItem("Exit");
		infoMenu.add(m1);
		infoMenu.add(m2);
		mainMb.add(infoMenu);
		f.setMenuBar(mainMb);
		
		f.add(this);
		f.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("logout")){
			
		}else if(e.getActionCommand().equals("exit")){
			
		}
		
	}
	
}
