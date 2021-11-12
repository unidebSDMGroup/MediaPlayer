package view;

import javafx.stage.FileChooser;

public class Chooser {
	
	public FileChooser fileChooser = new FileChooser();

	
	public Chooser() {
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Media", "*.jpg","*.mp3","*.mp4")
			    
			);
	}

}
