package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstants;
import constants.GEConstants.EFileMenuItem;
import frame.GEDrawingPanel;
import manager.GEModel;
import transformer.GEMover;


public class GEFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GEDrawingPanel mDrawingPanel;
	private FileMenuHandler fileMenuHandler;
	private String currentDir=".";
	private String fileName=null;

	public GEFileMenu() {
		super("File");
		//Object creation
		fileMenuHandler=new FileMenuHandler();
		for(EFileMenuItem text: EFileMenuItem.values()){
			JMenuItem file=new JMenuItem(text.getText());
			file.addActionListener(fileMenuHandler);
			file.setActionCommand(text.name());
			this.add(file);
		}
	}
	public void init(GEDrawingPanel drawingPanel){
		this.mDrawingPanel=drawingPanel;
	}
	
	public void newFile(){
		System.out.println("newFile");
		int mConfirm=SaveOrNot();
		if(!(mConfirm==JOptionPane.CANCEL_OPTION)){
			this.mDrawingPanel.newPanel();
		}
	}
	
	public void save(){
		
	}
	public void open(){
		int mConfirm=SaveOrNot();
		if(!(mConfirm==JOptionPane.CANCEL_OPTION)){
			JFileChooser chooser=new JFileChooser(this.currentDir);
			FileNameExtensionFilter filter=new FileNameExtensionFilter(
					"GraphicEditor", "gps");
			chooser.setFileFilter(filter);
			int temp=chooser.showOpenDialog(null);
			if(temp==JFileChooser.APPROVE_OPTION){
				currentDir=chooser.getSelectedFile().getParent();
				fileName=this.currentDir+"\\"+chooser.getSelectedFile().getName();
				this.mDrawingPanel.readShape(fileName);
			}
		}
	}
	public void saveAs(){
		JFileChooser chooser=new JFileChooser(this.currentDir);
		FileNameExtensionFilter filter=new FileNameExtensionFilter(
				"GraphicEditor", "gps");
		chooser.setFileFilter(filter);
		int temp=chooser.showSaveDialog(null);
		if(temp==JFileChooser.APPROVE_OPTION){
			currentDir=chooser.getSelectedFile().getParent();
			fileName=currentDir+"\\"+chooser.getSelectedFile().getName();
			if(!fileName.endsWith("gps")){
				fileName=fileName+"."+"gps";
			}
			this.mDrawingPanel.saveShapes(fileName);
			try{
				GEModel.save("config\\file.config",currentDir);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public void close(){
		int dialogNum=JOptionPane.NO_OPTION;
		JFileChooser chooser=new JFileChooser();
		chooser.showSaveDialog(null);
		System.exit(1);
	}
	private int SaveOrNot(){
		int reply = JOptionPane.NO_OPTION;
//		if(mDrawingPanel.isUpdated()==true){
//			reply = JOptionPane.showConfirmDialog(null, GEConstants.SSAVEORNOT);
//		}
		if(reply == JOptionPane.OK_OPTION){
			save();
		}
		return reply;
	}
	
	
	private class FileMenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(EFileMenuItem.valueOf(e.getActionCommand())){
			case newItem:newFile();break;
			case open:open();break;
			case save:save();break;
			case saveAs:saveAs();break;
			case close:close();break;
			}
		}
		
	}
}
