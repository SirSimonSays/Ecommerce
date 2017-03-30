package user;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginWindow {
	
	private JFrame loginFrame;
	private JPanel loginPanel;
	private GroupLayout gl;
	private JLabel nameLabel, pswLabel;
	private JTextField nameTxt;
	private JPasswordField pswTxt;
	private JButton okButton;
	private ActionListener okButtonListener;
	private Utente[] arrayUtenti = new Utente[2];
	private int contaProve;
	
	/**
	 * costruttore che definisce e setta tutti gli oggetti della finestra
	 */
	public LoginWindow() {
		
		/**definizione di una "base di dati" di utenti e relativi attributi*/		
		Admin admin1 = new Admin("simone","simone@gmail.com","ciao");
		User user1 = new User("carlo","carlo@libero.it","mamma");
		arrayUtenti[0] = admin1; 
		arrayUtenti[1] = user1;
			
		contaProve = 0;
		
		loginFrame = new JFrame("Login");
		loginFrame.setBounds(1270/2 - 100, 650/2 - 100, 250, 250);
		loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(Color.white);
		loginPanel.setLayout(gl);
	    loginPanel.setBorder(BorderFactory.createEmptyBorder(1270/2 - 100, 650/2 - 100, 250, 250));

		gl = new GroupLayout(loginPanel);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		nameLabel = new JLabel("Username: ");
		nameTxt = new JTextField(15); 
		nameTxt.setEditable(true);
		pswLabel = new JLabel("Password: ");
		pswTxt = new JPasswordField(15); 
		pswTxt.setEditable(true);
		
		okButton = new JButton("Conferma");
		okButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onOkButtonPressed(arg0);
			}
		};
		okButton.addActionListener(okButtonListener);
		
		loginFrame.add(loginPanel);
		loginPanel.add(nameLabel);
		loginPanel.add(nameTxt);
		loginPanel.add(pswLabel);
		loginPanel.add(pswTxt);
		loginPanel.add(okButton);
		
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameLabel)
							.addComponent(pswLabel)
							)
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nameTxt)
							.addComponent(pswTxt)
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
		
		loginFrame.setVisible(true);
	}
	
	/**
	 * controllo delle stringhe immesse con i dati nel "database" se true => controlla se
	 * l'utente riconosciuto è admin o user e lancia una nuova finestra in base ai generics, 
	 * altrimenti da errore e lascia riprovare l'immissione dei dati. I tentativi sono
	 * limitati a un massimo di 5 in modo da ridurre la probabilità di un intrusione.
	 */ 
	public void onOkButtonPressed(ActionEvent e){ 
		
		boolean checklogin = false;
			
		if(contaProve >= 5){
			nameTxt.setEditable(false);
			pswTxt.setEditable(false);
			nameTxt.setText("");
			pswTxt.setText("");
			JOptionPane.showMessageDialog(loginFrame, "Non hai più tentativi disponibili, contatta il nostro servizio clienti",
				    "A caccia di malintenzionati",JOptionPane.ERROR_MESSAGE);
		}else{
			
			for(int i = 0; i < arrayUtenti.length; i++){
				if(nameTxt.getText().equals(arrayUtenti[i].getNome()) && pswTxt.getPassword().equals(arrayUtenti[i].getPassword())){
					if(arrayUtenti[i].getIsAdmin()){
						//finestra per admin
						checklogin = true;
						System.out.println("admin");
					}else{
						//finestra per user
						checklogin = true;
						System.out.println("user");
					}
				}
			}
			
			if(!checklogin){
			
				contaProve++;
				JOptionPane.showMessageDialog(loginFrame,"Controlla le tue credenziali, user e/o password sono errati",
						"Credenziali errate",JOptionPane.WARNING_MESSAGE);
				nameTxt.setText("");
				pswTxt.setText("");
			}
		}	
	}

}
