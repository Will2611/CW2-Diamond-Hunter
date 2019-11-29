package MapViewerMain;
	
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private BorderPane root;
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("MapViewer");

			starter();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void starter() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MapViewer.fxml"));
			
			root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			
			setUserAgentStylesheet(STYLESHEET_MODENA);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			MapViewerController controller = loader.getController();
			controller.setMainApp(this);			
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
