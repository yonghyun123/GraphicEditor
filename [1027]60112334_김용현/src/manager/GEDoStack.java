package manager;

import java.util.ArrayList;
import java.util.Vector;


import shapes.GEShapes;

public class GEDoStack {
	private Vector<ArrayList<GEShapes>> mStack;
	private int mCurrentLayer;

	public GEDoStack() {
		// TODO Auto-generated constructor stub
		this.mStack=new Vector<ArrayList<GEShapes>>();
		this.mCurrentLayer=0;
	}
	
	public void push(ArrayList<GEShapes> mShapelists){
		System.out.println("history push");
		ArrayList<GEShapes> temp=new ArrayList<GEShapes>();
		if(mCurrentLayer<mStack.size()){
			for(int i=mStack.size()-1;mCurrentLayer<=i;i--){
				mStack.remove(i);
			}
		}
		for(int i =0; i<mShapelists.size(); i++){
			temp.add(mShapelists.get(i).deepCopy());
		}
		mStack.add(temp);
		mCurrentLayer++; 
		System.out.println("history size : " + mStack.size() + ", num : " + mCurrentLayer);
	}
	
	public ArrayList<GEShapes> undo(){
		if(0 > mCurrentLayer-1){
			System.out.println("undo error");
			return new ArrayList<GEShapes>();
		}
		mCurrentLayer = mCurrentLayer-1;
		System.out.println("history size : " + mStack.size() + ", num : " + mCurrentLayer);
		if(mCurrentLayer == 0){
			return new ArrayList<GEShapes>();
		}
		else {
			ArrayList<GEShapes> temp = new ArrayList<GEShapes>();
			ArrayList<GEShapes> base = mStack.get(mCurrentLayer - 1); 
			for(int i =0; i<base.size(); i++){
				temp.add(base.get(i).deepCopy());
			}
			return temp;
		}
	}
	public ArrayList<GEShapes> redo(){
		ArrayList<GEShapes> temp = new ArrayList<GEShapes>();
		if(mStack.size() < mCurrentLayer+1){
			System.out.println("redo error");
			if(mStack.size() == 0){
//				System.out.println("mStack가0이다");
				return new ArrayList<GEShapes>();
			}
			else {
//				System.out.println("마지막전송");
				ArrayList<GEShapes> base = mStack.get(mCurrentLayer - 1); 
				for(int i =0; i<base.size(); i++){
					temp.add(base.get(i).deepCopy());
				}
				return temp;
			}
		}
//		System.out.println("중간전송");
		mCurrentLayer = mCurrentLayer+1;
		ArrayList<GEShapes> base = mStack.get(mCurrentLayer - 1); 
		for(int i =0; i<base.size(); i++){
			temp.add(base.get(i).deepCopy());
		}
		return temp;
	}
	
	
	public int stackSize(){
		return mStack.size();
	}
	
	public void clear(){
		this.mStack.clear();
	}

}
