package fxmlFiles;

import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import fxmlFiles.UIControllerFunctions;

public class StatusGetters {
	
	int[] CursorCords = new int[2];
	int[] AxeCords = {26, 37};
	int[] BoatCords = {12, 4};
	int [] PlayerCords = {17,17};
	String coordinates ="Co-ordinates- X: %d, Y: %d\nStatus: %s";
	String tileStatus = "Staus: %s";
	
	TileMap tileMap;
	int [][] map;
	
	public StatusGetters() {
		UIControllerFunctions holder = new UIControllerFunctions();
		tileMap = holder.getTileMap();
		map = holder.getMap();
	}
	

	
	public int[] getcords(GridPane grid, Label cords, MouseEvent hover) {
		CursorCords[0] = (int) hover.getX()/tileMap.getTileSize();
		CursorCords[1] = (int) hover.getY()/tileMap.getTileSize();
		setCordsText(cords,CursorCords[0],CursorCords[1]);
		return CursorCords;
	}
	public void setCordsText(Label cords, int getX, int getY) {
		cords.setText(String.format(coordinates, getX, getY,"Hello"));
		
	}
	
	public int getTileStatus() {
		int getX = CursorCords[0];
		int getY = CursorCords[1];
		return tileMap.getIndex(getX, getY);
		/*if(tileMap.getIndex(getX, getY)==1 || tileMap.getIndex(getX, getY)==3) { // || CursorCords==AxeCords || CursorCords==BoatCords) {
			return Tile.NORMAL;
		}else {
			return Tile.BLOCKED;
		}*/
	}
	
	
}
