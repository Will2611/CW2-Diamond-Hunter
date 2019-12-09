package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Tiles.TileMap;
import fxmlFiles.UIControllerFunctions;


public class UIMapviewerController implements UIMVControllerInterface {

	UIControllerFunctions holder = new UIControllerFunctions();
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
	public Label reminder,cords;
	public StatusGetters status = new StatusGetters();
	
	
	public void initialize() {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
		status.setCordsText(cords,false, 0, 0);
	}
	
	public void LoadMap() {
		if (hasLoaded == false) {
		hasLoaded = true;
		holder.loadMap(grid,reminder);
		}

	}
	
	public void hoverCursor(MouseEvent event) {
		cursor = event;
		holder.setcords(grid, cords, cursor);	
	}
	
	public void PlaceAxe() {
		holder.setAxe(grid, reminder);
	}
	
	public void PlaceBoat() {
		holder.setBoat(grid, reminder);
		System.out.println("Boat");
	}
	
	public void Reset() {
		System.out.println("Reset Game");
	}
	
	public void Play() {
		System.out.println("Play Game");
	}

    /*public void setMainApp(MainViewer mainapp) {
    	
    }*/
	
	public void Close() {
		Stage stage = (Stage) grid.getScene().getWindow();
		stage.close();
	}

}
