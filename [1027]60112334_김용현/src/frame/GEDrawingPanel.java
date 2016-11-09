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
		Cursor mCursor=new Cursor(Cursor.HAND_CURSOR);
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			if(shape.onShape(p)){
				
				setCursor(mCursor);
				return shape;
			}
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		System.out.println("null");
		return null;
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
			}
			selectedShape=onShape(e.getPoint());
			if(selectedShape!=null){
				clearSelectedShapes();
				selectedShape.setSelected(true);
			}
			
		}	
	}

}
