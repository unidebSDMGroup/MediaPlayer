package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	public void import_file() {
        picker.getFile( chooser.fileChooser.showOpenDialog(stage),vbox);

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
