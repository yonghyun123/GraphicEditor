package frame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants;
import constants.GEConstants.EAnchors;
import manager.GECursor;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShapes;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GETransformer;


public class GEDrawingPanel extends JPanel {

	static private enum EState{
		idle, drawing,Polygon,Moving,Resizing;
	}
	static private enum EAnchorState{
		idle, onAnchor;
	}
	//attribute
	private static final long serialVersionUID = 1L;
	//working variables
	private GEShapes mShapes, mSelectedShape;
	private ArrayList<GEShapes> mShapelists;
	private EState eState;
	private EAnchorState eAnchorState;
	private GETransformer mTransfomer;
	//associative attributes
	private Color mLineColor;
	private Color mFillColor;
	//cursor handler
	private GECursor mCursor;
	
	public GEDrawingPanel(){
		super();
		this.setBackground(Color.white);
		mLineColor=Color.black;
		mFillColor=Color.black;
		eState=EState.idle;
		mCursor=new GECursor();
		eAnchorState=EAnchorState.idle;
		mShapelists=new ArrayList<GEShapes>();
		MouseHandler mouseEventHandler=new MouseHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void initialize(){
//		mLineColor=Color.BLACK;
	}
	//line color at shapes
	public void setLineColor(Color lineColor){
		if(selectedSetColor(true,lineColor))
			return;
		this.mLineColor=lineColor;
	}
	//fill color at shapes
	public void setFillColor(Color fillColor){
		if(selectedSetColor(false, fillColor))
			return;
		this.mFillColor=fillColor;
	}
	//which setColor(Line,Fill) is selected and color setting
	private boolean selectedSetColor(boolean flag,Color color){
		if(mSelectedShape!=null){
			if(flag){
				mSelectedShape.setLineColor(color);
			}else{
				mSelectedShape.setFillColor(color);
			}
			repaint();
			return true;
		}
		return false;
	}
	public void setCurrentState(EState currentState){
		this.eState=currentState;
	}
	public void setShapeTool(GEShapes shapes){  //eSeletedTool을 이용해 어떤 툴바인지 설정 반드시 static
		this.mShapes=shapes;
	}	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);            
		Graphics2D g2D = (Graphics2D)g;
		for(GEShapes shape : mShapelists){
			shape.draw(g2D);
		}
	}
	
	private void initDrawing(Point startP){
		mShapes=mShapes.clone();
		mShapes.setLineColor(mLineColor);
		mTransfomer=new GEDrawer(mShapes);
	}

	private void continueDrawing(Point p){
		((GEPolygon)mShapes).continueDrawing(p);
	}
	
	
	private GEShapes onShape(Point p){
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			if(shape.onShape(p)){
				return shape;
			}
		}
		return null;
	}
	
	private void clearSelectedShapes(){
		for(GEShapes shape:mShapelists){
			shape.setSelected(false);
		}
	}
	private void changeCursor(Point p, EAnchors eAnchors) {
		for (@SuppressWarnings("unused") GEShapes shape: this.mShapelists) {
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
			case RR:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("Image/custom.png").getImage(),
						new Point(0,0),"custom cursor"));
				break;
			default:
				break;
			}
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
	
						((GEDrawer)mTransfomer).finalize(mShapelists);
						eState=EState.idle;
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
					mSelectedShape=null;
					initDrawing(e.getPoint());
					mTransfomer=new GEDrawer(mShapes);
					
					((GEDrawer)mTransfomer).init(e.getPoint());
					if(mShapes instanceof GEPolygon){
						eState = EState.Polygon;
					}
					else{
						eState=EState.drawing;
					}
				}
				else{
					mSelectedShape=onShape(e.getPoint());
					if(mSelectedShape!=null){
						clearSelectedShapes();
						mSelectedShape.setSelected(true);
						mSelectedShape.onAnchor(e.getPoint());
						if(mSelectedShape.getSelectedAnchor()==EAnchors.NONE){
							mTransfomer=new GEMover(mSelectedShape);
							eState=EState.Moving;
							((GEMover)mTransfomer).init(e.getPoint());
						}
						else{
							mTransfomer=new GEResizer(mSelectedShape);
							((GEResizer)mTransfomer).init(e.getPoint());
							setCurrentState(EState.Resizing);
						}
				
					}
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
			
			if(eState == EState.drawing){
				((GEDrawer)mTransfomer).finalize(mShapelists);
				
			}else if(eState==EState.Polygon){
				return;
			}else if(eState==EState.Resizing){
				((GEResizer)mTransfomer).finalize(e.getPoint());
			}
			eState=EState.idle;
			repaint();
		}
		public void mouseDragged(MouseEvent e) {
			if(eState!=EState.idle){
				
				mTransfomer.transfomer((Graphics2D)getGraphics(), e.getPoint());
			}
		}
		public void mouseMoved(MouseEvent e) {
			if(eState==EState.Polygon){
				((GEDrawer)mTransfomer).transfomer((Graphics2D)getGraphics(), e.getPoint());
			}else if(eState==EState.idle){
				GEShapes shape=onShape(e.getPoint());
				if(shape==null){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else{
					if(shape.isSelected()){
						EAnchors anchorType=shape.onAnchor(e.getPoint());
						setCursor(mCursor.get(anchorType.ordinal()));
					}
				}
			}
			
		}	
		
	}
}
