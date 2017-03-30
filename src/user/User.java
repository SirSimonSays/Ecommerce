package user;


public class User extends Utente {

	private String indirizzoSpedizione;
	private int phoneNumber;
		
	public User(String nome, String email, String password) {
		super(nome, email, password, false);
		// TODO Auto-generated constructor stub
		indirizzoSpedizione = null;
		phoneNumber = 0;
	}
	
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void visualizzaProdotti(){
		
	}
	
	public void mettiNelCarrello(){
		
	}
	
	public void togliDalCarrello(){
		
	}
	
	public void ricerca(){
		
	}
	
	public void filtra(){
		
	}
	
	public void acquista(){
		
	}
	
}
