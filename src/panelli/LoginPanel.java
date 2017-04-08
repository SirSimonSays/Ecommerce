package panelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import user.Admin;
import user.User;
import user.Utente;

public class LoginPanel  extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata  
	 */
	public static final String TAG = "login";

	private JLabel nameLabel, pswLabel;
	private JTextField nameTxt;
	private JPasswordField pswTxt;
	private JButton okButton, gearButton;
	
	private Utente[] arrayUtenti = new Utente[2];
	private int contaProve;
	
	/**
	 * Costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra
	 */
	public LoginPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		/**definizione di una "base di dati" di utenti e relativi attributi*/		
		Admin admin1 = new Admin("simone","simone@gmail.com","ciao");
		User user1 = new User("carlo","carlo@libero.it","mamma");
		arrayUtenti[0] = admin1; 
		arrayUtenti[1] = user1;
			
		contaProve = 0;
		
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
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		gearButton = new JButton(new ImageIcon("/image/gear.png"));
		gearButton.addActionListener(this);
		
		add(nameLabel);
		add(nameTxt);
		add(pswLabel);
		add(pswTxt);
		add(okButton);
		
		/**
		 * Posizionamento dei widget all'interno del layout gl.
		 */
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameLabel)
							.addComponent(pswLabel)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameTxt)
							.addComponent(pswTxt)
							.addGroup(gl.createSequentialGroup()
									.addComponent(okButton)
									.addComponent(gearButton)
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
							.addComponent(gearButton)
							)
		);
		
	}
	
	/**
	 * Controllo delle stringhe immesse con i dati nel "database" se true => controlla se
	 * l'utente riconosciuto è admin o user e lancia una nuova finestra in base ai generics, 
	 * altrimenti da errore e lascia riprovare l'immissione dei dati. I tentativi sono
	 * limitati a un massimo di 5 in modo da ridurre la probabilità di un intrusione.
	 * Utilizzo di una variabile temp di tipo String perchè il tipo di ritorno di getPassword
	 * è un array di caratteri e per effettuare il controllo con la password all'interno
	 * dell'array bisogna effettuare un cast implicito.
	 */ 
	@Override
	public void actionPerformed(ActionEvent e){ 
		if(e.getActionCommand().equals("Conferma")){
			String temp = new String(pswTxt.getPassword());
			boolean checklogin = false;
				
			if(contaProve >= 5){
				nameTxt.setEditable(false);
				pswTxt.setEditable(false);
				nameTxt.setText("");
				pswTxt.setText("");
				JOptionPane.showMessageDialog(this, "Non hai più tentativi disponibili, contatta il nostro servizio clienti",
					    "A caccia di malintenzionati",JOptionPane.ERROR_MESSAGE);
			}else{
				for(int i = 0; i < arrayUtenti.length; i++){
					if(nameTxt.getText().equals(arrayUtenti[i].getNome()) && temp.equals(arrayUtenti[i].getPassword())){
						if(arrayUtenti[i].getIsAdmin()){
							handlePanel.switchPanel(AdminPanel.TAG);
							checklogin = true;
						}else{
							handlePanel.switchPanel(UserPanel.TAG);
							checklogin = true;
						}
					}
				}
				
				if(!checklogin){
					contaProve++;
					JOptionPane.showMessageDialog(this,"Controlla le tue credenziali, user e/o password sono errati",
							"Credenziali errate",JOptionPane.WARNING_MESSAGE);
					nameTxt.setText("");
					pswTxt.setText("");
				}
			}	
		}else if(e.getActionCommand().equals(gearButton)){
			//add user
		}
	}
		

}
