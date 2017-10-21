package carrello;

import java.util.Vector;

import prodotto.Prodotto;

public class HandleCarrello {
	
	/**
	 * @var carrello
	 * Vettore che contiene le istanze dei prodotti nel carrello.
	 */	
	private static Vector<Prodotto> carrello = new Vector<Prodotto>();
	
	private HandleCarrello(){
		
	}
	
	public static void aggiungiProd(Prodotto p){
		carrello.addElement(p);
	}
	
	public void rimuoviProd(Prodotto p){
		carrello.removeElement(p);
	}
	
	public void svuota(){
		carrello.removeAllElements();
	}

}
