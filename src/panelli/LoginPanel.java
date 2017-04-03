package panelli;

import java.awt.event.ActionEvent;
import javax.swing.*;
import user.Admin;
import user.User;
import user.Utente;

public class LoginPanel  extends DefaultPanel{
	
	public static final String TAG = "login";
	
	private GroupLayout gl;
	private JLabel nameLabel, pswLabel;
	private JTextField nameTxt;
	private JPasswordField pswTxt;
	private JButton okButton;
	
	private Utente[] arrayUtenti = new Utente[2];
	private int contaProve;
	
	/**
	 * costruttore che definisce e setta tutti gli oggetti della finestra
	 */
	public LoginPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		/**definizione di una "base di dati" di utenti e relativi attributi*/		
		Admin admin1 = new Admin("simone","simone@gmail.com","ciao");
		User user1 = new User("carlo","carlo@libero.it","mamma");
		arrayUtenti[0] = admin1; 
		arrayUtenti[1] = user1;
			
		contaProve = 0;
		
		//this.setBorder(BorderFactory.createEmptyBorder(1270/2 - 100, 650/2 - 100, 250, 250));
		
		gl = new GroupLayout(this);
		this.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		nameLabel = new JLabel("Username: ");
		nameTxt = new JTextField(15); 
		nameTxt.setEditable(true);
		pswLabel = new JLabel("Password: ");
		pswTxt = new JPasswordField(15); 
		pswTxt.setEditable(true);
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		this.add(nameLabel);
		this.add(nameTxt);
		this.add(pswLabel);
		this.add(pswTxt);
		this.add(okButton);
		
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
							.addComponent(okButton)
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
					.addComponent(okButton)
		);
		
	}
	
	/**
	 * controllo delle stringhe immesse con i dati nel "database" se true => controlla se
	 * l'utente riconosciuto è admin o user e lancia una nuova finestra in base ai generics, 
	 * altrimenti da errore e lascia riprovare l'immissione dei dati. I tentativi sono
	 * limitati a un massimo di 5 in modo da ridurre la probabilità di un intrusione.
	 */ 
	public void actionPerformed(ActionEvent e){ 
		
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
				if(nameTxt.getText().equals(arrayUtenti[i].getNome()) && pswTxt.getPassword().equals(arrayUtenti[i].getPassword())){
					if(arrayUtenti[i].getIsAdmin()){
						handlepanel.switchPanel(AdminPanel.TAG);
						checklogin = true;
						System.out.println("admin");
					}else{
						handlepanel.switchPanel(LoginPanel.TAG);
						checklogin = true;
						System.out.println("user");
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
	}

}
