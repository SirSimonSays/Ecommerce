package pannelli;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import prodotto.ModelloProdotto;
import prodotto.TabellaProdotto;

public class AdminPanel extends DefaultPanel{
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata.
	 */
	public static final String TAG = "admin";
	
	/**
	 * @var edit
	 * bottone per modificare un prodotto.
	 */
	private JButton edit;
	
	/**
	 * @var add
	 * bottone per aggiungere un prodotto.
	 */
	private JButton add;
	
	/**
	 * @var delete
	 * bottone per eliminare un prodotto.
	 */
	private JButton delete;
	
	/**
	 * @var tabProd
	 * Tabella dei prodotti.
	 */
	private TabellaProdotto tabProd;
	
	/**
	 * @brief costruttore
	 * @param handlePanel
	 * Costruttore che definisce e setta tutti gli oggetti della finestra.
	 */
	public AdminPanel(HandlePanel handlePanel) {
		
		super(handlePanel);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		try{
			edit = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/edit.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine" + e);
		}
		edit.addActionListener(this);
		
		try{
			add = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/add.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine" + e);
		}
		add.addActionListener(this);
		
		try{
			delete = new JButton(new ImageIcon(AdminPanel.class.getResource("/image/delete.png")));
		}catch(Exception e){
			System.out.println("impossibile trovare l'immagine" + e);
		}
		delete.addActionListener(this);
		
		toolBar.add(edit);
		toolBar.add(add);
		toolBar.add(delete);
		
		tabProd = new TabellaProdotto(new ModelloProdotto());
		
		add(toolBar, BorderLayout.PAGE_START);		
		add(tabProd, BorderLayout.CENTER);

	}

	/**
	 * @param e
	 * controllo degli eventi in base alla pressione dei bottoni.
	 */
	@Override
	public void actionPerformed(ActionEvent e){ 
		if(e.getSource().equals(edit)){
			
		}else if(e.getSource().equals(add)){
			HandlePanel.switchPanel(CreaProdotto.TAG);
		}else if(e.getSource().equals(delete)){
			
		}
	}
}
