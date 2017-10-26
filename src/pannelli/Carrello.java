package pannelli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import prodotto.HandleProduct;
import carrello.HandleCarrello;

/**
 * @author simone
 *
 */
public class Carrello extends DefaultPanel {
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "carrello";
	
	/**
	 * @var prec
	 * bottone che ti permette di tornare alla pagina precedente.
	 */
	private JButton prec;
	
	/**
	 * @var carrello
	 * bottone che ti permette di acquistare i prodotti presenti nel carrello.
	 */
	private JButton acquista;
	
	/**
	 * @var addCarrello
	 * bottone che ti permette di vuotare il carrello.
	 */
	private JButton svuotaCarrello;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public Carrello(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		try{
			prec = new JButton(new ImageIcon(Carrello.class.getResource("/image/prev.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//prec.setMaximumSize(new Dimension(32, 32));
		prec.addActionListener(this);
		
		try{
			acquista = new JButton(new ImageIcon(Carrello.class.getResource("/image/pay.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//acquista.setMaximumSize(new Dimension(32, 32));
		acquista.addActionListener(this);
		
		try{
			svuotaCarrello = new JButton(new ImageIcon(Carrello.class.getResource("/image/flush.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//svuotaCarrello.setMaximumSize(new Dimension(32, 32));
		svuotaCarrello.addActionListener(this);
		
		toolBar.add(prec);
		toolBar.addSeparator();
		toolBar.add(svuotaCarrello);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(acquista);
		
		add(toolBar, BorderLayout.PAGE_START);
	}
	
	/**
	 * @param e
	 * controllo degli eventi in base alla pressione dei bottoni.
	 */
	@Override
	public void actionPerformed(ActionEvent e){ 
		super.actionPerformed(e);
		if(e.getSource().equals(prec)){
			HandlePanel.switchPanel(UserPanel.TAG);
		}else if(e.getSource().equals(acquista)){
			
		}else if(e.getSource().equals(svuotaCarrello)){
			HandleCarrello.svuota();
		}
	}	

}
