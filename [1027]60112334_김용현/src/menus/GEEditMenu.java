package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EEditMenuItem;
import frame.GEDrawingPanel;
import manager.GEGroup;


public class GEEditMenu extends JMenu {

	/**
	 * 
	 */	
	private GEDrawingPanel mDrawingPanel;
	private EditMenuHandler editMenuHandler;
	private static final long serialVersionUID = 1L;

	public GEEditMenu(){
		super("Edit");  //JMenu에 이름을 붙인다.
		editMenuHandler= new EditMenuHandler();
		for(EEditMenuItem text: EEditMenuItem.values()){
			JMenuItem item=new JMenuItem(text.getText());
			item.addActionListener(editMenuHandler);
			item.setActionCommand(text.toString());
			System.out.println(text.toString());
			this.add(item);
		}
	}
	public void init(GEDrawingPanel drawingPanel){
		mDrawingPanel=drawingPanel;
	}
	public void unGroup(){
		mDrawingPanel.unGroup();
	}
	public void group(){
		mDrawingPanel.group(new GEGroup());
	}
	private class EditMenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println(e.getActionCommand().toString());
			if(e.getActionCommand().equals(EEditMenuItem.Group.getText())){
				group();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Copy.getText())){
				//group();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Cut.getText())){
				//group();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Delete.getText())){
				//group();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Ungroup.getText())){
				unGroup();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Redo.getText())){
				//group();
			}
			else if(e.getActionCommand().equals(EEditMenuItem.Undo.getText())){
				//group();
			}
			
		}
	}
}
