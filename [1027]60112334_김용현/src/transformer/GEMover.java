package transformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEShapes;

public class GEMover extends GETransformer {

	public GEMover(GEShapes shape) {
		// TODO Auto-generated constructor stub
		super(shape);
		mPrevP=new Point();
	}

	@Override
	public void init(Point p) {
		// TODO Auto-generated method stub
		mPrevP=p;
	}

	@Override
	public void transfomer(Graphics2D g2D, Point p) {
		// TODO Auto-generated method stub
		Point tempP=new Point(p.x-mPrevP.x,p.y-mPrevP.y);
		g2D.setXORMode(Color.WHITE);
		mShapes.draw(g2D);
		mShapes.moveCoordinate(tempP);
		mShapes.draw(g2D);
		mPrevP=p;
	}

}
