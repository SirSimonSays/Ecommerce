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
	 * Vettore che contiene le istante di tutti i prodotti
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
	 * @brief scrittura sul file
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
	public static boolean rimuoviProdotto(int i, String n, String m, String c){
		
		
	}*/
}
