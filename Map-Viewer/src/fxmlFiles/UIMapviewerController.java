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
	public Label reminder,cords,cordsAxe,cordsBoat; //cordsboat & cordsaxe
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
		functionHolder.setAxe(grid, reminder, cordsAxe); //edited line
	}
	
	public void PlaceBoat() {
		functionHolder.setBoat(grid, reminder, cordsBoat); //edited line
	}
	
	public void Reset() {
		System.out.println("Reset Game");
	}
	
	public void Play() {
		System.out.println("Play Game");
	}

    /*public void setMainApp(MainViewer mainapp) {
    	
    }*/
	
	public void closeApp() {
		Stage stage = (Stage) grid.getScene().getWindow();
		stage.close();
	}
	
	public void aboutInfo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("About the software");
		alert.setContentText("University of Nottingham \n\nVersion 1.0\n");
		alert.showAndWait();
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
