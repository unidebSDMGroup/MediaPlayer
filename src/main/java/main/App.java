package main;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	 	
	
	    
	  public void start(Stage primaryStage) {
		  
		    Group group = new Group();
	        
	        Scene scene = new Scene(group, 1024, 800);
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        
	    }
	  
	
}
