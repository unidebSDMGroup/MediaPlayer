package controller;

import java.io.File;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
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
	public boolean zoneGenerated = false;
	public double startxlimit = -135;
	public double finishxlimit = 275;
	
public void getFile(File in,VBox vbox_parent,StackPane stack_pane) {
	
		
		switch (getExtensionByStringHandling(in.toString()).get()) {
		
		case "mp4" : init_Video(in,vbox_parent,stack_pane); break;
		case "jpg" : init_Image(in,vbox_parent,stack_pane); break;
		case "mp3" : init_Audio(in,stack_pane); break;
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
	
	public void init_Image(File in, VBox parent_vbox,StackPane stack_pane) {
		
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
		zoneSelectors(stack_pane);

		media_instance.preview_position = new Vector2(0,0);
		
		
		
		//TODO add preview parts
	}
	
	public void init_Video(File in, VBox parent_vbox, StackPane stack_pane) {

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
		Button muteButton = new Button();
		muteButton.setMaxSize(50,50);
		muteButton.setPrefSize(50,50);
		muteButton.setStyle("-fx-background-color: #282828");
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

		zoneSelectors(stack_pane);
	}

	//zone selectors generation
	public void zoneSelectors(StackPane stack_pane){

        if(!zoneGenerated) {
			//start line
			double height = 250;
			Rectangle line = new Rectangle(0, 5, 5, height);
			line.setFill(Color.RED);
			//finish line generation
			Rectangle line2 = new Rectangle(0, 5, 5, height);
			line2.setFill(Color.BLUE);
			//x label for start
			Label startxlabel = new Label();
			startxlabel.setTextFill(Color.WHITE);
			startxlabel.setTranslateY(-133);
			//x label for finish
			Label finishxlabel = new Label();
			finishxlabel.setTextFill(Color.WHITE);
			finishxlabel.setTranslateY(-133);


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
			line.setOnMouseReleased(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					line.setCursor(Cursor.HAND);
					double offset = 10;
					if (line.getTranslateX() >= line2.getTranslateX() - offset) {
						line.setTranslateX(line.getTranslateX() - offset);
					}
					if(line.getTranslateX() < startxlimit){
						line.setTranslateX(startxlimit + 1);
					}
				}
			});
			line.setOnMouseDragged(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {

					double offset = 10;
					if ((line.getTranslateX() <= line2.getTranslateX() - offset) && (line.getTranslateX() >= -135)) {
						line.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);

					}
					//System.out.println("start line x: " + line.getTranslateX());
					String line1x = Double.toString(line.getTranslateX());
					startxlabel.setText(line1x);
					startxlabel.setTranslateX(line.getTranslateX());


				}
			});

			//finish line events
			final Delta dragDelta2 = new Delta();
			line2.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					// record a delta distance for the drag and drop operation.
					dragDelta2.x = line2.getTranslateX() - mouseEvent.getSceneX();
					dragDelta2.y = line2.getTranslateY() - mouseEvent.getSceneY();
					line2.setCursor(Cursor.MOVE);
				}
			});
			line2.setOnMouseReleased(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					line2.setCursor(Cursor.HAND);
					double offset = 10;
					if (line2.getTranslateX() <= line.getTranslateX() + offset) {
						line2.setTranslateX(line.getTranslateX() + offset);
					}
					if(line2.getTranslateX() >= finishxlimit){
						line2.setTranslateX(finishxlimit - 1);
					}

				}
			});
			line2.setOnMouseDragged(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					double offset = 10;
					if ((line2.getTranslateX() >= line.getTranslateX() + offset) && (line2.getTranslateX() < 275)) {
						line2.setTranslateX(mouseEvent.getSceneX() + dragDelta2.x);
					}
					//System.out.println("finish line x: " + line2.getTranslateX());
					String line2x = Double.toString(line2.getTranslateX());
					finishxlabel.setText(line2x);
					finishxlabel.setTranslateX(line2.getTranslateX());

				}
			});

			stack_pane.getChildren().add(line2);
			line2.toFront();
			line2.setTranslateX(20);
			stack_pane.getChildren().add(line);
			line.toFront();
			stack_pane.getChildren().add(startxlabel);
			startxlabel.toFront();
			stack_pane.getChildren().add(finishxlabel);
			finishxlabel.toFront();
			zoneGenerated = true;
		}

	}

	public void init_Audio(File in, StackPane stack_pane) {
		 //TODO create audio
		zoneSelectors(stack_pane);
	}
	 
}
