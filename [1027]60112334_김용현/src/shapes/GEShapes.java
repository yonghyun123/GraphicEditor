package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import constants.GEConstants.EAnchors;
import manager.GEAnchors;

public abstract class GEShapes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Shape mShapes;
	protected Point startPoint;
	
	protected boolean selected;
	protected GEAnchors anchorList;
	protected EAnchors selectedAnchor;
	
	protected Color mLineColor,mFillColor;
	
	protected AffineTransform affineTransform;
	public void setLineColor(Color lineColor){
		this.mLineColor=lineColor;
	}
	public void setFillColor(Color fillColor){
		this.mFillColor=fillColor;
	}
	public Color getLineColor(){
		return mLineColor;
	}
	public Color getFillColor(){
		return mFillColor;
	}
	public GEShapes(Shape shape){
		this.mShapes=shape;
		affineTransform=new AffineTransform();
		anchorList=null;
		selected=false;
	}
	abstract public void initPosition(Point start);
	abstract public GEShapes clone();
	abstract public void setShapeCreate(Point point);
	abstract public GEShapes deepCopy();

	public GEAnchors getAnchorList(){
		return anchorList;
	}
	public EAnchors getSelectedAnchor(){
		return selectedAnchor;
	}
	public Rectangle getBounds(){
		return mShapes.getBounds();
	}
	public Shape getShape(){
		return mShapes;
	}
	public void setShape(Shape shape){
		mShapes=shape;
	}
	public boolean isSelected(){
		return selected;
	}
	public void setAnchorList(GEAnchors anchorList){
		this.anchorList = anchorList;
	}
	public void setGraphicsAttributes(GEShapes shape){
		setLineColor(shape.getLineColor());
		setFillColor(shape.getFillColor());
		setAnchorList(shape.getAnchorList());
		setAnchorList(shape.getAnchorList());
		setSelected(shape.isSelected());
		
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
		this.selectedAnchor=anchorList.onAnchors(p);
		return selectedAnchor;
	}
	//���� �߿��� �׷����� ���� ä������ ���� ä������ �׷����� 
	  //clear ��ų�� �ݴ�� ���� ������ ���ְ� ����� ������ ������
	public void draw(Graphics2D g2d){ 
		if(this.mFillColor!=null){
			g2d.setColor(mFillColor);
			g2d.fill(mShapes);
		}
		
		if(this.mLineColor!=null){
			g2d.setColor(this.mLineColor);
			g2d.draw(mShapes);
		}

		if(selected){
			//anchors ��ġ���� �ٽ� ����ְ� �׷������
			anchorList.computeCoordinates(this.getBounds()); 
			anchorList.draw(g2d);
		}
		
	}
	public void moveCoordinate(Point moveP){
		affineTransform.setToTranslation(moveP.getX(), moveP.getY());
		mShapes=affineTransform.createTransformedShape(mShapes);
	}
	public void resizeCoordinate(Point2D resizeFactor){
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		mShapes=(affineTransform.createTransformedShape(mShapes));
	}
	public void moveReverse(Point2D resizeAnchor){
		affineTransform.setToTranslation(-resizeAnchor.getX(), -resizeAnchor.getY());
		mShapes=(affineTransform.createTransformedShape(mShapes));
	}
	public void move(Point2D resizeAnchor){
		affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		mShapes=(affineTransform.createTransformedShape(mShapes));
	}
	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY() );
		mShapes = (affineTransform.createTransformedShape(mShapes));
	}
	
}
