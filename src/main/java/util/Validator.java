package util;

import controller.RenderController;
import main.App;
import model.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validator {
    private static boolean lineIntersecting;

    public static void startLineCheck(){
        for(MediaType mt :Media_container.media_collection) {
            if (mt instanceof VideoType || mt instanceof AudioType) {

                if (Parameters.timeline_region_start_time - mt.clip_start_time < 0 || Parameters.timeline_region_end_time > mt.clip_end_time) {

                    lineIntersecting = true;
                } else {
                    lineIntersecting = false;

                }
            }
        }
        if(lineIntersecting == true){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Render issue");
            alert.setContentText("The Start line or finish line does not align with all videos");

            alert.showAndWait();
        }else{
            RenderController.init_render();
        }

    }



    }

