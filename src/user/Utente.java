package user;

import java.io.Serializable;

/**
 * @author Simone Cavana
 * classe astratta che rappresenta un utente, sia admin che user semplice in base
 * al parametro @isAdmin.
 */
public abstract class Utente implements Serializable{
	
	/**
	 * @var nome
	 * Nome utente
	 */
	private String nome;
	
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
	 * @param password Password utente
	 * @param isAdmin Admin o user semplice
	 */
	public Utente(String nome, String password, Boolean isAdmin) {
		super();
		this.nome = nome;
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
	
	/**
	 * @brief redifinizione del metodo toString per la stampa di un oggetto di
	 * tipo Utente.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + " " + password + " " + isAdmin;
	}
	
	/**
	 * @brief redifinizione del metodo equals per confrontare un elemento con
	 * un elemento del Vector.
	 */
	public boolean equals(Object o){
		if(o == null || !getClass().equals(o.getClass())) return false;
		if(o == this) return true;
		
		Utente u = (Utente) o;
		if(this.nome.compareTo(u.nome) == 0 && 
		   this.password.compareTo(u.password) == 0 && 
		   this.isAdmin.compareTo(u.isAdmin) == 0)
			return true;
		return false;
		
	}
}
