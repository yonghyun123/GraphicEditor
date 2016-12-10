package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EEditMenuItem;


public class GEEditMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GEEditMenu(){
		super("Edit");  //JMenu�� �̸��� ���δ�.
		for(EEditMenuItem text: EEditMenuItem.values()){
			JMenuItem item=new JMenuItem(text.getText());
			this.add(item);
		}
		
	}
	
}
