package main;


import controller.TimelineController;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
		static DoubleProperty vPosition = new SimpleDoubleProperty();

	
	public void start(Stage primaryStage) {
		  
		    timelineStage = primaryStage;
		  	
		    new Global_elements();
		    new Color_table();
			 
		    //timelineStage.initStyle(StageStyle.UNDECORATED);
	        //previewStage.initStyle(StageStyle.UNDECORATED);
	        //renderStage.initStyle(StageStyle.UNDECORATED);


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

			  renderStage.setResizable(false);


		  } catch (Exception exc) {

			  exc.printStackTrace();

		  }
		  
		  //TODO add lines dynamically
			for (int i = 0 ; i < 4000; i=i+20) {
				create_line(i);
			}
			
 				
			    bind_scrollbars();
	        
	    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bind_scrollbars() {
		vPosition.bind(TimelineController.static_scroll_pane.vvalueProperty());
	    
	    vPosition.addListener(new ChangeListener() {
	        @Override
	        public void changed(ObservableValue arg0, Object arg1, Object arg2) {
	        	TimelineController.static_layer_scroll_pane.setVvalue((double) arg2);
	        	
	        }
	    });
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
