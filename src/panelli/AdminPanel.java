package panelli;

import java.awt.Color;

import javax.swing.JButton;

public class AdminPanel extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "admin";
	
	private JButton edit;
	private JButton add;
	private JButton delete;
	private JButton save;
	private JButton load;
	
	public AdminPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		setBackground(Color.cyan);
		
		
		
		
	}

}
