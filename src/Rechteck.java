public class Rechteck {

	private int x1; 
	private int x2; 
	private int y1; 
	private int y2;
	
	public Rechteck(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Rechteck(Koordinate obenLinks, Koordinate untenRechts) {
		this.x1 = obenLinks.getX();
		this.y1 = obenLinks.getY();
		this.x2 = untenRechts.getX();
		this.y2 = untenRechts.getY();
	}
	
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public boolean ueberlappt(Rechteck eck) {
		return getX1() <= eck.getX2() && getX2() >= eck.getX1() && getY1() <= eck.getY2() && getY2() >= eck.getY1();
	}
	
	public boolean vollstaendigUeberdecktVon(Rechteck anderesRechteck) {
		return anderesRechteck.x1 <= x1 && anderesRechteck.x2 >= x2 && anderesRechteck.y1 <= y1 && anderesRechteck.y2 >= y2;
	}
	
	public Koordinate getStart() {
		return new Koordinate(x1, y1);
	}
	
	public Koordinate getEnde() {
		return new Koordinate(x2, y2);
	}
	
	@Override
	public String toString() {
		return "(" + x1 + "," + y1 + ")--(" + x2 + "," + y2 + ")";
	}
}
