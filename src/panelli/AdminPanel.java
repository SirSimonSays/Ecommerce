package panelli;

import java.awt.Color;

public class AdminPanel extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "admin";
	
	public AdminPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		setBackground(Color.cyan);
		
	}

}
