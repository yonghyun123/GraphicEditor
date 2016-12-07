package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EColorMenuItems;
import frame.GEDrawingPanel;

public class GEColorMenu extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GEDrawingPanel mDrawingPanel;
	private ColorMenuHandler mColorMenuHandler;
	//initializer
	public GEColorMenu() {
		super("Color");  //JMenu에 이름을 붙인다.
		mColorMenuHandler=new ColorMenuHandler();
		for(EColorMenuItems text: EColorMenuItems.values()){
			JMenuItem item=new JMenuItem(text.getText());
			item.addActionListener(mColorMenuHandler);
			item.setActionCommand(text.name());
			this.add(item);
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		mDrawingPanel=drawingPanel;
	}
	private void setLineColor(){
		Color lineColor=JColorChooser.showDialog(null, GEConstants.LINECOLOR,null);
		if(lineColor!=null){
			mDrawingPanel.setLineColor(lineColor);
		}
		
	}
	private void setFillColor(){
		Color fillColor=JColorChooser.showDialog(null, GEConstants.FILLCOLOR,null);
		if(fillColor!=null){
			mDrawingPanel.setFillColor(fillColor);
		}
		
	}
	private void clearLineColor(){
		mDrawingPanel.setLineColor(Color.BLACK);
	}
	private void clearFillColor(){
		mDrawingPanel.setFillColor(Color.WHITE);
	}

	private class ColorMenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch(EColorMenuItems.valueOf(e.getActionCommand())){
			case SetLine:
				setLineColor();break;
			case SetFill:
				setFillColor();break;
			case ClearLine:
				clearLineColor();break;
			case ClearFill:
				clearFillColor();break;
			
			}
			
		}
		
	}

}
