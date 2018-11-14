package minesweeper;

public class Square {

	private boolean mine, clicked, isFlag, spread;

	public Square(boolean mine){
		this.mine=false;
		this.clicked=false;
		this.isFlag=false;
		this.spread=false;	
	}
	
	public boolean getIsFlag(){
		return isFlag;
	}
	
	public void setIsFlag(boolean x){
		isFlag=x;
	}
	
	public boolean getMine(){
		return mine;
	}
	
	public void setMine(boolean x){
		mine=x;
	}
	
	public boolean getClicked(){
		return clicked;
	}
	
	public void setClicked(boolean x){
		clicked =x;
	}
	public boolean getSpread(){
		return spread;
	}
	public void setSpread(boolean x){
		spread=x;
	}	
}