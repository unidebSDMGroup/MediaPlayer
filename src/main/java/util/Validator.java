package util;

import controller.RenderController;
import main.App;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.Parameters;
import model.VideoType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validator {

    public static boolean startLineCheck(){
    	
        for(MediaType mt :Media_container.media_collection) {
            if (mt instanceof VideoType) {
                System.out.println( mt.clip_end_time);
                System.out.println(Parameters.timeline_region_end_time);
                if (Parameters.timeline_region_start_time - mt.clip_start_time < 0 || Parameters.timeline_region_end_time > mt.clip_end_time) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Render issue");
                    alert.setContentText("The Start line or finish line does not align with all videos");

                    alert.showAndWait(); 
                    return false;
                } else {
                    return true;
                }
            }
        }
        
        return true;

    }
}
