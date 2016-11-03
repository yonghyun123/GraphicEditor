package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EditFileMenu;

public class GEEditMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GEEditMenu(){
		super("Edit");  //JMenu�� �̸��� ���δ�.
		for(EditFileMenu text: EditFileMenu.values()){
			JMenuItem item=new JMenuItem(text.getText());
			this.add(item);
		}
		
	}
	
}
