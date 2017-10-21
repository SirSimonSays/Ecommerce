package prodotto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TabellaProdotto extends JScrollPane{

	/**
	 * Modello basato sul modello creato in ModelloProdotto.
	 */
	private AbstractTableModel dataModel;
	
	/**
	 * Tabella concreta in cui verranno visualizzati gli elementi.
	 */
	private JTable t;
	
	/**
	 * Costruttore.
	 */
	public TabellaProdotto(ModelloProdotto m){
		
		super();
		
		dataModel = m;
		t = new JTable(dataModel);
		
		t.setColumnSelectionAllowed(false);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
	
}
