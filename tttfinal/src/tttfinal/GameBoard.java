package tttfinal;

/**
 *
 * @author Xam 3xPloiTeR
 */

public class GameBoard {
	private  static int boardSize = 9;
	private Box[][] board = new Box[3][3];

	public GameBoard(Box[][] board){
			this.board = board;
	}
	
	public GameBoard(){  
			board[0][0] = Box.EMPTY;
			board[0][1] = Box.EMPTY;
			board[0][2] = Box.EMPTY;
			board[1][0] = Box.EMPTY;
			board[1][1] = Box.EMPTY;
			board[1][2] = Box.EMPTY;
			board[2][0] = Box.EMPTY;
			board[2][1] = Box.EMPTY;
			board[2][2] = Box.EMPTY;
		}
        
	public int getSize(){
		return boardSize;
	}
	
        
	public boolean equals(GameBoard gb){
		boolean echeck = true;
		for(int i = 0; i< 3; i++){
			for(int j = 0; j < 3; j++){
				if(gb.getBoard()[i][j] != board[i][j]){
					echeck = false;
				}
			}
		}
		return echeck;
	}
        public void random(){
            int[] mov = new int[2];
            int flag=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                if(board[i][j]==Box.EMPTY){
                mov[0]=i;
                mov[1]=j;
                flag=1;
                break;
                }
                
                }
                if(flag==1)
                    break;
            }
            System.out.println(mov[0]+mov[0]);
            setBoardSpace(mov[0],mov[1], Box.O);
        }
        
	

	public GameBoard clone(){
		Box[][] tempBox = new Box[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[i][j] == Box.X)
				tempBox[i][j] = Box.X;
				if(board[i][j] == Box.O)
				tempBox[i][j] = Box.O;
				if(board[i][j] == Box.EMPTY)
				tempBox[i][j] = Box.EMPTY;
			}
		}
		return new GameBoard(tempBox);
	}
        
	
        
	public String toString(){
		String s = "\n";
		
		for(int i = 0; i < 3 ; i++){
			for(int j = 0; j < 3 ; j++){
				if(j%3 == 0){
					s+='\n';
				}
				if(board[i][j] == Box.EMPTY)
					s += ("|_|");
				if(board[i][j] == Box.X)
					s += ("|X|");
				if(board[i][j] == Box.O)
					s+=("|O|");
			}

		}
		return s + '\n';
		
	}
      
	public void setBoardSpace(int i, int j, Box turn){
            if(board[i][j] != Box.EMPTY && i==0 && j==0){
            return;
            }
            if(board[i][j] == Box.EMPTY){
		if(turn == Box.X){
			board[i][j] = Box.X;
		}
		if(turn == Box.O){
			board[i][j] = Box.O;
		}
            }
            
	}
	
        
	public Box getSpace(int i, int j) {
		if(board[i][j] == Box.EMPTY){
			return Box.EMPTY;
		}
		if(board[i][j] == Box.X){
			return Box.X;
		}
		if(board[i][j] == Box.O){
			return Box.O;
		}
			return null;
	}
	
        
	public Box[][] getBoard(){
		return board;
	}
	
}
