package controller;


import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.App;
import model.*;
import util.Delta;
import view.Chooser;

public class TimelineController {
	
	private Stage stage;
	private Type_picker picker = new Type_picker();
	private Chooser chooser = new Chooser();
	public boolean zoneGenerated = false;

	 
	

	@FXML
	 public Button import_button;
	@FXML
	 public Button export_button;
	@FXML
	public VBox vbox;
	@FXML
	public StackPane stack_pane;
	
	
	
	 @FXML
	    public void initialize() {
		 //vbox.setAlignment(Pos.CENTER_RIGHT);
		 zoneSelectors(stack_pane);

	    }
	
	@FXML
	public void import_file() {
        picker.getFile( chooser.fileChooser.showOpenDialog(stage),vbox,stack_pane);

	}
	 
	@FXML
	public void export() {
		
        
        RenderController.init_render();
        
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
	public void create_line(StackPane stack_pane,int height,int offset,int edge_limit,boolean start_flag) {

		Rectangle line = new Rectangle(0, 5, 5, height);
		if ( start_flag ) line.setFill(Color.RED);
		else line.setFill(Color.BLUE);

		//x label for start
		Label line_label = new Label();
		line_label.setTextFill(Color.WHITE);
		line_label.setTranslateY(-133);

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

				start_cond.set((mouseEvent.getSceneX() + dragDelta.x <= Parameters.timeline_region_end_time - offset)
						&& (mouseEvent.getSceneX() + dragDelta.x >= edge_limit));

				end_cond.set((mouseEvent.getSceneX() + dragDelta.x >= Parameters.timeline_region_start_time + offset)
						&& (mouseEvent.getSceneX() + dragDelta.x < edge_limit));

				if (start_flag ? start_cond.get() : end_cond.get()) {

					line.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
				}
				//System.out.println("start line x: " + line.getTranslateX());
				line_label.setText(Double.toString(line.getTranslateX()));
				line_label.setTranslateX(line.getTranslateX());

				if ( start_flag ) Parameters.timeline_region_start_time = (float) line.getTranslateX();
				else Parameters.timeline_region_end_time = (float) line.getTranslateX();

			}
		});

		stack_pane.getChildren().add(line);
		line.toFront();
		if ( start_flag) {
			line.setTranslateX(-20);
			Parameters.timeline_region_start_time = -20;
		}
		line_label.setText(Double.toString(line.getTranslateX()));
		line_label.setTranslateX(line.getTranslateX());

		stack_pane.getChildren().add(line_label);
		line_label.toFront();
	}
	//zone selectors generation
	public void zoneSelectors(StackPane stack_pane){

		if(!zoneGenerated) {

			//start line
			int height = 250;
			int startxlimit = -135;
			int finishxlimit = 275;

			int offset = 10;

			create_line(stack_pane, height, offset, startxlimit, true);
			create_line(stack_pane, height, offset, finishxlimit, false);

			zoneGenerated = true;
		}

	}
	
}
