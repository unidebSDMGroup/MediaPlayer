package util;

public class Vector2 {
	
	public double x;
	public double y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vector2() {
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	

}
