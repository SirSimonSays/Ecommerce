package prodotto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class HandleProduct {
	
	/**
	 * @var nomeFileProdotto
	 * costante contente path assoluto del file dei prodotti.
	 */
	private static final String nomeFileProdotto = "src/prodotto/prodFile.txt";
	
	/**
	 * @var fileProdotto
	 * oggeto di tipo File che rappresenta il file dei prodotti.
	 */
	private static File fileProdotto = new File(nomeFileProdotto);
	
	/**
	 * @var prodotti
	 * Vettore che contiene le istanze di tutti i prodotti
	 */	
	private static Vector<Prodotto> prodotti = new Vector<Prodotto>();
	
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private HandleProduct() {
		// TODO Auto-generated constructor stub
	
	}
	
	/**
	 * @brief metodo per aprire il file "prodFile.txt". 
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
	 * metodo per leggere i prodotti dal file e inserirli in un array di
	 * Prodotti, dopo che è stato aperto il file in modo corretto.
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
	 * @brief scrittura sul file di un nuovo prodotto
	 * @param i id prodotto
	 * @param n nome prodotto
	 * @param m marca prodotto
	 * @param c categoria prodotto
	 * @param pr prezzo prodotto
	 * @param pp percorso della foto del prodotto
	 * metodo per l'inserimento di un nuovo prodotto all'interno del file. Non
	 * viene effettuato alcun controllo sull'esistenza del file in modo che
	 * se non disponibile venga creato.
	 * @return stato della scrittura
	 */
	public static boolean aggiungiProdotto(int i, String n, String m, String c, float pr, String pp){
		
		boolean writeState = false;

		Prodotto p;
		p = new Prodotto(i, n, m, c, pr, pp);
		
		//controllo blando per verificare che il prodotto non sia presente
		if(prodotti.contains(p))
			return writeState;
		
		prodotti.addElement(p);
				
		//for(int index = 0; index < prodotti.size(); index ++)
		//	System.out.println(prodotti.elementAt(index));
		
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
	
	/*
	 * @brief rimozione di un prodotto
	 * metodo per la rimozione prodotto all'interno del file.
	 * @return stato dell'operazione
	 */
	public static boolean rimuoviProdotto(int index){
		boolean removeState = false;
		
		prodotti.remove(index);
		
		try {
			FileOutputStream fos = new FileOutputStream(fileProdotto);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(prodotti);
			
			oos.flush();
			oos.close();
			
			System.out.println("File dei prodotti salvato");
			removeState = true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return removeState;
		
	}
	
	/*
	 * @brief modifica di un prodotto
	 * metodo per modificare un prodotto all'interno del file.
	 * @return stato dell'operazione
	public static boolean modificaProdotto(int index){
		
		
	}*/
}
