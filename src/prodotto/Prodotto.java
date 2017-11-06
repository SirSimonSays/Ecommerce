package prodotto;

import java.io.Serializable;

/**
 * @author Simone Cavana
 * @brief classe che implementa il Prodotto che poi si suddivide in altre due 
 * sottoclassi {@link ProdSconto} e {@link Prod3x2}.
 */
public class Prodotto implements Serializable{

	private int id;
	private String nome;
	private String marca;
	private String categoria;
	private float prezzo;
	private String photoPath;
	
	/**
	 * @brief costruttore
	 */
	public Prodotto(int id, String nome, String marca, String categoria, float prezzo, String photoPath){
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.setCategoria(categoria);
		this.setPrezzo(prezzo);
		this.setPhotoPath(photoPath);
	}
	
	/**
	 * @brief getter del codice univoco
	 * @return id del prodotto
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @brief getter del del nome
	 * @return nome del prodotto
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @brief getter della marca
	 * @return marca del prodotto
	 */
	public String getMarca() {
		return marca;
	}
	
	/**
	 * @brief getter della categoria
	 * @return categoria del prodotto
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @brief getter del prezzo
	 * @return prezzo del prodotto
	 */
	public float getPrezzo() {
		return prezzo;
	}

	/**
	 * @brief getter percorso della foto
	 * @return percorso della foto del prodotto
	 */
	public String getphotoPath() {
		return photoPath;
	}
	
	/**
	 * @brief setter del codice univoco
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @brief setter del nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @brief setter della marca
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/**
	 * @brief setter della categoria
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * @brief setter del prezzo controllando che se è minore
	 * di zero viene settato a zero.
	 * @param prezzo
	 */
	public void setPrezzo(float prezzo) {
		if (prezzo < 0)
			prezzo = 0;
		this.prezzo = prezzo;
	}
	
	/**
	 * @brief setter del percorso della foto controllando che se è nullo
	 * o vuoto viene settato a un valore di default.
	 * @param photoPath
	 */
	public void setPhotoPath(String photoPath) {
		if (photoPath == null || photoPath.equals(""))
			photoPath = "/image/default.png";
		this.photoPath = photoPath;
	}
	
	/**
	 * @brief redifinizione del metodo toString per la stampa di un oggetto di
	 * tipo Prodotto.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + nome + " " + marca + " " + categoria + " " + prezzo + " " + photoPath;
	}
	
	/**
	 * @brief redifinizione del metodo equals per confrontare un elemento con
	 * un elemento del Vector.
	 */
	@Override
	public boolean equals(Object o){
		if(o == null || !getClass().equals(o.getClass())) return false;
		if(o == this) return true;
		
		Prodotto p = (Prodotto) o;
		if(this.id == p.id && 
		   this.nome.compareTo(p.nome) == 0 && 
		   this.marca.compareTo(p.marca) == 0 && 
		   this.categoria.compareTo(p.categoria) == 0)
			return true;
		return false;
		
	}
	
	/**
	 * @brief getter dello sconto di un prodotto
	 * il metodo è presente per compatibilità nel caso di up-casting ma ritorna sempre 0
	 */
	public int getSconto(){
		return 0;
	}
	
	/**
	 * @brief setter dello sconto di un prodotto
	 * il metodo è presente per compatibilità nel caso di up-casting ma non implementato
	 */
	public void setSconto(int s){
		
	}
	
	/**
	 * @brief Ritorna il totale dell'acquisto di n prodotti
	 * @return Totale acquisto
	 */
	public float getTotal(int n){
		return prezzo * n;
	}
	
	
}
