package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import constants.GEConstants;
import constants.GEConstants.EAnchors;

public abstract class GEShapes {
	protected Shape mShapes;
	protected Point startPoint;
	
	protected boolean selected;
	protected GEAnchors anchorList;
	GEConstants.EAnchors selectedAnchor;
	
	public GEShapes(Shape shape){
		this.mShapes=shape;
		anchorList=null;
		selected=false;
	}
	abstract public void initPosition(Point start);
	abstract public GEShapes clone();
	abstract public void setShapeCreate(Point point);
	
	public void draw(Graphics2D g2d){
		g2d.draw(mShapes);
		if(selected){
			anchorList.draw(g2d);
		}
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
	public boolean isSelected(){
		return selected;
	}

	public Shape getShape(){
		return mShapes;
	}
	public boolean onShape(Point p) {
		// TODO Auto-generated method stub
		if(anchorList!=null){
			selectedAnchor=anchorList.onAnchors(p);
			if(selectedAnchor!=EAnchors.NONE)
				return true;
		}
		return mShapes.intersects(new Rectangle(p.x,p.y,2,2));
	}
	
	public EAnchors onAnchor(Point p){
		if(selectedAnchor!=null){
			this.selectedAnchor=anchorList.onAnchors(p);
			return selectedAnchor;
		}
		else{
			return null;
		}
		
	}
	
}
