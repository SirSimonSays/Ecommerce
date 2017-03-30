package user;

public class Admin extends Utente{
	
	public Admin(String nome, String email, String password){
		super(nome, email, password, true);
		// TODO Auto-generated constructor stub
	}

	public void aggiungiProdotto(){
		
	}
	
	public void modificaProdott(){
		
	}
	
	public void eliminaProdotto(){
		
	}

	public void salvaProdotti(){
		
	}
	
	public void caricaProdotti(){
		
	}

	public void visualizzaProdotti() {
		
	}
	
	/** promozione 3x2, per cui il cliente può acquistare prodotti uguali e pagarne solo 2*/
	public void promo1(){
		// TODO Auto-generated method stub
		
	}
	
	/** promozione che ribassa il prezzo di un prodotto di una certa percentuale*/
	public void promo2(int sconto){
		// TODO Auto-generated method stub
		
		if(sconto > 0 && sconto < 100){
			//setPrezzo((getPrezzo()*sconto)/100);
		}
		
	}
}
