package shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;


public class GEPolygon extends GEShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GEPolygon() {
		// TODO Auto-generated constructor stub
		super(new Polygon());
	}
	public void initPosition(Point start) {
		((Polygon) mShapes).addPoint(start.x, start.y);
		
	}
	public void continueDrawing(Point continueP){
		((Polygon)mShapes).addPoint(continueP.x, continueP.y);
	}
	@Override
	public GEShapes clone() {
		return new GEPolygon();
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setShapeCreate(Point point) {
		// TODO Auto-generated method stub
		((Polygon)mShapes).xpoints[((Polygon)mShapes).npoints-1] = point.x;
		((Polygon)mShapes).ypoints[((Polygon)mShapes).npoints-1] = point.y;
		if(anchorList!=null){
			anchorList.computeCoordinates(mShapes.getBounds());
		}
	}
	@Override
	public GEShapes deepCopy() {
		AffineTransform affineTansform=new AffineTransform();
		Shape temp=affineTansform.createTransformedShape(mShapes);
		GEPolygon shape=new GEPolygon();
		shape.setShape(temp);
		shape.setGraphicsAttributes(this);
		return shape;
	}

}
