package prodotto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class TabellaProdotto extends JScrollPane{

	/**
	 * Modello basato sul modello creato in ModelloProdotto.
	 */
	private TableModel dataModel;
	
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
		
		add(t);
		
	}
	
}
