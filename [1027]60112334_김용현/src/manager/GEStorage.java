package manager;

import java.util.ArrayList;

import shapes.GEShapes;

public class GEStorage {
	private ArrayList <GEShapes> mTempStorage;

	public GEStorage() {
		mTempStorage=new ArrayList<GEShapes>();
	}
	public ArrayList<GEShapes> paste(){
		return (ArrayList<GEShapes>) mTempStorage.clone();
	}
	public void copy(ArrayList<GEShapes> shapeList){
		mTempStorage.clear();
		GEShapes shape;
		for(int i=shapeList.size();i>0;i--){
			shape=shapeList.get(i-1);
			if(shape.isSelected()){
				mTempStorage.add(shape.deepCopy());
			}
		}
	}
	public void cut(ArrayList<GEShapes> shapeList){
		mTempStorage.clear();
		GEShapes shape;
		for(int i=shapeList.size();i>0;i--){
			shape=shapeList.get(i-1);
			if(shape.isSelected()){
				mTempStorage.add(shape.deepCopy());
				shapeList.remove(shape);
			}
		}
	}

}
