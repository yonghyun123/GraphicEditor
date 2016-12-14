package manager;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import constants.GEConstants;
import constants.GEConstants.EAnchors;
import frame.GEDrawingPanel;
import shapes.GEShapes;

public class GETextRotater {
	TextField mTextField;
	GEShapes mShapes;
	InputListener inputListener;
	GEDrawingPanel mDrawingPanel;
	public GETextRotater(){
		mTextField = new TextField();
		inputListener = new InputListener();
	}
	
	public void init(GEShapes shape, GEDrawingPanel drawingPanel){
		this.mShapes = shape;
		this.mDrawingPanel = drawingPanel;
		Rectangle2D rec = shape.getAnchorList().getAnchors().get(EAnchors.RR.ordinal()).getFrame();
		int x = (int)rec.getX();
		int y = (int)rec.getY();
		mTextField.addKeyListener(inputListener);
		mTextField.setBounds(x + GEConstants.TEXT_POS_X_OFFSET, y - GEConstants.TEXT_POS_Y_OFFSET, 
				GEConstants.TEXT_WIDTH_LENGTH, GEConstants.TEXT_HEIGHT_LENGTH);
	}
	
	public TextField getTextField(){
		return mTextField;
	}
	
	public void requestFocus(){
		mTextField.requestFocus();
	}
	
	private class InputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				System.out.println("press Enter");
				if(!mTextField.getText().equals("")){
					try{
						double delta = Math.toRadians(Double.parseDouble(mTextField.getText()));
						Point2D.Double ROrigin = new Point2D.Double(mShapes.getBounds().getCenterX(), mShapes.getBounds().getCenterY());
						mShapes.rotaterCoordinate(delta, ROrigin);
						mDrawingPanel.freshTextRotater();
						mDrawingPanel.repaint();
						mDrawingPanel.addStack();
						}
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
					
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			  char c = e.getKeyChar();
			  
			  if (!Character.isDigit(c)) {
				   e.consume();
				   return;
			  }
		}

	}
	
}
