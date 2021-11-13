package controller;

import java.io.File;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import model.AudioType;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.Parameters;
import model.VideoType;
import model.VisualType;
import util.Delta;
import util.Vector2;
import view.Global_elements;


public class Type_picker {
	
public void getFile(File in,VBox vbox_parent) {
	
		
		switch (getExtensionByStringHandling(in.toString()).get()) {
		
		case "mp4" : init_Video(in,vbox_parent); break;
		case "jpg" : init_Image(in,vbox_parent); break;
		case "mp3" : init_Audio(in); break;
		default:
			break;
		}
			
	} 
	

	public Optional<String> getExtensionByStringHandling(String filename) {
		
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	 
	public void init_media(MediaType media_instance) {
		
		media_instance.name = "Media layer "+Media_container.media_collection.size();
		media_instance.color = Color.PURPLE;
		
		Media_container.media_collection.add(media_instance);
	}
	
	public void init_Image(File in, VBox parent_vbox) {
		
		ImageType media_instance = new ImageType();
		
		init_media(media_instance);
        
        
		  //media box ( parameters box, rectangle )
        HBox media_box = new HBox(30);
        Label name = new Label(media_instance.name);
        
        //parameters box ( name, icon, color,hidden icon, mute icon )
        HBox params = new HBox(20);
        params.setAlignment(Pos.CENTER_LEFT);
        params.getChildren().add(name);
        
        //mute icon
		//creating mute buttons
		//Color transparent = new Color(0,0,0,0);
		Button muteButton = new Button();
		muteButton.setMaxSize(50,50);
		muteButton.setPrefSize(50,50);
		muteButton.setStyle("-fx-background-color: #282828");
		//muteButton.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		params.getChildren().add(muteButton);

		//adding image to the mute button
		ImageView sound_image = new ImageView();
		sound_image.setImage(Global_elements.sound_image);
		sound_image.setFitHeight(20);
		sound_image.setPreserveRatio(true);
		muteButton.setGraphic(sound_image);

        
        
        media_box.getChildren().add(params);
        
		//image creation
		try {
			Image image = new Image(in.toURI().toURL().toString());
		      final ImageView imageView = new ImageView();
		      imageView.setImage(image);
		      imageView.setX(0);
		      imageView.setY(0);
		      //imageView.setFitWidth(575);
		      imageView.setPreserveRatio(true);
		      media_instance.image_view = imageView;
		      

				media_instance.preview_height = (float) image.getHeight();
		    	media_instance.preview_width = (float) image.getWidth();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
      	parent_vbox.getChildren().add(media_box);

		media_instance.preview_position = new Vector2(0,0);
		
		
		
		//TODO add preview parts
	}
	
	public void init_Video(File in, VBox parent_vbox) {

		VideoType media_instance = new VideoType();
		
		init_media(media_instance);
		
		//rectangle creation and fill
		Stop[] stops = new Stop[] { new Stop(0, Color.PURPLE), new Stop(1, Color.GREY)};
        LinearGradient lg1 = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stops);
        
        Rectangle rectangle = new Rectangle(0, 0, 200, 50);
        rectangle.setFill(lg1);
        
        //rectangle events
        final Delta dragDelta = new Delta(); 
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	          // record a delta distance for the drag and drop operation.
	          dragDelta.x = rectangle.getTranslateX() - mouseEvent.getSceneX();
	          dragDelta.y = rectangle.getTranslateY() - mouseEvent.getSceneY();
	          rectangle.setCursor(Cursor.MOVE);
	        }
	      });
          rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	        	rectangle.setCursor(Cursor.HAND);
	        }
	      });
          rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	          //r.setX(mouseEvent.getSceneX() + dragDelta.x);
	        	rectangle.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
	           
	        }
	      });
        
        //media box ( parameters box, rectangle )
        HBox media_box = new HBox(30);
        Label name = new Label(media_instance.name);
        
        //parameters box ( name, icon, color,hidden icon, mute icon )
        HBox params = new HBox(20);
        params.setAlignment(Pos.CENTER_LEFT);
        params.getChildren().add(name);
        
        //mute icon
		//creating mute buttons
		//Color transparent = new Color(0,0,0,0);
		Button muteButton = new Button();
		muteButton.setMaxSize(50,50);
		muteButton.setPrefSize(50,50);
		muteButton.setStyle("-fx-background-color: #282828");
		//muteButton.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		params.getChildren().add(muteButton);

		//adding image to the mute button
		ImageView sound_image = new ImageView();
		sound_image.setImage(Global_elements.sound_image);
		sound_image.setFitHeight(20);
		sound_image.setPreserveRatio(true);
		muteButton.setGraphic(sound_image);

		//button click setting mute and changing image to notify muted/unmuted
		muteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(media_instance.muted == true){
					media_instance.muted = false;
					sound_image.setImage(Global_elements.sound_image);
				}else{
					media_instance.muted = true;
					sound_image.setImage(Global_elements.mute_image);
				}
			}
		});
        
        
        media_box.getChildren().add(params);
        media_box.getChildren().add(rectangle);
        
        
        //TODO create and store mediaView
		
        Media media; 
        
		try {
			media = new Media(in.toURI().toURL().toString());
			
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			

	        
			MediaView mediaView = new MediaView(mediaPlayer);

	        mediaPlayer.setOnReady(() -> {
	        	media_instance.preview_height = media.getHeight();
	        	media_instance.preview_width = media.getWidth();
	        });
	        
	        media_instance.preview_position = new Vector2(0,0);
	        media_instance.video = mediaView;
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
        //TODO add preview parts
		
        
      //adding UI elements to timeline grid
      	parent_vbox.getChildren().add(media_box);
	}
	public void init_Audio(File in) {
		 //TODO create audio
	}
	 
}
