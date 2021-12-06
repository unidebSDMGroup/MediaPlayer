package model;

import javafx.scene.shape.Rectangle;
import util.Vector2;

/**
 * Visual type for media objects with attributes from media type class for the preview window
 * Also includes the drag-able rectangle for the preview pane
 *
 */
public abstract class VisualType extends MediaType {
	
	public Vector2 preview_position = new Vector2();
	public float preview_width;
	public float preview_height;
	
	public Rectangle drag_rect;
	
	

}
