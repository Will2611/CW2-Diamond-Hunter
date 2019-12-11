package fxmlFiles;

import Tiles.Content;

import Tiles.TileMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;


public class UIControllerFunctions {
	TileMap tileMap = new TileMap(16); //16 is the tile size
	int[][] map;
	int[][] mapStatus= new int[40][40];
	int NUM_COL = 40;
	int NUM_ROW = 40;
	
	StatusGetters getStatus;
	public UIControllerFunctions(StatusGetters getStatus) {//prevent multiple instance of the same objects being used for the same purpose
		this.getStatus=getStatus;
	}
	
	public void loadMap(GridPane grid, Label reminder) {
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		map = tileMap.getMap();
		for (int row = 0; row < NUM_ROW; row++) {
			for (int col = 0; col < NUM_COL; col++) {
				tileMap.generateOneTileByMap(grid, row, col);
				if (row==17&&col==17) {
					HBox imageField = new HBox();
					imageField.setAlignment(Pos.CENTER);
					grid.add(imageField, row, col);

					BufferedImage PlayerBuf = Content.PLAYER[0][0];
					Image PlayerImage = SwingFXUtils.toFXImage(PlayerBuf, null);
					imageField.getChildren().add(new ImageView(PlayerImage));	

				}
			}

		}
		
		// tell user that load map successful & to click on the boat and axe
		reminder.setText("Successfully loaded map!\n\n Set Axe/Boat now!");

	}
	
	public void setAxe(GridPane grid, Label reminder, Label cordsAxe) {
	
		String filePath = "/DiamondHunter/SettingFile/axe.txt"; //to be changed later
		
		StatusGetters.showReminder(reminder, "Please click a position \n to input axe!");
		
		capturePutAxe(filePath, grid, reminder, cordsAxe);
		
	}
	
	public void setBoat(GridPane grid, Label reminder, Label cordsBoat ) {
	   
		String filePath = "/DiamondHunter/SettingFile/boat.txt"; //to be changed later
		
		StatusGetters.showReminder(reminder, "Please click a position \n to input boat!");
		
		capturePutBoat(filePath, grid, reminder, cordsBoat); 
	}

	public void capturePutAxe(String filePath, GridPane grid, Label reminder, Label cordsAxe) {
		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/tileMap.getTileSize();
			int getY = (int) e.getY()/tileMap.getTileSize();
			int [] clicked = {getX,getY};
		
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
				  StatusGetters.showReminder(reminder,"Unable to set axe \n at tree position."); 
			 } 
			else if (map[getY][getX]== 22) {
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at water position."); 
			} 
			else if(getStatus.isPlayerCords(clicked)) {
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at player position."); 
			}
			else if(getStatus.isAxeCords(clicked)) {
				  StatusGetters.showReminder(reminder, "You have put axe at \n this position here."); 
			}
			else if(getStatus.isBoatCords(clicked)) {
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at boat position."); 
			}
			else {  
				  this.clearLastAxe(filePath, grid, reminder); // removes previous axe image, if any
				  StatusGetters.writePositionToFile(filePath, getX, getY); //put coordinates in the text file
				  getStatus.generateAxeOnMap(grid, getX, getY); // display the axe on the map
				  StatusGetters.showReminder(reminder, "Set Axe Successfully!"); // shows message
				  
				  //put here that it prints the coordinate of the axe in a message
				  getStatus.getAxeCords(getX, getY, cordsAxe);
			}

			
			
			grid.setOnMouseClicked(null);
			
		});

	}

	public void capturePutBoat(String filePath, GridPane grid, Label reminder, Label cordsBoat) {
		
		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/tileMap.getTileSize();
			int getY = (int) e.getY()/tileMap.getTileSize();
			int [] clicked = {getX,getY};
		
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
			 
				  System.out.println("Unable to set boat at tree position.");
				  StatusGetters.showReminder(reminder,"Unable to set boat \n at tree position."); 
				  reminder.setText("Unable to set boat \n at tree position");
			 } 
			else if (map[getY][getX]== 22) {
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at water position."); 
			} 
			else if(getStatus.isPlayerCords(clicked)) {
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at player position."); 
			}
			else if(getStatus.isAxeCords(clicked)) {
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at axe position."); 
			}
			else if(getStatus.isBoatCords(clicked)) {
				  StatusGetters.showReminder(reminder, "You have put boat \n at this position here."); 
			}
			else {  
				  this.clearLastBoat(filePath, grid, reminder); //clear current position before saving
				  StatusGetters.writePositionToFile(filePath, getX, getY);
				  getStatus.generateBoatOnMap(grid, getX, getY); // to display the boat on the desired location
				  StatusGetters.showReminder(reminder, "Set Boat Successfully!");
				  
				//put here that it prints the coordinate of the boat in a message
				  getStatus.getBoatCords(getX, getY, cordsBoat);
			}
			
			grid.setOnMouseClicked(null);
			
		});
	}
	
	public void clearLastAxe(String filePath, GridPane grid, Label reminder) {
		
		int [] temp_axe = new int [2];
		temp_axe = getStatus.getAxeCords();
		
		tileMap.generateOneTileByMap(grid, temp_axe[0], temp_axe[1]);
	}

	public void clearLastBoat(String filePath, GridPane grid, Label reminder) {
		
		int [] temp_boat = new int [2];
		temp_boat = getStatus.getBoatCords();
		tileMap.generateOneTileByMap(grid, temp_boat[0], temp_boat[1]);
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
		getStatus.getCurrCords(getX, getY, tileStatusfree(getX, getY), grid, cords, hover);
		
	}
	
	
	public boolean tileStatusfree(int getX, int getY) {// x is row, y is column, not the other way around
		int [] cord = {getX, getY}; //coordinated x and y
		
		if (map[getY][getX]==1||map[getY][getX]==2||map[getY][getX]==3) { //map y and x
			if ( !getStatus.isAxeCords(cord) && !getStatus.isBoatCords(cord) && !getStatus.isPlayerCords(cord)) {
				return true;
			}
		}
			return false;
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}
	
}