package controller;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.swing.text.BoxView;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Color_table;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.Parameters;
import model.VideoType;
import model.VisualType;



@ExtendWith(ApplicationExtension.class)
class ImageObjectTest {

	Label l;
	VBox layer_vbox;
	VBox box_vbox;
	
    @Start
    private void start(Stage stage) { 
        
        PreviewController.static_prev_pane = new Pane();
        new Color_table();

    	box_vbox = new VBox();
    	layer_vbox = new VBox(); 
    	l = new Label("");
    	AnchorPane anchorPane = new AnchorPane(); 
    	
    	File f = new File("image.jpg");
    	Type_picker tp = new Type_picker();
    	
    	tp.getFile(f, box_vbox, layer_vbox, anchorPane, l);
    	 
    }



    @Test
    void should_be_added_to_container() {
    	
    	assertNotEquals(Media_container.media_collection.size(), 0); 
    }  
    
    @Test
    void should_be_a_media_type() {
    	
    	ImageType it = (ImageType) Media_container.media_collection.get(0);
    	assertTrue(it instanceof MediaType);
    	assertTrue(it instanceof VisualType);
    }  
    
    @Test
    void test_duration() {
    	ImageType it = (ImageType) Media_container.media_collection.get(0);
    	assertEquals((int)it.duration, 0 );
    	assertEquals((int)(it.clip_end_time - it.clip_start_time), 0 );

    }  
    
    @Test
    void should_contain_parameters() {
    	ImageType it = (ImageType) Media_container.media_collection.get(0);
    	
    	assertNotNull(it.color);
    	assertNotNull(it.name);
    	assertNotNull(it.image_view);
    	 
    }  
    
    @Test
    void containers_test() {
    	ImageType it = (ImageType) Media_container.media_collection.get(0);
    	
    	assertEquals(box_vbox.getChildren().size(), 1);
    	assertEquals(layer_vbox.getChildren().size(), 1);
    }  
     
    
    @AfterEach
    void reset() {
    	Media_container.media_collection.clear();
    }
    
    
    
}