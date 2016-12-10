package shapes;

import java.awt.Point;
import java.awt.Polygon;


public class GEPolygon extends GEShapes {

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

}
