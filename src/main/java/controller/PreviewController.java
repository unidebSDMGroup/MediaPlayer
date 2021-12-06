package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.App;

public class PreviewController {

	@FXML
	public Button maximize_button;

	@FXML
	public Button minimize_button;


	@FXML
	public Button close_button;

	@FXML
	public Pane prev_pane;

	public static Pane static_prev_pane;
	double x,y;


	@FXML
	public void initialize() {

		//uwu
		static_prev_pane = prev_pane;
	}

	@FXML
	private void max(MouseEvent event) {
		if (App.previewStage.isMaximized()) {
			App.previewStage.setMaximized(false);
			App.previewStage.setFullScreen(false);
		} else {
			App.previewStage.setFullScreen(true);
			App.previewStage.setMaximized(true);
		}
	}

	@FXML
	private void close(MouseEvent event) {
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setContentText("Do you want to exit?");
		a.showAndWait();

		if (a.getResult() == ButtonType.OK) {
			//App.timelineStage.close();
			//App.previewStage.close();
			Platform.exit();

		}

	}
	@FXML
	private void min (MouseEvent event){
		App.previewStage.setIconified(true);

	}
	@FXML
	void pressed(MouseEvent event){
		x = event.getSceneX();
		y = event.getSceneY();
	}
	@FXML
	void dragged(MouseEvent event){
		App.previewStage.setY(event.getScreenY() - y);
		App.previewStage.setX(event.getScreenX() - x);

	}
}
