package fxmlFiles;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


import Tiles.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

//this class contains functions to run map viewer

public class StatusGetters {
	
	int NUM_ROW = 40;
	int NUM_COL = 40;
	int[] CursorCords = new int[2];
	int[] AxeCords = new int [2];
	int[] BoatCords = new int [2];
	static boolean loadCords = false;
	int [] PlayerCords = {17, 17};
	int[] defaultAxeCords = {37, 26};
	int[] defaultBoatCords = {4, 12};
	String coordinates ="Co-ordinates- X: %d, Y: %d\nStatus: %s";
	String tileStatus = "";
	TileMap tileMap;
	
	public StatusGetters() {
		
	}
	
	public static void showReminder(Label reminder, String message) {
		reminder.setText(message);
	}
	
	//load image of axe on the tile
	public void generateAxeOnMap(GridPane grid, int rowIndex, int colIndex) { // only called upon if can place axe successful, can update axe cords
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, rowIndex, colIndex);
		AxeCords[0] = rowIndex;
		AxeCords[1] = colIndex;

		BufferedImage axeBuf = Content.ITEMS[1][1];
		Image axeImage = SwingFXUtils.toFXImage(axeBuf, null);
		imageField.getChildren().add(new ImageView(axeImage));
	}
	
	//generate image of boat on the tile
	public void generateBoatOnMap(GridPane grid, int rowIndex, int colIndex) {// only called upon if can place boat succesful, can update boat cords
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, rowIndex, colIndex);
		BoatCords[0] = rowIndex;
		BoatCords[1] = colIndex;
		
		BufferedImage boatBuf = Content.ITEMS[1][0];
		Image boatImage = SwingFXUtils.toFXImage(boatBuf, null);
		imageField.getChildren().add(new ImageView(boatImage));	
	}

	//print the coordinates on the file
	public static void writePositionToFile(String filePath, int rowIndex, int colIndex) {
		try {
			File file = new File(filePath);

			// if file does not exists, then create it
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			PrintStream ps = new PrintStream(filePath);
			ps.println(rowIndex);
			ps.println(colIndex);

			ps.close();

		} catch (IOException x) {
			System.out.println("Error: " + x);
			x.printStackTrace();
		}
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

	public boolean isPlayerCords(int [] cord) {
		if (cord [0]==PlayerCords[0] && cord[1]==PlayerCords[1]) {
			return true;
		}else {
		return false;	
		}
	}
	public boolean isAxeCords(int [] cord) {
		if (cord [0]==AxeCords[0] && cord[1]==AxeCords[1]) {
			return true;
		}else {
		return false;	
		}
	}
	public boolean isBoatCords(int [] cord) {
		if (cord [0]==BoatCords[0] && cord[1]==BoatCords[1]) {
			return true;
		}else {
		return false;	
		}
	}
	
	public int[] getDefaultAxeCords() {
		return defaultAxeCords;
	}
	
	public int[] getDefaultBoatCords() {
		return defaultBoatCords;
	}
	
	public int[] getAxeCords() {
		return AxeCords;
	}
	
	public int[] getBoatCords() {
		return BoatCords;
	}
	public void setCords(int [] Axe, int [] Boat) {
		for (int i =0; i<2; i++) {
			AxeCords[i] = Axe[i];
			BoatCords[i] = Boat[i];
		}
		
	}
	
}
