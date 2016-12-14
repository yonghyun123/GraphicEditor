package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;


public class GERectangle extends GEShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GERectangle(){
		super(new Rectangle());
	}
	
	public GEShapes clone() {
		return new GERectangle();
	}
	@Override
	public void setShapeCreate(Point point) {
		// TODO Auto-generated method stub
		Rectangle tempRect=(Rectangle) mShapes;
		tempRect.setFrameFromDiagonal(startPoint.x,startPoint.y,point.x,point.y);
		
		if(anchorList!=null){
			anchorList.computeCoordinates(mShapes.getBounds());
		}
	}

	@Override
	public void initPosition(Point start) {
		startPoint=start;
	}

	@Override
	public GEShapes deepCopy() {
		AffineTransform affineTansform=new AffineTransform();
		Shape temp=affineTansform.createTransformedShape(mShapes);
		GERectangle shape=new GERectangle();
		shape.setShape(temp);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
