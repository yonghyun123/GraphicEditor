package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants.toolbarButtons;
import shapes.GEShapes;

public class GEToolBar extends JToolBar {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 1L;
	private GEDrawingPanel drawingPanel; // Association 연결의 의미
	


	public GEToolBar(){
		ButtonGroup buttonGroup=new ButtonGroup();
		ActionHandler actionHandler=new ActionHandler();
		// Icon and image created in Toolbar 
		JRadioButton radioButton=null;
		
		for(toolbarButtons mButton: toolbarButtons.values()){
			radioButton=new JRadioButton();
			radioButton.setIcon(new ImageIcon(mButton.getButtonImage()));
			radioButton.setSelectedIcon(new ImageIcon(mButton.getSelectButtonImage()));
			this.add(radioButton);
			buttonGroup.add(radioButton);
			radioButton.addActionListener(actionHandler);
			radioButton.setActionCommand(mButton.toString());
		}	

	}
	//default 값으로 rectangle click
	public void initialize() {
		JRadioButton button=(JRadioButton) this.getComponentAtIndex(0);
		button.doClick();
		
	}
	public void setDrawingPanel(GEDrawingPanel drawingPanel){
		this.drawingPanel=drawingPanel;
	}

	// ActionListener class created
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GEShapes toolbarShape=toolbarButtons.valueOf(e.getActionCommand()).getShape();
			drawingPanel.setShapeTool(toolbarShape);
		}	
	}
}
