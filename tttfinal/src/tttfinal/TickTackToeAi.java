package tttfinal;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.sun.glass.ui.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author Xam 3xPloiTeR
 */

public class TickTackToeAi extends Application{
	

	private GameBoardNode[] undo = new GameBoardNode[9];
	private int counter = 0;
        private int mode;
	private static Media thud = new Media(GameBoard.class.getResource("thud.wav").toString());
	private static Media flip = new Media(GameBoard.class.getResource("cardflip.wav").toString());

	private static Image x = new Image(TickTackToeAi.class.getResource("x.png").toExternalForm());
	private static Image o = new Image(TickTackToeAi.class.getResource("o.png").toExternalForm());
	private static Image blank = new Image(TickTackToeAi.class.getResource("blank.png").toExternalForm());
	private static Image undoPic = new Image(TickTackToeAi.class.getResource("undo.png").toExternalForm());

	
	private static MediaPlayer flipPlay = new MediaPlayer(flip);
	private static MediaPlayer thudPlay = new MediaPlayer(thud);
        
	
	private static Stage choseStage = new Stage();
	private static GridPane chosePane = new GridPane();
	private static Scene choseScene = new Scene(chosePane, 350, 280); 
	private static GameBoard gameBoard = new GameBoard();
	private static GameBoardNode gbn = new GameBoardNode(gameBoard, Box.X);
	private static GameTree gameTree;
	private static Button[][] buttons = new Button[3][3];
	

	
        @Override
	public void start(Stage primaryStage) throws Exception {
            
            gameTree = new GameTree(new GameBoardNode(gameBoard, Box.X));
		gameTree.buildTree(gameTree.getCursor(), Box.X);
		gameTree.getCursor().setVals();
		gameTree.getCursor().printBoardStats();
		Stage choseStage = new Stage();
		gameBoard = gbn.getBoard();
		chosePane.setHgap(5.5);
		chosePane.setVgap(5.5);
		choseStage.setTitle("Tic-Tac-Toe");
		choseStage.setScene(choseScene);
		
		choseStage.setResizable(false);
                choseStage.fullScreenProperty();
		chosePane.setHgap(5.5);
		chosePane.setVgap(5.5);
		gameTree.getCursor().setVals();
                
        Stage stage = new Stage();
                GridPane g = new GridPane();
                g.setId("firstDialog");
                g.setPadding(new Insets(20,20,20,20));
                g.setVgap(20.0D);
                g.setHgap(20.0D);
                Label label = new Label("Choose difficulty");
                Button btn1 = new Button("Easy");
                Button btn2 = new Button("Greedy");
                Button btn3 = new Button("PerfectAI");
                g.add(label, 0, 0, 1, 1);
                g.add(btn1, 0, 1, 1, 1);
                g.add(btn2, 0, 2, 1, 1);
                g.add(btn3, 0, 3, 1, 1);
                
                Scene sc = new Scene(g, 450.0D, 200.0D);
                g.setAlignment(Pos.CENTER);
                stage.setTitle("Choose turn");
                stage.setScene(sc);
                stage.show();
                stage.setOnCloseRequest((e) -> {
                   System.exit(0);
                });
      
      
                
						
		

		for(int i = 0; i < 3; i ++){
			for (int j = 0; j < 3; j++){
				buttons[i][j] = new Button();
				buttons[i][j].setPrefSize(92, 92);
				chosePane.add(buttons[i][j], j, i);
				buttons[i][j].setBackground(new Background(new BackgroundImage(blank, null, null, null, null)));
			}			
		}
		Button undo = new Button();
		chosePane.add(undo, 4, 0);
		
		
		undo.setBackground(new Background(new BackgroundImage(undoPic, null, null, null, null)));
		undo.setPrefSize(37, 37);
		undo.setOnMouseClicked(e->{
			undo();
			updateBoardImages();
		});
		buttons[0][0].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[0][0] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(0,0, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}
			
		});
		buttons[0][1].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[0][1] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(0,1, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}
		});
		buttons[0][2].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[0][2] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(0,2, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}

		});
		buttons[1][0].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[1][0] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(1,0, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}

		});
		buttons[1][1].setOnMouseClicked(e ->{
			
			if(gameTree.getCursor().getBoard().getBoard()[1][1] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(1,1, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}

		});
		buttons[1][2].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[1][2] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(1,2, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}

		});
		buttons[2][0].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[2][0] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(2,0, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
			}
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}

		});
		buttons[2][1].setOnMouseClicked(e ->{
			if(gameTree.getCursor().   getBoard().getBoard()[2][1] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(2,1, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
                        }
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}
		});
		buttons[2][2].setOnMouseClicked(e ->{
			if(gameTree.getCursor().getBoard().getBoard()[2][2] == Box.EMPTY){
			addToUndo();
			gameBoard = gameTree.getNext(2,2, Box.X,2);
			updateBoardImages();
                        if(mode==1){
                        gameBoard = gameTree.getNextran();
                        updateBoardImages();
                        }
                        else if(mode==2){
                        gameBoard = gameTree.getNextgreedy();
                        }
                        else if(mode==3){
			gameBoard = gameTree.getNext();
                        }
                        updateBoardImages();
                        }
			else{
				thudPlay.play();
				thudPlay.seek(thudPlay.getStartTime());
		}
		});
                btn1.setOnMouseClicked((event) -> {
                    
                    stage.close();
                    mode=1;
                    choseStage.show();
                 });

                 btn2.setOnMouseClicked((event) -> {
                    stage.close();
                    mode=2;
                    choseStage.show();
                 });
                 
                 btn3.setOnMouseClicked((event) -> {
                    stage.close();
                    mode=3;
                    choseStage.show();
                 });
                 
	}
        
        
        
	private void undo() {
		if(counter > 0){
		counter--;
		gameTree.setCursor(undo[counter]);
		
		updateBoardImages();}
	}

	private void addToUndo() {
		if(counter < 9){
		undo[counter] = gameTree.getCursor().clone();
		counter++;}
		
	}


	public static void main(String[] args){
		launch(args);
	}
	
	public void updateBoardImages(){
//		if(true){//gameBoard.positionCheck()){
		for(int i = 0; i < 3; i ++){
			for (int j = 0; j < 3; j++){
				if(gameBoard.getSpace(i, j) == Box.X){
				buttons[i][j].setBackground(new Background(new BackgroundImage(x, null, null, null, null)));
				}
				if(gameBoard.getSpace(i, j) == Box.O){
				buttons[i][j].setBackground(new Background(new BackgroundImage(o, null, null, null, null)));	
				}
				if(gameBoard.getSpace(i, j) == Box.EMPTY){
				buttons[i][j].setBackground(new Background(new BackgroundImage(blank, null, null, null, null)));	
				}

			flipPlay.play();
			flipPlay.seek(flipPlay.getStartTime());
			}
                        
		}
		
		//}
		

}
}
