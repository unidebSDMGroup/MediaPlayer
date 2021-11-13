package controller;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.App;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.VideoType;

public class RenderController {
	
	
	@FXML
	public Pane render_pane; 
	
	public static Pane static_pane;
	
	
	 @FXML
	    public void initialize() {
		    //uwu
	        static_pane = render_pane;
	    }
	
	 
	
	public static void init_render() {
		
		App.previewStage.hide();
		App.timelineStage.hide();
        App.renderStage.show();
        
		for(MediaType mt :Media_container.media_collection) {
			//an image
			if (mt instanceof ImageType) {
				
				//TODO image render
				System.out.println("image");
			}
			//a video
			else if(mt instanceof VideoType) {
				render_video((VideoType)mt);
			}
			// an audio
			else {
				//TODO play audio
				System.out.println("audio");
			}
		}
	}
	
	
	public static void render_video(VideoType vt) {
		
		vt.video.getMediaPlayer().play();
		vt.video.setX(vt.preview_position.x);
		vt.video.setY(vt.preview_position.y);
		vt.video.setFitHeight(vt.preview_height);
		vt.video.setFitWidth(vt.preview_width);
		
		static_pane.getChildren().add(vt.video);

		
	}
	
}
