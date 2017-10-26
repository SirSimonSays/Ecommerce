package pannelli;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HandlePanel extends JFrame{

	/**
	 * @var container
	 * Panello principale che gestisce i pannelli tramite un cardLayout.
	 */
	private static JPanel container;
	
	/**
	 * Costruttore della classe HandlePanel. Crea e gestisce il cardLayout ed è
	 * inoltre il Frame principale dell'ECommerce.
	 */
	public HandlePanel(){
		
		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new CreaUtente(this), CreaUtente.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		container.add(new UserPanel(this), UserPanel.TAG);
		container.add(new CreaProdotto(this), CreaProdotto.TAG);
		container.add(new EditProdotto(this), EditProdotto.TAG);
		container.add(new Carrello(this), Carrello.TAG);
		
		add(container);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(1000, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("ECommerce");
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	
	/**
	 * @param nextTag
	 * Serve a spostarsi fra un pannello ed un altro con il parametro nextTag
	 * che dice appunto il prossimo pannello.
	 */
	public static void switchPanel(String nextTag){
		CardLayout cl = (CardLayout)(container.getLayout());
        cl.show(container, nextTag);  
	}
	
}
