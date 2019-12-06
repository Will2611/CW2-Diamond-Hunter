package fxmlFiles;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public interface UIMVControllerInterface {
	public void LoadMap();
	public void PlaceAxe();
	public void PlaceBoat();
	public void hoverCursor(MouseEvent event);
	public void closeApp();
	
	
}
