package fxmlFiles;

import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class UIControllerFunctions {
	TileMap tileMap = new TileMap(16);
	int[][] map;
	int NUM_COL = 40;
	int NUM_ROW = 40;
	
	public void loadMap(GridPane grid, Label reminder) {
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		map = tileMap.getMap();
		for (int row = 0; row < NUM_ROW; row++) {
			for (int col = 0; col < NUM_COL; col++) {

				tileMap.generateOneTileByMap(grid, row, col);
			}

		}
		// tell user that load map successful
		reminder.setText("Load Map Successfully!\n\nPlease Set Axe/Boat\nJust Click the button!");

	}
	public void setAxe(GridPane grid, Label reminder) {
		System.out.println("axe");
	}
	
	public void setBoat(GridPane grid, Label reminder) {
		System.out.println("boat");
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}
	
	public int [][] getMap(){
		return map;
	}
}
