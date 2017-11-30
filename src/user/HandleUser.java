package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import pannelli.AdminPanel;
import pannelli.HandlePanel;
import pannelli.UserPanel;

/**
 * @author Simone Cavana
 * @brief classe che implementa il manipolatore degli utenti. Al
 * suo interno vediamo la gestione dell'array degli utenti e l'IO
 * su file per salvare tutti gli utenti e caricarli.
 */
public class HandleUser{
	
	/**
	 * @var nomeFileUtente
	 * costante contente path assoluto del file degli utenti.
	 */
	private static final String nomeFileUtente = "src/user/userFile.txt";
	
	/**
	 * @var fileUtente
	 * oggeto di tipo File che rappresenta il file degli utenti.
	 */
	private static File fileUtente = new File(nomeFileUtente);;
	
	/**
	 * @var utenti
	 * array contenente gli utenti presi dal file fileUtente.
	 */
	private static Vector<Utente> utenti = new Vector<Utente>();
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza.
	 */
	private HandleUser(){
		
	}
	
	/**
	 * @brief metodo per aprire il file "userFile.txt". 
	 * @return stato dell'apertura
	 */
	public static boolean apriFileU(){
		
		boolean openState = false;
		
		if(fileUtente.exists()){
			openState = true;
		}else{
			System.out.println("File degli utenti non trovato");
			openState = false;
		}
		
		return openState;
	}
	
	/**
	 * metodo per leggere gli utenti dal file e inserirli in un array di Utenti,
	 * dopo che è stato aperto il file in modo corretto.
	 * @return stato della lettura
	 */
	@SuppressWarnings("unchecked")
	public static boolean leggiUtenti(){
		
		boolean readState = false;
		
		if(apriFileU()){
			try {
				FileInputStream fis = new FileInputStream(fileUtente);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				utenti = (Vector<Utente>) ois.readObject();
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
	 * @param String nome
	 * @param String password
	 * metodo per il controllo dell'esistenza dell'utente immesso per il login
	 * con il file degli utenti. Questo metodo lavora solamente se prima vanno
	 * a buon fine i metodi di apertura e lettura su file.
	 * @return stato del controllo
	 */
	public static boolean checkUser(String n, String p){
		
		boolean checkState = false;
		Utente a,u;
		
		if(leggiUtenti()){
			
			a = new Admin(n,p);
			u = new User(n,p);
			
			//for(int index = 0; index < utenti.size(); index ++)
			//	System.out.println(utenti.elementAt(index));
			
			if(utenti.contains(a)){
				HandlePanel.switchPanel(AdminPanel.TAG);
				checkState = true;
			}
			
			if(utenti.contains(u)){
				HandlePanel.switchPanel(UserPanel.TAG);
				checkState = true;
			}
		}
		
		return checkState;
	}
	
	/**
	 * @param String nome
	 * @param String password 
	 * @param Boolean isAdmin
	 * metodo per l'inserimento di un nuovo utente all'interno del file. Non
	 * viene effettuato alcun controllo sull'esistenza del file in modo che
	 * se non disponibile venga creato.
	 * @return stato della scrittura
	 */
	public static boolean aggiungiUtente(String n, String p, Boolean t){
		
		boolean writeState = false;
		Utente u;
			
		if (t)
			u = new Admin(n,p);
		else
			u = new User(n,p); 
		
		//controllo blando per vedere che l'utente non esista già
		if(utenti.contains(u))
			return writeState;
				
		utenti.addElement(u);
		
		//for(int index = 0; index < utenti.size(); index ++)
		//	System.out.println(utenti.elementAt(index));
		
		try {
			FileOutputStream fos = new FileOutputStream(fileUtente);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(utenti);
			
			oos.flush();
			oos.close();
			
			System.out.println("File degli utenti salvato");
			writeState = true;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return writeState;
	}
	
}
