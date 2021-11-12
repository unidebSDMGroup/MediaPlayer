package controller;

import main.App;
import model.ImageType;
import model.MediaType;
import model.Media_container;
import model.VideoType;

public class RenderController {
	
	
	
	
	
	public static void init_render() {
		
		App.previewStage.hide();
		App.timelineStage.hide();
        App.renderStage.show();
        
		for(MediaType mt :Media_container.media_collection) {
			//an image
			if (mt instanceof ImageType) {
				
				System.out.println("image");
			}
			//a video
			else if(mt instanceof VideoType) {
				System.out.println("video");
			}
			// an audio
			else {
				System.out.println("audio");
			}
		}
	}
	
}
