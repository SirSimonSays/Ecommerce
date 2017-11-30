package user;

/**
 * @author Simone Cavana
 * @brief classe che implementa la sottoclasse user relativa a Utente.  
 */
public class User extends Utente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief costruttore
	 * richiama il super costruttore ma con @var isAdmin settata a false.
	 * @param nome
	 * @param password
	 */
	public User(String nome, String password) {
		super(nome, password, false);
		// TODO Auto-generated constructor stub
	}
	
}
