package fxmlFiles;

import Tiles.Tile;
import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class UIControllerFunctions {
	TileMap tileMap = new TileMap(16);
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
	
	public void setcords(GridPane grid, Label cords, MouseEvent hover) {
		int getX = (int) hover.getX()/tileMap.getTileSize();
		int getY = (int) hover.getY()/tileMap.getTileSize();
		getStatus.getcords(getX, getY, tileStatus(getX, getY), grid, cords, hover);
		
	}
	
	public boolean tileStatus(int getX, int getY) {
		int [] cord = {getX, getY};
		if (map[getX][getY]==1||map[getX][getY]==2||map[getX][getY]==3) {
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
