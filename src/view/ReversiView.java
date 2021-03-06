package view;

import java.util.Observable;
import java.util.Observer;

import controller.ReversiController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.ReversiModel;

/**
 * This is the view class for the Reversi program. In the program, the initial
 * board setup is done and all of the event handlers are set up. This class 
 * has methods that communicate with ReversiController in order to process a 
 * users click and determine whether or not the requested move is valid. This
 * class also contains methods that update the board after each click.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class ReversiView extends Application implements Observer {
	
	private GridPane gridPane = new GridPane();
	private StackPane stackPane;
	private StackPane pane;
	private TilePane tilePane = new TilePane();
	private ReversiModel model;
	private ReversiController controller;
	private Label label = new Label();
	private MenuBar menuBar = new MenuBar();
	private ReversiBoard board;
	private int row;
	private int col;
	private int gameCount = 0;

	/**
	 * This method is called by Reversi.java to initialize the game when the program
	 * is ran.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Link observable and observer
		ReversiModel modelObserved = new ReversiModel();
		ReversiView observer = new ReversiView();
		modelObserved.addObserver(observer);
		
		BorderPane window = new BorderPane();

		// Initialize game board and menu bar
		createGridPane();
		createMenuBar();
		
		// Add gridpane to main tilepane
		tilePane.getChildren().add(gridPane);
		tilePane.setPadding(new Insets(8, 8, 8, 8));
		tilePane.setStyle("-fx-background-color:green");
		
		// Finish setting up the main window
		window.setCenter(tilePane);		
		window.setBottom(label);
		window.setTop(menuBar);
		window.setPrefWidth(384);
		
		// Create the primary scene
		Scene scene = new Scene(window); 
	    primaryStage.setTitle("Reversi"); 
	    primaryStage.setScene(scene); 
	
	    //Display stage
	    primaryStage.show();
	}
	
	public void update(Observable obs, Object obj) {
		System.out.println("Notified");
	}
	
	/**
	 * This method creates the menu bar where the user can start a new game
	 * or determine what kind of "network settings" they would like to apply 
	 * (whether or not they want to play on a server, against an AI, etc).
	 */
	private void createMenuBar() {
		// File Menu
		Menu file = new Menu("File");
		
		// Create menu items
		MenuItem newGame = new MenuItem("New Game");
		MenuItem connectedGame = new MenuItem("Networked Game");
		
		newGame.setOnAction(e -> {
			gridPane.getChildren().clear();
			createGridPane();
		});
		
		connectedGame.setOnAction(e -> {
			NetworkSetup networkSetup = new NetworkSetup();
			networkSetup.initSetup();
		});
		
		// Add menu items to file dropdown
		file.getItems().add(newGame);
		file.getItems().add(connectedGame);
		menuBar.getMenus().add(file);
		
	}

	/**
	 * This method first creates the main gridpane that the user interacts with. This
	 * method also contains the MouseClickEventHandler for the gridpane. When the user 
	 * clicks a location on the grid, the position of the users click is converted to
	 * a row and column position where methods are called to determine if the click is 
	 * valid.
	 */
	private void createGridPane() {	
		
		// Initialize score
		label.setText("White: " + 2 + " - Black: " + 2);
		
		// Instantiate model and controller
		model = new ReversiModel();
		controller = new ReversiController(model);
		board = new ReversiBoard(model);
		
		
		// Initial grid setup
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				stackPane = new StackPane();
				stackPane.setBorder(new Border(new BorderStroke(Color.BLACK, 
			            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				stackPane.setPadding(new Insets(2, 2, 2, 2));
				Circle circle = new Circle(20);
				if(i == 3 && j == 3 || i == 4 && j == 4) {
					circle.setFill(Color.WHITE);
				}
				else if(i == 4 && j == 3 || i == 3 && j == 4) {
					circle.setFill(Color.BLACK);
				} else {
					circle.setFill(Color.TRANSPARENT);
				}
				stackPane.getChildren().add(circle);	
				gridPane.add(stackPane, i, j);
			}
		}	
		
		// Register click on grid and determine the grid location from x and 
		// y coordinates.
		gridPane.setOnMouseClicked(e -> {
			
			// Grab x-position of the user click
			if (e.getX() > 0 && e.getX() <= 47) {
				row = 0;
			} else if (e.getX() > 47 && e.getX() <= 94) {
				row = 1;
			} else if (e.getX() > 94 && e.getX() <= 141) {
				row = 2;
			} else if (e.getX() > 141 && e.getX() <= 188) {
				row = 3;
			} else if (e.getX() > 188 && e.getX() <= 235) {
				row = 4;
			} else if (e.getX() > 235 && e.getX() <= 282) {
				row = 5;
			} else if (e.getX() > 282 && e.getX() <= 329) {
				row = 6;
			} else if (e.getX() > 329 && e.getX() <= 376) {
				row = 7;
			}
			
			// Grab y-position of the user click
			if (e.getY() > 0 && e.getY() <= 45) {
				col = 0;
			} else if (e.getY() > 45 && e.getY() <= 92) {
				col = 1;
			} else if (e.getY() > 92 && e.getY() <= 139) {
				col = 2;
			} else if (e.getY() > 139 && e.getY() <= 186) {
				col = 3;
			} else if (e.getY() > 186 && e.getY() <= 233) {
				col = 4;
			} else if (e.getY() > 233 && e.getY() <= 280) {
				col = 5;
			} else if (e.getY() > 280 && e.getY() <= 327) {
				col = 6;
			} else if (e.getY() > 327 && e.getY() <= 374) {
				col = 7;
			}

			controller.humanTurn(row, col);
			drawBoard();
			
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			controller.computerTurn();
//			drawBoard();
			
			label.setText("White: " + controller.getWhiteScore() + " - Black: " + controller.getBlackScore());
			
			if (gameCount == 0) {
				System.out.println("first turn");
			} else if (controller.isGameOver()) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Message");
				alert.setHeaderText("Message");
				alert.setResizable(false);
				alert.setContentText("You won!");
				alert.showAndWait();
			}
			
			gameCount++;
		});	
	}
	
	/**
	 * This method draws the entire board based on the 2D array in the model. It iterates through each node in the gridPane
	 * and fills the circle of that StackPane with a color based on the value in the array. (0 is transparent, 1 is white, 2 is black)
	 */
	private void drawBoard() {
		// Iterate through the different stackpane nodes in gridPane and
		// if the row and column indexes match the user click, make that 
		// location our desired stackpane.
		
		ObservableList<Node> lis = gridPane.getChildren();
		
		for (Node node : lis) {
			int color = board.getColor(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
			pane = (StackPane) node;
			Circle changedCircle = (Circle) pane.getChildren().get(0);
			if(color == 1) {
				changedCircle.setFill(Color.WHITE);
			} else if (color == 2) {
				changedCircle.setFill(Color.BLACK);
			} else if (color == 0) {
				changedCircle.setFill(Color.TRANSPARENT);
			}
		}
		
	}
	

}
