package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShapes {

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

}
