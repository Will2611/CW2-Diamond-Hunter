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
	
	public void setAxe(GridPane grid, Label reminder) {
	
		String filePath = "../DiamondHunter/bin/SettingFile/axe.txt";
		
		StatusGetters.showReminder(reminder, "Please click a position to input axe!");
		
		capturePutAxe(filePath, grid, reminder);
		
	}
	
	public void setBoat(GridPane grid, Label reminder ) {
	   
		String filePath = "../DiamondHunter/bin/SettingFile/boat.txt";
		
		StatusGetters.showReminder(reminder, "Please click a position to input boat!");
		
		capturePutBoat(filePath, grid, reminder); 
	}
	
	public void capturePutAxe(String filePath, GridPane grid, Label reminder) {

		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/16;
			int getY = (int) e.getY()/16;
		
			temp_axe[0] = getX;
			temp_axe[1] = getY;
			
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
			 
				  System.out.println("Unable to set axe at tree position.");
				  StatusGetters.checkPos(grid, getX, getY, reminder,"Unable to set axe at tree position."); 
				  reminder.setText("Unable to set axe at tree position");
			 } 
			else if (map[getY][getX]== 22) {
				
				  System.out.println("Unable to set axe at water position.");
				  StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set axe at water position."); 
				  reminder.setText("Unable to set axe at water position");
			} 
			else {   
				  System.out.println("axe");
				  StatusGetters.generateAxeOnMap(grid, getX, getY);
				  StatusGetters.writePositionToFile(filePath, getX, getY);
			}

			StatusGetters.showReminder(reminder, "Set Axe Successfully!");
			
			grid.setOnMouseClicked(null);
			
		});

	}

	public void capturePutBoat(String filePath, GridPane grid, Label reminder) {
		
		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/16;
			int getY = (int) e.getY()/16;
		
			temp_boat[0] = getX;
			temp_boat[1] = getY;
			
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
			 
				  System.out.println("Unable to set boat at tree position.");
				  StatusGetters.checkPos(grid, getX, getY, reminder,"Unable to set boat at tree position."); 
				  reminder.setText("Unable to set boat at tree position");
			 } 
			else if (map[getY][getX]== 22) {
				
				  System.out.println("Unable to set boat at water position.");
				  StatusGetters.checkPos(grid, getX, getY, reminder, "Unable to set boat at water position."); 
				  reminder.setText("Unable to set boat at water position");
			} 
			else {   
				  System.out.println("axe");
				  StatusGetters.generateBoatOnMap(grid, getX, getY);
				  StatusGetters.writePositionToFile(filePath, getX, getY);
			}

			StatusGetters.showReminder(reminder, "Set Boat Successfully!");
			
			grid.setOnMouseClicked(null);
			
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
	
	
	public boolean tileStatus(int getX, int getY) {// x is row, y is column, not the other way around
		int [] cord = {getX, getY}; //coordinated x and y
		
		if (map[getY][getX]==1||map[getY][getX]==2||map[getY][getX]==3) { //map y and x
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
