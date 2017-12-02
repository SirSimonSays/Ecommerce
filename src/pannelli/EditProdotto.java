package pannelli;

import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import prodotto.HandleProduct;
import prodotto.Prod3x2;
import prodotto.ProdSconto;
import prodotto.Prodotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello per modificare un prodotto
 * già esistente. Prende i dati in ingresso dal prodotto selezionato e
 * li visualizza nelle TextField in modo che l'admin possa modificarne
 * i dati.
 */
public class EditProdotto extends CreaProdotto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "edit";
	
	/**
	 * @var index
	 * serve a memorizzare l'indice del prodotto passato come parametro
	 */
	private static int index = -1;
	
	/**
	 * @var pType
	 * serve a memorizzare la calsse del prodotto da modificare. Ad es. Prod3x2
	 */
	private static String pType;
	
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
			imageButton.setText(p.getphotoPath());
			
			pType = HandleProduct.getProductClass(index);
			switch(pType){
				case "Prodotto":
					nosconto.setSelected(true);
					break;
				case "Prod3x2":
					treXdue.setSelected(true);
					break;
				case "ProdSconto":
					sconto.setSelected(true);
					scontoT.setEnabled(true);
					scontoT.setText(Integer.toString(p.getSconto()));
					break;
			}
		}else{
			System.out.println("errore nell'passaggio dell'indice");
		}
		
	}
	
	/**
	 * @param e
	 * Metodo che gestisce gli eventi a seguito della pressione dei bottoni.
	 * Viene richiamato il metodo del padre (super) perchè viene modificato
	 * solamente il caso in cui venga premuto "Conferma". L'unica differenza
	 * con il metodo del padre (CreaProdotto) è la chiamata del metodo a HandleProduct.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Conferma")){
			
			if(codT.getText().isEmpty() || nomeT.getText().isEmpty() || marcaT.getText().isEmpty() ||
			   catT.getText().isEmpty() || prezzoT.getText().isEmpty() || (sconto.isSelected() && scontoT.getText().isEmpty())){
				JOptionPane.showMessageDialog(this, "Controlla di aver immesso i dati correttamente",
					   "Non possono essere presenti campi vuoti",JOptionPane.WARNING_MESSAGE);
			}else{
				
				//controlla il tipo di prodotto e in base al radioButton cambia la classe, poi rimuove il vecchio
				//prodotto e inserisce quello nuovo nella sua posizione altrimenti rimane come prima.
				Prodotto p = null;
				
				if(nosconto.isSelected())
					p = new Prodotto(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(), catT.getText(), 
							Float.parseFloat(prezzoT.getText()), imageP);
				else if(sconto.isSelected())
					p = new ProdSconto(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(), catT.getText(), 
							Float.parseFloat(prezzoT.getText()), imageP, Integer.parseInt(scontoT.getText()));
				else if(treXdue.isSelected())
					p = new Prod3x2(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(), catT.getText(), 
							Float.parseFloat(prezzoT.getText()), imageP);

				if(prodotto.HandleProduct.modificaProdotto(getIndex(), p)){
					JOptionPane.showMessageDialog(this, "Prodotto modificato correttamente",
							   "Operazione andata a buon fine",JOptionPane.INFORMATION_MESSAGE);
					HandlePanel.switchPanel(AdminPanel.TAG);
				}else{
					JOptionPane.showMessageDialog(this, "I dati immessi non sono corretti",
							   "Errore",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		}else if(e.getSource().equals(nosconto) || e.getSource().equals(treXdue)){
			scontoT.setEnabled(false);
		
		}else if(e.getSource().equals(sconto)){
			scontoT.setEnabled(true);
		
		}else if(e.getSource().equals(imageButton)){
			JFileChooser fc = new JFileChooser();
			FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
			fc.setFileFilter(imageFilter);
			int returnVal = fc.showOpenDialog(new JFrame());
						
			if(returnVal == JFileChooser.APPROVE_OPTION){
				imageP = fc.getSelectedFile().getPath();
				imageButton.setText(imageP);
			}else{
				JOptionPane.showMessageDialog(this,"Non hai selezionato un file adeguato.",
						"Errore nella selezione dell'immagine",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getActionCommand().equals("Annulla")){
			HandlePanel.switchPanel(AdminPanel.TAG);
		}
		
	}
	
}
