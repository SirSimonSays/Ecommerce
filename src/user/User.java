package user;

public class User extends Utente {

	private String indirizzoSpedizione;
	private int phoneNumber;
		
	public User(String nome, String password) {
		super(nome, password, false);
		// TODO Auto-generated constructor stub
		indirizzoSpedizione = null;
		phoneNumber = 0;
	}
	
	/**
	 * @brief getter dell'indirizzo dell'utente
	 * @return indirizzo di spedizione dell'utente
	 */
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}
	
	/**
	 * @brief getter del telefono dell'utente
	 * @return telefono dell'utente
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @brief setter dell'indirizzo di spedizione
	 * @param indirizzoSpedizione
	 */
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	
	/**
	 * @brief setter del numero di telefono
	 * @param phoneNumber
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
