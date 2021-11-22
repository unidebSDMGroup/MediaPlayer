package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Color_table {

	//public static ArrayList<Color> colors;
	public static Map<Color,Boolean> colors;

	public Color_table() { 
		
		colors = new HashMap<Color,Boolean>();
		colors.put(Color.PURPLE, false);
		colors.put(Color.RED, false);
		colors.put(Color.GREEN, false);
		colors.put(Color.MAGENTA, false);
		colors.put(Color.BROWN, false);
		colors.put(Color.BEIGE, false);

	}
	
}
