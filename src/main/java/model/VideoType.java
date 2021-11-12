package model;

import com.sun.media.jfxmedia.MediaPlayer;

public class VideoType extends VisualType {

	MediaPlayer video;
	
	public boolean muted;
	public float clip_start_time;
	public float clip_end_time;
	public float duration;
	
	public VideoType() {
	}

}
