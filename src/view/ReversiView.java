package view;



import controller.ReversiController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

public class ReversiView extends Application{
	
	private GridPane gridPane = new GridPane();
	private StackPane stackPane;
	private Circle circle;
	private TilePane tilePane = new TilePane();
	private ReversiModel model = new ReversiModel();
	private ReversiController controller = new ReversiController(model);
	private Label label = new Label();
	private MenuBar menuBar = new MenuBar();
	

	@Override
	public void start(Stage primaryStage) {
		BorderPane window = new BorderPane();
		createGridPane();
		createLabel();
		createMenuBar();
		tilePane.getChildren().add(gridPane);
		tilePane.setPadding(new Insets(8, 8, 8, 8));
		tilePane.setStyle("-fx-background-color:green");
		window.setCenter(tilePane);		
		window.setBottom(label);
		window.setTop(menuBar);
		window.setPrefWidth(384);
		Scene scene = new Scene(window); 
	    primaryStage.setTitle("Reversi"); 
	    primaryStage.setScene(scene); 
	    primaryStage.show();
		
	}
	
	private void createMenuBar() {
		Menu file = new Menu("File");
		MenuItem newGame = new MenuItem("New Game");
		MenuItem connectedGame = new MenuItem("Connected Game");
		file.getItems().add(newGame);
		file.getItems().add(connectedGame);
		menuBar.getMenus().add(file);
		
	}

	private void createLabel() {
		label.setText("White: " + controller.getWhiteScore() + " - Black: " + controller.getBlackScore());
	}

	public void createGridPane() {
		
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				stackPane = new StackPane();
				stackPane.setBorder(new Border(new BorderStroke(Color.BLACK, 
			            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				stackPane.setPadding(new Insets(2, 2, 2, 2));
				circle = new Circle(20);
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
	}

}
