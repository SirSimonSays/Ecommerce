package pannelli;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import carrello.HandleCarrello;

public class Acquista extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata  
	 */
	public static final String TAG = "acquista";

	private String[] cardsString = {"Visa", "Mastercard", "AmericanExpress"};
	
	private JLabel nomeL, cognL, cartaL, indL;
	private JTextField nomeT, cognT, indT;
	private JRadioButton card, paypal;
	private ButtonGroup bg;
	private JComboBox<String> cards;
	private JButton okButton, cancelButton;
	
	public Acquista(HandlePanel handlePanel) {
		super(handlePanel);
		// TODO Auto-generated constructor stub
		
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
		 * Aggiunta dei widget al pannello acquista. 
		 */
		nomeL = new JLabel("Nome: ");
		nomeT = new JTextField(15);
		cognL = new JLabel("Cognome: ");
		cognT = new JTextField(15);
		cartaL = new JLabel("Metodo di pagamento: ");
		indL = new JLabel("Indirizzo di spedizione: ");
		indT = new JTextField(15);
		
		card = new JRadioButton("Carta di credito");
		card.setSelected(true);
		card.addActionListener(this);
		
		paypal = new JRadioButton("Paypal");
		paypal.addActionListener(this);
		
		bg = new ButtonGroup();
		bg.add(card);
		bg.add(paypal);
		
		cards = new JComboBox<String>(cardsString);
		
		okButton = new JButton("Conferma");
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);

		add(nomeL);
		add(nomeT);
		add(cognL);
		add(cognT);
		add(cartaL);
		add(indL);
		add(indT);
		add(card);
		add(paypal);
		add(cards);
		add(okButton);
		add(cancelButton);
		
		
		/**
		 * Posizionamento dei widget all'interno del layout gl.
		 */
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nomeL)
							.addComponent(cognL)
							.addComponent(indL)
							.addComponent(cartaL)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(nomeT)
							.addComponent(cognT)
							.addComponent(indT)
							.addGroup(gl.createSequentialGroup()
									.addComponent(card)
									.addComponent(cards)
							)
							.addComponent(paypal)
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
							.addComponent(nomeL)
							.addComponent(nomeT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(cognL)
							.addComponent(cognT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(indL)
							.addComponent(indT)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(cartaL)
							.addComponent(card)
							.addComponent(cards)
							)
					.addGroup(
							gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(paypal)
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
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		if(e.getSource().equals(card)){
			cards.setEnabled(true);
		}else if(e.getSource().equals(paypal)){
			cards.setEnabled(false);
		}else if(e.getSource().equals(okButton)){
			if(nomeT.getText().isEmpty() || cognT.getText().isEmpty() || indT.getText().isEmpty() ){
				JOptionPane.showMessageDialog(this, "Non possono essere presenti campi vuoti",
						   "Controlla di aver immesso i dati correttamente",JOptionPane.ERROR_MESSAGE);
			}else{
				HandleCarrello.svuota();
				JOptionPane.showMessageDialog(this, "Il tuo ordine è stato effettuato con successo",
						   "Complimenti",JOptionPane.INFORMATION_MESSAGE);
				HandlePanel.switchPanel(Carrello.TAG);
			}
		}else if(e.getSource().equals(cancelButton)){
			HandlePanel.switchPanel(Carrello.TAG);
		}
	}
	
	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		nomeT.setText("");
		cognT.setText("");
		indT.setText("");
	}

}
