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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Color_table;
import model.MediaType;
import model.Media_container;
import model.AppParameters;
import model.VideoType;
import model.VisualType;



@ExtendWith(ApplicationExtension.class)
class VideoObjectTest {

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
    	
    	File f = new File("vid.mp4");
    	Type_picker tp = new Type_picker();
    	
    	tp.getFile(f, box_vbox, layer_vbox, anchorPane, l);
    	 
    }



    @Test
    void should_be_added_to_container() {
    	
    	assertNotEquals(Media_container.media_collection.size(), 0); 
    }  
    
    @Test
    void should_be_a_media_type() {
    	
    	VideoType vt = (VideoType) Media_container.media_collection.get(0);
    	assertTrue(vt instanceof MediaType);
    	assertTrue(vt instanceof VisualType);
    }  
    
    @Test
    void test_duration() {
    	VideoType vt = (VideoType) Media_container.media_collection.get(0); 
    	assertEquals((int)(vt.duration * AppParameters.PPMS), (int)(vt.clip_end_time - vt.clip_start_time) );
    }  
    
    @Test
    void should_contain_parameters() {
    	VideoType vt = (VideoType) Media_container.media_collection.get(0); 
    	
    	assertNotNull(vt.color);
    	assertNotNull(vt.name);
    	assertNotNull(vt.video);
    	 
    }  
    
    @Test
    void containers_test() {
    	VideoType vt = (VideoType) Media_container.media_collection.get(0); 
    	
    	assertEquals(box_vbox.getChildren().size(), 1);
    	assertEquals(layer_vbox.getChildren().size(), 1);
    }  
    
    @AfterEach
    void reset() {
    	Media_container.media_collection.clear();
    }
    
    
}