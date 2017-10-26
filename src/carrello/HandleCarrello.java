package carrello;

import java.util.Vector;

import prodotto.Prodotto;

public class HandleCarrello {
	
	/**
	 * @var carrello
	 * Vettore che contiene le istanze dei prodotti nel carrello.
	 */	
	private static Vector<Prodotto> carrello = new Vector<Prodotto>();
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private HandleCarrello(){
		
	}
	
	public static void aggiungiProd(Prodotto p){
		carrello.addElement(p);
	}
	
	public static void rimuoviProd(Prodotto p){
		carrello.removeElement(p);
	}
	
	public static void svuota(){
		carrello.removeAllElements();
	}

}
