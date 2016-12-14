package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class GELine extends GEShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GELine() {
		// TODO Auto-generated constructor stub
		super(new Line2D.Double());
	}
	public void initPosition(Point start) {
		startPoint=start;
	}
	@Override
	public GEShapes clone() {
		// TODO Auto-generated method stub
		return new GELine();
	}

	@Override
	public void setShapeCreate(Point point) {
		// TODO Auto-generated method stub
		Line2D tempLine=(Line2D)mShapes;
		tempLine.setLine(startPoint.x, startPoint.y, point.x, point.y);
		if(anchorList!=null){
			anchorList.computeCoordinates(mShapes.getBounds());
		}
	}
	
	public boolean contains(Point point){
		Line2D tempLine=(Line2D)mShapes;
		Rectangle tempRect=new Rectangle();
		tempRect.setFrameFromDiagonal(tempLine.getP1(), tempLine.getP2());
		return tempRect.contains(point);	
		
	}
	@Override
	public GEShapes deepCopy() {
		AffineTransform affineTansform=new AffineTransform();
		Shape temp=affineTansform.createTransformedShape(mShapes);
		GELine shape=new GELine();
		shape.setShape(temp);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
