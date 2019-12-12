package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import java.io.IOException;

import DiamondHunter.Main.MainGame;

//import com.neet.DiamondHunter.Main.Game;

import TilesFX.TileMapFX;
import fxmlFiles.UIControllerFunctions;


public class UIMapviewerController implements UIMVControllerInterface {
	
	StatusGetters getStatus = new StatusGetters();
	UIControllerFunctions functionHolder = new UIControllerFunctions(getStatus);
	int NUM_COL = 40;
	int NUM_ROW = 40;
	
	//make true map loaded once
	boolean hasLoaded = false;
	TileMapFX tileMap = new TileMapFX(16); //16 is the tile size
	MouseEvent cursor;
	int[][] map;
	
	@FXML
	public GridPane grid;
	
	@FXML 
	public Label reminder,cords,cordsAxe,cordsBoat;

	
	
	public void initialize() {
		hasLoaded = true;
		functionHolder.loadMap(grid,reminder);
		getStatus.setCurrCordsText(cords,false, 0, 0);
		getStatus.setLastSavedCords(grid);
		int [] AxeCords = getStatus.getAxeCords().clone();
		int [] BoatCords = getStatus.getBoatCords().clone();
		getStatus.getAxeCords(AxeCords[0], AxeCords[1], cordsAxe);
		getStatus.getBoatCords(BoatCords[0], BoatCords[1], cordsBoat);
	}
	
	public void LoadMap() {
		if (hasLoaded == false) {
		hasLoaded = true;
		functionHolder.loadMap(grid,reminder);
		}

	}
	
	public void hoverCursor(MouseEvent event) {
		cursor = event;
		functionHolder.setHoverCords(grid, cords, cursor);	
	}
	
	public void PlaceAxe() {
		functionHolder.setAxe(grid, reminder, cordsAxe); 
	}
	
	public void PlaceBoat() {
		functionHolder.setBoat(grid, reminder, cordsBoat); 
	}
	
	public void Reset() {
		functionHolder.resetHandler(grid, cordsAxe, cordsBoat);
	}
	
	public void Play() throws IOException {
		this.howToPlay();
		this.closeApp();
		MainGame.main(null);
	}
	
	public void aboutInfo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("About the software & Diamond Hunter");
		alert.setContentText("Diamond Hunter - Version 1.0\n\nDiamond Hunter"
				+ "is a 2D role playing game which the user needs to "
				+ "control the hero to collect all of the 15 diamonds "
				+ "on the map in order to win game. To achieve the goal, "
				+ "the player needs to find axe and boat so that it can "
				+ "open the blocked path. In addition, the map viewer "
				+ "application serve as a purpose for user to overview "
				+ "the whole map and place the axe and boat.");
		alert.showAndWait();
		alert.setOnCloseRequest(event -> {alert.close();});
	}
	
	public void howToPlay() {
		Alert alert = new Alert(AlertType.INFORMATION); //incomplete
		alert.setTitle("Instruction");
		alert.setHeaderText(null);
		alert.setContentText("- Up arrow: move forward\n"
				+ "- Down arrow: move backwards\n"
				+ "- Left arrow: turn left\n"
				+ "- Right Arrow: turn right\n"
				+ "- Enter: start the game\n"
				+ "- Space: to clear dead trees\n"
				+ "- Esc: to pause and unpause \n"
				+ "- F1: to return to main menu when paused\n");
		alert.showAndWait();
		alert.setOnCloseRequest(event -> {alert.close();});
	}
	
	public void closeApp() {
		Stage stage = (Stage) grid.getScene().getWindow();
		stage.close();
	}
	
	
}
