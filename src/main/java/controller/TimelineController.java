package controller;


import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.App;
import model.*;
import util.Delta;
import util.String_util;
import util.Validator;
import view.Chooser;

public class TimelineController {
	
	private Stage stage;
	private Type_picker picker = new Type_picker();
	private Chooser chooser = new Chooser();
	public boolean zoneGenerated = false;

	 
	@FXML
	public Label start_label;
	@FXML
	public Label end_label;
	@FXML
	public Label media_start_label;

	@FXML
	 public Button import_button;
	@FXML
	 public Button export_button;
	@FXML
	public VBox vbox;
	@FXML
	public AnchorPane stack_pane;
	
	@FXML
	public ScrollPane scroll_pane;
	@FXML
	public ScrollPane layer_scroll_pane;
	@FXML
	public VBox layer_vbox;
	
	public static ScrollPane static_scroll_pane;
	public static ScrollPane static_layer_scroll_pane;

	
	
	public static AnchorPane static_stack_pane;
	
	
	 @FXML
	    public void initialize() {


		 layer_scroll_pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		 layer_scroll_pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		 
		 
		 vbox.setViewOrder(10);

		 static_stack_pane = stack_pane;
		 static_scroll_pane = scroll_pane;
		 static_layer_scroll_pane = layer_scroll_pane;
		 
		 media_start_label.setText(String_util.default_media_text);

	
	
		 //vbox.setAlignment(Pos.CENTER_RIGHT);
		 zoneSelectors(stack_pane);

	    }
	
	@FXML
	public void import_file() {
		
		
		
        picker.getFile( chooser.fileChooser.showOpenDialog(stage),vbox,layer_vbox,stack_pane,media_start_label);

	}
	 
	@FXML
	public void export() {
		
        Validator.startLineCheck();
        //RenderController.init_render();
        
        //minimize window
        //App.timelineStage.setIconified(true);
        
        //exits app
        //Platform.exit();

	}
	
	

	@FXML
	 public void import_select() {
		import_button.setText("...");
	 }
	
	@FXML
	 public void import_unselect() {
		import_button.setText("Import");
	 }
	
	@FXML
	 public void export_select() {
		export_button.setText("...");
	 }
	
	@FXML
	 public void export_unselect() {
		export_button.setText("Export");
	 }
	public void create_line(AnchorPane pane,int height,int offset,int edge_limit,boolean start_flag) {

		Rectangle line = new Rectangle(0, 5, 5, height);
		if ( start_flag ) line.setFill(Color.RED);
		else line.setFill(Color.BLUE);

		//FIXME change delta to vector2
		//FIXME attempt to create the line out of an actual line

		//start line events
		final Delta dragDelta = new Delta();
		line.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = line.getTranslateX() - mouseEvent.getSceneX();
				dragDelta.y = line.getTranslateY() - mouseEvent.getSceneY();
				line.setCursor(Cursor.MOVE);
			}
		});

		SimpleBooleanProperty start_cond = new SimpleBooleanProperty();
		SimpleBooleanProperty end_cond = new SimpleBooleanProperty();

		line.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {

				start_cond.set((mouseEvent.getSceneX() + dragDelta.x <= (Parameters.timeline_region_end_time/Parameters.PPMS) - offset)
						&& (mouseEvent.getSceneX() + dragDelta.x >= edge_limit));

				end_cond.set((mouseEvent.getSceneX() + dragDelta.x >= (Parameters.timeline_region_start_time/Parameters.PPMS) + offset)
						&& (mouseEvent.getSceneX() + dragDelta.x < edge_limit));

				if (start_flag ? start_cond.get() : end_cond.get()) {

					line.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);

				}

				if ( start_flag ) {
					Parameters.timeline_region_start_time = (float) line.getTranslateX() * Parameters.PPMS;
					start_label.setText(String_util.format_text("start mark",Parameters.timeline_region_start_time /1000, "s"));

				}
				else {
					Parameters.timeline_region_end_time = (float) line.getTranslateX() * Parameters.PPMS;
					end_label.setText(String_util.format_text("end mark",Parameters.timeline_region_end_time/1000, "s"));

				}
				
				
				
			}
		});


		pane.getChildren().addAll(line);
		line.toFront();
		if ( start_flag) {
			line.setTranslateX(100);
			Parameters.timeline_region_start_time = 100 * Parameters.PPMS ;
		}
		else {
			line.setTranslateX(150);
			Parameters.timeline_region_end_time = 150 * Parameters.PPMS ;
		}
	
		start_label.setText(String_util.format_text("start mark",Parameters.timeline_region_start_time/1000, "s"));
		end_label.setText(String_util.format_text("end mark",Parameters.timeline_region_end_time /1000, "s"));

	}
	//zone selectors generation
	public void zoneSelectors(AnchorPane pane){

		if(!zoneGenerated) {

			//start line
			int height = 250;
			int startxlimit = 0;
			int finishxlimit = 1000;

			int offset = 10;

			create_line(pane, height, offset, startxlimit, true);
			create_line(pane, height, offset, finishxlimit, false);

			zoneGenerated = true;
		}

	}
	
	
	
}
