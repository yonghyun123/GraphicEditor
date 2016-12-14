package frame;
import javax.swing.JMenuBar;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;

public class GEMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GEFileMenu fileMenu;
	private GEEditMenu editMenu;
	private GEColorMenu colorMenu;

	public GEMenuBar() {
		fileMenu = new GEFileMenu();
		this.add(fileMenu);
		
		editMenu=new GEEditMenu();
		this.add(editMenu);
		
		colorMenu=new GEColorMenu();
		this.add(colorMenu);
	}
	public void initialize(GEDrawingPanel drawingPanel) {
		// TODO Auto-generated method stub
		colorMenu.init(drawingPanel);
		editMenu.init(drawingPanel);
		fileMenu.init(drawingPanel);
		
	}
}
