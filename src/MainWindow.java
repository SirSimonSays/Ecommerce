import java.awt.*;
import javax.swing.*;


public class MainWindow<U extends Utente>{

	private JFrame mainFrame;
	private JPanel mainPanel;
	private MenuBar mainMb;
	private Menu infoMenu;
	private Menu opMenu;
	
	
	public MainWindow(){
		
		mainFrame = new JFrame("Login");
		mainFrame.setBounds(200, 100, 1000, 600);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainMb = new MenuBar();
		infoMenu = new Menu("File");
		opMenu = new Menu("Edit");
	
		MenuItem m1 = new MenuItem("Search");
		MenuItem m2 = new MenuItem("Save");
		MenuItem m3 = new MenuItem("Charge");
		MenuItem m4 = new MenuItem("About");
		MenuItem m5 = new MenuItem("Op1");
		MenuItem m6 = new MenuItem("Op2");
		infoMenu.add(m1);
		infoMenu.add(m2);
		infoMenu.add(m3);
		infoMenu.add(m4);
		opMenu.add(m5);
		opMenu.add(m6);
		mainMb.add(infoMenu);
		mainMb.add(opMenu);
		mainFrame.setMenuBar(mainMb);
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
	}
	
}
