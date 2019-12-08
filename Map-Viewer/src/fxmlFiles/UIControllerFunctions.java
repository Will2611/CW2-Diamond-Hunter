package fxmlFiles;

import Tiles.Tile;
import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class UIControllerFunctions {
	TileMap tileMap = new TileMap(16); //16 is the tile size
	int[][] map;
	int[][] mapStatus= new int[40][40];
	int[] temp_axe = new int[2];
	int[] temp_boat = new int[2];

	int NUM_COL = 40;
	int NUM_ROW = 40;
	StatusGetters getStatus = new StatusGetters();
	
	public void loadMap(GridPane grid, Label reminder) {
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		map = tileMap.getMap();
		for (int row = 0; row < NUM_ROW; row++) {
			for (int col = 0; col < NUM_COL; col++) {
				tileMap.generateOneTileByMap(grid, row, col);
			}

		}
		
		// tell user that load map successful & to click on the boat and axe
		reminder.setText("Successfully loaded map!\n\n Set Axe/Boat now!");

	}
	
	public void setAxe(GridPane grid, Label reminder, MouseEvent xy) {
	
		String filePath = "../DiamondHunter/bin/SettingFile/axe.txt";
		
		StatusGetters.showReminder(reminder, "Please click a position to input axe!");
		
		int getX = (int) xy.getX()/tileMap.getTileSize();
		int getY = (int) xy.getY()/tileMap.getTileSize();
		
		if (map[getX][getY]==20 || map[getX][getY]==21) {
			System.out.println("Unable to set axe at tree position.");
			StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set axe at tree position.");
		}
		else if (map[getX][getY]== 22) {
			System.out.println("Unable to set axe at water position.");
			StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set axe at water position.");
		}
		else {
		System.out.println("axe");
		capturePutAxe(getX, getY, filePath, grid, reminder);
		}
	
	}
	
	public void setBoat(GridPane grid, Label reminder, MouseEvent xy ) {
	    int getX = (int) xy.getX()/tileMap.getTileSize();
		int getY = (int) xy.getY()/tileMap.getTileSize();

		String filePath = "../DiamondHunter/bin/SettingFile/boat.txt";
		
		StatusGetters.showReminder(reminder, "Please click a position to input boat!");
		StatusGetters. getcords(getX,getY,true, grid, reminder, xy); 
		
		if (map[getX][getY]==20 || map[getX][getY]==21) {
			System.out.println("Unable to set boat at tree position.");
			StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set boat at tree position.");
		}
		else if (map[getX][getY]== 22) {
			System.out.println("Unable to set boat water position.");
			StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set boat at water position.");
		}
		else {
		System.out.println("boat");
		capturePutAxe(getX, getY, filePath, grid, reminder);
		}
		
	}
	
	public void capturePutAxe(int x, int y, String filePath, GridPane grid, Label reminder) {

		Pane pane = new Pane();
		grid.add(pane, x, y);

		pane.setOnMouseClicked(e -> {
			
			
			// keep the same position expression way with game
			StatusGetters.writePositionToFile(filePath, y, x);

			StatusGetters.generateAxeOnMap(grid, x, y);

			temp_axe[0] = y;
			temp_axe[1] = x;
			StatusGetters.showReminder(reminder, "Set Axe Successfully!");

		});

	}

	public void capturePutBoat(int x, int y, String filePath, GridPane grid, Label reminder) {

		Pane pane = new Pane();
		grid.add(pane, x, y);

		pane.setOnMouseClicked(e -> {

			StatusGetters.writePositionToFile(filePath, x, y);

			StatusGetters.generateBoatOnMap(grid, x, y);
			temp_boat[0] = x;
			temp_boat[1] = y;

			StatusGetters.showReminder(reminder, "Set Boat Successfully!");

		});

	}
	
	public void setcords(GridPane grid, Label cords, MouseEvent hover) {
		int getX = (int) hover.getX()/tileMap.getTileSize();
		int getY = (int) hover.getY()/tileMap.getTileSize();
		if (getX>=40) { //error capture for array out of index
			getX=39;
		}
		if (getY>=40) {
			getY=39;
		}
		getStatus.getcords(getX, getY, tileStatus(getX, getY), grid, cords, hover);
		
	}
	
	
	public boolean tileStatus(int getX, int getY) {// Column, row, not the other way around
		int [] cord = {getX, getY};
		
		if (map[getY][getX]==1||map[getY][getX]==2||map[getY][getX]==3) {
			if (cord != getStatus.getAxeCords() && cord != getStatus.getBoatCords() && cord != getStatus.getPlayercords()) {
				return true;
			}
		}
			return false;
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}
	
}
