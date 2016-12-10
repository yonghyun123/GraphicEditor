package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import shapes.GEGroup;
import shapes.GEShapes;

public class GERotater extends GETransformer{
	private ArrayList<GEShapes> mShapeList;
	private Point2D.Double mCenter;
	private double mTheta;

	public GERotater(GEShapes shape) {
		super(shape);
		if(shape instanceof GEGroup){
			mShapeList=new ArrayList<GEShapes>();
			for(GEShapes child:((GEGroup)shape).getChildList()){
				mShapeList.add(child);
			}
		}
	}
	public void init(Point p){
		mCenter=new Point2D.Double(mShapes.getBounds().getCenterX(),
				mShapes.getBounds().getCenterY());
		mTheta=Math.atan2(mCenter.y-p.getY(), mCenter.x-p.getX());
	}


	@Override
	public void transfomer(Graphics2D g2d, Point p) {
		// TODO Auto-generated method stub
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		//obtain theta
		double theta2 = mTheta - Math.atan2(mCenter.y- p.getY(), p.getX() - mCenter.x);
		if(mShapes instanceof GEGroup){
			GEShapes temp;
			for(int i = 0; i < mShapeList.size(); i++){
				temp = mShapeList.get(i);
				temp.draw(g2d);
				temp.rotaterCoordinate(theta2, mCenter);
				temp.draw(g2d);
			}
		}else{
			mShapes.draw(g2d);
			mShapes.rotaterCoordinate(theta2, mCenter);
			mShapes.draw(g2d);
		}
		//AffineTransform으로 변환
		mTheta = Math.atan2(mCenter.y - p.getY(), p.getX() - mCenter.x); // 이동한 각 저장
	}
	
	public void finalize(ArrayList<GEShapes> shapeList){
		for(GEShapes tempShape:shapeList){

			if(mShapes.getBounds().contains(tempShape.getBounds())){
				
				tempShape.setSelected(true);
			}
		}
	}

}
