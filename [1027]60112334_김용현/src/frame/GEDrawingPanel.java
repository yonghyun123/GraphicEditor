package frame;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EAnchors;
import shapes.GEPolygon;
import shapes.GEShapes;


public class GEDrawingPanel extends JPanel {

	static private enum EState{
		idle, drawing,Polygon;
	}
	static private enum EAnchorState{
		idle, onAnchor;
	}
	//attribute
	private static final long serialVersionUID = 1L;
	//working variables
	private GEShapes mShapes, selectedShape;
	private ArrayList<GEShapes> mShapelists;
	private EState eState;
	private EAnchorState eAnchorState;
	//associative attributes

	public GEDrawingPanel(){
		super();
		eState=EState.idle;
		eAnchorState=EAnchorState.idle;
		mShapelists=new ArrayList<GEShapes>();
		MouseHandler mouseEventHandler=new MouseHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	
	public void setShapeTool(GEShapes shapes){  //eSeletedTool을 이용해 어떤 툴바인지 설정 반드시 static
		this.mShapes=shapes;
	}	
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);            
		Graphics2D g2D = (Graphics2D)g;
		for(GEShapes shape : mShapelists){
			shape.draw(g2D);
		}
	}
	private void continueDrawing(Point p){
		((GEPolygon)mShapes).continueDrawing(p);
	}
	
	private void initDrawing(Point point){
		mShapes=mShapes.clone();
		mShapes.initPosition(point);
	}
	
	private void keepDrawing(Point point){
		Graphics2D g2D=(Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		g2D.draw(mShapes.getShape());
		mShapes.setShapeCreate(point);
		g2D.draw(mShapes.getShape());
		
	}

	private void finishDrawing(GEShapes shape){
		mShapelists.add(shape);
		eState=EState.idle;
		repaint();	
	}
	
	private GEShapes onShape(Point p){
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			if(shape.onShape(p)){
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				return shape;
			}
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return null;
	}
	private void changeCursor(Point p, EAnchors eAnchors) {
		for (GEShapes shape: this.mShapelists) {
			switch(eAnchors){
			case NN:
				this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));break;
			case NW:
				this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));break;
			case NE:
				this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));break;
			case SS:
				this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));break;
			case SE:
				this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));break;
			case SW:
				this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));break;
			case EE:
				this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));break;
			case WW:
				this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));break;
			default:
				break;
			}
		}
	}
	
	private void clearSelectedShapes(){
		for(GEShapes shape:mShapelists){
			shape.setSelected(false);
		}
	}
	
	class MouseHandler
		implements MouseInputListener, MouseMotionListener{
		
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(eState == EState.Polygon){
					if(e.getClickCount() == 1 ){
						continueDrawing(e.getPoint());
					}else if(e.getClickCount() == 2){
						finishDrawing(mShapes);
						repaint();
					}
				}
			}
		}
		public void mouseEntered(MouseEvent e) { //마우스가 영역으로 들어왔을때,
			
		}
		public void mouseExited(MouseEvent e) { //마우스가 영역을 떠났을때
			
		}
		public void mousePressed(MouseEvent e) {
			if(eState == EState.idle){
				if(mShapes!=null){
					clearSelectedShapes();
					initDrawing(e.getPoint());
					if(mShapes instanceof GEPolygon){
						eState = EState.Polygon;
					}
					else{
						eState=EState.drawing;
					}
				}
				if(eAnchorState==EAnchorState.idle){
					selectedShape=onShape(e.getPoint());
					if(selectedShape!=null){
						clearSelectedShapes();
						selectedShape.setSelected(true);
					}
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
			
			if(eState == EState.drawing){
				finishDrawing(mShapes);
			}
		}
		public void mouseDragged(MouseEvent e) {
			if(eState!=EState.idle){
				keepDrawing(e.getPoint());
			}
		}
		public void mouseMoved(MouseEvent e) {
			if(eState == EState.Polygon){
				keepDrawing(e.getPoint());
			}else if(eState==EState.idle){
				GEShapes shape=onShape(e.getPoint());
				if(shape==null){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				else{
					if(shape.isSelected()){
						GEConstants.EAnchors anchorType=shape.onAnchor(e.getPoint());
						changeCursor(e.getPoint(), anchorType);
					}
					
				}
			}
			
			
	
			
		}	
	}
}
