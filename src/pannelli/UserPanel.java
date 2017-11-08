package pannelli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import carrello.HandleCarrello;

import prodotto.HandleProduct;
import prodotto.tabella.ModelloProdotto;
import prodotto.tabella.TabellaProdotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello per l'utente nel quale può
 * visualizzare i prodotti e cercare fra di essi in base ad alcune caratteristiche.
 * Inoltre può scegliere se mettere nel carrello dei prodotti per poi acquistarli.
 */
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
	 * @var nProd
	 * spinner per selezionare quanti prodotti mettere nel carrello.
	 */
	private JSpinner nProd;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public UserPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBarH = new JToolBar();
		toolBarH.setFloatable(false);
		
		JToolBar toolBarF = new JToolBar();
		toolBarF.setFloatable(false);
		
		ricerca = new JTextField(15);
		ricerca.setMaximumSize(new Dimension(24, 24));
		ricerca.setEditable(true);
		
		try{
			trova = new JButton(new ImageIcon(UserPanel.class.getResource("/image/explore.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		trova.setMaximumSize(new Dimension(24, 24));
		trova.addActionListener(this);
		
		try{
			carrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/basket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		carrello.addActionListener(this);
		
		try{
			addCarrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/addbasket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		addCarrello.addActionListener(this);
		
		toolBarH.addSeparator();
		toolBarH.add(addCarrello);
		toolBarH.addSeparator();
		toolBarH.add(ricerca);
		toolBarH.add(trova);
		toolBarH.addSeparator();
		toolBarH.add(Box.createHorizontalGlue());
		toolBarH.add(carrello);
		toolBarH.addSeparator();

		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1);
		nProd = new JSpinner(model);
		
		toolBarF.add(Box.createHorizontalGlue());
		toolBarF.add(new JLabel("Quantità: "));
		toolBarF.add(nProd);
		toolBarF.add(Box.createHorizontalGlue());
		//toolBarF.setVisible(false);
		
		tabProd = new TabellaProdotto(new ModelloProdotto());
		
		add(toolBarH, BorderLayout.PAGE_START);
		add(tabProd, BorderLayout.CENTER);
		add(toolBarF, BorderLayout.PAGE_END);
		
	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti
	 */
	@Override
	public void onEnter() {
		HandleProduct.leggiProdotti();
		nProd.setValue(1);
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
				
				//gestione della quantità
				//nProd.getValue();
				HandleCarrello.aggiungiProd(HandleProduct.getProduct(tabProd.getSelectedRow()));
				
			}else{
				JOptionPane.showMessageDialog(this,"Per poter aggiungere un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(trova)){
			//HandleProduct.saerchName(ricerca.getText());
			//vuotare la tabella e selezionare solo quelli con quel nome
			
		}else if(e.getSource().equals(carrello)){
			HandlePanel.switchPanel(Carrello.TAG);
		}
	}

	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		ricerca.setText("");
		nProd.setValue(1);
	}
	
}
