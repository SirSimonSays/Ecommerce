import pannelli.HandlePanel;
import pannelli.LoginPanel;

/**
 * @brief Classe main
 * @author Simone Cavana Cavana
 */

public class Main {

	/**
	 * Metodo main
	 * @param args
	 * @brief metodo iniziale che istanzia una finestra principale e switcha al
	 * pannello di login.
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HandlePanel mainWin = new HandlePanel();
		mainWin.switchPanel(LoginPanel.TAG);
		
	}

}
