package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class GEEllipse extends GEShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GEEllipse() {
		// TODO Auto-generated constructor stub
		super(new Ellipse2D.Double());
	}
	public void initPosition(Point start) {
		startPoint=start;
	}
	@Override
	public GEShapes clone() {
		// TODO Auto-generated method stub
		return new GEEllipse();
	}

	@Override
	public void setShapeCreate(Point point) {
		// TODO Auto-generated method stub
		Ellipse2D tempEllipse=(Ellipse2D)mShapes;
		tempEllipse.setFrameFromDiagonal(startPoint, point);
		if(anchorList!=null){
			anchorList.computeCoordinates(mShapes.getBounds());
		}
		
	}
	@Override
	public GEShapes deepCopy() {
		AffineTransform affineTansform=new AffineTransform();
		Shape temp=affineTansform.createTransformedShape(mShapes);
		GEEllipse shape=new GEEllipse();
		shape.setShape(temp);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
