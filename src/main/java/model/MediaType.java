package model;

import javafx.scene.paint.Color;
/**
 * Attributes for media objects used in the time-line
 */
public abstract class MediaType {
	
	
	public String name;
	public Color color;
	public boolean muted;
	public boolean hidden;
	
	public float clip_start_time;
	public float clip_end_time;
	public double duration;
 
	
	
	public MediaType() {
		
	}

}
