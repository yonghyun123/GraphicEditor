package transformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import shapes.GEPolygon;
import shapes.GEShapes;

public class GEDrawer extends GETransformer{

	public GEDrawer(GEShapes shape) {
		// TODO Auto-generated constructor stub
		super(shape);
	}

	@Override
	public void init(Point p) {
		// TODO Auto-generated method stub
		mShapes.initPosition(p);
	}

	@Override
	public void transfomer(Graphics2D g2D, Point p) {
		// TODO Auto-generated method stub
	
		g2D.setXORMode(Color.white);
		mShapes.draw(g2D);
		mShapes.setShapeCreate(p);
		mShapes.draw(g2D);
	}
	
	public void continueDrawing(Point p){
		((GEPolygon)mShapes).continueDrawing(p);
	}

	public void finalize(ArrayList<GEShapes> shapeList){
		shapeList.add(mShapes);
	}
}
