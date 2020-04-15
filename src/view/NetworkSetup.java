package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NetworkSetup extends Stage {
	
	private Stage dialog = new Stage();
	private VBox vbox = new VBox();
	private HBox createHbox = new HBox();
	private HBox playAsHbox = new HBox();
	private HBox serverPortHbox = new HBox();
	private HBox buttonHbox = new HBox();
	private ToggleGroup group1 = new ToggleGroup();
	private RadioButton createButton1 = new RadioButton("Server");
	private RadioButton createButton2 = new RadioButton("Client");
	private ToggleGroup group2 = new ToggleGroup();
	private RadioButton playAsButton1 = new RadioButton("Human");
	private RadioButton playAsButton2 = new RadioButton("Computer");
	private Button okButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");
	private TextField server = new TextField();
	private TextField port = new TextField();
	private String createSelection;
	private String playAsSelection;
	
	/**
	 * This method sets up the GUI for the network settings dialog box.
	 */
	public void initSetup() {
		dialog.setTitle("Network Setup");
		dialog.setMinHeight(200);
		dialog.setMinWidth(300);
		
		// Create hbox
		Label createLabel = new Label("Create: ");
		createButton1.setToggleGroup(group1);
		createButton1.setSelected(true);
		createButton2.setToggleGroup(group1);
		group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old, Toggle new_toggle) {
				if (group1.getSelectedToggle() != null) {
					RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();
					createSelection = check.getText();
				}
			}
		});
		createHbox.getChildren().addAll(createLabel, createButton1, createButton2);
		createHbox.setSpacing(15);
		
		// Play as hbox
		Label playAsLabel = new Label("Play as: ");
		playAsButton1.setToggleGroup(group2);
		playAsButton1.setSelected(true);
		playAsButton2.setToggleGroup(group2);
		group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old, Toggle new_toggle) {
				if (group1.getSelectedToggle() != null) {
					RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();
					playAsSelection = check.getText();
				}
			}
		});
		playAsHbox.getChildren().addAll(playAsLabel, playAsButton1, playAsButton2);
		playAsHbox.setSpacing(15);
		
		//Server hbox
		server.setText("localhost");
		port.setText("4000");
		Label serverLabel = new Label("Server ");
		Label portLabel = new Label("Port ");
		serverPortHbox.getChildren().addAll(serverLabel, server, portLabel, port);
		serverPortHbox.setAlignment(Pos.CENTER);
		serverPortHbox.setSpacing(7);
		
		// Buttons
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clickedOk();
			}
		});
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clickedCancel();
			}
		});
		buttonHbox.getChildren().addAll(okButton, cancelButton);
		buttonHbox.setSpacing(7);
		
		// Set up vbox
		vbox.getChildren().addAll(createHbox, playAsHbox, serverPortHbox, buttonHbox);
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(15);
		
		Scene dialogScene = new Scene(vbox);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setScene(dialogScene);
		dialog.showAndWait();
	}
	
	/**
	 * When the OK button is clicked to confirm the network settings, 
	 * this method is called.
	 */
	private void clickedOk() {
		String serverName = server.getText();
		String portId = port.getText();
	}
	
	/**
	 * This method simply closes the dialog box if the user presses "cancel".
	 */
	private void clickedCancel() {
		dialog.close();
	}
	
}
