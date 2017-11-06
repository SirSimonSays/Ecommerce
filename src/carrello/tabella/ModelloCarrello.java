package carrello.tabella;

import javax.swing.table.AbstractTableModel;

import carrello.HandleCarrello;

import prodotto.Prod3x2;
import prodotto.Prodotto;
import prodotto.tabella.ModelloProdotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il modello della tabella presente nel 
 * pannello carrello, del tutto simile a quella presente negli altri 
 * pannelli ma prende i dati dall'array di carrello. 
 */
public class ModelloCarrello extends ModelloProdotto{

	/**
	 * @brief costruttore
	 */
	public ModelloCarrello() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di righe della tabella
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return HandleCarrello.getCarrelloCount();
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
		Prodotto p = HandleCarrello.getProduct(riga);
		
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

}
