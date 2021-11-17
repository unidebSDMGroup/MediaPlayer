package main;


import controller.TimelineController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Color_table;
import view.Global_elements;

public class App extends Application{
	 	
	 	public static Stage previewStage = new Stage();
	    public static Stage renderStage = new Stage();
	    public static Stage timelineStage;

	  public void start(Stage primaryStage) {
		  
		    timelineStage = primaryStage;
		  	
		    new Global_elements();
		    new Color_table();
			 
		   
	        //previewStage.initStyle(StageStyle.UNDECORATED);
		     

		  try {
			  Parent timelineRoot = FXMLLoader.load(getClass().getClassLoader().getResource("Timeline_Screen.fxml"));
			  Scene timelineScene = new Scene(timelineRoot);
			  primaryStage.setScene(timelineScene);
			  primaryStage.show();
			  primaryStage.setTitle("Timeline");
			  primaryStage.setY(550);
			  primaryStage.setResizable(false);
			  

			  Parent previewRoot = FXMLLoader.load(getClass().getClassLoader().getResource("Preview_Screen.fxml"));
			  Scene previewScene = new Scene(previewRoot);

			  previewStage.setScene(previewScene);
			  previewStage.show();
			  previewStage.setTitle("Preview");
			  previewStage.setY(100);
			  previewStage.setResizable(false);
			  
			  Parent renderRoot = FXMLLoader.load(getClass().getClassLoader().getResource("Render_Screen.fxml"));
			  Scene renderScene = new Scene(renderRoot);

			  renderStage.setScene(renderScene);
			  renderStage.setTitle("Render");

			  renderStage.setY(300);
			  renderStage.setResizable(false);


		  } catch (Exception exc) {

			  exc.printStackTrace();

		  }
		  

			//for (int i = 180 ; i < 4000; i=i+20) {
				create_line(10);
			//}
			
			
	        
	    }
	  
	  public void create_line(int position) {
			 Line line = new Line(0, 0, 0, 250);
			 line.setStroke(Color.GREY);
			
	         line.setStrokeWidth(1);
	         
	         TimelineController.static_stack_pane.getChildren().add(line);
	         line.setViewOrder(100);
	         
	         line.setTranslateX(position);
		 }
	  
	
}
