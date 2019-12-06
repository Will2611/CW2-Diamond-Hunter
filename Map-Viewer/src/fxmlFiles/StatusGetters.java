package fxmlFiles;

import Tiles.Tile;
import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import fxmlFiles.UIControllerFunctions;

public class StatusGetters {
	
	int NUM_ROW = 40;
	int NUM_COL = 40;
	int[] CursorCords = new int[2];
	int[] AxeCords = {26, 37};
	int[] BoatCords = {12, 4};
	int [] PlayerCords = {17,17};
	String coordinates ="Co-ordinates- X: %d, Y: %d\nStatus: %s";
	String tileStatus = "";
	TileMap tileMap;
	
	public StatusGetters() {
	}
	

	
	public int[] getcords(int getX, int getY, boolean status, GridPane grid, Label cords, MouseEvent hover) {
		CursorCords[0] = getX;
		CursorCords[1] =  getY;
		setCordsText(cords, status ,CursorCords[0],CursorCords[1]);
		return CursorCords;
	}
	public void setCordsText(Label cords, boolean status, int getX, int getY) {
		if (status) {
			cords.setText(String.format(coordinates, getX, getY,"free"));
		}else {
			cords.setText(String.format(coordinates, getX, getY,"blocked"));
		}
			
	}

	public int getXCord(int [] sprite) {
		return sprite[0];
	}
	public int getYCord(int [] sprite) {
		return sprite[1];
	}
	public int[] getPlayercords() {
		return PlayerCords;
	}
	public int[] getAxeCords() {
		return AxeCords;
	}
	public int[] getBoatCords() {
		return BoatCords;
	}
	
}
