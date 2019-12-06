package fxmlFiles;

import Tiles.Tile;
import Tiles.TileMap;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import fxmlFiles.UIControllerFunctions;

public class StatusGetters {
	
	int[] CursorCords = new int[2];
	int[] AxeCords = new int[2];
	int[] BoatCords = new int[2];
	String coordinates ="Co-ordinates- X: %d, Y: %d\n%s";
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
		//String tileStatusTemp;
		getTileStatus();
		System.out.println(String.format(tileStatus, "Blocked"));
		/*if (getTileStatus()==Tile.BLOCKED) {
			
		}else {
			tileStatusTemp = String.format(tileStatus, "Free");
		}*/
		
		cords.setText(String.format(coordinates, getX, getY,"Hello"));
	}
	
	public int getTileStatus() {
		int getX = CursorCords[0];
		int getY = CursorCords[1];
		if(tileMap.getIndex(getX, getY)<1 || tileMap.getIndex(getX, getY)>3) { // || CursorCords==AxeCords || CursorCords==BoatCords) {
			return Tile.BLOCKED;
		}else {
			return Tile.NORMAL;
		}
	}
	
	
}
