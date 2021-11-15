package controller;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.App;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.VideoType;
import view.Chooser;

public class TimelineController {
	
	private Stage stage;
	private Type_picker picker = new Type_picker();
	private Chooser chooser = new Chooser();

	 
	

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
	
}
