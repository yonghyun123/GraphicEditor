package manager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import constants.GEConstants;
import constants.GEConstants.EAnchors;
import shapes.GEAnchors;
import shapes.GEShapes;

public class GEGroup extends GEShapes{
	private ArrayList<GEShapes> mShapeList;
	private BasicStroke dashedLineStroke;
	
	public GEGroup() {
		super(new Rectangle());
		mShapeList=new ArrayList<GEShapes>();
		float dashed[]={GEConstants.DEFAULT_DASH_OFFSET};
		dashedLineStroke=new BasicStroke(
				GEConstants.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dashed,0);
	}

	@Override
	public void initPosition(Point start) {
		for(GEShapes shape:mShapeList){
			shape.initPosition(start);
		}
	}

	@Override
	public GEShapes clone() {
		return null;
	}

	@Override
	public void setShapeCreate(Point point) {
		// TODO Auto-generated method stub
		for(GEShapes shape:mShapeList){
			shape.setShapeCreate(point);
		}	
	}
	
	public void addShape(GEShapes shape){
		mShapeList.add(0,shape);
		if(mShapeList.size()==1){
			mShapes=shape.getBounds();
		}
		else{
			mShapes=mShapes.getBounds().createUnion(shape.getBounds());
		}
	}
	public ArrayList<GEShapes> getChildList(){
		return mShapeList;
	}
	public void setLineColor(Color lineColor){
		for(GEShapes shape: mShapeList){
			shape.setLineColor(lineColor);
		}
	}
	public void setFillColor(Color fillColor){
		for(GEShapes shape: mShapeList){
			shape.setFillColor(fillColor);
		}
	}
	public boolean isSelected(){
		return selected;
	}
	public void setSelected(boolean selected){
		this.selected=selected;
		if(selected){
			anchorList=new GEAnchors();
			anchorList.computeCoordinates(mShapes.getBounds());
		}else{
			anchorList=null;
		}
	}
	public boolean onShape(Point p){
		if(anchorList!=null){
			selectedAnchor=anchorList.onAnchors(p);
			if(selectedAnchor!=EAnchors.NONE)
				return true;
		}
		for(GEShapes shape:mShapeList){
			if(shape.onShape(p)){
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics2D g2d){
		for(GEShapes shape:mShapeList){
			shape.draw(g2d);
		}
		if(this.isSelected()){
			g2d.setColor(Color.BLACK);
			g2d.setStroke(dashedLineStroke);
			g2d.draw(mShapes);
			g2d.setStroke(new BasicStroke());
			this.getAnchorList().computeCoordinates(this.getBounds());
			this.getAnchorList().draw(g2d);
		}
	}
	
	public void moveCoordinate(Point tempP){
		super.moveCoordinate(tempP);
		for(GEShapes shape:mShapeList){
			shape.moveCoordinate(tempP);
		}
	}
	
	public void resizeCoordinate(Point2D resizeFactor){
		super.resizeCoordinate(resizeFactor);
		for(GEShapes shape:mShapeList){
			shape.resizeCoordinate(resizeFactor);
		}
	}
	
	public void move(Point2D resizeAnchor){
		super.move(resizeAnchor);
		for(GEShapes shape:mShapeList){
			shape.move(resizeAnchor);
		}
	}
	
}
