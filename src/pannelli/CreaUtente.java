package pannelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreaUtente extends DefaultPanel{

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata  
	 */
	public static final String TAG = "creaU";

	private JLabel nameLabel, pswLabel;
	private JTextField nameTxt;
	private JPasswordField pswTxt;
	private JCheckBox checkIsAdmin;
	private JButton okButton, cancelButton;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public CreaUtente(HandlePanel handlePanel) {
		super(handlePanel);
		// TODO Auto-generated constructor stub
	
		/**
		 * Creazione di un nuovo pannello per centrare gli elementi del login nella pagina.
		 * Il meccanismo funziona così: LoginPanel che contiene tutto ed è gestito da un 
		 * layout gbl. Inoltre al pannello principale vengono aggiunti un secondo pannello
		 * jp e il suo layout gl.
		 * Impostazione del colore di sfondo per entrambi i pannelli. 
		 */
		JPanel jp = new JPanel();
		setBackground(Color.green);
		jp.setBackground(Color.green);
		
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
		nameLabel = new JLabel("Username: ");
		nameTxt = new JTextField(15); 
		nameTxt.setEditable(true);
		pswLabel = new JLabel("Password: ");
		pswTxt = new JPasswordField(15);
		pswTxt.setEditable(true);
		checkIsAdmin = new JCheckBox("Admin");
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		add(nameLabel);
		add(nameTxt);
		add(pswLabel);
		add(pswTxt);
		add(okButton);
		add(cancelButton);
		
		/**
		 * Posizionamento dei widget all'interno del layout gl.
		 */
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameLabel)
							.addComponent(pswLabel)
							.addComponent(checkIsAdmin)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameTxt)
							.addComponent(pswTxt)
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
							.addComponent(nameLabel)
							.addComponent(nameTxt)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(pswLabel)
							.addComponent(pswTxt)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton)
							.addComponent(checkIsAdmin)
							)
		);
	}

	/**
	 * @param e
	 * Metodo che gestisce gli eventi a seguito della pressione dei bottoni.
	 * Nel caso if vengono analizzati i dati e inseriti nel file "userFile.txt"
	 * mentre nell'else viene annullata la procedura di immissione e chiusa la
	 * finestra di creazione. Infine vengono puliti i JtextField in modo che
	 * siano vuoti alla prossima apertura della finestra di creazione.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		if(e.getActionCommand().equals("Conferma")){
			String temp = new String(pswTxt.getPassword());
			
			if(nameTxt.getText().isEmpty() || temp.isEmpty()){
				JOptionPane.showMessageDialog(this, "Controlla di aver immesso i dati correttamente",
					   "Nome e password non possono essere vuoti",JOptionPane.ERROR_MESSAGE);
			}else{
				if(user.HandleUser.aggiungiUtente(nameTxt.getText(), temp, checkIsAdmin.isSelected())){
					JOptionPane.showMessageDialog(this, "Utente inserito correttamente",
							   "Ok",JOptionPane.INFORMATION_MESSAGE);
					HandlePanel.switchPanel(LoginPanel.TAG);
				}else{
					JOptionPane.showMessageDialog(this, "L'utente potrebbe già esistere o i dati immessi non sono corretti",
							   "è stato riscontrato un problema!",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getActionCommand().equals("Annulla")){
			HandlePanel.switchPanel(LoginPanel.TAG);
		}
		
	}
	
	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		nameTxt.setText("");
		pswTxt.setText("");
		checkIsAdmin.setSelected(false);
	}
}
