package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.event.EventHandler;
import javafx.event.ActionEvent; 

import Tiles.TileMap;
import fxmlFiles.UIControllerFunctions;


public class UIMapviewerController implements UIMVControllerInterface {
	
	StatusGetters getStatus = new StatusGetters();
	UIControllerFunctions functionHolder = new UIControllerFunctions();
	int NUM_COL = 40;
	int NUM_ROW = 40;
	
	//make true map loaded once
	boolean hasLoaded = false;
	TileMap tileMap = new TileMap(16); //16 is the tile size
	MouseEvent cursor;
	int[][] map;
	
	@FXML
	public GridPane grid;
	
	@FXML 
	public Label reminder,cords,cordsAxe,cordsBoat;
	public StatusGetters status = new StatusGetters();

	
	
	public void initialize() {
		hasLoaded = true;
		functionHolder.loadMap(grid,reminder);
		/*int [] AxeCords = new int [2];
		int [] BoatCords = new int [2];
		readPositionFromFile("../DiamondHunter/bin/SettingFile/axe.txt", AxeCords);// to call upon most recently saved cords
		readPositionFromFile("../DiamondHunter/bin/SettingFile/boat.txt", BoatCords);
		getStatus.setCords(AxeCords, BoatCords);
		*/
		getStatus.setCordsText(cords,false, 0, 0);
		//getStatus.generateAxeOnMap(grid, getStatus.getAxeCords()[0], getStatus.getAxeCords()[1]);
		//getStatus.generateBoatOnMap(grid, getStatus.getBoatCords()[0], getStatus.getBoatCords()[1]);
	}
	
	public void LoadMap() {
		if (hasLoaded == false) {
		hasLoaded = true;
		functionHolder.loadMap(grid,reminder);
		}

	}
	
	public void hoverCursor(MouseEvent event) {
		cursor = event;
		functionHolder.setcords(grid, cords, cursor);	
	}
	
	public void PlaceAxe() {
		functionHolder.setAxe(grid, reminder, cordsAxe); 
	}
	
	public void PlaceBoat() {
		functionHolder.setBoat(grid, reminder, cordsBoat); 
	}
	
	public void Reset() {
		System.out.println("Reset Game");
	}
	
	public void Play() {
		System.out.println("Play Game");
	}

    /*public void setMainApp(MainViewer mainapp) {
    	
    }*/
	
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
				+ "- Enter: start the game"
				+ "- Space: to clear dead trees"
				+ "- Esc: to pause and unpause "
				+ "- F1: to return to main menu when paused");
		alert.showAndWait();
		alert.setOnCloseRequest(event -> {alert.close();});
	}
	
	public void closeApp() {
		Stage stage = (Stage) grid.getScene().getWindow();
		stage.close();
	}
	
	void readPositionFromFile(String filePath, int[] pos) {

		try {
			InputStream in = getClass().getResourceAsStream(filePath);
			System.out.println("exist");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			System.out.println("existing");//debuging, finding error
			pos[0] = Integer.parseInt(br.readLine());
			pos[1] = Integer.parseInt(br.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
