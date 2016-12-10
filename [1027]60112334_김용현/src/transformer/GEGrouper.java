package transformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import shapes.GEShapes;

public class GEGrouper extends GETransformer {

	public GEGrouper(GEShapes shape) {
		super(shape);
	
	}
	@Override
	public void init(Point p) {
		// TODO Auto-generated method stub
		mShapes.initPosition(p);
	}

	@Override
	public void transfomer(Graphics2D g2d, Point p) {
		g2d.setXORMode(Color.WHITE);
		g2d.setStroke(dashedLineStroke);
		mShapes.draw(g2d);
		mShapes.setShapeCreate(p);
		mShapes.draw(g2d);			
	}
	public void finalize(ArrayList<GEShapes> shapeList){
		for(GEShapes tempShape:shapeList){

			if(mShapes.getBounds().contains(tempShape.getBounds())){
				
				tempShape.setSelected(true);
			}
		}
	}

}
