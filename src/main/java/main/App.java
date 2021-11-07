package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	 	
	
	    
	  public void start(Stage primaryStage) {
		  
		    Group group = new Group();

		  Stage previewStage = new Stage();

		  try {
			  FXMLLoader loader = new FXMLLoader(); // FXML for primary stage
			  Parent root = loader.load(getClass().getResource("/scenes/Editor_Screen.fxml"));
			  Scene scene = new Scene(root);
			  primaryStage.setScene(scene);
			  primaryStage.show();
			  primaryStage.setTitle("Editor");
			  primaryStage.setX(50);

			  FXMLLoader anotherLoader = new FXMLLoader() ; // FXML for second stage
			  Parent anotherRoot = anotherLoader.load(getClass().getResource("/scenes/Preview_Screen.fxml"));
			  Scene anotherScene = new Scene(anotherRoot);
			  previewStage.setScene(anotherScene);
			  previewStage.show();
			  previewStage.setTitle("Preview");

		  } catch (Exception exc) {

			  exc.printStackTrace();

		  }
	        
	    }
	  
	
}
