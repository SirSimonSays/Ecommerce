package prodotto;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ModelloProdotto extends AbstractTableModel{
	
	/**
	 * intestazioni delle colonne
	 */
	String[] ColName = {"Nome","Marca","Categoria","Prezzo","Immagine"};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isCellEditable(int row, int col) {
		// nessuna cella editabile
		return false;
	}

}
