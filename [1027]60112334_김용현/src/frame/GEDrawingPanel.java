package frame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GEConstants.EAnchors;
import manager.GECursor;
import manager.GEDoStack;
import manager.GEStorage;
import manager.GETextRotater;
import shapes.GEGroup;
import shapes.GEPolygon;
import shapes.GESelect;
import shapes.GEShapes;
import transformer.GEDrawer;
import transformer.GEGrouper;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotater;
import transformer.GETransformer;
import util.GEModel;


public class GEDrawingPanel extends JPanel implements Printable{

	private GEDrawingPanel mInstance;
	static private enum EState{
		idle, drawing,Polygon,Moving,Resizing,Selecting,Rotating;
	}
	//attribute
	private static final long serialVersionUID = 1L;
	//working variables
	private GEShapes mShapes, mSelectedShape;
	private ArrayList<GEShapes> mShapelists;
	private EState eState;
	private GETransformer mTransfomer;
	private boolean fictureUpdate;
	//associative attributes
	private Color mLineColor;
	private Color mFillColor;
	//cursor handler
	private GECursor mCursor;
	//component
	private GEStorage mStorage;
	private GEDoStack mDoStack;
	private GETextRotater mTextRotater;

	
	public GEDrawingPanel(){
		super();
		mInstance=this;
		this.setBackground(Color.white);
		mLineColor=Color.black;
		mFillColor=Color.WHITE;
		eState=EState.idle;
		mCursor=new GECursor();
		mStorage=new GEStorage();
		mDoStack=new GEDoStack();
		mTextRotater=new GETextRotater();
		mShapelists=new ArrayList<GEShapes>();
		MouseHandler mouseEventHandler=new MouseHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		fictureUpdate=false;
	}
	
	public void initialize(){
//		mLineColor=Color.BLACK;
		this.mDoStack.clear();
	}
	public boolean getFictureUpdate(){
		return fictureUpdate;
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
		boolean returnValue=false;
		for(GEShapes shape:mShapelists){
			if(shape.isSelected()){
				if(flag){
					shape.setLineColor(color);
				}else{
					shape.setFillColor(color);
				}
				returnValue=true;
			}
		}
		repaint();
		return returnValue;
	}
	//---------------------------------file menu items
	public void newPanel(){
		mShapelists.removeAll(mShapelists);
		repaint();
	}
	//saving panel
	public void saveShapes(String fileName){
		try {
			GEModel.save(fileName, mShapelists);
			fictureUpdate=false;
			//updated = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//reading panel
	@SuppressWarnings("unchecked")
	public void readShape(String fileName){
			try {
				this.mShapelists = (ArrayList<GEShapes>) GEModel.read(fileName);
				fictureUpdate=false;
				this.repaint();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		
	}
	//TextRotator
	public void freshTextRotater(){
		if(mTextRotater != null){
			remove(mTextRotater.getTextField());
			mTextRotater = null;
		}
	}
	//add working stack
	public void addStack(){
		mDoStack.push(mShapelists);
	}
	//------------------------------------edit menu items
	public void push(){
	
	}
	public void undo(){
		mShapelists=mDoStack.undo();
		mSelectedShape=null;
		repaint();
	}
	public void redo(){
		mShapelists=mDoStack.redo();
		mSelectedShape=null;
		repaint();
	}
	public void group(GEGroup group){
		boolean check=false;
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			
			if(shape.isSelected()){
				shape.setSelected(false);
				group.addShape(shape);
				mShapelists.remove(shape);
				check=true;
			}
		}
		if(check){
			group.setSelected(true);
			mShapelists.add(group);
		}
		repaint();
	}
	
	public void unGroup(){
		ArrayList<GEShapes> tempList=new ArrayList<GEShapes>();
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			if(shape instanceof GEGroup && shape.isSelected()){
				for(GEShapes childShape: ((GEGroup)shape).getChildList()){
					childShape.setSelected(true);
					tempList.add(childShape);
				}
				mShapelists.remove(shape);
			}
		}
		mShapelists.addAll(tempList);
		repaint();
	}
	//paste
	public void paste(){
		for(GEShapes shape:mStorage.paste()){
			mShapelists.add(shape.deepCopy());
		}
		addStack();
		repaint();
	}
	//copy
	public void copy(){
		mStorage.copy(mShapelists);
	}
	//cut
	public void cut(){
		mStorage.cut(mShapelists);
		addStack();
		repaint();
	}
	//delete
	public void delete(){
		for(int i=mShapelists.size();i>0;i--){
			GEShapes shape=mShapelists.get(i-1);
			if(shape.isSelected()){
				shape.setSelected(false);
				mShapelists.remove(shape);
			}
		}
		addStack();
		repaint();
	}
	
	public void setCurrentState(EState currentState){
		this.eState=currentState;
	}
	public void setShapeTool(GEShapes shapes){  //eSeletedTool�� �̿��� � �������� ���� �ݵ�� static
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
		mShapes.setFillColor(mFillColor);
		//mTransfomer=new GEDrawer(mShapes);
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
		public void mouseEntered(MouseEvent e) { //���콺�� �������� ��������,
			
		}
		public void mouseExited(MouseEvent e) { //���콺�� ������ ��������
			
		}
		public void mousePressed(MouseEvent e) {
			freshTextRotater();
			if(eState == EState.idle){
				if(mShapes instanceof GESelect){
					mSelectedShape=onShape(e.getPoint());
					if(mSelectedShape!=null){
						clearSelectedShapes();
						mSelectedShape.setSelected(true);
						mSelectedShape.onAnchor(e.getPoint());
						if(mSelectedShape.getSelectedAnchor()==EAnchors.NONE){
							mTransfomer=new GEMover(mSelectedShape);
							((GEMover)mTransfomer).init(e.getPoint());
							eState=EState.Moving;
						}else if(mSelectedShape.getSelectedAnchor()==EAnchors.RR){
							mTransfomer=new GERotater(mSelectedShape);
							((GERotater)mTransfomer).init(e.getPoint());
							eState=EState.Rotating;
						}
						else{
							mTransfomer=new GEResizer(mSelectedShape);
							((GEResizer)mTransfomer).init(e.getPoint());
							eState=EState.Resizing;
						}
					}
					else{
						eState=EState.Selecting;
						clearSelectedShapes();
						initDrawing(e.getPoint());
						mTransfomer=new GEGrouper(mShapes);
						((GEGrouper)mTransfomer).init(e.getPoint());
					}
				}
				else{
					clearSelectedShapes();
					initDrawing(e.getPoint());
					mTransfomer=new GEDrawer(mShapes);
					((GEDrawer)mTransfomer).init(e.getPoint());
					if(mShapes instanceof GEPolygon){
						eState=EState.Polygon;
					}else{
						eState=EState.drawing;
					}
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
			
			if(eState == EState.drawing){
				((GEDrawer)mTransfomer).finalize(mShapelists);
				addStack();
				fictureUpdate=true;
			}else if(eState==EState.Polygon){
				fictureUpdate=true;
				return;
			}else if(eState==EState.Resizing){
				((GEResizer)mTransfomer).finalize(e.getPoint());
				addStack();
				fictureUpdate=true;
			}else if(eState==EState.Moving){
				((GEMover)mTransfomer).isMoved();
			}else if(eState==EState.Selecting){
				((GEGrouper)mTransfomer).finalize(mShapelists);
			}else if(eState==EState.Rotating){
				if(((GERotater)mTransfomer).isMoved()){
					((GERotater)mTransfomer).finalize(mShapelists);
					addStack();
					fictureUpdate=true;
				}else{
					System.out.println("rotation");
					mTextRotater=new GETextRotater();
					mTextRotater.init(mSelectedShape, mInstance);
					add(mTextRotater.getTextField());
					mTextRotater.requestFocus();
				}
				
			}
			eState=EState.idle;
			repaint();
		}
		public void mouseDragged(MouseEvent e) {
			if(eState!=EState.idle){
				mTransfomer.transfomer((Graphics2D)getGraphics(), e.getPoint());
				if(mTransfomer instanceof GEMover){
					((GEMover)mTransfomer).setMove(true);
				}else if(mTransfomer instanceof GERotater){
					((GERotater)mTransfomer).setMove(true);
				}
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
						if(anchorType==EAnchors.RR){
							setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
									new ImageIcon("Image/custom.png").getImage(),
									new Point(0,0),"custom cursor"));
						}
						else{
							setCursor(mCursor.get(anchorType.ordinal()));
						}
						
					}
				}
			}
			
		}	
		
	}


	@Override
	public int print(Graphics g, PageFormat pageFormat, int pagIndex) throws PrinterException {
		Graphics2D g2 = (Graphics2D) g;
		if (pagIndex >= 1) {
			return Printable.NO_SUCH_PAGE;
		} else {
			this.printAll(g2);
			return Printable.PAGE_EXISTS;
		}
	}
}
