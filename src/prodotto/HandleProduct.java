package prodotto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * @author Simone Cavana
 * @brief classe che implementa il gestore dei prodotti al cui interno gestisce 
 * l'IO da file dell'array dei prodotti modificato nella gui dall'admin.
 */
public class HandleProduct {
	
	/**
	 * @var nomeFileProdotto
	 * costante contente path assoluto del file dei prodotti di default.
	 */
	private static final String nomeFileProdotto = "src/prodotto/prodFile.txt";
	
	/**
	 * @var fileProdotto
	 * oggeto di tipo File che rappresenta il file dei prodotti.
	 */
	private static File fileProdotto = new File(nomeFileProdotto);
	
	/**
	 * @var prodotti
	 * Vettore che contiene le istanze di tutti i prodotti.
	 */	
	private static Vector<Prodotto> prodotti = new Vector<Prodotto>();
	
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private HandleProduct() {
		// TODO Auto-generated constructor stub
	
	}
	
	/**
	 * @brief metodo per controllare che il file @var fileProdotto esista. 
	 * @return stato dell'apertura
	 */
	public static boolean apriFileP(){
		
		boolean openState = false;
		
		if(fileProdotto.exists()){
			openState = true;
		}else{
			System.out.println("File dei prodotti non trovato");
			openState = false;
		}
		
		return openState;
	}
	
	/**
	 * @brief lettura dei prodotti da file
	 * metodo per leggere i prodotti dal file e inserirli in un array di
	 * prodotti, dopo che è stato controllata l'esistenza del file.
	 * @return stato della lettura
	 */
	public static boolean leggiProdotti(){
		
		boolean readState = false;
		
		if(apriFileP()){
			try {
				FileInputStream fis = new FileInputStream(fileProdotto);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				prodotti = (Vector<Prodotto>) ois.readObject();
				ois.close();
				readState = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return readState;
	}
	
	/**
	 * @brief scrittura dei prodotti su file
	 * metodo per scrivere l'array @var prodotti sul file @var fileProdotto.
	 * @return stato della scrittura
	 */
	public static boolean scriviProdotti(){
		
		boolean writeState = false;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileProdotto);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(prodotti);
			
			oos.flush();
			oos.close();
			
			System.out.println("File dei prodotti salvato");
			writeState = true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return writeState;
	}
	
	/**
	 * @brief getter del numero di prodotti
	 * @return numero dei prodotti
	 */
	public static int getProductCount(){
		return prodotti == null ? 0 : prodotti.size();
	}
	
	/**
	 * @brief getter dell'indice di p
	 * @param p
	 * @return l'indice del prodotto p 
	 */
	public static int getProductIndex(Prodotto p){
		return prodotti.indexOf(p);
	}
	
	/**
	 * @brief getter del prodotto all'index
	 * @param index
	 * @return prodotto all'indice index
	 */
	public static Prodotto getProduct(int index){
		if(index < prodotti.size()){
			return prodotti.get(index);
		}
		return null;
	}
	
	/**
	 * @brief serve a controllare il tipo di prodotto contenuto all'index.
	 * Inoltre toglie i primi 14 caratteri in modo da avere solo il nome della
	 * classe e non anche quello del package.
	 * String iniziale = "class prodotto.Prodotto"
	 * String finale   = "Prodotto"
	 * @param index
	 * @return la classe dell'elemento all'indice passato come parametro
	 */
	public static String getProductClass(int index){
		return prodotti.elementAt(index).getClass().toString().substring(15);
	}
	
	/**
	 * @brief scrittura sul file di un nuovo prodotto
	 * @param p
	 * metodo per l'inserimento di un nuovo prodotto all'interno del file. Non
	 * viene effettuato alcun controllo sull'esistenza del file in modo che
	 * se non disponibile venga creato.
	 * @return stato della scrittura
	 */
	public static boolean aggiungiProdotto(Prodotto p){
		
		boolean addState = false;
		
		//controllo blando per verificare che il prodotto non sia presente
		if(prodotti.contains(p))
			return addState;
		
		prodotti.addElement(p);
		
		//for(int index = 0; index < prodotti.size(); index ++)
		//	System.out.println(prodotti.elementAt(index));
		
		addState = scriviProdotti();
				
		return addState;
	}
	
	/**
	 * @brief rimozione di un prodotto
	 * @param index
	 * metodo per la rimozione prodotto all'interno del file all'indice
	 * @var index dell'array.
	 * @return stato dell'operazione
	 */
	public static boolean rimuoviProdotto(int index){
		boolean removeState = false;
		
		prodotti.remove(index);
		
		removeState = scriviProdotti();
				
		return removeState;
		
	}
	
	/**
	 * @brief modifica di un prodotto
	 * @param index
	 * @param p
	 * metodo per modificare un prodotto all'interno del file all'indice
	 * @var index dell'array.
	 * @return stato dell'operazione
	 */
	public static boolean modificaProdotto(int index, Prodotto p){
		boolean editState = false;
		
		//controllo blando per verificare che il prodotto non sia presente
		if(prodotti.contains(p))
			return editState;
		
		prodotti.set(index, p);
		
		editState = scriviProdotti();
		
		return editState;
	}
	
	/**
	 * @brief metodo per dare la possibilità all'utente di scegliere su
	 * quale file salvare e da cui caricare i prodotti momentaneamente,
	 * perchè al prossimo avvio del programma verrà di nuovo scelto il
	 * file di default che si trova a nomeFileProdotto.
	 * @param f
	 * @return false se il file selezionato è vuoto, true altrimenti
	 */
	public static boolean setFileP(File f){
		
		boolean changeState = false;
		
		fileProdotto = f;
		prodotti.removeAllElements();
		
		/**
		 * modo per controllare se il file è vuoto, in modo che non venga generato alcun
		 * errore in fase di lettura di un file vuoto.
		 */
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(f.getAbsolutePath()));
			
			if(br.readLine() == null){
			    System.out.println("Il file è vuoto");
			}else{
				changeState = leggiProdotti();
			}
			
			br.close();
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     	
		
		return changeState;
	}
	
	/**
	 * @brief ricerca veloce di un prodotto in base al nome
	 * @param text
	 * @return il vettore degli elementi che corrispondono altimenti null
	 */
	public static Vector<Prodotto> saerchName(String text) {
		/**
		 * @var sp
		 * vettore contenente gli elementi che soddisfano la ricerca sul nome.
		 */
		Vector<Prodotto> sp = new Vector<Prodotto>();
		
		for(int i = 0; i < prodotti.size(); i++){
			if(prodotti.elementAt(i).getNome().toLowerCase().equals(text.toLowerCase()))
				sp.addElement((prodotti.elementAt(i)));
		}
		
		return sp;
	}
}
