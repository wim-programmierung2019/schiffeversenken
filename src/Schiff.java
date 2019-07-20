public class Schiff {

	// Groesse des Schiffs
	private int groesse;
	// Position des Schiffs
	private Rechteck position;
	// Array der Trefferpunkte
	private boolean[] treffer;
	
	public Schiff(int groesse) {
		this.groesse = groesse;
		treffer = new boolean[groesse];
	}
	
	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}
	
	public Rechteck getPosition() {
		return position;
	}

	public void setPosition(Rechteck position) {
		this.position = position;
	}

	public boolean[] getTreffer() {
		return treffer;
	}

	public void setTreffer(boolean[] treffer) {
		this.treffer = treffer;
	}

	public void setzePosition(Rechteck position) {
		this.position = position;
	}
	
	public TrefferTyp beschiesse(Koordinate versuch) {
		if(versuch.liegtZwischen(position.getStart(), position.getEnde())) {
			// Treffer -> im Trefferarray vermerken 
			treffer[versuch.getX() - position.getStart().getX() + versuch.getY() - position.getStart().getY()] = true;

			// Rueckgabe
			if(isVersenkt()) {
				return TrefferTyp.VERSENKT;
			} else {
				return TrefferTyp.TREFFER;
			}
		}
		return TrefferTyp.WASSER;
	}
	
	public boolean isGetroffen(Koordinate stelle) {
		int offset = stelle.getX() - position.getX1() + stelle.getY() - position.getY1();
		
		// Check, dass die Koordinate innerhalb des Arrays liegt 
		if(offset >= 0 && offset < treffer.length) {
			return treffer[offset];
		}
		return false;
	}
	
	public boolean isVersenkt() {
		return zaehleTrue(treffer) == groesse;
	}
	
	public String toString() {
		// Ergibt eine Schiffsrepraesentation, z.B. "Schiff (xx.x)"
		String ergebnis = "Schiff (";
		for(boolean position:treffer) {
			ergebnis += (position==true) ? "x" : "."; 
		}
		return ergebnis + ")";
	}
	
	/**
	 * Zaehlt das Vorkommen von wahren Werten in einem Array.
	 * @param elemente
	 * @return
	 */
	private int zaehleTrue(boolean[] elemente) {
		int counter = 0;
		for(boolean element : elemente) {
			if(element == true) counter ++;
		}
		return counter;
	}
	
	public boolean kollidiert(Schiff schiff) {
		Rechteck eigeneBoxMitPuffer = new Rechteck(position.getX1() - 1, position.getY1() - 1, position.getX2() + 1, position.getY2() + 1);
		return eigeneBoxMitPuffer.ueberlappt(schiff.position);
	}
}
