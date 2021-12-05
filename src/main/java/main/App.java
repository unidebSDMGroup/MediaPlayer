package main;


import controller.TimelineController;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Color_table;
import util.Vector2;
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

			  
			  ParallelCamera c = new ParallelCamera();
			  
		      final SimpleBooleanProperty held = new SimpleBooleanProperty();
			  final Vector2 dragDelta = new Vector2();
 		      
			  previewScene.setOnMousePressed(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		          // record a delta distance for the drag and drop operation.
		          dragDelta.x = c.getTranslateX() - mouseEvent.getSceneX();
		          dragDelta.y = c.getTranslateX() - mouseEvent.getSceneY();
		        }
		      });
			  previewScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) { 
		        }
		      });
			  previewScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		           
		          if (!held.get()) { 
		        	  c.setTranslateX( - (mouseEvent.getSceneX() + dragDelta.x));
		        	  c.setTranslateY( - (mouseEvent.getSceneY() + dragDelta.y));
		          } 
		        }
		      });
			   
			  previewScene.setOnKeyPressed(new EventHandler<KeyEvent>()
		      {

				public void handle(KeyEvent event) {
					 if (event.getCode() == KeyCode.SHIFT) {
						 held.set(true); 
						 System.out.println("holding");
					 }
				}

		      });
		      
			  previewScene.setOnKeyReleased(new EventHandler<KeyEvent>()
		      {

				public void handle(KeyEvent event) {
					 if (event.getCode() == KeyCode.SHIFT) {
						 held.set(false); 
						 System.out.println("not holding");
					 }
				}

		      }); 
			  
			  
			  previewScene.setCamera(c);
			  
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
