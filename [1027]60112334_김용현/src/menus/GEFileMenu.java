package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EFileMenuItem;


public class GEFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public GEFileMenu() {
		super("File");
		//Object creation
		for(EFileMenuItem text: GEConstants.EFileMenuItem.values()){
			JMenuItem file=new JMenuItem(text.getText());
			this.add(file);
		}
	}
}
