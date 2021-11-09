package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
	 	
	
	    
	  public void start(Stage primaryStage) {

		    Group group = new Group();


		  Stage previewStage = new Stage();
	        previewStage.initStyle(StageStyle.UNDECORATED);


		  try {
			  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Editor_Screen.fxml"));
			  Scene scene = new Scene(root);
			  primaryStage.setScene(scene);
			  primaryStage.show();
			  primaryStage.setTitle("Timeline");
			  primaryStage.setY(550);


			  Parent anotherRoot = FXMLLoader.load(getClass().getClassLoader().getResource("Preview_Screen.fxml"));
			  Scene anotherScene = new Scene(anotherRoot);
			  previewStage.setScene(anotherScene);
			  previewStage.show();
			  previewStage.setTitle("Preview");
			  previewStage.setY(100);

		  } catch (Exception exc) {

			  exc.printStackTrace();

		  }
	        
	    }
	  
	
}
