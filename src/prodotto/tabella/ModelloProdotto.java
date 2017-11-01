package prodotto.tabella;

import javax.swing.table.AbstractTableModel;

import prodotto.HandleProduct;
import prodotto.Prod3x2;
import prodotto.Prodotto;

public class ModelloProdotto extends AbstractTableModel{
	
	/**
	 * @var ColName
	 * array di stringhe nel quale ogni elemento rappresenta l'intestazioni di una colonna.
	 */
	private String[] ColName = {"Codice","Nome","Marca","Categoria","Prezzo","Immagine","Offerta"};
	
	/**
	 * @var CODE_COL
	 * Indice della colonna contenente il codice dei prodotti
	 */
	public static final int COD_COL = 0;
	
	/**
	 * @var NAME_COL
	 * Indice della colonna contenente il nome dei prodotti
	 */
	public static final int NOME_COL = 1;
	
	/**
	 * @var BRAND_COL
	 * Indice della colonna contenente la marca dei prodotti
	 */
	public static final int MARCA_COL = 2;
	
	/**
	 * @var CATEGORY_COL
	 * Indice della colonna contenente la categoria dei prodotti
	 */
	public static final int CAT_COL = 3;
	
	/**
	 * @var PRICE_COL
	 * Indice della colonna contenente il prezzo dei prodotti
	 */
	public static final int PRZ_COL = 4;
	
	/**
	 * @var IMG_COL
	 * Indice della colonna contenente le immagini dei prodotti
	 */
	public static final int IMG_COL = 5;
	
	/**
	 * @var OFFER_COL
	 * Indice della colonna contenente le offerte dei prodotti 
	 */
	public static final int OFFER_COL = 6;
	
	/**
	 * @brief Costruttore
	 */
	public ModelloProdotto() {
		super();
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di righe della tabella
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return HandleProduct.getProductCount();
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di colonne della tabella
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return ColName.length;
	}
	
	/**
	 * @brief Serve a recuperare il nome della colonna con indice col
	 * @param col
	 * @return nome della colonna passata come parametro
	 */
	public String getColumnName(int col) {
	    return ColName[col];
	}

	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param riga Indice della riga
	 * @param col Indice della colonna
	 * @return Ritorna l'oggetto contenuto in una cella della tabella
	 */
	@Override
	public Object getValueAt(int riga, int col) {
		// TODO Auto-generated method stub
		Prodotto p = HandleProduct.getProduct(riga);
		
		if(p != null){
			switch (col){	
				case COD_COL:
					return p.getId();
					
				case NOME_COL:
					return p.getNome();
					
				case MARCA_COL:
					return p.getMarca();
					
				case CAT_COL:
					return p.getCategoria();
					
				case PRZ_COL:
					return "€ " + String.format("%.2f", p.getPrezzo());
				
				case IMG_COL:
					//caricare l'immagine se il path è settato altrimenti niente
					return null;
				
				case OFFER_COL:
					if(p instanceof Prod3x2){
						return "3x2";
					}
					else if(p.getSconto() > 0){
						return Integer.toString(p.getSconto()) + "%";
					}
					return "";
			}
		}
		
		return "";
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return falso in modo che nessuna cella sia editabile
	 */
	public boolean isCellEditable(int riga, int col) {
		return false;
	}

}