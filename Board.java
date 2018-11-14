package minesweeper;

import java.util.*;

public class Board {
	private int[] sizeBoard = {6,10,15,0};	
	private int[] noMines = {4,15,30,0};
	int flagCounter=0;
    private Square[][] board;
   
    public Board(int type, int i, int j){
		
		 sizeBoard[3] = i;
		 noMines[3] = j; 
		 
		board = new Square[sizeBoard[type]][sizeBoard[type]];
		for(int a=0; a<board.length; a++){
			for(int b=0; b<board.length; b++){
				board[a][b] = new Square(false);
			}
		}
		
		this.resetMines(type);
		}
    
    
	public int getSizeBoard(int x){
		try{
			return sizeBoard[x];
		}catch(Exception e){
			throw new IllegalArgumentException("wrong value of x");
		}
	}
	
	public int getNoMines(int x){

		try{
			return noMines[x];
		}catch(Exception e){
			throw new IllegalArgumentException("wrong value of x");
		}
	}
	
	
	 
	
	

  public void resetMines(int type){
	  	int x;
	  	int y;
	  	int c = 0; 
	    int mine=noMines[type];
	  	Random ran = new Random();
	  	
	    
	  	for(int a=0; a<board.length; a++){
			for(int b=0; b<board.length; b++){
				board[a][b].setMine(false);
			}
		}
	  	
		while(c<mine){
			x = ran.nextInt(board.length);
		    y = ran.nextInt(board.length);
		    
		    if(!board[x][y].getMine()){
		    	board[x][y].setMine(true);
		    	c=c+1;
		    }
		}
  
  }
	
	public String clicked(int x, int y, int d){

		int c= 0;
			if(!board[x][y].getMine()){
				if(x>0){
					if(board[x-1][y].getMine()){
						c=c+1;
					}
				}
				if(y>0){
					if(board[x][y-1].getMine()){
						c=c+1;
					}
				}
				if(x<sizeBoard[d]-1){
					if(board[x+1][y].getMine()){
						c=c+1;
					}
				}
				if(y<sizeBoard[d]-1){
					if(board[x][y+1].getMine()){
						c=c+1;
					}
				}
				if(x>0 && y>0){
					if(board[x-1][y-1].getMine()){
						c=c+1;
					}
				}
				if(x<sizeBoard[d]-1 && y<sizeBoard[d]-1){
					if(board[x+1][y+1].getMine()){
						c=c+1;
					}
				}
				if(x<sizeBoard[d]-1 && y>0){
					if(board[x+1][y-1].getMine()){
						c=c+1;
					}
				}
				if(x>0 && y<sizeBoard[d]-1){
					if(board[x-1][y+1].getMine()){
						c=c+1;
					}
				}
			}
			
		return Integer.toString(c);
	}
	
	public boolean checkWin(int type){
		int counter=0;
		boolean checkFlag = false;
		for(int x = 0; x<board.length; x++){
			for(int y = 0; y<board.length; y++){
			
			if(board[x][y].getClicked()==true){
				counter=counter+1;
			}
			if(board[x][y].getMine()==false && board[x][y].getIsFlag()==true){
				checkFlag=true;
			}
		
		}
	}
		
		if(counter == (sizeBoard[type]*sizeBoard[type])-noMines[type] && checkFlag==false){
			return true;
	}
		else{
			return false;
		}
	
	}
	
	
	public boolean clickedMine(int x, int y){	
		return board[x][y].getMine();
	}
	
	public boolean getClicked(int x, int y){
		return board[x][y].getClicked(); 	
	}
	
	public void setFlag(int x, int y){
		board[x][y].setIsFlag(!board[x][y].getIsFlag());
	}
	
	public boolean getFlag(int x, int y){
		return board[x][y].getIsFlag();
	}
	
	public void setClicked(int x, int y, boolean set){
		board[x][y].setClicked(set);
	}
	public boolean getSpread(int x, int y){
		return board[x][y].getSpread();
	}
	public void setSpread(int x, int y, boolean set){
		board[x][y].setSpread(set);
	}
	public boolean getMine(int x, int y){
		return board[x][y].getMine();
	}
	public void addFlagCounter(int x){
		flagCounter = flagCounter + x;
	}
	public int getFlagCounter(){
		return flagCounter;
	}

	public int correctFlags(int x, int y, int d){
		int c=0;
		if(!board[x][y].getMine()){
			if(x>0){
				if(board[x-1][y].getMine() && board[x-1][y].getIsFlag()){
					c=c+1;
				}
			}
			if(y>0){
				if(board[x][y-1].getMine() && board[x][y-1].getIsFlag()){
					c=c+1;
				}
			}
			if(x<sizeBoard[d]-1){
				if(board[x+1][y].getMine() && board[x+1][y].getIsFlag()){
					c=c+1;
				}
			}
			if(y<sizeBoard[d]-1){
				if(board[x][y+1].getMine() && board[x][y+1].getIsFlag()){
					c=c+1;
				}
			}
			if(x>0 && y>0){
				if(board[x-1][y-1].getMine() && board[x-1][y-1].getIsFlag()){
					c=c+1;
				}
			}
			if(x<sizeBoard[d]-1 && y<sizeBoard[d]-1){
				if(board[x+1][y+1].getMine() && board[x+1][y+1].getIsFlag()){
					c=c+1;
				}
			}
			if(x<sizeBoard[d]-1 && y>0){
				if(board[x+1][y-1].getMine() && board[x+1][y-1].getIsFlag()){
					c=c+1;
				}
			}
			if(x>0 && y<sizeBoard[d]-1){
				if(board[x-1][y+1].getMine() && board[x-1][y+1].getIsFlag()){
					c=c+1;
				}
			}
		}
		return c;
	}
		
}