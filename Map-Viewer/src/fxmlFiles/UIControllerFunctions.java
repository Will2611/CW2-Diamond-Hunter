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
	
	StatusGetters getStatus = new StatusGetters();
	
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
	
	public void setAxe(GridPane grid, Label reminder) {
	
		String filePath = "../Diamond-Hunter/bin/SettingFile/axe.txt"; //to be changed later
		
		StatusGetters.showReminder(reminder, "Please click a position \n to input axe!");
		
		capturePutAxe(filePath, grid, reminder);
		
	}
	
	public void setBoat(GridPane grid, Label reminder ) {
	   
		String filePath = "../Diamond-Hunter/bin/SettingFile/boat.txt"; //to be changed later
		
		StatusGetters.showReminder(reminder, "Please click a position \n to input boat!");
		
		capturePutBoat(filePath, grid, reminder); 
	}
	
	public void capturePutAxe(String filePath, GridPane grid, Label reminder) {

		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/tileMap.getTileSize();
			int getY = (int) e.getY()/tileMap.getTileSize();
		
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
			 
				  System.out.println("Unable to set axe at tree position.");
				  StatusGetters.showReminder(reminder,"Unable to set axe \n at tree position."); 
				  reminder.setText("Unable to set axe \n at tree position");
			 } 
			else if (map[getY][getX]== 22) {
				
				  System.out.println("Unable to set axe at water position.");
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at water position."); 
				  reminder.setText("Unable to set axe \n at water position");
			} 
			else if(getX == 17 && getY == 17) {
				  System.out.println("Unable to set axe at player position.");
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at player position."); 
				  reminder.setText("Unable to set axe \n at player position.");
			}
			else if(getX == getStatus.getAxeCords()[0] && getY == getStatus.getAxeCords()[1]) {
				  System.out.println("You have put axe at this position here.");
				  StatusGetters.showReminder(reminder, "You have put axe at \n this position here."); 
				  reminder.setText("You have put axe at \n this position here.");
			}
			else if(getX == getStatus.getBoatCords()[0] && getY == getStatus.getBoatCords()[1]) {
				  System.out.println("Unable to set axe at boat position.");
				  StatusGetters.showReminder(reminder, "Unable to set axe \n at boat position."); 
				  reminder.setText("Unable to set axe \n at boat position.");
			}
			else {  
				  this.clearLastAxe(filePath, grid, reminder);
				  System.out.println("axe");
				  StatusGetters.showReminder(reminder, "Set Axe Successfully!");
				  getStatus.generateAxeOnMap(grid, getX, getY);
				  StatusGetters.writePositionToFile(filePath, getX, getY);
			}

			
			
			grid.setOnMouseClicked(null);
			
		});

	}

	public void capturePutBoat(String filePath, GridPane grid, Label reminder) {
		
		grid.setOnMouseClicked(e -> {
			int getX = (int) e.getX()/tileMap.getTileSize();
			int getY = (int) e.getY()/tileMap.getTileSize();
		
			 if (map[getY][getX]==20 || map[getY][getX]==21) {
			 
				  System.out.println("Unable to set boat at tree position.");
				  StatusGetters.showReminder(reminder,"Unable to set boat \n at tree position."); 
				  reminder.setText("Unable to set boat \n at tree position");
			 } 
			else if (map[getY][getX]== 22) {
				
				  System.out.println("Unable to set boat at water position.");
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at water position."); 
				  reminder.setText("Unable to set boat \n at water position");
			} 
			else if(getX == 17 && getY == 17) {
				  System.out.println("Unable to set boat at player position.");
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at player position."); 
				  reminder.setText("Unable to set boat \n at player position.");
			}
			else if(getX == getStatus.getAxeCords()[0] && getY == getStatus.getAxeCords()[1]) {
				  System.out.println("Unable to set boat at axe position.");
				  StatusGetters.showReminder(reminder, "Unable to set boat \n at axe position."); 
				  reminder.setText("Unable to set boat \n at axe position.");
			}
			else if(getX == getStatus.getBoatCords()[0] && getY == getStatus.getBoatCords()[1]) {
				  System.out.println("You have put boat at this position here.");
				  StatusGetters.showReminder(reminder, "You have put boat \n at this position here."); 
				  reminder.setText("You have put boat \n at this position here.");
			}
			else {  
				  this.clearLastBoat(filePath, grid, reminder);
				  System.out.println("boat");
				  StatusGetters.showReminder(reminder, "Set Boat Successfully!");
				  getStatus.generateBoatOnMap(grid, getX, getY);
				  StatusGetters.writePositionToFile(filePath, getX, getY);
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
		getStatus.getcords(getX, getY, tileStatus(getX, getY), grid, cords, hover);
		
	}
	
	
	public boolean tileStatus(int getX, int getY) {// x is row, y is column, not the other way around
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
