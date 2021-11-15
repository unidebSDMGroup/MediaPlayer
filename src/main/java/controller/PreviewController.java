package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PreviewController {
	
	
	@FXML
	public Pane prev_pane;
	
	public static Pane static_prev_pane;
	
	
	 @FXML
	    public void initialize() {

		    //uwu
	        static_prev_pane = prev_pane;
	    }
	
	
}
