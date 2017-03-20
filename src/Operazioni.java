
public interface Operazioni {
	
	/** possibilità di visualizzare la lista di prodotti all'admin*/
	public void visualizza();
	/** promozione 3x2, per cui il cliente può acquistare prodotti uguali e pagarne solo 2*/
	public void promo1();
	/** promozione che ribassa il prezzo di un prodotto di una certa percentuale*/
	public void promo2(int sconto);

}
