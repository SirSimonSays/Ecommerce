
public abstract class Utente {
	
	private int id;
	private String nome;
	private String email;
	private String password;
	private Boolean isAdmin;
	
	public Utente(int id, String nome, String email, String password, Boolean isAdmin) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
		
}
