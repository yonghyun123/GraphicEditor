package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class GEShapes {
	protected Shape mShapes;
	protected Point startPoint;
	
	public GEShapes(Shape shape){
		this.mShapes=shape;
	}
	abstract public void initPosition(Point start);
	abstract public GEShapes clone();
	abstract public void setShapeCreate(Point point);
	
	public void draw(Graphics2D g2d){
		g2d.draw(mShapes);
	}

	public Shape getShape(){
		return mShapes;
	}
	
}
