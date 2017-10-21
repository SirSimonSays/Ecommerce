package pannelli;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public abstract class DefaultPanel extends JPanel implements ActionListener{
	
	/**
	 * @var handlePanel
	 * Variabile per tenere il riferimento alla classe HandlePanel per la gestione del cardLayout. 
	 */
	protected HandlePanel handlePanel;
	
	/**
	 * @var mainMb 
	 * Barra dei menu per navigare nell'ECommerce.
	 */
	protected MenuBar mainMb;
	
	/**
	 * @var infoMenu 
	 * Singola voce della barra dei menu con all'interno il menu a tendina.
	 */
	private Menu infoMenu;
	
	/**
	 * Costruttore della classe astratta
	 * @param handlePanel
	 * Costruisce la MenuBar di default per tutti i pannelli.
	 */
	public DefaultPanel(HandlePanel handlePanel){
		
		this.handlePanel = handlePanel;
		
		mainMb = new MenuBar();
		infoMenu = new Menu("File");
		MenuItem m1 = new MenuItem("Logout");
		MenuItem m2 = new MenuItem("Exit");
		
		infoMenu.add(m1);
		infoMenu.add(m2);
		mainMb.add(infoMenu);
		infoMenu.addActionListener(this);
		
		handlePanel.setMenuBar(mainMb);
	}

	/**
	 * @brief metodo chiamato in automatico alla visualizzazione della schermata
	 */
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @brief metodo chiamato in automatico alla chiusura della schermata
	 */
	public void onExit(){
		
	}
	
	/**
	 * @brief override per implementare la chiamata automatica di onEnter e onExit
	 */
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(aFlag){
			onEnter();
		}
		else{
			onExit();
		}
	}
	
	/**
	 * @param e
	 * metodo per la gestione degli eventi a seguito di azioni sull'interfaccia.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Logout")){
			HandlePanel.switchPanel(LoginPanel.TAG);
		}else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		
	}
	
}
