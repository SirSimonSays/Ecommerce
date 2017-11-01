package prodotto;

public class Prod3x2 extends Prodotto{
	
	/**
	 * @brief costruttore
	 * @param id
	 * @param nome
	 * @param marca
	 * @param categoria
	 * @param prezzo
	 * @param photoPath
	 */
	public Prod3x2(int id, String nome, String marca, String categoria,
			float prezzo, String photoPath) {
		super(id, nome, marca, categoria, prezzo, photoPath);
		// TODO Auto-generated constructor stub
		//se prendi tre prodotti dello stesso tipo ne paghi solamente due
	}
	
	/**
	 * @brief applicazione dell'offerta 3x2 non cumulabile
	 * @return il prezzo dopo l'offerta
	 */
	@Override
	public float getTotal(int n){
		if(n == 3){
			return super.getTotal(n) - this.getPrezzo();
		}else{
			return super.getTotal(n);
		}
		
	}

}
