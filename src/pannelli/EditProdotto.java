package pannelli;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import prodotto.HandleProduct;
import prodotto.Prod3x2;
import prodotto.ProdSconto;
import prodotto.Prodotto;

public class EditProdotto extends CreaProdotto {

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "edit";
	
	/**
	 * @var index
	 * serve a tenere in memoria l'indice del prodotto passato come parametro
	 */
	private static int index = -1;
	
	/**
	 * costruttore vuoto perchè è uguale al CreaProdotto e cambiano solo
	 * le funzioni
	 * @param handlePanel
	 */
	public EditProdotto(HandlePanel handlePanel){
		super(handlePanel);
		
	}
	
	/**
	 * @brief getter dell'indice
	 * metodo che ritorna il valore attuale dell'indice
	 * @return indice
	 */
	public static int getIndex() {
		return index;
	}
	
	/**
	 * @brief setter dell'indice
	 * metodo per settare l'indice del prodotto da editare
	 * @param i
	 */
	public static void setIndex(int i){
		index = i;
	}

	/**
	 * @brief All'ingresso della schermata carica i dati del prodotto.
	 */
	@Override
	public void onEnter(){
		
		//controllo che l'indice sia stato settato correttamente e non si vada in
		//locazioni della memoria non adatti.
		if(getIndex() != -1){
			Prodotto p = HandleProduct.getProduct(index);
					
			codT.setText(Integer.toString(p.getId()));
			codT.setEditable(false);
			
			nomeT.setText(p.getNome());
			marcaT.setText(p.getMarca());
			catT.setText(p.getCategoria());
			prezzoT.setText(Float.toString(p.getPrezzo()));
			//fotoT.setPhotoPath(p.getphotoPath);
			
			String c = HandleProduct.getProductClass(index);
			switch(c){
			case "prodotto.Prodotto":
				nosconto.setSelected(true);
			case "prodotto.Prod3x2":
				treXdue.setSelected(true);
			case "prodotto.ProdSconto":
				sconto.setSelected(true);
				scontoT.setText(Integer.toString(p.getSconto()));
		}
			
			
		}else{
			System.out.println("errore nell'passaggio dell'indice");
		}
		
	}
	
	/**
	 * @param e
	 * Metodo che gestisce gli eventi a seguito della pressione dei bottoni.
	 * Nel caso if vengono analizzati i dati e modificato il prodotto passato 
	 * come parametro e riscritto nel file "prodFile.txt" mentre nell'else viene
	 * annullata la procedura di immissione e chiusa la finestra di creazione.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Conferma")){
			
			if(codT.getText().isEmpty() || nomeT.getText().isEmpty() || marcaT.getText().isEmpty() ||
			   catT.getText().isEmpty() || prezzoT.getText().isEmpty() || (sconto.isSelected() && scontoT.getText().isEmpty())){
				JOptionPane.showMessageDialog(this, "Controlla di aver immesso i dati correttamente",
					   "Non possono essere presenti campi vuoti",JOptionPane.WARNING_MESSAGE);
			}else{
				if(prodotto.HandleProduct.modificaProdotto(index, nomeT.getText(), marcaT.getText(),
				   catT.getText(), Float.parseFloat(prezzoT.getText()), "ciao")){
					JOptionPane.showMessageDialog(this, "Prodotto modificato correttamente",
							   "Operazione andata a buon fine",JOptionPane.INFORMATION_MESSAGE);
					HandlePanel.switchPanel(AdminPanel.TAG);
				}else{
					JOptionPane.showMessageDialog(this, "I dati immessi non sono corretti",
							   "Errore",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}else if(e.getActionCommand().equals("Annulla")){
			HandlePanel.switchPanel(AdminPanel.TAG);
		}
	}
	
}
