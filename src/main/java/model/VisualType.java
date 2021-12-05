package model;

import javafx.scene.shape.Rectangle;
import util.Vector2;

public abstract class VisualType extends MediaType {
	
	public Vector2 preview_position = new Vector2();
	public float preview_width;
	public float preview_height;
	
	public Rectangle drag_rect;
	
	

}
