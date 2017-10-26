package prodotto.tabella;

import javax.swing.table.AbstractTableModel;

import prodotto.HandleProduct;


public class ModelloProdotto extends AbstractTableModel{
	
	/**
	 * @var ColName
	 * array di stringhe nel quale ogni elemento rappresenta l'intestazioni di una colonna.
	 */
	String[] ColName = {"Nome","Marca","Categoria","Prezzo","Immagine"};
	
	public String getColumnName(int col) {
	    return ColName[col];
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return ColName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return HandleProduct.getProductCount();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return falso in modo che nessuna cella sia editabile
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
