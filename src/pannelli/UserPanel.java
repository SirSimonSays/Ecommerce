package pannelli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
public class UserPanel extends DefaultPanel implements DropTargetListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * @var rowSorter
	 * oggetto per la ricerca all'interno della tabella 
	 */
	private TableRowSorter<TableModel> rowSorter;
	
	/**
	 * @var ricerca
	 * barra per l'inserimento di stringhe di ricerca.
	 */
	private JTextField ricerca;
	
	/**
	 * @var lricerca
	 * label per indicare dove cercare
	 */
	private JLabel lricerca;
	
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
		
		lricerca = new JLabel("Cerca: ");
		ricerca = new JTextField(15);
		ricerca.setMaximumSize(new Dimension(24, 24));
		ricerca.setEditable(true);
		
		try{
			carrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/basket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		carrello.addActionListener(this);
		@SuppressWarnings("unused")
		DropTarget dTarget = new DropTarget(carrello, this);
		
		try{
			addCarrello = new JButton(new ImageIcon(UserPanel.class.getResource("/image/addbasket.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		addCarrello.addActionListener(this);
		
		toolBarH.addSeparator();
		toolBarH.add(addCarrello);
		toolBarH.add(Box.createHorizontalGlue());
		toolBarH.addSeparator();
		toolBarH.add(lricerca);
		toolBarH.add(ricerca);
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

		tabProd = new TabellaProdotto(new ModelloProdotto());
		rowSorter = new TableRowSorter<TableModel>(tabProd.getModel());
		tabProd.setRSorter(rowSorter);
		
		add(toolBarH, BorderLayout.PAGE_START);
		add(tabProd, BorderLayout.CENTER);
		add(toolBarF, BorderLayout.PAGE_END);
		
		//metodo per cercare nella tabella senza l'utilizzo di un bottone
		//credit: stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
		ricerca.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = ricerca.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = ricerca.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
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
				
				HandleCarrello.aggiungiProd(HandleProduct.getProduct(tabProd.getSelectedRow()), (Integer)nProd.getValue());
				
			}else{
				JOptionPane.showMessageDialog(this,"Per poter aggiungere un prodotto devi prima selezionarlo.",
						"Seleziona una riga",JOptionPane.INFORMATION_MESSAGE);
			}	
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

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @brief metodo utilizzato per inserire prodotti nel carrello con dnd
	 * @param dtde
	 */
	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
        System.out.println("dropped");
		
        try {                	
			//Add product to the basket
			HandleCarrello.aggiungiProd(HandleProduct.getProduct(tabProd.getSelectedRow()),(Integer)nProd.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
        dtde.dropComplete(true);
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
