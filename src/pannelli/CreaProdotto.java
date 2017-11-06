package pannelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import prodotto.Prod3x2;
import prodotto.ProdSconto;
import prodotto.Prodotto;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello per la creazione di un
 * nuovo prodotto, offre quindi la possibilità di inserire tutti i dati
 * relativi a un prodotto e darne la conferma.
 */
public class CreaProdotto extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "creaP";
	
	/**
	 * variabili che rappresentano le componenti del pannello, 
	 * le Label sono le scritte che indicano all'utente cosa inserire 
	 * dentro le TextField. Dalle Textfield vengono ricavati i dati
	 * per creare i nuovi prodotti. Queste ultime sono dichiarate 
	 * protected in quanto vengono utilizzati i loro metodi anche dentro 
	 * alla classe {@link EditProdotto}
	 */
	private JLabel codL, nomeL, marcaL, catL, prezzoL, fotoL, scontoL;
	protected JTextField codT, nomeT, marcaT, catT, prezzoT, scontoT;

	/**
	 * @var imageP
	 * stringa utilizzata per salvare il path dell'immagine selezionata
	 * nel JFileChooser, dichiarata protected perchè utilizzata anche
	 * nella classe {@link EditProdotto}.
	 */
	protected String imageP = null;
	
	/**
	 * gruppo di radioButton composto da tre Radio per selezionare il tipo
	 * di classe in cui andare a istanziare il nuovo prodotto.
	 * I tre radioButton sono protected in quanto vengono utilizzati anche
	 * nella classe {@link EditProdotto}.
	 */
	private ButtonGroup bg1;
	protected JRadioButton sconto, nosconto, treXdue;
	
	/**
	 * bottoni che danno la possibilità all'utente di confermare o annullare
	 * la creazione del prodotto.
	 */
	private JButton okButton, cancelButton;
	
	/**
	 * @var imageButton
	 * bottone per la creazione di un JFileChooser per selezionare l'immagine 
	 * dal pc dell'utente. Variabile protected perchè utilizzata in {@link EditProdotto}
	 */
	protected JButton imageButton;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public CreaProdotto(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		/**
		 * Creazione di un nuovo pannello per centrare gli elementi del login nella pagina.
		 * Il meccanismo funziona così: LoginPanel che contiene tutto ed è gestito da un 
		 * layout gbl. Inoltre al pannello principale vengono aggiunti un secondo pannello
		 * jp e il suo layout gl.
		 * Impostazione del colore di sfondo per entrambi i pannelli. 
		 */
		JPanel jp = new JPanel();
		setBackground(Color.yellow);
		jp.setBackground(Color.yellow);
		
		GroupLayout gl = new GroupLayout(jp);
		jp.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gblc = new GridBagConstraints();
		gblc.anchor = GridBagConstraints.CENTER;
		setLayout(gbl);
		add(jp, gblc);
		
		/**
		 * Dichiarazione dei widget e dei relativi parametri.
		 * Aggiunta dei widget al LoginPanel. 
		 */
		codL = new JLabel("Codice: ");
		codT = new JTextField(15); 
		codT.setEditable(true);
		
		nomeL = new JLabel("Nome: ");
		nomeT = new JTextField(15); 
		nomeT.setEditable(true);
		
		marcaL = new JLabel("Marca: ");
		marcaT = new JTextField(15); 
		marcaT.setEditable(true);
		
		catL = new JLabel("Categoria: ");
		catT = new JTextField(15); 
		catT.setEditable(true);
		
		prezzoL = new JLabel("Prezzo: ");
		prezzoT = new JTextField(15); 
		prezzoT.setEditable(true);
		
		fotoL = new JLabel("Foto: ");
		
		scontoL = new JLabel("Sconto: ");
		scontoT = new JTextField(15);
		scontoT.setEnabled(false);
		
		nosconto = new JRadioButton("Prezzo completo");
		nosconto.addActionListener(this);
	
		sconto = new JRadioButton("Sconto");
		sconto.addActionListener(this);
		
		treXdue = new JRadioButton("3x2");
		treXdue.addActionListener(this);
		
		bg1 = new ButtonGroup();
		bg1.add(nosconto);
		bg1.add(sconto);
		bg1.add(treXdue);
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		imageButton = new JButton("Premere qui per selezionare l'immagine");
		imageButton.addActionListener(this);
		
		/**
		 * Posizionamento dei widget all'interno del layout gl.
		 */
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(codL)
							.addComponent(nomeL)
							.addComponent(marcaL)
							.addComponent(catL)
							.addComponent(prezzoL)
							.addComponent(fotoL)
							.addGroup(gl.createSequentialGroup()
									.addGap(20)
									//inserimento di uno spazio per dare più leggibilità alla gui
									) 
							.addComponent(scontoL)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(codT)
							.addComponent(nomeT)
							.addComponent(marcaT)
							.addComponent(catT)
							.addComponent(prezzoT)
							.addComponent(imageButton)
							.addGroup(gl.createSequentialGroup()
									.addGap(20)
									//inserimento di uno spazio per dare più leggibilità alla gui
									)
							.addGroup(gl.createSequentialGroup()
									.addComponent(nosconto)
									.addComponent(sconto)
									.addComponent(treXdue)
									)
							.addComponent(scontoT)
							.addGroup(gl.createSequentialGroup()
									.addComponent(okButton)
									.addComponent(cancelButton)
									)
							)
		);
		gl.setVerticalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(codL)
							.addComponent(codT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(nomeL)
							.addComponent(nomeT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(marcaL)
							.addComponent(marcaT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(catL)
							.addComponent(catT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(prezzoL)
							.addComponent(prezzoT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(fotoL)
							.addComponent(imageButton)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addGap(20)
							//inserimento di uno spazio per dare più leggibilità alla gui
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(nosconto)
							.addComponent(sconto)
							.addComponent(treXdue)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(scontoL)
							.addComponent(scontoT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton)
							)
		);

	}
	
	/**
	 * @brief metodo chiamato in automatico all'apertura della schermata
	 */
	@Override
	public void onEnter(){
		nosconto.setSelected(true);
		scontoT.setEnabled(false);
	}
	
	/**
	 * @param e
	 * Metodo che gestisce gli eventi a seguito della pressione dei bottoni.
	 * Nel caso if vengono analizzati i dati e inseriti nel file "prodFile.txt"
	 * mentre nell'else viene annullata la procedura di immissione e chiusa la
	 * finestra di creazione.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		if(e.getActionCommand().equals("Conferma")){
			
			if(codT.getText().isEmpty() || nomeT.getText().isEmpty() || marcaT.getText().isEmpty() ||
			   catT.getText().isEmpty() || prezzoT.getText().isEmpty() || (sconto.isSelected() && scontoT.getText().isEmpty())){
				JOptionPane.showMessageDialog(this, "Controlla di aver immesso i dati correttamente",
					   "Non possono essere presenti campi vuoti",JOptionPane.WARNING_MESSAGE);
			}else{
				
				Prodotto p;
				
				if(nosconto.isSelected())
					p = new Prodotto(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(),catT.getText(),
							Float.parseFloat(prezzoT.getText()), imageP);
				else if(sconto.isSelected())
					p = new ProdSconto(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(),catT.getText(),
							Float.parseFloat(prezzoT.getText()), imageP, Integer.parseInt(scontoT.getText()));
				else
					p = new Prod3x2(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(),catT.getText(),
							Float.parseFloat(prezzoT.getText()), imageP);
				
				if(prodotto.HandleProduct.aggiungiProdotto(p)){
					JOptionPane.showMessageDialog(this, "Prodotto inserito correttamente",
							   "Operazione andata a buon fine",JOptionPane.INFORMATION_MESSAGE);
					HandlePanel.switchPanel(AdminPanel.TAG);
				}else{
					JOptionPane.showMessageDialog(this, "Il prodotto potrebbe già esistere o i dati immessi non sono corretti",
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
				imageP = fc.getSelectedFile().toString();
				
			}else{
				JOptionPane.showMessageDialog(this,"Non hai selezionato un file adeguato.",
						"Errore nella selezione dell'immagine",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getActionCommand().equals("Annulla")){
			HandlePanel.switchPanel(AdminPanel.TAG);
		}
	}

	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 * vengono puliti i JtextField in modo che siano vuoti alla prossima
	 * apertura della finestra.
	 */
	public void onExit(){
		codT.setText("");
		nomeT.setText("");
		marcaT.setText("");
		catT.setText("");
		prezzoT.setText("");
		scontoT.setText("");
	}
	
}
