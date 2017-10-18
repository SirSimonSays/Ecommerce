package user;

public class Admin extends Utente{
	
	public Admin(String nome, String password){
		super(nome, password, true);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @brief promozione 3x2, per cui il cliente può acquistare prodotti uguali e pagarne solo 2. 
	 */
	public void promo1(){
		// TODO Auto-generated method stub
		
	}
	
	/** 
	 * @brief promozione che ribassa il prezzo di un prodotto di una certa percentuale. 
	 */
	public void promo2(int sconto){
		// TODO Auto-generated method stub
		
		if(sconto > 0 && sconto < 100){
			//setPrezzo((getPrezzo()*sconto)/100);
		}
		
	}
}
