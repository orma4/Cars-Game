package vehicles;

public class Point {
	private int x, y;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {

	}

	/**
	 * 
	 * @return this.x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * 
	 * @return this.y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * 
	 * @return the current point
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	

	
	public boolean equals(Point point) {
	    if(this.getY() == point.getY() && this.getX() == point.getX())
		return true;
	    return false;
	}
	
	
}
