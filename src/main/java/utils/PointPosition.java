package utils;

public class PointPosition {
	private int x;
	private int y;
	private float angle;
	
	public PointPosition(int x, int y, float angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "X: " + x +" "+"Y: " + y +" avec un angle "+angle;
	}
}
