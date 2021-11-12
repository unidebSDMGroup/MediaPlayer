package view;

import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Global_elements {
	
	public static Image mute_image;
	public static Image sound_image;

	
	
	
	public Global_elements() {
		mute_image = new Image(getClass().getResourceAsStream("/assets/mute.png"));
		sound_image = new Image(getClass().getResourceAsStream("/assets/sound.png"));
 
	}

}
