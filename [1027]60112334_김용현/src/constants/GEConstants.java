package constants;

import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelect;
import shapes.GEShapes;

public class GEConstants {
	public final static int FRAME_X=100;
	public final static int FRAME_Y=100;
	public final static int FRAME_W=400;
	public final static int FRAME_H=600;
	//GEAnchors
	public enum EAnchors{
		NW,NN,NE,WW,EE,SW,SS,SE,RR, NONE;
	}
	//GEMenuItems
	public static enum EFileMenuItem{
		newItem("new"),
		open("open"), 
		save("save"),
		saveAs("saveAs"),
		print("print"),
		close("close");
		
		private String text;
		private EFileMenuItem(String text){
			this.text=text;
		}
		public String getText(){
			return this.text;
		}
	}
	//GEMenuItems
	public static enum EEditMenuItem{
		Cut("Cut"),
		Copy("Copy"), 
		Paste("Paste"),
		Delete("Delete"),
		Redo("Redo"),
		Undo("Undo"),
		Group("Group"),
		Ungroup("Ungroup");
		
		private String text;
		private EEditMenuItem(String text){
			this.text=text;
		}
		
		public String getText(){
			return this.text;
		}
	}
	//GEMenuItems
	public static enum EColorMenuItems{
		SetLine("SetLine"),
		ClearLine("ClearLine"),
		SetFill("SetFill"),
		ClearFill("ClearFill");
		private String text;
		private EColorMenuItems(String text){
			this.text=text;
		}
		public String getText(){
			return this.text;
		}
	}
	
	//GEToolbar
	public enum toolbarButtons{
		select("Image/select.gif", "Image/selectSLT.gif",new GESelect()),
		rectangle("Image/rectangle.gif", "Image/rectangleSLT.gif", new GERectangle()),
		ellipse("Image/ellipse.gif", "Image/ellipseSLT.gif", new GEEllipse()),
		line("Image/line.gif", "Image/lineSLT.gif", new GELine()),
		polygon("Image/polygon.gif", "Image/polygonSLT.gif", new GEPolygon()),
		backward("Image/backward.gif","Image/backward.gif",null),
		forward("Image/forward.gif","Image/forward.gif",null);
		
		
		
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
	//GEMenuColor
	public static final String FILLCOLOR="Selected Fill Color";
	public static final String LINECOLOR="Selected Line Color";
	//GETransformer
	public final static int DEFAULT_DASH_OFFSET=4;
	public final static int DEFAULT_DASHEDLINE_WIDTH=1;
	//GEMenuFile
	public static String SFILECONFIG="config\\file.config";
	public static String SWORKSPACE="data\\";
	public static String SSAVEORNOT="변경 내용을 저장하시겠습니까?";
	public static String SFILEKIND="Graphics Editor";
	public static String SFILEEXTENSION="gps";
	//GETextRotater
	public final static int TEXT_POS_X_OFFSET = 10;
	public final static int TEXT_POS_Y_OFFSET = 5;
	public final static int TEXT_WIDTH_LENGTH = 40;
	public final static int TEXT_HEIGHT_LENGTH = 20;
			
}
