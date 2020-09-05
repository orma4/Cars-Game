package vehicles;

public class Location {
	private Point point;
	private Orientation orientation;

	/**
	 * 
	 * @param point
	 * @param orientation
	 */
	public Location(Point point, Orientation orientation) {
		this.point = point;
		this.orientation = orientation;
	}

	public Location() {
		this.point = new Point(0, 0);
		this.orientation = Orientation.East;
	}

	/**
	 * 
	 * @return this.point
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * 
	 * @param point
	 * @return true
	 */
	public boolean setPoint(Point point) {
		this.point = point;
		return true;
	}

	/**
	 * 
	 * @return orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * 
	 * @param orientation
	 * @return true
	 */
	public boolean setOrientation(Orientation orientation) {
		this.orientation = orientation;
		return true;
	}

	@Override
	public String toString() {
		return point + ", orientation=" + orientation;
	}

}
