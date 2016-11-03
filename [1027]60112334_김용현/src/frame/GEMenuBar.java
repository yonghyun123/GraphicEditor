package frame;
import javax.swing.JMenuBar;

import menus.GEEditMenu;
import menus.GEFileMenu;

public class GEMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		GEFileMenu fileMenu = new GEFileMenu();
		this.add(fileMenu);
		
		GEEditMenu EditMenu=new GEEditMenu();
		this.add(EditMenu);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
