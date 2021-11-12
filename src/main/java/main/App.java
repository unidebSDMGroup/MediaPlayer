package main;


import controller.TimelineController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.Global_elements;

public class App extends Application{
	 	
	
	    
	  public void start(Stage primaryStage) {
		  
		  	
		    new Global_elements();
			 
		    Stage previewStage = new Stage();
	        //previewStage.initStyle(StageStyle.UNDECORATED);
		     

		  try {
			  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Timeline_Screen.fxml"));
			  Scene scene = new Scene(root);
			  primaryStage.setScene(scene);
			  primaryStage.show();
			  primaryStage.setTitle("Timeline");
			  primaryStage.setY(550);
			  primaryStage.setResizable(false);
			  

				 



			  Parent anotherRoot = FXMLLoader.load(getClass().getClassLoader().getResource("Preview_Screen.fxml"));
			  Scene anotherScene = new Scene(anotherRoot);
			  previewStage.setScene(anotherScene);
			  previewStage.show();
			  previewStage.setTitle("Preview");
			  previewStage.setY(100);
			  previewStage.setResizable(false);


		  } catch (Exception exc) {

			  exc.printStackTrace();

		  }
	        
	    }
	  
	
}