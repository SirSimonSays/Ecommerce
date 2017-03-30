package prodotto;

public class Prodotto {

	private int id;
	private String nome;
	private String marca;
	private String categoria;
	private float prezzo;
	private String fotoPath;
	
	public Prodotto(int id, String nome, String marca, String categoria, float prezzo, String fotoPath){
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.setCategoria(categoria);
		this.prezzo = prezzo;
		this.fotoPath = fotoPath;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getMarca() {
		return marca;
	}
	public String getCategoria() {
		return categoria;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public String getFotoPath() {
		return fotoPath;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}
}
