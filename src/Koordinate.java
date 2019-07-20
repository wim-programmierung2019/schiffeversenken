

public class Koordinate {

	private int x;
	private int y;
	
	public Koordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Testet, ob sich eine Position zwischen einer Start- und Endposition befindet.
	 * @param suche
	 * @param start
	 * @param ende
	 * @return
	 */
	public boolean liegtZwischen(Koordinate start, Koordinate ende) {
		return x >= start.x && x <= ende.x 
			&& y >= start.y && y <= ende.y;
	}
	
	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		return object instanceof Koordinate && ((Koordinate) object).getX() == getX() && ((Koordinate) object).getY() == getY(); 
	}
}
