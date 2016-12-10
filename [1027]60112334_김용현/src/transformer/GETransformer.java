package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

import constants.GEConstants;
import shapes.GEShapes;

public abstract class GETransformer {
	protected GEShapes mShapes;
	protected Point mPrevP;
	protected BasicStroke dashedLineStroke;
	public GETransformer(GEShapes shape) {
		// TODO Auto-generated constructor stub
		this.mShapes=shape;
		float dashed[]={GEConstants.DEFAULT_DASH_OFFSET};
		dashedLineStroke=new BasicStroke(
				GEConstants.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dashed,0);
		
	}
	abstract public void init(Point p);
	abstract public void transfomer(Graphics2D g2D, Point p);

}
