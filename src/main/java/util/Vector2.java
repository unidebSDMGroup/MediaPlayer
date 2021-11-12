package util;

public class Vector2 {
	
	public float x;
	public float y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	

}
