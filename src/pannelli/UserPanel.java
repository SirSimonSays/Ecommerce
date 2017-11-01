package pannelli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import carrello.HandleCarrello;

import prodotto.HandleProduct;
import prodotto.tabella.ModelloProdotto;
import prodotto.tabella.TabellaProdotto;

public class UserPanel extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "user";
	
	/**
	 * @var tabProd
	 * Tabella dei prodotti.
	 */
	private TabellaProdotto tabProd;
	
	/**
	 * @var ricerca
	 * barra per l'inserimento di stringhe di ricerca.
	 */
	private JTextField ricerca;
	
	/**
	 * @var trova
	 * bottone per la ricerca dei prodotti.
	 */
	private JButton trova;
	
	/**
	 * @var carrello
	 * bottone che ti permette di visualizzare il contenuto del carrello.
	 */
	private JButton carrello;
	
	/**
	 * @var addCarrello
	 * bottone che ti permette di aggiungere il prodotto selezionato al carrello.
	 */
	private JButton addCarrello;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public UserPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		ricerca = new JTextField(15);
		ricerca.setMaximumSize(new Dimension(24, 24));
		ricerca.setEditable(true);
		
		try{
			trova = new JButton(new ImageIcon(UserPanel.class.getResource("/image/explore.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//trova.setMaximumSize(new Dimension(32, 32));
		trova.addActionListener(this);
		
		try{
			carrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/basket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//carrello.setMaximumSize(new Dimension(32, 32));
		carrello.addActionListener(this);
		
		try{
			addCarrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/addbasket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		//addCarrello.setMaximumSize(new Dimension(32, 32));
		addCarrello.addActionListener(this);
		
		toolBar.add(addCarrello);
		toolBar.add(ricerca);
		toolBar.add(trova);
		toolBar.addSeparator();
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(carrello);
		
		tabProd = new TabellaProdotto(new ModelloProdotto());
		
		add(toolBar, BorderLayout.PAGE_START);		
		add(tabProd, BorderLayout.CENTER);
		
	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti
	 */
	@Override
	public void onEnter() {
		HandleProduct.leggiProdotti();
		tabProd.refresh();
	}
	
	/**
	 * @param e
	 * controllo degli eventi in base alla pressione dei bottoni.
	 */
	@Override
	public void actionPerformed(ActionEvent e){ 
		super.actionPerformed(e);
		if(e.getSource().equals(addCarrello)){
			if(tabProd.getSelectedRow() != -1){
				HandleCarrello.aggiungiProd(HandleProduct.getProduct(tabProd.getSelectedRow()));
			}else{
				JOptionPane.showMessageDialog(this,"Per poter aggiungere un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(trova)){
			int index = HandleProduct.saerchName(ricerca.getText());
			System.out.println(index);
		}else if(e.getSource().equals(carrello)){
			HandlePanel.switchPanel(Carrello.TAG);
		}
	}

	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		ricerca.setText("");
	}
	
}
