package pannelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Simone Cavana
 * @brief classe che implementa il pannello per effettuare il login iniziale
 * al programma. Da questa schermata se non si hanno delle credenziali si può
 * uscire oppure creare un nuovo utente.
 */
public class LoginPanel extends DefaultPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata  
	 */
	public static final String TAG = "login";
	
	/**
	 * label per indicare all'utente cosa inserire dentro alle TextField. 
	 * @var imgLabel immagazzina un'icona per abbellire la gui.
	 */
	private JLabel nameLabel, pswLabel, imgLabel;
	
	/**
	 * @var nameTxt
	 * TextField che da la possibilità all'utente di inserire lo username.
	 */
	private JTextField nameTxt;
	
	/**
	 * @var pswTxt
	 * PasswordField che da la possibilità all'utente di inserire la password
	 * non in chiaro come dovrebbe succedere in un sito/applicazione professionale. 
	 */
	private JPasswordField pswTxt;
	
	/**
	 * bottoni che danno la possibilità all'utente di confermare oppure di creare
	 * un nuovo utente tramite il pannello {@link CreaUtente}.
	 */
	private JButton okButton, gearButton;
	
	/**
	 * @var contaProve
	 * variabile contatore che controlla che non vengano fatte più di MAX prove.
	 */
	private int contaProve;
	
	/**
	 * @var maxProve
	 * numero massimo di tentativi per effettuare il login.
	 */
	private static int maxProve;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra
	 */
	public LoginPanel(HandlePanel handlePanel) {
		
		super(handlePanel);		
		
		contaProve = 0;
		maxProve = 3;
		
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
		
		InputStream imageStream = this.getClass().getResourceAsStream("/image/user.png");
		BufferedImage img;
		try {
			img = ImageIO.read(imageStream);
			imgLabel = new JLabel(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("impossibile trovare l'immagine " + e1);
			e1.printStackTrace();
		}
	
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		try{
			gearButton = new JButton(new ImageIcon(LoginPanel.class.getResource("/image/gear.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine " + e);
		}
		
		gearButton.setSize(250, 350);
		gearButton.addActionListener(this);
		
		/**
		 * Posizionamento dei widget all'interno del layout gl.
		 */
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(imgLabel)
							)
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
							.addComponent(imgLabel)
							.addGroup(gl.createSequentialGroup()
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
								)
							)
		);
		
	}
	
	/**
	 * @param e
	 * Nell'if viene chiamato il metodo di HandleUser per confrontare i dati immessi con quelli
	 * presenti nei file "userFile.txt", se l'utente riconosciuto è admin o user viene lanciata una
	 * nuova finestra in base ai generics, altrimenti da errore e lascia riprovare l'immissione
	 * dei dati. I tentativi sono limitati a @var maxProve in modo da ridurre la probabilità 
	 * di intrusione.
	 * Utilizzo di una variabile temp di tipo String perchè il tipo di ritorno di getPassword
	 * è un array di caratteri e per effettuare il controllo con la password all'interno
	 * dell'array bisogna effettuare un cast implicito.
	 * Nel caso else viene analizzato l'evento create dalla pressione del bottone "ingranaggio" 
	 * per creare un nuovo utente e quindi lanciata la finestra adeguata tramite HandleUser. 
	 */ 
	@Override
	public void actionPerformed(ActionEvent e){ 
		super.actionPerformed(e);
		if(e.getActionCommand().equals("Conferma")){
			String temp = new String(pswTxt.getPassword());
				
			if(contaProve >= maxProve){
				nameTxt.setEditable(false);
				pswTxt.setEditable(false);
				JOptionPane.showMessageDialog(this, "Non hai più tentativi disponibili, contatta il nostro servizio clienti",
					    "A caccia di malintenzionati",JOptionPane.ERROR_MESSAGE);
			}else{

				if(!user.HandleUser.checkUser(nameTxt.getText(), temp)){
					contaProve++;
					JOptionPane.showMessageDialog(this,"Controlla le tue credenziali, user e/o password sono errati",
							"Credenziali errate",JOptionPane.WARNING_MESSAGE);
				}
			}	
		}else if(e.getSource().equals(gearButton)){
			HandlePanel.switchPanel(CreaUtente.TAG);
		}
	}
	
	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		nameTxt.setText("");
		pswTxt.setText("");
	}

}
