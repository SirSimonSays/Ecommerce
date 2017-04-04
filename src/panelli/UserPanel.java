package panelli;

import java.awt.Color;

public class UserPanel extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "user";
	
	public UserPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		setBackground(Color.yellow);
		
	}

}
