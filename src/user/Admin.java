package user;

/**
 * @author Simone Cavana
 * @brief classe che implementa la sottoclasse Admin relativa a Utente.
 *  
 */
public class Admin extends Utente{
	
	/**
	 * @brief costruttore 
	 * richiama il super costruttore ma con @var isAdmin settata a true.
	 * @param nome
	 * @param password
	 */
	public Admin(String nome, String password){
		super(nome, password, true);
		// TODO Auto-generated constructor stub
	}
}
