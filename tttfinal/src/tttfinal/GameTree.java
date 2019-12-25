package tttfinal;

/**
 *
 * @author Xam 3xPloiTeR
 */

public class GameTree {

        private static GameBoardNode root;
	static GameBoardNode cursor;
	private int numLeaves;
	private static int globalCounter;
        
	public GameTree(){
		root = null;
		root = cursor;
		
	}
	
	public GameTree(GameBoardNode root){
		this.root = root;
		cursor = root;
	}
	
        
        
	public void setCursor(GameBoardNode newCursor){
		cursor = newCursor;
	}
	
        public void makeMove(){
		
	}
	
	public static GameBoardNode buildTree(GameBoardNode rootNode, Box turn){
		int counter = 0;
		boolean[][] possibleMoves = rootNode.checkSpaces();
		GameBoard tempBoard = new GameBoard();
		boolean draw = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(rootNode.checkSpaces()[i][j] == true){
					draw = false;
				}}}
                int x=rootNode.checkWin();
                if(x == 0 || x == 1 || (x == -1 && draw == true)){
			return cursor;
		}
//		if(root.checkWin() == 0 || root.checkWin() == 1 || (root.checkWin() == -1 && draw == true)){
//			return cursor;
//		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				tempBoard = new GameBoard();
				if(possibleMoves[i][j]){
					for(int n = 0; n < 3; n++){
						for(int m = 0; m < 3; m++){
						tempBoard.getBoard()[n][m] = rootNode.getBoard().getBoard()[n][m];
						if(n == i && m == j){
							tempBoard.getBoard()[n][m] = turn;
						}
						}
                                        }
					GameBoardNode tempGBN = new GameBoardNode(tempBoard, turn);
					tempGBN.setMinMax(turn);
					rootNode.setConfig(new GameBoardNode(tempBoard, turn), counter);
					counter++;
					}
			}
				}

			for(int i = 0; i < counter; i++){
				if(turn == Box.O){
				buildHelper(rootNode.getConfig()[i], Box.X);
				}
				if(turn == Box.X){
				buildHelper(rootNode.getConfig()[i], Box.O);
				}
			}
		return cursor;
	}
	
	public static void buildHelper(GameBoardNode rootNode, Box turn){
		boolean draw = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(rootNode.checkSpaces()[i][j] == true){
					draw = false;
				}}}
		if(rootNode.checkWin() == 0 || rootNode.checkWin() == 1 || rootNode.checkWin() == -1 && draw == true){
			return;
		}
		buildTree(rootNode, turn);
	}
	
	public GameBoardNode getCursor(){
		return cursor;
	}
	
	public Box checkWin(GameBoardNode node){
		if(node.checkWin() == 0)
			return Box.O;
		if(node.checkWin() == 1)
			return Box.X;
		return Box.EMPTY;
	}
	
	public double cursorProbability(){
		return cursor.getWinProb();	
	}
	
	public GameBoard getNext(int i, int j, Box turn,int mode) {
		//cursor.setVals();
		if(cursor.checkWin() == 1 || cursor.checkWin() == 0){
			return cursor.getBoard();}
			cursor.getBoard().getBoard()[i][j] = turn;
			for(int m = 0; m < 9; m++){
				if(cursor.getConfig()[m]!= null){
				if(cursor.getBoard().equals(cursor.getConfig()[m].getBoard())){
					cursor = cursor.getConfig()[m];
				}
				
				}}
				cursor.setMinMax(turn);

				cursor.setVals();
				cursor.getMovesArray()[i][j] = -1;
				getCursor().printBoardStats();

			return cursor.getBoard();
	}
	
	public GameBoard getNext(){
		cursor.setMinMax(Box.X);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(!cursor.checkSpaces()[i][j])
				cursor.getMovesArray()[i][j] = -100;
				}}
				
		boolean finished = true;		
		int temp = -2; 
		int iPos = 0;
		int jPos = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(cursor.getMovesArray()[i][j]!= -1)
					finished = false;
					
				if(cursor.moveScores[i][j] > temp){
					iPos = i;
					jPos = j;
					temp = cursor.getMovesArray()[i][j];
				}
			}
		}
		

		cursor.getMovesArray()[iPos][jPos] = -1;
		if(!finished)
		return getNext(iPos, jPos, Box.O,2);
		
		return cursor.getBoard();
		
	}
        
        public GameBoard getNextran() {
            int x = 0,y = 0;
		if(cursor.checkWin() == 1 || cursor.checkWin() == 0){
			return cursor.getBoard();}
                int flag=0;
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                if(cursor.checkSpaces()[i][j]==true){
                                flag=1;
                                x=i;
                                y=j;
                                cursor.getBoard().getBoard()[i][j] = Box.O;
                                break;
                                    }
                                }
                                if(flag==1){
                                    break;
                                }
                            }
			for(int m = 0; m < 9; m++){
				if(cursor.getConfig()[m]!= null){
				if(cursor.getBoard().equals(cursor.getConfig()[m].getBoard())){
					cursor = cursor.getConfig()[m];
				}
				
				}
                        }
				cursor.setVals();
				cursor.getMovesArray()[x][y] = -1;
				getCursor().printBoardStats();

			return cursor.getBoard();
	}
        public GameBoard getNextgreedy(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(!cursor.checkSpaces()[i][j])
				cursor.getMovesArray()[i][j] = -100;
				}}
				
		boolean finished = true;		
		int temp = -2; 
		int iPos = 0;
		int jPos = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(cursor.getMovesArray()[i][j]!= -1)
					finished = false;
					
				if(cursor.moveScores[i][j] > temp){
					iPos = i;
					jPos = j;
					temp = cursor.getMovesArray()[i][j];
				}
			}
		}
		

		cursor.getMovesArray()[iPos][jPos] = -1;
		if(!finished)
		return getNext(iPos, jPos, Box.O,2);
		
		return cursor.getBoard();
		
	}
}