package carrello;

import java.util.Vector;

import prodotto.Prodotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il manipolatore del Carrello che gestisce
 * gli articoli scelti dal cliente e da la possibilità di procedere all'acquisto. 
 */
public class HandleCarrello {
	
	/**
	 * @var carrello
	 * Vettore che contiene le istanze dei prodotti nel carrello.
	 */	
	private static Vector<Prodotto> carrello = new Vector<Prodotto>();
	
	/**
	 * @var qcarrello
	 * Vettore che contiene la quantità del rispettivo prodotto nella stessa posizione
	 * nel vector carrello.
	 */
	private static Vector<Integer> qcarrello = new Vector<Integer>();
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private HandleCarrello(){
		
	}
	
	/**
	 * @brief getter del numero di elementi nel Vector carrello  
	 * @return numero di elementi nel carrello
	 */
	public static int getCarrelloCount(){
		return carrello == null ? 0 : carrello.size();
	}
	
	/**
	 * @brief getter del prodotto nel carrello all'index
	 * @param index
	 * @return prodotto all'indice index
	 */
	public static Prodotto getProductAt(int index){
		if(index < carrello.size()){
			return carrello.get(index);
		}
		return null;
	}
	
	/**
	 * @brief getter del elemento all'index 
	 * @param index
	 * @return intero all'indice index
	 */
	public static Integer getQcarrelloAt(int index) {
		return qcarrello.elementAt(index);
	}
	
	/**
	 * @brief nella parte else del metodo aggiunge il prodotto passato come 
	 * parametro al Vector carrello e la rispettiva quantità al Vector qcarrello
	 * se il prodotto non era già esistente, mentre nella parte if incrementa
	 * solo la quantità di quel prodotto di n in quanto l'if ci dice che il prodotto
	 * era già presente nel carrello.
	 * @param p
	 * @param n
	 */
	public static void aggiungiProd(Prodotto p, int n){
		if(carrello.contains(p)){
			qcarrello.setElementAt(getQcarrelloAt(carrello.indexOf(p)) + n, carrello.indexOf(p));
		}else{
			carrello.addElement(p);
			qcarrello.addElement(n);
		}
	}
	
	/**
	 * @brief incrementa (+1) la quantità di un prodotto presente nel carrello.
	 * @param p
	 */
	public static void incrementProd(Prodotto p){
		qcarrello.setElementAt(getQcarrelloAt(carrello.indexOf(p)) + 1, carrello.indexOf(p));
	}
	
	/**
	 * @brief elimina un prodotto dal carrello e la rispettiva quantità.
	 * @param p
	 */
	public static void rimuoviProd(Prodotto p){
		qcarrello.removeElementAt(carrello.indexOf(p));
		carrello.removeElement(p);
	}
	
	/**
	 * @brief nella if controllo se la quantità del prodotto da eliminare è
	 * più di uno e quindi la decrementa di uno, altrimenti elimina il prodotto
	 * passato come parametro dal Vector carrello e la rispettiva quantità al
	 * Vector qcarrello.
	 * @param p
	 */
	public static void reduceProd(Prodotto p){
		if(qcarrello.elementAt(carrello.indexOf(p)) > 1){
			qcarrello.setElementAt(getQcarrelloAt(carrello.indexOf(p)) - 1, carrello.indexOf(p));
		}else{
			rimuoviProd(p);
		}
		
	}
	
	/**
	 * @brief eliminare tutti gli elementi presenti nel Vector carrello
	 * e le rispettive quantità
	 */
	public static void svuota(){
		carrello.removeAllElements();
		qcarrello.removeAllElements();
	}

}
