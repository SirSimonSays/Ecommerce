package prodotto.tabella;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Simone Cavana
 * @brief classe che implementa la tabella che ospita i modelli
 * prodotto e carrello. 
 */
public class TabellaProdotto extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Modello basato sul modello creato in ModelloProdotto.
	 */
	private AbstractTableModel dataModel;
	
	/**
	 * Tabella concreta in cui verranno visualizzati gli elementi.
	 */
	private JTable t;
	
	/**
	 * Costruttore che setta i principali parametri della tabella per
	 * le schermate di admin e user.
	 */
	public TabellaProdotto(ModelloProdotto m){
		
		super();
		
		dataModel = m;
		t = new JTable(dataModel);
		
		t.setColumnSelectionAllowed(false);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.setDragEnabled(true);
		
		t.setRowHeight(100);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		setViewportView(t);
		refresh();
		
	}
	
	/**
	 * @brief Ricarica i valori contenuti nella tabella
	 */
	public void refresh(){
		dataModel.fireTableDataChanged();
	}
	
	/**
	 * @brief Ritorna l'indice della riga selezionata.
	 * @return Indice riga selezionata o -1 se nessuna riga è selezionata.
	 */
	public int getSelectedRow(){
		return t.getSelectedRow();
	}

	/**
	 * @brief getter dell'attributo modello su cui la tabella si struttura 
	 * @return il modello della tabella
	 */
	public AbstractTableModel getModel(){
		return dataModel;
	}

	/**
	 * @brief setter dell'oggetto rowSorter della tabella  
	 * @param rowSorter
	 */
	public void setRSorter(TableRowSorter<TableModel> rowSorter) {
		// TODO Auto-generated method stub
		t.setRowSorter(rowSorter);
	}
}
