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
import view.Global_elements;



@ExtendWith(ApplicationExtension.class)
class LayerTest {

	VBox layer_vbox;
	Button b;
	Label l;
	VideoType vt;
	
    @Start
    private void start(Stage stage) { 
        
        PreviewController.static_prev_pane = new Pane();
        new Color_table();
        new Global_elements();
        
 
    	layer_vbox = new VBox(); 
    	
        stage.setScene(new Scene(layer_vbox, 200, 50));

    	 stage.show();
    	
    	File f = new File("vid.mp4");
    	Type_picker tp = new Type_picker();
    	
    	vt = new VideoType();
    	vt.name = "something";
    	 
    	
		try {
			HBox params;params = tp.add_layer_parameters(vt);
	      	layer_vbox.getChildren().add(params); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
   	 b = (Button)((HBox)layer_vbox.getChildren().get(0)).getChildren().get(1);
   	 l = (Label)((HBox)layer_vbox.getChildren().get(0)).getChildren().get(0);

    	 
    }
    
    @Test
    void media_should_mute_onClick(FxRobot robot) {
    	
    	assertEquals(vt.muted,false);

        robot.clickOn(b);

    	assertEquals(vt.muted,true);
    	
    	robot.clickOn(b);

    	assertEquals(vt.muted,false);
    }

    @Test
    void name_test(FxRobot robot) {
    	
    	assertEquals(l.getText(),"something");
    }
 
    
}