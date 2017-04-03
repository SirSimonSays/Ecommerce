package panelli;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HandlePanel extends JFrame{

	/**
	 * @var container
	 * Panello principale che gestisce i pannelli tramite un cardLayout
	 */
	private JPanel container;
	
	public HandlePanel(){
		
		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		container.add(new UserPanel(this), UserPanel.TAG);
		container.add(new Carrello(this), Carrello.TAG);
		
		add(container);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("ECommerce");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void switchPanel(String currentTag){
		CardLayout cl = (CardLayout)(container.getLayout());
        cl.show(container, currentTag);  
	}
	
}
