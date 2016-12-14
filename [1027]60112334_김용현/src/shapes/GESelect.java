package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GESelect extends GEShapes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GESelect() {
		super(new Rectangle());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPosition(Point start) {
		// TODO Auto-generated method stub
		this.startPoint=start;
		
	}

	@Override
	public GEShapes clone() {
		// TODO Auto-generated method stub
		return new GESelect();
	}

	@Override
	public void setShapeCreate(Point point) {
		Rectangle tempRect=(Rectangle)mShapes;
		tempRect.setFrameFromDiagonal(startPoint.x, startPoint.y, point.x,point.y);
	}

	@Override
	public GEShapes deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
