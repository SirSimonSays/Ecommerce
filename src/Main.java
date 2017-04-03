import panelli.HandlePanel;
import panelli.LoginPanel;

/**
 * Classe main
 * @author Simone Cavana
 */

public class Main {

	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HandlePanel mainWin = new HandlePanel();
		mainWin.switchPanel(LoginPanel.TAG);
		
	}

}
