package minesweeper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener{
	
private int[] expansion = {40,35,30,0};	
private int[] mineSize = {45,40,35,0};	
boolean lose = false;
JButton buttons[][];
JFrame game;
JFrame start;
JButton back;
Board board;
JLabel name;
JLabel difficulty;
JButton easy;
JButton medium;
JButton hard;
JButton variable;
ImageIcon face;
ImageIcon mine;
ImageIcon flagImage;
ImageIcon cheakyFace;
ImageIcon unhappyFace;
JButton reset;
JLabel minelabel;
JLabel NoOfMines;
JTextField size;
JTextField mines;
JTextArea variableInfo;
int noMines = 0;
int dimensions = 0;

MouseListener rightClick;

boolean play = true;
int counter;
int flag;
int type;

public GUI(){
	
	board = new Board(0, 0, 0);
	
	rightClick = new MouseAdapter() {
		boolean rightClicked;
		boolean leftClicked;
		
		@Override
		public void mousePressed(MouseEvent e){
			if(SwingUtilities.isRightMouseButton(e)){
				rightClicked=true;
			}
			if(SwingUtilities.isLeftMouseButton(e)){
				leftClicked=true;
				if(play==true && lose == false){					
					reset.setIcon(cheakyFace);
				}
				if(e.getSource()==size){
					if(size.getText().equals("Enter dimensions of board") || size.getText().equals(" Dimensions too small!!!") || size.getText().equals(" Dimensions too large!!!") || size.getText().equals(" must be integer")){
						size.setText("");
					}
				}
				if(e.getSource()==mines){
					if(mines.getText().equals("   Enter number of mines") || mines.getText().equals(" Must have at least 1 mine!!!") || mines.getText().equals("    Too many mines !!!") || mines.getText().equals(" must be integer")){
						mines.setText("");
					}
				}
			}
			if(rightClicked){
				if(play){
					
					for(int x=0; x<buttons.length; x++){
						for(int y=0; y<buttons.length; y++){
							if(e.getSource()==buttons[x][y]){
								if(!board.getFlag(x, y) && buttons[x][y].getText().equals(" ") && !board.getClicked(x, y)){
									
									
									buttons[x][y].setIcon(flagImage);
									flag=flag-1;
									NoOfMines.setText("Number of mines: " + flag);
									board.setFlag(x, y);
									if(!board.getMine(x, y)){
										board.setClicked(x, y, true);
									}
								}
								else{
									if(board.getFlag(x, y)){
										board.setFlag(x, y);
										if(board.clicked(x, y, type).equals("0")){
											buttons[x][y].setBackground(Color.gray);
										}
										flag=flag+1;
										NoOfMines.setText("Number of mines: " + flag);
										buttons[x][y].setIcon(null);
										if(!board.getMine(x, y)){
											board.setClicked(x, y, false);
										}
									}
								}
							}
						}
						if(board.checkWin(type)==true){
							name.setText("You Win!!!");
							name.setFont(new Font("Serif", Font.PLAIN, 18));
							play=false;
						}
					}
				}
			}
			if(rightClicked && leftClicked){
				if(play){
					for(int x=0; x<buttons.length; x++){
						for(int y=0; y<buttons.length; y++){
							if(e.getSource()==buttons[x][y]){
								if(board.getClicked(x, y)){
									int clicked=Integer.parseInt(board.clicked(x , y , type));
									if(clicked==board.correctFlags(x , y , type)){
										doubleClick(x, y, type);
									}
								}
							}
						}
					}
				}
			}
		}
		
		public void mouseReleased(MouseEvent e){
			if(SwingUtilities.isRightMouseButton(e)){
				rightClicked=false;
			}
			if(SwingUtilities.isLeftMouseButton(e)){
				leftClicked=false;
				if(lose==false){
				reset.setIcon(face);
				}
			}
		}
	};
	
	game = new JFrame("MineSweeper");
	game.getContentPane().setBackground(Color.lightGray);
	game.getContentPane().setLayout(null);
	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	game.setVisible(false);
	
	start = new JFrame("Start");
	start.getContentPane().setBackground(Color.lightGray);
	start.getContentPane().setLayout(null);
	start.setSize(300, 360);
	start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	start.setVisible(true);
	
	try{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	}catch(Exception e){}
		
	name = new JLabel("name");
	name.setText("Welcome");
	name.setSize(100, 35);
	name.setLocation(20, 20);
	name.setFont(new Font("Serif", Font.PLAIN, 14));
		
	mine = new ImageIcon("C:/Users/Dom/eclipse-workspace/minesweeper/images/mine.PNG");
	minelabel = new JLabel(mine);
	
	face = new ImageIcon("C:/Users/Dom/eclipse-workspace/minesweeper/images/face.PNG");
	cheakyFace = new ImageIcon("C:/Users/Dom/eclipse-workspace/minesweeper/images/cheakyFace.PNG");
	unhappyFace= new ImageIcon("C:/Users/Dom/eclipse-workspace/minesweeper/images/unhappyFace.PNG");
	reset= new JButton(face);
	
	flagImage = new ImageIcon("C:/Users/Dom/eclipse-workspace/minesweeper/images/flag.PNG");
	
	back = new JButton("back");
	back.setText("BACK");
	back.setSize(100, 20);
	back.addActionListener(this);
	back.setActionCommand("back");
	
	reset.setBorderPainted(false);
	reset.setOpaque(false);
    reset.setForeground(Color.WHITE);
    reset.setContentAreaFilled(false);
	reset.setSize(38, 38); 
	reset.addActionListener(this);
	reset.setActionCommand("reset");
	
	NoOfMines = new JLabel("NoOfMines");
	NoOfMines.setText(" Number of mines : " + 5);
	NoOfMines.setSize(150, 35);
	
	easy = new JButton("Easy");
	easy.setSize(100, 40);
	easy.setLocation(100, 85);
	easy.addActionListener(this);
	start.getContentPane().add(easy);
	
	medium = new JButton("Medium");
	medium.setSize(100, 40);
	medium.setLocation(100, 135);
	medium.addActionListener(this);
	start.getContentPane().add(medium);
	
	hard = new JButton("Hard");
	hard.setSize(100, 40);
	hard.setLocation(100, 185);
	hard.addActionListener(this);
	start.getContentPane().add(hard);
		
	variable = new JButton("Variable");
	variable.setSize(100, 40);
	variable.setLocation(100, 280);
	variable.addActionListener(this);
	start.getContentPane().add(variable);
	
	difficulty = new JLabel("difficulty");
	difficulty.setSize(180, 40);
	difficulty.setLocation(68, 40);
	difficulty.setText("Choose your difficulty");
	difficulty.setFont(new Font("Verdana", Font.PLAIN, 16));
	start.getContentPane().add(difficulty);

	mines = new JTextField (20);
	mines.setText("   Enter number of mines");
	mines.setSize(160, 30);
	mines.setLocation(75, 225);
	mines.addMouseListener(rightClick);
	start.getContentPane().add(mines);
	
	size = new JTextField (20);
	size.setText("Enter dimensions of board");
	size.setSize(160, 30);
	size.setLocation(75, 253);
	size.addMouseListener(rightClick);
	start.getContentPane().add(size);
	
	variableInfo = new JTextArea();
	variableInfo.setText("Number of mines \n \n Width and \n height of board \n      '1-21'");
	variableInfo.setSize(80, 80);
	variableInfo.setLocation(0, 220);
	variableInfo.setFont(new Font("Serif", Font.PLAIN, 10));
	variableInfo.setBackground(Color.lightGray);
	variableInfo.setEditable(false);
	start.getContentPane().add(variableInfo);
}

public void spread(int x, int y, int d){
	
	if(!board.getSpread(x, y)){
		
		if(board.clicked(x, y, d).equals("0")){
		
			if(board.getFlag(x,y)==false){
				buttons[x][y].setBackground(Color.gray);
			}
			board.setSpread(x, y, true);
			board.setClicked(x, y, true);
			
			if(x>0 && y<board.getSizeBoard(d)-1){
				if(!board.clicked(x-1, y+1, d).equals("0")){
					buttons[x-1][y+1].setText(board.clicked(x-1, y+1, d));
				}
				board.setClicked(x-1, y+1, true);
				spread(x-1,y+1, d );
				}
			if(x>0){
				if(!board.clicked(x-1, y, d).equals("0")){
					buttons[x-1][y].setText(board.clicked(x-1, y, d));
				}
				board.setClicked(x-1, y, true);
				spread(x-1,y, d );
				}
			if(x>0 && y>0){
				if(!board.clicked(x-1, y-1, d).equals("0")){
					buttons[x-1][y-1].setText(board.clicked(x-1, y-1, d));
				}
				board.setClicked(x-1, y-1, true);
				spread(x-1,y-1, d );
				}
			if(y<board.getSizeBoard(d)-1){
				if(!board.clicked(x, y+1, d).equals("0")){
					buttons[x][y+1].setText(board.clicked(x, y+1, d));
				}
				board.setClicked(x, y+1, true);
				spread(x,y+1, d );
				}
			if(y>0){
				if(!board.clicked(x, y-1, d).equals("0")){
					buttons[x][y-1].setText(board.clicked(x, y-1, d));
				}
				board.setClicked(x, y-1, true);
				spread(x,y-1, d );
				}
			if(x<board.getSizeBoard(d)-1 && y<board.getSizeBoard(d)-1){
				if(!board.clicked(x+1, y+1, d).equals("0")){
					buttons[x+1][y+1].setText(board.clicked(x+1, y+1, d));
				}
				board.setClicked(x+1, y+1, true);
				spread(x+1,y+1, d );
				}
			if(x<board.getSizeBoard(d)-1){
				if(!board.clicked(x+1, y, d).equals("0")){
					buttons[x+1][y].setText(board.clicked(x+1, y, d));
				}
				board.setClicked(x+1, y, true);
				spread(x+1,y, d );
				}
			if(x<board.getSizeBoard(d)-1 && y>0){
				if(!board.clicked(x+1, y-1, d).equals("0")){
					buttons[x+1][y-1].setText(board.clicked(x+1, y-1, d));
				}
				board.setClicked(x+1, y-1, true);
				spread(x+1,y-1, d );
				}
	    }
	}
}

public void doubleClick(int x, int y, int d){

	if(x>0 && y<board.getSizeBoard(d)-1){
		if(!board.getMine(x-1, y+1)){
			if(!board.clicked(x-1, y+1, d).equals("0")){
			buttons[x-1][y+1].setText(board.clicked(x-1, y+1, d));
			board.setClicked(x-1, y+1, true);
			}
			else{
				spread(x-1, y+1, type);
			}
	     	}
		}
	if(x>0){
		if(!board.getMine(x-1, y)){
			if(!board.clicked(x-1, y, d).equals("0")){
			buttons[x-1][y].setText(board.clicked(x-1, y, d));
			board.setClicked(x-1, y, true);
			}
			else{
				spread(x-1, y, type);
			}
		   }
		}
	if(x>0 && y>0){
		if(!board.getMine(x-1, y-1)){
			if(!board.clicked(x-1, y-1, d).equals("0")){
				buttons[x-1][y-1].setText(board.clicked(x-1, y-1, d));
				board.setClicked(x-1, y-1, true);
			}
			else{
				spread(x-1, y-1, type);
			}
	    	}
		}
	if(y<board.getSizeBoard(d)-1){
		if(!board.getMine(x, y+1)){
			if(!board.clicked(x, y+1, d).equals("0")){
				buttons[x][y+1].setText(board.clicked(x, y+1, d));
				board.setClicked(x, y+1, true);
			}
			else{
				spread(x, y+1, type);
			}
		  }
		}
	if(y>0){
		if(!board.getMine(x, y-1)){
			if(!board.clicked(x, y-1, d).equals("0")){
			buttons[x][y-1].setText(board.clicked(x, y-1, d));
			board.setClicked(x, y-1, true);
			}
			else{
				spread(x, y-1, type);
			}
		  }
		}
	if(x<board.getSizeBoard(d)-1 && y<board.getSizeBoard(d)-1){
		if(!board.getMine(x+1, y+1)){
			if(!board.clicked(x+1, y+1, d).equals("0")){
				buttons[x+1][y+1].setText(board.clicked(x+1, y+1, d));
				board.setClicked(x+1, y+1, true);
	    	 }
			else{
				spread(x+1, y+1, type);
			}
	    	}
		}
	if(x<board.getSizeBoard(d)-1){
		if(!board.getMine(x+1, y)){
			if(!board.clicked(x+1, y, d).equals("0")){
				buttons[x+1][y].setText(board.clicked(x+1, y, d));
				board.setClicked(x+1, y, true);
			}
		  else{
			  spread(x+1, y, type);
		  }
		 }
		}
	if(x<board.getSizeBoard(d)-1 && y>0){
		if(!board.getMine(x+1, y-1)){
			if(!board.clicked(x+1, y-1, d).equals("0")){
				buttons[x+1][y-1].setText(board.clicked(x+1, y-1, d));
				board.setClicked(x+1, y-1, true);
			 }
			else{
				spread(x+1, y-1, type);
			}
		   }
	 }
}

public void actionPerformed(ActionEvent ae){
	
	if(ae.getActionCommand().equals("Easy")){
		game.setSize(300, 360);
		board = new Board(0, 0, 0);
		game.getContentPane().add(back);
		back.setLocation(180, 4);
		game.getContentPane().add(reset);
		reset.setLocation(105, 7);
		game.getContentPane().add(name);
		game.getContentPane().add(NoOfMines);
		NoOfMines.setLocation(150, 20);
		flag= board.getNoMines(0);
		NoOfMines.setText("Number of mines: " + flag);
		buttons = new JButton[board.getSizeBoard(0)][board.getSizeBoard(0)];
		for(int x=0; x<buttons.length; x++){
			for(int y= 0; y<buttons.length; y++){
				buttons[x][y]= new JButton("button");
				buttons[x][y].setActionCommand("button");
				buttons[x][y].addActionListener(this);
				buttons[x][y].setText(" ");
				buttons[x][y].setSize(45, 45);
				buttons[x][y].setLocation(16 + 40*x, 70 + 40*y);
				buttons[x][y].addMouseListener(rightClick);
				game.getContentPane().add(buttons[x][y]);
			}
		}
		type=0;
		start.setVisible(false);
		game.setVisible(true);
	}
	
	if(ae.getActionCommand().equals("Medium")){
		game.setSize(400, 480);
		board = new Board(1, 0, 0);
		game.getContentPane().add(back);
		back.setLocation(280, 4);
		game.getContentPane().add(reset);
		reset.setLocation(160, 7);
		game.getContentPane().add(name);
		game.getContentPane().add(NoOfMines);
		NoOfMines.setLocation(247, 20);
		flag= board.getNoMines(1);
		NoOfMines.setText("Number of mines: " + flag);
		buttons = new JButton[board.getSizeBoard(1)][board.getSizeBoard(1)];
		for(int x=0; x<buttons.length; x++){
			for(int y= 0; y<buttons.length; y++){
				buttons[x][y]= new JButton("button");
				buttons[x][y].setActionCommand("button");
				buttons[x][y].addActionListener(this);
				buttons[x][y].setText(" ");
				buttons[x][y].setSize(40, 40);
				buttons[x][y].setLocation(16 + 35*x, 70 + 35*y);
				buttons[x][y].addMouseListener(rightClick);
				game.getContentPane().add(buttons[x][y]);
			}
		}
		type=1;
		start.setVisible(false);
		game.setVisible(true);
	}
	
	if(ae.getActionCommand().equals("Hard")){
		game.setSize(500, 600);
		board = new Board(2, 0, 0);
		game.getContentPane().add(back);
		back.setLocation(380, 4);
		game.getContentPane().add(reset);
		reset.setLocation(225, 7);
		game.getContentPane().add(name);
		game.getContentPane().add(NoOfMines);
		NoOfMines.setLocation(345, 20);
		flag= board.getNoMines(2);
		NoOfMines.setText("Number of mines: " + flag);
		buttons = new JButton[board.getSizeBoard(2)][board.getSizeBoard(2)];
		for(int x=0; x<buttons.length; x++){
			for(int y= 0; y<buttons.length; y++){
				buttons[x][y]= new JButton("button");
				buttons[x][y].setActionCommand("button");
				buttons[x][y].addActionListener(this);
				buttons[x][y].setText(" ");
				buttons[x][y].setSize(35, 35);
				buttons[x][y].setLocation(16 + 30*x, 70 + 30*y);
				buttons[x][y].addMouseListener(rightClick);
				game.getContentPane().add(buttons[x][y]);
			}
		}
		type=2;
		start.setVisible(false);
		game.setVisible(true);
	}
	
	if(ae.getActionCommand().equals("Variable")){
		String noOfMines = mines.getText();
		String sizeOfBoard = size.getText();
		try{
			 noMines = Integer.parseInt(noOfMines);
		}catch(NumberFormatException e){
			size.setText(" must be integer");
		}
		try{
			 dimensions = Integer.parseInt(sizeOfBoard);
		}catch(NumberFormatException e){
			mines.setText(" must be integer");
		}
		if(noMines<((dimensions*dimensions)+1) && noMines>0 && dimensions<22){
			board = new Board(3, dimensions, noMines);
			game.getContentPane().add(back);
			game.getContentPane().add(reset);
			game.getContentPane().add(name);
			game.getContentPane().add(NoOfMines);
			flag = board.getNoMines(3);
			NoOfMines.setText("Number of mines: " + flag);
			buttons = new JButton[board.getSizeBoard(3)][board.getSizeBoard(3)];
			for(int x=0; x<buttons.length; x++){
				for(int y= 0; y<buttons.length; y++){
					buttons[x][y]= new JButton("button");
					buttons[x][y].setActionCommand("button");
					buttons[x][y].addActionListener(this);
					buttons[x][y].setText(" ");
					if(dimensions<=10){
						buttons[x][y].setSize(50, 50);
						buttons[x][y].setLocation(16 + 45*x, 70 + 45*y);
						expansion[3]=45;
						mineSize[3]=50;
						game.setSize(280 + (dimensions*26), 340 + (dimensions*26));
						back.setLocation(160 + dimensions*26, 4);
						reset.setLocation(105 + dimensions*13, 7);
						NoOfMines.setLocation(140 + dimensions*26, 20);
					}
					if(dimensions>10 & dimensions<20){
						buttons[x][y].setSize(40, 40);
						buttons[x][y].setLocation(16 + 35*x, 70 + 35*y);
						expansion[3]=35;
						mineSize[3]=40;
						game.setSize((dimensions*40), 78 + (dimensions*40));
						back.setLocation(-130 + dimensions*40, 4);
						reset.setLocation(-45 + dimensions*20, 7);
						NoOfMines.setLocation(-150 + dimensions*40, 20);
					}
					if(dimensions>=20){
						buttons[x][y].setSize(35, 35);
						buttons[x][y].setLocation(16 + 30*x, 70 + 30*y);
						expansion[3]=30;
						mineSize[3]=35;
						
						game.setSize((dimensions*34) - 10, 40 + (dimensions*35));
						back.setLocation(-140 + dimensions*34, 4);
						reset.setLocation(-55 + dimensions*17, 7);
						NoOfMines.setLocation(-175 + dimensions*34, 20);
					}
					
					buttons[x][y].addMouseListener(rightClick);
					game.getContentPane().add(buttons[x][y]);
				}
			}
			type=3;
			start.setVisible(false);
			game.setVisible(true);
			
		}
		if(dimensions<1){
			size.setText(" Dimensions too small!!!");
		}
		if(dimensions>21){
			size.setText(" Dimensions too large!!!");
		}
		if(noMines<1){
			mines.setText(" Must have at least 1 mine!!!");
		}
		if(noMines>(dimensions*dimensions)){
			mines.setText("    Too many mines !!!");
		}
	}
	
	if(play){
		for(int x=0; x<buttons.length; x++){
			for(int y= 0; y<buttons.length; y++){
				if (ae.getSource()==buttons[x][y]){
					if(board.clickedMine(x, y) && !board.getFlag(x, y)){
				
					
							name.setText("You lose !!!");
							lose=true;
							reset.setIcon(unhappyFace);
							name.setFont(new Font("Serif", Font.PLAIN, 18));
							buttons[x][y].setVisible(false);
							minelabel = new JLabel(mine);
							minelabel.setSize(mineSize[type], mineSize[type]);
							minelabel.setLocation(16 + expansion[type]*x, 70 + expansion[type]*y);
							game.getContentPane().add(minelabel);
							play=false;
					}else
						if(!board.getFlag(x, y)){
							
								if(!board.clicked(x, y, type).equals("0")){
									buttons[x][y].setText(board.clicked(x, y, type));
								}
						        board.setClicked(x, y, true);
			
								spread(x,y,type); 
								
								if(board.checkWin(type)==true){
									name.setText("You Win!!!");
									name.setFont(new Font("Serif", Font.PLAIN, 18));
									play=false;		
								}
					}
				}
			}
		}
	}
	
	
	if(ae.getActionCommand().equals("reset") || ae.getActionCommand().equals("back")){
			
				for(int x=0; x<buttons.length; x++){
					for(int y= 0; y<buttons.length; y++){
						buttons[x][y].setText(" ");
						buttons[x][y].setVisible(true);
						buttons[x][y].setBackground(Color.lightGray);
						board.setClicked(x, y, false);
						board.setSpread(x, y, false);
						buttons[x][y].setIcon(null);
						if(board.getFlag(x, y)){
							board.setFlag(x, y);
						}
					}
				}
				minelabel.setVisible(false);
				play=true;
				lose=false;
				reset.setIcon(face);
				name.setText("Welcome");
				name.setFont(new Font("Serif", Font.PLAIN, 14));
				flag=board.getNoMines(type);
				NoOfMines.setText("Number of mines: " + flag);
				if(type==3){
					String noOfMines = mines.getText();
					String sizeOfBoard = size.getText();
					int mines = Integer.parseInt(noOfMines);
					int size = Integer.parseInt(sizeOfBoard);
					if(mines<((size*size)+1) && mines>0 && size<41){
						board = new Board(type, size, mines);
					}
				}else{
					board = new Board(type, 0, 0);
				}
				
			if(ae.getActionCommand().equals("back")){
				mines.setText("   Enter number of mines");
				size.setText("Enter dimensions of board");
				for(int x=0; x<buttons.length; x++){
					for(int y=0; y<buttons.length; y++){
						buttons[x][y].setVisible(false);
					}
				}
				game.setVisible(false);
				start.setVisible(true);
			}	
	}	
}
}
