package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import constants.GEConstants.EAnchors;

public class GEAnchors extends Vector<Ellipse2D.Double> {
	public final static int ANCHORWIDTH=8;
	public final static int ANCHORHEIGHT=8;
	private Vector<Ellipse2D.Double> mAnchor;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	public GEAnchors() {
		mAnchor=new Vector<Ellipse2D.Double>();
		// TODO Auto-generated constructor stub
		for(int i=0;i<EAnchors.values().length;i++){
			mAnchor.add(new Ellipse2D.Double(0, 0, 0, 0));
		}
	}
	
	public EAnchors onAnchors(Point p){
		for(Ellipse2D ellipse: mAnchor){
			if(ellipse.contains(new Point(p))){
				return EAnchors.values()[mAnchor.indexOf(ellipse)];
			}
		}
		return EAnchors.NONE;
	}
	
	public void computeCoordinates(Rectangle boundingRect){
		int x=boundingRect.x;
		int y=boundingRect.y;
		int w=boundingRect.width;
		int h=boundingRect.height;
		int dividedX=ANCHORWIDTH/2;
		int dividedY=ANCHORHEIGHT/2;
		
		mAnchor.get(EAnchors.NW.ordinal()).setFrame(x-dividedX, y-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.NN.ordinal()).setFrame(x+w/2-dividedX, y-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.NE.ordinal()).setFrame(x+w-dividedX, y-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.WW.ordinal()).setFrame(x-dividedX, y+h/2-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.EE.ordinal()).setFrame(x+w-dividedX, y+h/2-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.SW.ordinal()).setFrame(x-dividedX, y+h-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.SS.ordinal()).setFrame(x+w/2-dividedX, y+h-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		mAnchor.get(EAnchors.SE.ordinal()).setFrame(x+w-dividedX, y+h-dividedY,
				ANCHORWIDTH, ANCHORHEIGHT);
		
	}
	public void draw(Graphics2D g2D){
		for(int i=0;i<EAnchors.values().length-1;i++){
			Ellipse2D.Double ellipse=mAnchor.get(i);
			g2D.draw(ellipse);
		}
	}
	

}
