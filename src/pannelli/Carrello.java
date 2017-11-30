package pannelli;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import carrello.HandleCarrello;
import carrello.tabella.ModelloCarrello;
import carrello.tabella.TabellaCarrello;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello Carrello che contiene gli oggetti da acquistare
 * e ne fa il totale dei prezzi.
 */
public class Carrello extends DefaultPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "carrello";
	
	/**
	 * @var totL
	 * Label contenente il prezzo totale dei prodtti nel carrello
	 */
	protected JLabel totL;
	
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
	 * @var decrementProd
	 * bottone che permette di decrementare la quantità di un prodotto nel carrello.
	 */
	private JButton decrementProd;
	
	/**
	 * @var incrementProd
	 * bottone che permette di incrementare la quantità di un prodotto nel carrello.
	 */
	private JButton incrementProd;
	
	/**
	 * @var tabProd
	 * Tabella dei prodotti nel carrello.
	 */
	private TabellaCarrello tabProd;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public Carrello(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBarH = new JToolBar();
		toolBarH.setFloatable(false);
		
		JToolBar toolBarF = new JToolBar();
		toolBarF.setFloatable(false);
		
		totL = new JLabel("0.00");
		
		try{
			prec = new JButton(new ImageIcon(Carrello.class.getResource("/image/prev.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		prec.addActionListener(this);
		
		try{
			acquista = new JButton(new ImageIcon(Carrello.class.getResource("/image/pay.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		acquista.addActionListener(this);
		
		try{
			svuotaCarrello = new JButton(new ImageIcon(Carrello.class.getResource("/image/flush.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		svuotaCarrello.addActionListener(this);
		
		try{
			eliminaProd = new JButton(new ImageIcon(Carrello.class.getResource("/image/delete.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		eliminaProd.addActionListener(this);
		
		try{
			decrementProd = new JButton(new ImageIcon(Carrello.class.getResource("/image/arrow_down.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		decrementProd.addActionListener(this);
		
		try{
			incrementProd = new JButton(new ImageIcon(Carrello.class.getResource("/image/arrow_up.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		incrementProd.addActionListener(this);
		
		toolBarH.addSeparator();
		toolBarH.add(prec);
		toolBarH.addSeparator();
		toolBarH.add(svuotaCarrello);
		toolBarH.add(eliminaProd);
		toolBarH.addSeparator();
		toolBarH.add(incrementProd);
		toolBarH.add(decrementProd);
		toolBarH.add(Box.createHorizontalGlue());
		toolBarH.add(acquista);
		toolBarH.addSeparator();
		
		toolBarF.add(Box.createHorizontalGlue());
		toolBarF.add(new JLabel("Totale: € "));
		toolBarF.add(totL);
		
		tabProd = new TabellaCarrello(new ModelloCarrello());
		
		add(toolBarH, BorderLayout.PAGE_START);
		add(tabProd, BorderLayout.CENTER);
		add(toolBarF, BorderLayout.PAGE_END);

	}
	
	/**
	 * @brief calcola il totale dei prezzi e lo stampa nella Label del totale
	 */
	public void loadTotal(){
		float t = 0;
		
		for(int i = 0; i < HandleCarrello.getCarrelloCount(); i++){
			t += HandleCarrello.getProductAt(i).getTotal(HandleCarrello.getQcarrelloAt(i));
		}
		
		totL.setText(Float.toString(t));
	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti e calcola il totale
	 */
	@Override
	public void onEnter(){
		loadTotal();
		tabProd.refresh();
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
			if(HandleCarrello.getCarrelloCount() == 0){
				JOptionPane.showMessageDialog(this,"Per poter effettuare un pagamento devi prima mettere dei prodotti nel carrello\n Altrimenti effettua una donazione a www.cavanaSimone.it",
						"Selezionare i prodotti da acquistare",JOptionPane.INFORMATION_MESSAGE);
			}else{
				HandlePanel.switchPanel(Acquista.TAG);
				loadTotal();
			}
			
		}else if(e.getSource().equals(svuotaCarrello)){
			int res = JOptionPane.showConfirmDialog(this, "Vuoi svuotare il carrello?", "Svuotare?", JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION){
				HandleCarrello.svuota();
				tabProd.refresh();
				loadTotal();
			}
		}else if(e.getSource().equals(eliminaProd)){
			if(tabProd.getSelectedRow() != -1){
				int res = JOptionPane.showConfirmDialog(this, "Vuoi eliminare questo prodotto dal carrello?", "Cancellare?", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
					HandleCarrello.rimuoviProd(HandleCarrello.getProductAt(tabProd.getSelectedRow()));
					tabProd.refresh();
					loadTotal();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Per poter eliminare un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(decrementProd)){
			if(tabProd.getSelectedRow() != -1){
				HandleCarrello.reduceProd(HandleCarrello.getProductAt(tabProd.getSelectedRow()));
				tabProd.refresh();
				loadTotal();
			}else{
				JOptionPane.showMessageDialog(this,"Per poter diminuire la quantità di un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(incrementProd)){
			if(tabProd.getSelectedRow() != -1){
				HandleCarrello.incrementProd(HandleCarrello.getProductAt(tabProd.getSelectedRow()));
				tabProd.refresh();
				loadTotal();
			}else{
				JOptionPane.showMessageDialog(this,"Per poter aumentare la quantità di un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	

}
