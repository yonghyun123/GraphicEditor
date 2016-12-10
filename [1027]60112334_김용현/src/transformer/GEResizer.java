package transformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constants.GEConstants.EAnchors;

import shapes.GEAnchors;
import shapes.GEShapes;


public class GEResizer extends GETransformer{
	private Point2D mResizeAnchor;
	
	public GEResizer(GEShapes shape) {
		// TODO Auto-generated constructor stub
		super(shape);
		mPrevP=new Point();
	}

	@Override
	public void init(Point p) {
		mPrevP=p;
		mResizeAnchor=getResizeAnchor();
		mShapes.moveReverse(mResizeAnchor);
		
	}

	@Override
	public void transfomer(Graphics2D g2d, Point p) {
		g2d.setXORMode(Color.WHITE);
		//g2d.setStroke();
		Point2D resizeFactor=computeResizeFactor(mPrevP,p);
		AffineTransform tempAffine=g2d.getTransform();
		g2d.translate(mResizeAnchor.getX(), mResizeAnchor.getY());
		mShapes.draw(g2d);
		mShapes.resizeCoordinate(resizeFactor);
		mShapes.draw(g2d);
		g2d.setTransform(tempAffine);
		mPrevP=p;
		
	}
	private Point2D computeResizeFactor(Point previousP,Point currentP){
		int deltaW=0;
		int deltaH=0;
		if(mShapes.getSelectedAnchor()==EAnchors.NW){
			deltaW=-(currentP.x-previousP.x);
			deltaH=-(currentP.y-previousP.y);
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.NN){
			deltaW=0;
			deltaH=-(currentP.y-previousP.y);
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.NE){
			deltaW=(currentP.x-previousP.x);
			deltaH=-(currentP.y-previousP.y);
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.WW){
			deltaW=-(currentP.x-previousP.x);
			deltaH=0;
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.EE){
			deltaW=currentP.x-previousP.x;
			deltaH=0;
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.SW){
			deltaW=-(currentP.x-previousP.x);
			deltaH=(currentP.y-previousP.y);
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.SS){
			deltaW=0;
			deltaH=currentP.y-previousP.y;
		}
		else if(mShapes.getSelectedAnchor()==EAnchors.SE){
			deltaW=currentP.x-previousP.x;
			deltaH=currentP.y-previousP.y;
		}
		double currentW=mShapes.getBounds().getWidth();
		double currentH=mShapes.getBounds().getHeight();
		double xFactor=1.0;
		double yFactor=1.0;
		if(currentW>0.0){
			xFactor=(1.0+deltaW/currentW);
		}
		if(currentH>0.0){
			yFactor=(1.0+deltaH/currentH);
		}
		return new Point2D.Double(xFactor, yFactor);
	}
	
	private Point getResizeAnchor() {
		GEAnchors anchors = mShapes.getAnchorList();
		Point resizeAnchor = new Point();
		switch (mShapes.getSelectedAnchor()) { 
		case EE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.WW).getX(),0); 	 break;
		case WW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.EE).getX(),0); 	 break;
		case SS: resizeAnchor.setLocation(0,anchors.getBounds(EAnchors.NN).getY()); break;
		case NN: resizeAnchor.setLocation(0,anchors.getBounds(EAnchors.SS).getY()); break;
		case SE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NW).getX(),anchors.getBounds(EAnchors.NW).getY()); break;
		case NE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SW).getX(),anchors.getBounds(EAnchors.SW).getY()); break;
		case SW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NE).getX(),anchors.getBounds(EAnchors.NE).getY()); break;
		case NW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SE).getX(),anchors.getBounds(EAnchors.SE).getY()); break;
		default: break;
		}
		return resizeAnchor;
	}
	public void finalize(Point point){
		mShapes.move(mResizeAnchor);
	}
}
