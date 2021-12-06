package controller;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.App;
import model.*;
import util.Validator;
/**
 * 
 *Render Controller class
 *
 */
public class RenderController {
	
	/**
	 * FXML Close button hides render stage and re-open preview and time-line stages 
	 */
	@FXML
	public void close() {
		App.previewStage.show();
		App.timelineStage.show();
        App.renderStage.hide();
        
	}
	
	@FXML
	public void min() {
		App.renderStage.setIconified(true);
	}
	
	
	@FXML
	public Pane render_pane; 
	
	public static Pane static_pane;
	
	
	 @FXML
	 public void initialize() {
		 
	        static_pane = render_pane;
	        
	    }
	
	 
	/**
	 * Hides preview and time-line panes when exported into render window and shows the render window
	 * Also initializes media objects for the render window
	 */
	public static void init_render() {
		
		App.previewStage.hide();
		App.timelineStage.hide();
        App.renderStage.show();
        
        
		for(MediaType mt :Media_container.media_collection) {
			//an image
			if (mt instanceof ImageType) {
				
				//TODO image render
				//System.out.println("image");
				render_image((ImageType) mt);
			}
			//a video
			else if(mt instanceof VideoType) {
				render_video((VideoType)mt);
			}
			// an audio
			else {
				//TODO play audio
				//System.out.println("audio");
				render_audio((AudioType)mt);
			}
		}
	}
	 
	
	/**
	 * Sets video parameters
	 * @param vt video object
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void render_video(VideoType vt) {
		
		vt.video.getMediaPlayer().play();
		vt.video.setX(vt.preview_position.x);
		vt.video.setY(vt.preview_position.y);
		vt.video.setFitHeight(vt.preview_height);
		vt.video.setFitWidth(vt.preview_width);
		vt.video.getMediaPlayer().seek(new Duration(AppParameters.timeline_region_start_time-vt.clip_start_time));
		vt.video.getMediaPlayer().setStopTime(new Duration(AppParameters.timeline_region_end_time-vt.clip_start_time));
		if(vt.muted == true){
			vt.video.getMediaPlayer().setMute(true);
		}else if(vt.muted == false){
			vt.video.getMediaPlayer().setMute(false);

		}
		
		static_pane.getChildren().add(vt.video);
		

		
	}
	
	/**
	 * Sets image parameters
	 * @param it image object
	 */
	public static void render_image(ImageType it){

		 it.image_view.setX(it.preview_position.x);
		 it.image_view.setY(it.preview_position.y);
		 it.image_view.setFitHeight(it.preview_height);
		 it.image_view.setFitWidth(it.preview_width);

		static_pane.getChildren().add(it.image_view);

	}
	
	/**
	 * Sets audio parameters
	 * @param at audio object
	 */
	public static void render_audio(AudioType at){

		at.audio.getMediaPlayer().play();
		at.audio.getMediaPlayer().seek(new Duration(AppParameters.timeline_region_start_time-at.clip_start_time));
		at.audio.getMediaPlayer().setStopTime(new Duration(AppParameters.timeline_region_end_time-at.clip_start_time));
		if(at.muted == true){
			at.audio.getMediaPlayer().setMute(true);
		}else if(at.muted == false){
			at.audio.getMediaPlayer().setMute(false);

		}
		static_pane.getChildren().add(at.audio);

	}
	
}
