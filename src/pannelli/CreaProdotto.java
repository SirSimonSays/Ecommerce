package pannelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Locale.Category;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prodotto.Prodotto;

public class CreaProdotto extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "creaP";
	
	private JLabel codL, nomeL, marcaL, catL, prezzoL, fotoL;
	protected JTextField codT, nomeT, marcaT, catT, prezzoT;
	private JButton okButton, cancelButton;
	
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
		/*widget in cui inserire la foto*/
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		add(codL);
		add(codT);
		add(nomeL);
		add(nomeT);
		add(marcaL);
		add(marcaT);
		add(catL);
		add(catT);
		add(prezzoL);
		add(prezzoT);
		add(fotoL);
		//add(fotoT);
		add(okButton);
		add(cancelButton);
		
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
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(codT)
							.addComponent(nomeT)
							.addComponent(marcaT)
							.addComponent(catT)
							.addComponent(prezzoT)
							//.addComponent(fotoT)
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
							//.addComponent(fotoT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton)
							)
		);

	}
	
	/**
	 * @param e
	 * Metodo che gestisce gli eventi a seguito della pressione dei bottoni.
	 * Nel caso if vengono analizzati i dati e inseriti nel file "prodFile.txt"
	 * mentre nell'else viene annullata la procedura di immissione e chiusa la
	 * finestra di creazione. Infine vengono puliti i JtextField in modo che
	 * siano vuoti alla prossima apertura della finestra di creazione.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		if(e.getActionCommand().equals("Conferma")){
			
			if(codT.getText().isEmpty() || nomeT.getText().isEmpty() || marcaT.getText().isEmpty() ||
			   catT.getText().isEmpty() || prezzoT.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Controlla di aver immesso i dati correttamente",
					   "Non possono essere presenti campi vuoti",JOptionPane.WARNING_MESSAGE);
			}else{
				if(prodotto.HandleProduct.aggiungiProdotto(Integer.parseInt(codT.getText()), nomeT.getText(), marcaT.getText(),
				   catT.getText(), Float.parseFloat(prezzoT.getText()), "ciao")){
					JOptionPane.showMessageDialog(this, "Prodotto inserito correttamente",
							   "Operazione andata a buon fine",JOptionPane.INFORMATION_MESSAGE);
					HandlePanel.switchPanel(AdminPanel.TAG);
				}else{
					JOptionPane.showMessageDialog(this, "Il prodotto potrebbe già esistere o i dati immessi non sono corretti",
							   "Errore",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getActionCommand().equals("Annulla")){
			HandlePanel.switchPanel(AdminPanel.TAG);
		}
	}

	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		codT.setText("");
		nomeT.setText("");
		marcaT.setText("");
		catT.setText("");
		prezzoT.setText("");
	}

	
}
