package fxmlFiles;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


import Tiles.Content;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
	
	//display chosen coordinates in labels
	String chosen_coordinates_axe = "Axe co-ordinates: \nX: %d, Y: %d\n";
	String chosen_coordinates_boat = "Boat co-ordinates: \n X: %d, Y: %d\n";
	
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
		BufferedImage axeBuf = Content.ITEMS[1][1];
		Image axeImage = SwingFXUtils.toFXImage(axeBuf, null);
		imageField.getChildren().add(new ImageView(axeImage));
	}
	
	//generate image of boat on the tile
	public void generateBoatOnMap(GridPane grid, int rowIndex, int colIndex) {// only called upon if can place boat succesful, can update boat cords
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, rowIndex, colIndex);
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
	

	public int[] getCurrCords(int getX, int getY, boolean status, GridPane grid, Label cords, MouseEvent hover) {
		CursorCords[0] = getX;
		CursorCords[1] =  getY;
		setCurrCordsText(cords, status ,CursorCords[0],CursorCords[1]);

		return CursorCords;
	}
	
	public void setCurrCordsText(Label cords, boolean status, int getX, int getY) {
		if (status) {
			cords.setText(String.format(coordinates, getX, getY,"free"));
		}else {
			cords.setText(String.format(coordinates, getX, getY,"blocked"));
		}
			
	}
	
	//to display coordinates of Axe
	public int[] getAxeCords(int getX, int getY, Label cordsAxe)
	{
		AxeCords[0] = getX;
		AxeCords[1] = getY;
		displayCordsAxeText(cordsAxe, AxeCords[0], AxeCords[1]);
		return AxeCords;
	}

	//to display coordinates of Axe
	private void displayCordsAxeText(Label cordsAxe, int getX, int getY) {
		cordsAxe.setText(String.format(chosen_coordinates_axe, getX, getY));
	}
	
	//to display coordinates of Boat
	public int[] getBoatCords(int getX, int getY, Label cordsBoat)
	{
		BoatCords[0] = getX;
		BoatCords[1] = getY;
		displayCordsBoatText(cordsBoat, BoatCords[0], BoatCords[1]);
		return BoatCords;
	}
	
	//to display coordinates of Boat
	private void displayCordsBoatText(Label cordsBoat, int getX, int getY) {
		cordsBoat.setText(String.format(chosen_coordinates_boat, getX, getY));
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
	
	public void factoryReset(GridPane grid) {
		String axePos = "/DiamondHunter/SettingFile/axe.txt";
		
		String boatPos = "/DiamondHunter/SettingFile/boat.txt";
		
		AxeCords = defaultAxeCords.clone();
		BoatCords = defaultBoatCords.clone();//prevent overwriting default/factory setting
		
		StatusGetters.writePositionToFile(axePos, AxeCords[0], AxeCords[1]);
		StatusGetters.writePositionToFile(boatPos, BoatCords[0], BoatCords[1]);//Write A file into existence;
		this.generateAxeOnMap(grid, AxeCords[0], AxeCords[1]);
		this.generateBoatOnMap(grid, BoatCords[0], BoatCords[1]);
	}
	
	public void setLastSavedCords(GridPane grid) {
		String axePos = "/DiamondHunter/SettingFile/axe.txt";
		File fileAxe = new File(axePos);
		
		String boatPos = "/DiamondHunter/SettingFile/boat.txt";
		File fileBoat = new File(boatPos);
		
		if (fileAxe.exists() && fileBoat.exists()){
			AxeCords = readPositionFromFile(fileAxe);
			BoatCords = readPositionFromFile(fileBoat);;
		}else {
			this.factoryReset(grid);
			
		}
			this.generateAxeOnMap(grid, AxeCords[0], AxeCords[1]);
			this.generateBoatOnMap(grid, BoatCords[0], BoatCords[1]);
	}
	
	private int[] readPositionFromFile(File file) {
		int [] pos = new int [2];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			pos[0] = Integer.parseInt(br.readLine());
			pos[1] = Integer.parseInt(br.readLine());
			br.close();//close reader when not called
			return pos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
}
