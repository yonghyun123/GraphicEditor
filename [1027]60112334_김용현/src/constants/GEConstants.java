package constants;

import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShapes;

public class GEConstants {
	public final static int FRAME_X=100;
	public final static int FRAME_Y=100;
	public final static int FRAME_W=400;
	public final static int FRAME_H=600;

	
	public static enum EFileMenuItem{
		newItem("new"),
		open("open"), 
		close("close"),
		save("save"),
		saveAs("saveAs"),
		exit("exit");
		
		private String text;
		private EFileMenuItem(String text){
			this.text=text;
		}
		
		public String getText(){
			return this.text;
		}
		
	}
	public static enum EditFileMenu{
		Cut("Cut"),
		Copy("Copy"), 
		Paste("Paste"),
		Delete("Delete"),
		Redo("Redo"),
		Undo("Undo"),
		Group("Group"),
		Ungroup("Ungroup");
		
		private String text;
		private EditFileMenu(String text){
			this.text=text;
		}
		
		public String getText(){
			return this.text;
		}
	}
	
	public enum toolbarButtons{
		rectangle("Image/rectangle.gif", "Image/rectangleSLT.gif", new GERectangle()),
		ellipse("Image/ellipse.gif", "Image/ellipseSLT.gif", new GEEllipse()),
		line("Image/line.gif", "Image/lineSLT.gif", new GELine()),
		polygon("Image/polygon.gif", "Image/polygonSLT.gif", new GEPolygon());
		
		
		private String mbuttonImage, mSelectedbuttonImage;
		private GEShapes mShape;
		
		//enumerate initializer
		private toolbarButtons(String buttonImage, String selectedbuttonImage, GEShapes shape){
			this.mbuttonImage = buttonImage;
			this.mSelectedbuttonImage = selectedbuttonImage;
			this.mShape = shape;
		}
		
		//getter
		public String getSelectButtonImage(){
			return mSelectedbuttonImage;
		}
		public String getButtonImage(){
			return mbuttonImage;
		}
		public GEShapes getShape(){
			return mShape;
		}
	}
}
