package user;

/**
 * Classe astratta che rappresenta un utente, sia admin che user semplice in base
 * al parametro @isAdmin
 * @author Simone Cavana
 */

public abstract class Utente {
	
	/**
	 * @var nome
	 * Nome utente
	 */
	private String nome;

	/**
	 * @var email
	 * Email utente
	 */
	private String email;
	
	/**
	 * @var password
	 * Password utente
	 */
	private String password;
	
	/**
	 * @var isAdmin
	 * tipo utente
	 */
	private Boolean isAdmin;
	
	/**
	 * @brief Costruttore
	 * @param nome Nome utente
	 * @param email Email utente
	 * @param password Password utente
	 * @param isAdmin Admin o user semplice
	 */
	public Utente(String nome, String email, String password, Boolean isAdmin) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * @brief Ritorna il nome dell'utente 
	 * @return Nome utente
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @brief Ritorna l'email dell'utente 
	 * @return Email utente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @brief Ritorna la password dell'utente 
	 * @return Password utente
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @brief Ritorna true se l'utente è amministratore 
	 * @return Admin o user semplice
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
	/**
	 * @brief Setta il nome dell'utente 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @brief Setta l'email dell'utente
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @brief Setta la password dell'utente
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @brief Setta se l'utente è admin o user semplice
	 * @param isAdmin
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
		
}
