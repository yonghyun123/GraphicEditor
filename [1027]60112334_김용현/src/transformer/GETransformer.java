package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEShapes;

public abstract class GETransformer {
	protected GEShapes mShapes;
	protected Point mPrevP;
	public GETransformer(GEShapes shape) {
		// TODO Auto-generated constructor stub
		this.mShapes=shape;
		
	}
	abstract public void init(Point p);
	abstract public void transfomer(Graphics2D g2D, Point p);

}
