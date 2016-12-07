package frame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;


// MainFrame specialize JFrame
public class GEMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	//components
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	private GEDrawingPanel drawingPanel;
	
	GEMainFrame() {
		// attribute initialization
		
		this.setTitle("GraphicEditor");
		this.setSize(400, 600);
		this.setLayout(new BorderLayout());


		//component creation & registration
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		drawingPanel = new GEDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		

		// add component
			
	}
	public void init(){
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//설명적인 상수
		this.getContentPane().setBackground(Color.black);
		//set association among components
		toolBar.setDrawingPanel(drawingPanel);
		menuBar.initialize(drawingPanel);
		toolBar.initialize();
		drawingPanel.initialize();
		
;	}
}
