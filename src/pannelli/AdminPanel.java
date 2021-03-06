package pannelli;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import prodotto.HandleProduct;
import prodotto.tabella.ModelloProdotto;
import prodotto.tabella.TabellaProdotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello per l'admin il quale
 * deve poter gestire i prodotti e visualizzarli. 
 */
public class AdminPanel extends DefaultPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "admin";
	
	/**
	 * @var edit
	 * bottone per modificare un prodotto.
	 */
	private JButton edit;
	
	/**
	 * @var add
	 * bottone per aggiungere un prodotto.
	 */
	private JButton add;
	
	/**
	 * @var delete
	 * bottone per eliminare un prodotto.
	 */
	private JButton delete;
	
	/**
	 * @var changeFile
	 * bottone per cambiare il file di default su cui salvare e caricare i prodotti
	 */
	private JButton changeFile;
	
	/**
	 * @var tabProd
	 * Tabella dei prodotti.
	 */
	private TabellaProdotto tabProd;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public AdminPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		try{
			edit = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/edit.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		edit.addActionListener(this);
		
		try{
			add = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/add.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		add.addActionListener(this);
		
		try{
			delete = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/delete.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		delete.addActionListener(this);
		
		try{
			changeFile = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/file.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		changeFile.addActionListener(this);
		
		toolBar.addSeparator();
		toolBar.add(edit);
		toolBar.add(add);
		toolBar.add(delete);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(changeFile);
		toolBar.addSeparator();
		
		tabProd = new TabellaProdotto(new ModelloProdotto());
		//modifica la dimensione delle righe della tabella
		
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
		if(e.getSource().equals(edit)){
			if(tabProd.getSelectedRow() != -1){
				EditProdotto.setIndex(tabProd.getSelectedRow());
				HandlePanel.switchPanel(EditProdotto.TAG);
				tabProd.refresh();
			}else{
				JOptionPane.showMessageDialog(this,"Per poter modificare un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(add)){
			HandlePanel.switchPanel(CreaProdotto.TAG);
		}else if(e.getSource().equals(delete)){
			if(tabProd.getSelectedRow() != -1){
				int res = JOptionPane.showConfirmDialog(this, "Vuoi cancellare questo prodotto?", "Cancellare?", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
					HandleProduct.rimuoviProdotto(tabProd.getSelectedRow());
					tabProd.refresh();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Per poter eliminare un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(changeFile)){
			
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(new JFrame());
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				if(!HandleProduct.setFileP(fc.getSelectedFile()))
					JOptionPane.showMessageDialog(this,"Il file selezionato è vuoto.",
							"Info",JOptionPane.INFORMATION_MESSAGE);
				tabProd.refresh();
			}else{
				JOptionPane.showMessageDialog(this,"Non hai selezionato un file adeguato.",
						"Errore nella selezione del file",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}
	}

}
