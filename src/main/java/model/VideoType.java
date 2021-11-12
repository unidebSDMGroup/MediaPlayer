package model;

import com.sun.media.jfxmedia.MediaPlayer;

import javafx.scene.media.MediaView;

public class VideoType extends VisualType {

	public MediaView video;
	
	public boolean muted;
	public float clip_start_time;
	public float clip_end_time;
	public float duration;
	
	public VideoType() {
	}

}
