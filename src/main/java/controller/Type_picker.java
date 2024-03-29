package controller;

import java.io.File;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import model.Color_table;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.AppParameters;
import model.VideoType;
import model.VisualType;
import util.Delta;
import util.DragResizer;
import util.String_util;
import util.Vector2;
import view.Global_elements;


/**
 * Contains most of the core logic for the software.
 * Mainly responsible 
 * 
 * @author kefta
 *
 */
public class Type_picker {
//	public boolean zoneGenerated = false;
	
	/**
	 * Attempts to select which media type to initialize as well as launch it's appropriate type initializer method
	 * 
	 * @param in file name
	 * @param vbox_parent draggable box container
	 * @param layer_vbox layer info container
	 * @param stack_pane parent stack pane
	 * @param media_start_label the label showing start-time of the selected media object
	 */
public void getFile(File in,VBox vbox_parent,VBox layer_vbox, AnchorPane stack_pane, Label media_start_label) {
	
		
		switch (getExtensionByStringHandling(in.toString()).get()) {
		
		case "mp4" : init_Video(in,vbox_parent,layer_vbox,stack_pane,media_start_label); break;
		case "jpg" : init_Image(in,vbox_parent,layer_vbox,stack_pane,media_start_label); break;
		case "mp3" : init_Audio(in,vbox_parent,layer_vbox,stack_pane,media_start_label); break;
		default:
			break;
		}
			
	} 
	
	/**
	 * fetches the filename's extension
	 * 
	 * @param filename provided filename
	 * @return the extention as an optional string
	 */
	public Optional<String> getExtensionByStringHandling(String filename) {
		
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	
	 /**
	  * Responsible for general common media initializing
	  * also assigns colors and names
	  * 
	  * @param media_instance
	  */
	public void init_media(MediaType media_instance) {
		
		media_instance.name = "Media layer "+Media_container.media_collection.size();
		
		Random rand = new Random();
		int a = rand.nextInt(Color_table.colors.size());
		
		int table_len = Color_table.colors.entrySet().toArray().length;
		//System.out.println("starting at "+a);
		
		for (int j = a ; true ; j = ((j==(table_len-1))? j=0 : j+1)) {
			if ( (Boolean)Color_table.colors.values().toArray()[j] == false) {
				//System.out.println("color free .. picking it");
				Color_table.colors.put((Color) Color_table.colors.keySet().toArray()[j], true);
				media_instance.color = (Color) Color_table.colors.keySet().toArray()[j];

				break;
			}
			else {
				//System.out.println(j);

				//System.out.println("still looking");

			}
			
		}
		
		
		Media_container.media_collection.add(media_instance);
	}
	
	/**
	 * Image initializer responsible for building image objects and storing them in the {@link model.Media_container}
	 * 
	 * @param in file name
	 * @param parent_vbox draggable box container
	 * @param layer_vbox layer info container
	 * @param stack_pane parent stack pane
	 * @param media_start_label the label showing start-time of the selected media object
	 */
	public void init_Image(File in, VBox parent_vbox,VBox layer_vbox, AnchorPane stack_pane, Label media_start_label) {
		
		ImageType media_instance = new ImageType();
		
		init_media(media_instance);
        
		 Rectangle prev_rectangle =  add_preview_UI(media_instance);
		 
		 Rectangle drag_rect = add_resize_rect(media_instance,prev_rectangle);
		 
	        
	        PreviewController.static_prev_pane.getChildren().add(drag_rect);

			PreviewController.static_prev_pane.getChildren().add(prev_rectangle);


	        Rectangle rectangle = add_timeline_UI(media_instance, media_start_label);
	      	parent_vbox.getChildren().add(rectangle);
	      	
			HBox params = add_layer_parameters(media_instance);
	      	layer_vbox.getChildren().add(params); 
        
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
		    	
		    	prev_rectangle.setWidth(image.getHeight()/2);
				prev_rectangle.setHeight(image.getWidth()/2);
				
				drag_rect.setX(prev_rectangle.getTranslateX()+prev_rectangle.getWidth());
	            drag_rect.setY(prev_rectangle.getTranslateY()+prev_rectangle.getHeight());
				
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Responsible for adding the resizing component for a given preview rectangle.
	 * 
	 * @param media_instance used media object
	 * @param prev_rectangle given preview rectangle
	 * @return
	 */
	private Rectangle add_resize_rect(VisualType media_instance , Rectangle prev_rectangle) {
		 Rectangle drag_rect = new Rectangle();
	        drag_rect.setWidth(20);
	        drag_rect.setHeight(20);
	        drag_rect.setFill(Color.WHITE);
	        
	        
	        drag_rect.setX(prev_rectangle.getWidth());
	        drag_rect.setY(prev_rectangle.getHeight());

	        
	        final Vector2 dragDelta = new Vector2(); 
	        final Vector2 oldDelta = new Vector2(); 

	        

	        drag_rect.setOnMousePressed(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		          // record a delta distance for the drag and drop operation.
		          drag_rect.setCursor(Cursor.NW_RESIZE);
		        	oldDelta.x = mouseEvent.getSceneX() ;
		        	
		        	AppParameters.block_camera_mouvement_flag = true;

		        }
		      });
	        drag_rect.setOnMouseReleased(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		        	drag_rect.setCursor(Cursor.DEFAULT);
		        	
		        	media_instance.preview_width = (float) prev_rectangle.getWidth() * 2;
		        	media_instance.preview_height = (float) prev_rectangle.getHeight() * 2;
		        	
		        	AppParameters.block_camera_mouvement_flag = false;
		        }
		      });
	        
	        drag_rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		        	drag_rect.setCursor(Cursor.NW_RESIZE);
		        	

		        }
		      });
			
	        drag_rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent mouseEvent) {
		        	
		        	dragDelta.x = mouseEvent.getSceneX() - oldDelta.x;
		        	
		        	oldDelta.x = mouseEvent.getSceneX();


		        	prev_rectangle.setWidth(prev_rectangle.getWidth() + dragDelta.x);
		        	prev_rectangle.setHeight(prev_rectangle.getWidth()+ dragDelta.x);
		        	
		        	drag_rect.setX(prev_rectangle.getTranslateX()+prev_rectangle.getWidth());
		            drag_rect.setY(prev_rectangle.getTranslateY()+prev_rectangle.getHeight());
		        	
		        }
		      });
			
	        media_instance.drag_rect = drag_rect;
	        
	        return drag_rect;
	}


	/**
	 * Video initializer responsible for building image objects and storing them in the {@link model.Media_container}
	 * 
	 * @param in file name
	 * @param parent_vbox draggable box container
	 * @param layer_vbox layer info container
	 * @param stack_pane parent stack pane
	 * @param media_start_label the label showing start-time of the selected media object
	 */
	public void init_Video(File in, VBox parent_vbox, VBox layer_vbox, AnchorPane stack_pane, Label media_start_label) {

		VideoType media_instance = new VideoType();
		
		init_media(media_instance);
		
        
        Rectangle prev_rectangle =  add_preview_UI(media_instance);
        
        Rectangle drag_rect = add_resize_rect(media_instance,prev_rectangle);
        
        PreviewController.static_prev_pane.getChildren().add(drag_rect);
        
        
		PreviewController.static_prev_pane.getChildren().add(prev_rectangle);
		

        Rectangle rectangle = add_timeline_UI(media_instance, media_start_label);
      	parent_vbox.getChildren().add(rectangle);
      	
		HBox params = add_layer_parameters(media_instance);
      	layer_vbox.getChildren().add(params); 
      	
      	Media media; 
		try {
			media = new Media(in.toURI().toURL().toString());
			
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mediaView = new MediaView(mediaPlayer);

	        mediaPlayer.setOnReady(() -> {
	        	//setting media height and width
	        	media_instance.preview_height = media.getHeight();
	        	media_instance.preview_width = media.getWidth();
	        	//setting media duration
				media_instance.duration = media.getDuration().toMillis()/AppParameters.PPMS;
				//setting media end time
				media_instance.clip_end_time = (float) (media_instance.clip_start_time + media_instance.duration) * AppParameters.PPMS;
				
				//setting preview image
				prev_rectangle.setWidth(media_instance.preview_width/2);
				prev_rectangle.setHeight(media_instance.preview_height/2);
				
				drag_rect.setX(prev_rectangle.getTranslateX()+prev_rectangle.getWidth());
	            drag_rect.setY(prev_rectangle.getTranslateY()+prev_rectangle.getHeight());

		        rectangle.setWidth(media_instance.duration);

	        });
	        
	        
	        media_instance.preview_position = new Vector2(0,0);
	        media_instance.video = mediaView;
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		 
        
		 
		

		//zoneSelectors(stack_pane);
	}
	
	/**
	 * Builds a rectangle to be added to the timeline grid using data from media instance.
	 * configure the rectangle for horizontal movement.
	 * uses a label to display start-time info.
	 * 
	 * @param media_instance the given media instance
	 * @param media_start_label used label
	 * @return the created rectangle
	 */
	private Rectangle add_timeline_UI(MediaType media_instance,Label media_start_label) {
		
        Rectangle rectangle = new Rectangle();

        
		//rectangle creation and fill
		Stop[] stops = new Stop[] { new Stop(0, media_instance.color), new Stop(1, Color.GREY)};
        LinearGradient lg1 = new LinearGradient(0, 0, 2, 1, true, CycleMethod.NO_CYCLE, stops);
        
        
        rectangle.setHeight(50);
        rectangle.setFill(lg1);


        
        //rectangle events
        final Vector2 dragDelta = new Vector2(); 
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
	        	//media_start_label.setText(String_util.default_media_text);
	        }
	      });
          rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	        	if (mouseEvent.getSceneX() + dragDelta.x > 0) {
	          //r.setX(mouseEvent.getSceneX() + dragDelta.x);
	        		
	        	
	        	rectangle.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
	        	media_instance.clip_start_time = (float) (mouseEvent.getSceneX() + dragDelta.x) * AppParameters.PPMS;
				media_instance.clip_end_time = (float) (media_instance.clip_start_time + media_instance.duration) * AppParameters.PPMS;
	            media_start_label.setText(String_util.format_text("media start", (float)(media_instance.clip_start_time/1000), "s"));
	        	}
	        }
	      });

		return rectangle;
	}
	
	/**
	 * Builds a rectangle to be added to the preview window using data from media instance.
	 * configure the rectangle for movement
	 * 
	 * @param media_instance provided media instance
	 * @return the created rectangle
	 */
	private Rectangle add_preview_UI(VisualType media_instance) {
		
        Rectangle prev_rectangle = new Rectangle();
        
        

        final Vector2 dragDelta = new Vector2(); 
        prev_rectangle.setFill(media_instance.color);
		
		prev_rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	          // record a delta distance for the drag and drop operation.
	          dragDelta.x = prev_rectangle.getTranslateX() - mouseEvent.getSceneX();
	          dragDelta.y = prev_rectangle.getTranslateY() - mouseEvent.getSceneY();
	          prev_rectangle.setCursor(Cursor.MOVE);
	          
	          AppParameters.block_camera_mouvement_flag = true;
	        }
	      });
		prev_rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	        	prev_rectangle.setCursor(Cursor.HAND);
	        	
	        	AppParameters.block_camera_mouvement_flag = false;
	        }
	      });
		
		prev_rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent mouseEvent) {
	        	
	        	prev_rectangle.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
	            prev_rectangle.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
	            
	            media_instance.preview_position.x = prev_rectangle.getTranslateX()*2;
	            media_instance.preview_position.y = prev_rectangle.getTranslateY()*2;
	            media_instance.drag_rect.setX(prev_rectangle.getTranslateX()+prev_rectangle.getWidth());
	            media_instance.drag_rect.setY(prev_rectangle.getTranslateY()+prev_rectangle.getHeight());
	            
	            
	            

	        }
	      });
		return prev_rectangle;
	}
	
	/**
	 * builds an HBox containing layer parameters ( name, mute icon, ... ) using data from a given media instance
	 * 
	 * @param media_instance The given media instance
	 * @return The created Hbox
	 */
	 HBox add_layer_parameters(MediaType media_instance) {
        Label name = new Label(media_instance.name);
        
        //parameters box ( name, icon, color,hidden icon, mute icon )
        HBox params = new HBox(20);
        
        //keeps the parameters box from resizing
        params.setMaxWidth(150);
        params.setPrefWidth(150);
        params.setMinWidth(150);
        
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
		
		if(media_instance instanceof ImageType) muteButton.setDisable(true);
		
		return params;
        
	}
	


	 /**
		 * Video initializer responsible for building image objects and storing them in the {@link model.Media_container}
		 * 
		 * @param in file name
		 * @param parent_vbox draggable box container
		 * @param layer_vbox layer info container
		 * @param stack_pane parent stack pane
		 * @param media_start_label the label showing start-time of the selected media object
		 */
	public void init_Audio(File in, VBox parent_vbox, VBox layer_vbox, AnchorPane stack_pane, Label media_start_label) {

		AudioType media_instance = new AudioType();

		init_media(media_instance);


		Rectangle rectangle = add_timeline_UI(media_instance, media_start_label);
		parent_vbox.getChildren().add(rectangle);

		HBox params = add_layer_parameters(media_instance);
		layer_vbox.getChildren().add(params);

		Media media;
		try {
			media = new Media(in.toURI().toURL().toString());

			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mediaView = new MediaView(mediaPlayer);

			mediaPlayer.setOnReady(() -> {
				//setting media duration
				media_instance.duration = media.getDuration().toMillis()/AppParameters.PPMS;
				//setting media end time
				media_instance.clip_end_time = (float) (media_instance.clip_start_time + media_instance.duration) * AppParameters.PPMS;


				rectangle.setWidth(media_instance.duration);

			});
			media_instance.audio = mediaView;



		} catch (MalformedURLException e) {
			e.printStackTrace();
		}






		//zoneSelectors(stack_pane);
	}
	}
	 

