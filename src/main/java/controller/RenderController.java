package controller;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.App;
import model.*;
import util.Validator;

public class RenderController {

	@FXML
	public Button maximize_button;

	@FXML
	public Button minimize_button;


	@FXML
	public Button close_button;

	@FXML
	public Pane render_pane; 
	
	public static Pane static_pane;
	double x,y;
	

	 @FXML
	    public void initialize() {
		    //uwu
	        static_pane = render_pane;
		    static_pane.setMaxWidth(Double.MAX_VALUE);
		    static_pane.setMaxHeight(Double.MAX_VALUE);
	    }

	 
	
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
	public static void render_image(ImageType it){

		 it.image_view.setX(it.preview_position.x);
		 it.image_view.setY(it.preview_position.y);
		 it.image_view.setFitHeight(it.preview_height);
		 it.image_view.setFitWidth(it.preview_width);

		static_pane.getChildren().add(it.image_view);

	}

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
	@FXML
	private void min(MouseEvent event) {
		App.renderStage.setIconified(true);

	}
	/*@Override
	public void initialize(java.net.URL url, ResourceBundle rb) {
		static_pane = render_pane;
		static_pane.setMaxWidth(Double.MAX_VALUE);
		static_pane.setMaxHeight(Double.MAX_VALUE);



		//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}*/

	@FXML
	private void max(MouseEvent event) {
		if(App.renderStage.isMaximized()){
			App.renderStage.setMaximized(false);
			App.renderStage.setFullScreen(false);
		}
		else{
			App.renderStage.setFullScreen(true);
			App.renderStage.setMaximized(true);
		}
	}

	@FXML
	private void close(MouseEvent event) {
		App.renderStage.hide();
		App.previewStage.show();
		App.timelineStage.show();
	}
	@FXML
	void pressed(MouseEvent event){
		x = event.getSceneX();
		y = event.getSceneY();
	}
	@FXML
	void dragged(MouseEvent event){
		App.renderStage.setY(event.getScreenY() - y);
		App.renderStage.setX(event.getScreenX() - x);

	}
}
