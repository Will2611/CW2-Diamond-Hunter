package fxmlFiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import fxmlFiles.UIControllerFunctions;

//this class contains functions to run mapviewer

public class StatusGetters {
	
	int NUM_ROW = 40;
	int NUM_COL = 40;
	int[] CursorCords = new int[2];
	int[] AxeCords = {26, 37};
	int[] BoatCords = {12, 4};
	int [] PlayerCords = {17, 17};
	String coordinates ="Co-ordinates- X: %d, Y: %d\nStatus: %s";
	String tileStatus = "";
	TileMap tileMap;
	
	public StatusGetters() {
	}
	
	public static void showReminder(Label reminder, String message) {
		reminder.setText(message);
	}
	
	//check the item position
	public static void checkPos(GridPane grid, int x, int y, Label reminder, String message){ 
		
		//x is colIndex and y is rowIndex
		
		Pane pane = new Pane();
		grid.add(pane, x, y);

		pane.setOnMouseClicked(e -> {
			showReminder(reminder, message);
		});
	}
	
	//load image of axe on the tile
	public static void generateAxeOnMap(GridPane grid, int x, int y) {
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, x, y);

		BufferedImage axeBuf = Content.ITEMS[1][1];
		Image axeImage = SwingFXUtils.toFXImage(axeBuf, null);
		imageField.getChildren().add(new ImageView(axeImage));
	}
	
	//generate image of boat on the tile
	public static void generateBoatOnMap(GridPane grid, int colIndex, int rowIndex) {
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, colIndex, rowIndex);

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
