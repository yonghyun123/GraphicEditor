package manager;

import java.awt.Cursor;
import java.util.Vector;

public class GECursor extends Vector<Cursor>{

	public GECursor() {
		add(new Cursor(Cursor.NW_RESIZE_CURSOR));
		add(new Cursor(Cursor.N_RESIZE_CURSOR));
		add(new Cursor(Cursor.NE_RESIZE_CURSOR));
		add(new Cursor(Cursor.W_RESIZE_CURSOR));
		add(new Cursor(Cursor.E_RESIZE_CURSOR));
		add(new Cursor(Cursor.SW_RESIZE_CURSOR));
		add(new Cursor(Cursor.S_RESIZE_CURSOR));
		add(new Cursor(Cursor.SE_RESIZE_CURSOR));
		add(new Cursor(Cursor.HAND_CURSOR));
		add(new Cursor(Cursor.MOVE_CURSOR));
	}
}
