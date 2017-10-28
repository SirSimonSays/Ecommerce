package pannelli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import prodotto.HandleProduct;
import prodotto.tabella.ModelloProdotto;
import prodotto.tabella.TabellaProdotto;
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
	 * @var eliminaProd
	 * bottone che ti permette di eliminare un prodotto selezionato dal carrello.
	 */
	private JButton eliminaProd;
	
	/**
	 * @var tabProd
	 * Tabella dei prodotti nel carrello.
	 */
	private TabellaProdotto tabProd;
	
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
		
		try{
			eliminaProd = new JButton(new ImageIcon(Carrello.class.getResource("/image/delete.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//prec.setMaximumSize(new Dimension(32, 32));
		prec.addActionListener(this);
		
		toolBar.add(prec);
		toolBar.addSeparator();
		toolBar.add(svuotaCarrello);
		toolBar.add(eliminaProd);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(acquista);
		
		//tabProd = new TabellaProdotto(new ModelloProdotto());
		
		add(toolBar, BorderLayout.PAGE_START);
		//add(tabProd, BorderLayout.CENTER);

	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti
	 */
	@Override
	public void onEnter() {
		
		//tabProd.refresh();
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
			HandlePanel.switchPanel(Acquista.TAG);
			
		}else if(e.getSource().equals(svuotaCarrello)){
			HandleCarrello.svuota();
			
		}else if(e.getSource().equals(eliminaProd)){
			if(tabProd.getSelectedRow() != -1){
				int res = JOptionPane.showConfirmDialog(this, "Vuoi eliminare questo prodotto dal carrello?", "Cancellare?", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
					HandleCarrello.rimuoviProd(HandleProduct.getProduct(tabProd.getSelectedRow()));
					tabProd.refresh();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Per poter eliminare un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	

}
