package fxmlFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public interface UIControllerInterface {

	
	/**
	 * This method is used to load map file and show the map on the screen
	 * 
	 * @param GridPane
	 *            grid the background grind pane
	 * @param Label
	 *            reminder reminder message
	 */
    @FXML
    void LoadMap();

    /**
	 * This method is used to Place Axe on map
	 * 
	 * @param GridPane
	 *            grid the background grind pane
	 * @param Label
	 *            reminder reminder message
	 */
    @FXML
    void PlaceAxe();
    
    
    /**
	 * This method is used to Place Boat on map
	 * 
	 * @param GridPane
	 *            grid the background grind pane
	 * @param Label
	 *            reminder reminder message
	 */
    @FXML
    void PlaceBoat();
    
    /**
	 * This method is used to detect coordinates on map
	 * 
	 * @param GridPane
	 *            grid the background grind pane
	 * @param Label
	 *            cords present coordinates
	 */
    @FXML
    void hoverCords(MouseEvent event);
    
    
    void closeApp(); 

}
