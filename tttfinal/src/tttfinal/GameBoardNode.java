package tttfinal;


/**
 *
 * @author Xam 3xPloiTeR
 */
public class GameBoardNode {
	private GameBoard board;
	private boolean isEnd;
	private Box currentTurn;
	private Box winner;
	private double winProb;
	private double loseProb;
	private double drawProb;
	private GameBoardNode[] Treenodes;
	
	private double totalLeaves = 0; 
	private double numWins = 0;
	private double numLoses = 0;
	private double numDraws = 0;
	static int[][] moveScores = new int[3][3];

	
	public GameBoardNode(){
        this.Treenodes = new GameBoardNode[9];
		board = new GameBoard();
		
	}
	
        
	public GameBoardNode(GameBoard board, Box currentTurn){
        this.Treenodes = new GameBoardNode[9];
		this.setBoard(board);
		this.currentTurn = currentTurn;
	}
	
	public void setVals(){
		totalLeaves =setTotalLeaves();
		numWins = numWins();
		numLoses = numLoses();
		numDraws = numDraws();
		
		loseProb = Math.round( numLoses/totalLeaves * 100.0 ) / 100.0;
		winProb = Math.round( numWins/totalLeaves * 100.0 ) / 100.0;
		drawProb = Math.round( numDraws/totalLeaves * 100.0 ) / 100.0;
	}
	
	public Box getCurrentTurn(){
		return currentTurn;
	}
	
	public double getWinProb(){
		return winProb;
	}
	
	public double getLoseProb(){
		return loseProb;
	}
	
	public double getDrawProb(){
		return drawProb;
	}
	
	public int checkWin(){

			if(board.getSpace(0, 0) == board.getSpace(0, 1) && board.getSpace(0, 1) == board.getSpace(0, 2) && board.getSpace(0, 0) != Box.EMPTY){ //first Horizontal
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
                        else if(board.getSpace(1, 0) == board.getSpace(1, 1) && board.getSpace(1, 1) == board.getSpace(1, 2) && board.getSpace(1, 0) != Box.EMPTY){//second Horizontal
				if(currentTurn == Box.O){
					return 0;
				}	
				else{
					return 1;
				}
			}
			else if(board.getSpace(2, 0) == board.getSpace(2, 1) && board.getSpace(2, 1) == board.getSpace(2, 2)&& board.getSpace(2, 0) != Box.EMPTY){//third Horizontal
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
			else if(board.getSpace(0, 0) == board.getSpace(1, 0) && board.getSpace(2, 0) == board.getSpace(1, 0) && board.getSpace(0, 0) != Box.EMPTY){//first vert
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
			else if(board.getSpace(0, 1) == board.getSpace(1, 1) && board.getSpace(2, 1) == board.getSpace(1, 1) && board.getSpace(0, 1) != Box.EMPTY){//second vert
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
			else if(board.getSpace(0, 2) == board.getSpace(1, 2) && board.getSpace(2, 2) == board.getSpace(1, 2) && board.getSpace(0, 2) != Box.EMPTY){//third vert
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
			else if(board.getSpace(2, 0) == board.getSpace(1, 1) && board.getSpace(2, 2) == board.getSpace(1, 1)&& board.getSpace(1, 1) != Box.EMPTY){//cross 1
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					return 1;
				}
			}
			else if(board.getSpace(0, 2) == board.getSpace(1, 1) && board.getSpace(2, 0) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){//cross 2
				if(currentTurn == Box.O){
					return 0;
				}
				else{
					
					return 1;
				}
			}
			return -1;
		}

	
	public boolean[][] checkSpaces(){
		boolean possibleMoves[][] = new boolean[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
                            possibleMoves[i][j] = getBoard().getSpace(i, j) == Box.EMPTY;
			}
		}
		//if(possibleMoves != null)
		return possibleMoves;
		//return new boolean[3][3];//possible bug
	}
	
	public void setConfig(GameBoardNode childNode, int counter){
            //System.out.println(counter);
		Treenodes[counter] = childNode;
	}
	
	public GameBoardNode[] getConfig(){
		return Treenodes;
	}
	
	public double getTotalLeaves(){
		return totalLeaves;
	}
	
	public double getNumWins(){
		return numWins;
	}
	
	public double getNumLoses(){
		return numLoses;
	}
	
	public double getNumDraws(){
		return numDraws;
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	public void printBoardStats(){
		setVals();
		System.out.println("__________________");
		System.out.println("Lose Probability: " + loseProb);
		System.out.println("Win Probability: " + winProb);
		System.out.println("Draw Probability: " + drawProb);

		System.out.println("Total Leaves: " + totalLeaves);
		System.out.println("Total Wins: " + numWins);
		System.out.println("Total Loses: " + numLoses);
		System.out.println("Total Draws: " + numDraws);
		
		System.out.println("__________________");

	}
	
	@Override
	public GameBoardNode clone(){
		return new GameBoardNode(board.clone(), currentTurn);
	}
	
	public double numLoses(){
		if(Treenodes[0] == null &&  checkWin() == 0){
			return 1;
		}
		for(int i =0; i < 9; i++){
			if(Treenodes[i] != null){
				numLoses += Treenodes[i].numLoses();
			}
		}
		return numLoses;
	}
	
	public double numWins(){
		if(Treenodes[0] == null &&  checkWin() == 1){
			return 1;
		}
		for(int i =0; i < 9; i++){
			if(Treenodes[i] != null){
				numWins += Treenodes[i].numWins();}
			
		}
		return numWins;
        }
	
	
	public double numDraws(){
		boolean draw = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(checkSpaces()[i][j] == true){
					draw = false;
				}}}
		if(  checkWin() == -1 && draw){
			return 1;
		}
		
		for(int i =0; i < 9; i++){
			if(Treenodes[i] != null){
				numDraws += Treenodes[i].numDraws();
			}
		}
		return numDraws;
		}
        
	public double setTotalLeaves(){	
	if(Treenodes[0] == null){
			return 1;
	}
        
	for(int i =0; i < 9; i++){
		if(Treenodes[i] != null){
			totalLeaves += Treenodes[i].setTotalLeaves();
		}
	}
	return totalLeaves;
	}
        
	public void setMinMax(Box turn) { 
		boolean[][] possibleMoves = checkSpaces();
		boolean draw = true;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				moveScores[i][j] = -1;
				if(checkSpaces()[i][j]){
					moveScores[i][j] = 1;
				}}}
		if(possibleMoves[1][1]){
			moveScores[1][1] = 100;
			}
		if(board.getSpace(0, 0) == board.getSpace(0, 1) && board.getSpace(0, 1) != Box.EMPTY){
			moveScores[0][2] = 100; 
			}
			if(board.getSpace(0, 0) == board.getSpace(0, 2) && board.getSpace(0, 2) != Box.EMPTY) {
			moveScores[0][1] = 100; 
			}
			if(board.getSpace(0, 2) == board.getSpace(0, 1) && board.getSpace(0, 1) != Box.EMPTY){

			moveScores[0][0] = 100;
			}
			if(board.getSpace(1, 2) == board.getSpace(1, 1) && board.getSpace(1, 1)!= Box.EMPTY){

			moveScores[1][0] = 100;
			}
			if(board.getSpace(1, 0) == board.getSpace(1, 2) && board.getSpace(1, 2 ) != Box.EMPTY){

			moveScores[1][1] = 100;
			}
			if(board.getSpace(1, 0) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){ 
			moveScores[1][2] = 100;
			}

			if(board.getSpace(2, 0) == board.getSpace(2, 1) && board.getSpace(2, 1) != Box.EMPTY){

			moveScores[2][2] = 100;
			}
			if(board.getSpace(2, 2) == board.getSpace(2, 1) && board.getSpace(2, 1) != Box.EMPTY){
			moveScores[2][0] = 100;
			}
			if(board.getSpace(2, 0) == board.getSpace(2, 2) && board.getSpace(2, 2) != Box.EMPTY){

			moveScores[2][1] = 100;
			}
			if(board.getSpace(0, 0) == board.getSpace(1, 0) && board.getSpace(1, 0) != Box.EMPTY){

			moveScores[2][0] = 100;
			}
			if(board.getSpace(2, 0) == board.getSpace(1, 0) && board.getSpace(1, 0) != Box.EMPTY){
			moveScores[0][0] = 100; 
			}
			if(board.getSpace(0, 0) == board.getSpace(2, 0) && board.getSpace(2, 0) != Box.EMPTY){

			moveScores[1][0] = 100;
			}

			if(board.getSpace(1, 1) == board.getSpace(0, 1) && board.getSpace(1, 0) != Box.EMPTY){

			moveScores[2][1] = 100;
			}
			if(board.getSpace(0, 1) == board.getSpace(2, 1) && board.getSpace(2, 1) != Box.EMPTY){

			moveScores[1][1] = 100;
			}
			if(board.getSpace(2, 1) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){

			moveScores[0][1] = 100;
			}

			if(board.getSpace(0, 2) == board.getSpace(1, 2) && board.getSpace(1, 2) != Box.EMPTY){

			moveScores[2][2] = 100;
			}
			if(board.getSpace(2, 2) == board.getSpace(1, 2) && board.getSpace(1, 2) != Box.EMPTY){

			moveScores[0][2] = 100;
			}
			if(board.getSpace(0, 2) == board.getSpace(2, 2) && board.getSpace(2, 2) != Box.EMPTY){

			moveScores[1][2] = 100;
			}
			if(board.getSpace(2, 0) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){

			moveScores[2][2] = 100;
			}
			if(board.getSpace(2, 2) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){

			moveScores[2][0] = 100;
			}
			if(board.getSpace(2, 0) == board.getSpace(2, 2) && board.getSpace(2, 2) != Box.EMPTY){//

			moveScores[1][1] = 100;
			}

			if(board.getSpace(0, 2) == board.getSpace(1, 1) && board.getSpace(1, 1) != Box.EMPTY){

			moveScores[2][0] = 100;
			}
			if(board.getSpace(0, 2) == board.getSpace(2, 0) && board.getSpace(2, 0) != Box.EMPTY){

			moveScores[1][1] = 100;
			}
			
			if(board.getSpace(0, 1) == Box.EMPTY && moveScores[0][1] < 100){
				moveScores[0][1] = 2;
			}
			if(board.getSpace(1, 0) == Box.EMPTY && moveScores[1][0] < 100){
				moveScores[1][0] = 2;
			}
			if(board.getSpace(2, 1) == Box.EMPTY && moveScores[2][1] < 100){
				moveScores[2][1] = 2;
			}
			if(board.getSpace(1, 2) == Box.EMPTY && moveScores[1][2] < 100){
				moveScores[1][2] = 2;
			}
			if(board.getSpace(0, 0) == Box.EMPTY && moveScores[0][0] < 100){
				moveScores[0][0] = 3;
			}	
			if(board.getSpace(2, 2) == Box.EMPTY && moveScores[2][2] < 100){
				moveScores[2][2] = 3;
			}
			if(board.getSpace(2, 0) == Box.EMPTY && moveScores[2][0] < 100){
				moveScores[2][0] = 3;
			}
			if(board.getSpace(0, 2) == Box.EMPTY && moveScores[0][2] < 100){
				moveScores[0][2] = 3;
			}
			if(board.getSpace(0, 1) == board.getSpace(1, 1) && board.getSpace(0, 1) == Box.X  && board.getSpace(2, 1) == Box.EMPTY){
				moveScores[2][1] = 1000;

			}
			if(board.getSpace(2, 0) == board.getSpace(1, 1) && board.getSpace(2, 0) == Box.X  && board.getSpace(2, 2) == Box.EMPTY){
				moveScores[0][2] = 1000;

			}
			if(board.getSpace(0, 2) == board.getSpace(1, 1) && board.getSpace(0, 2) == Box.EMPTY  && board.getSpace(1, 1) == Box.X){
				moveScores[1][1] = 1000;

			}
			if(board.getSpace(0, 0) == board.getSpace(2, 2) && board.getSpace(2, 2) == Box.X  && board.getSpace(1, 1) == Box.EMPTY){
				moveScores[1][1] = 1000;

			}
				//special
			if(board.getSpace(0, 2) == board.getSpace(2, 0) && board.getSpace(0, 2) == Box.X  && board.getSpace(1, 1) == Box.O){
				if(board.getSpace(1,2) == Box.EMPTY)
					moveScores[1][2] = 10000;
			}
			/*if(board.getSpace(0, 0) == board.getSpace(2, 1) ){
				if(board.getSpace(2,2) == Box.EMPTY)
					moveScores[2][2] = 100000;
			}*/
			if(board.getSpace(0, 0) == board.getSpace(2, 2) && board.getSpace(2, 2) == Box.X  && board.getSpace(1, 1) == Box.O){
				if(board.getSpace(1,2) == Box.EMPTY)
					moveScores[1][2] = 10000;
			}
		
	}
	
	public int[][] getMovesArray() {
		return moveScores;
	}
	
}