
public class Prodotto{

	private int id;
	private String nome;
	private String marca;
	private float prezzo;
	private String fotoPath;
	//categoria
	
	public Prodotto(int id, String nome, String marca, float prezzo, String fotoPath){
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
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
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}

}
