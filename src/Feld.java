
import java.util.ArrayList;

public class Feld {

	protected int groesse;
	
	ArrayList<Schiff> schiffe = new ArrayList<Schiff>();
	
	protected boolean aktiv = true;
	
	public Feld(int groesse) {
		this.groesse = groesse;
	}
	
	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public ArrayList<Schiff> getSchiffe() {
		return schiffe;
	}

	public void setSchiffe(ArrayList<Schiff> schiffe) {
		this.schiffe = schiffe;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

	public TrefferTyp beschiesse(Koordinate pos) {
		if(aktiv == true) {
			aktiv = false; // nur einen Zug erlauben
			
			for(Schiff schiff : schiffe) {
				TrefferTyp ergebnis = schiff.beschiesse(pos);
				switch(ergebnis) {
				case TREFFER:
					return ergebnis;
				case VERSENKT:
					return ergebnis;
				default:
					break;
				}
			}
			return TrefferTyp.WASSER;
		}
		throw new IllegalStateException("Nur ein Schuss pro Zug erlaubt!");
	}
	
	public void aktiviere() {
		aktiv = true;
	}
	
	public boolean hatVerloren() {
		for(Schiff schiff : schiffe) {
			if(!schiff.isVersenkt()) {
				return false;
			}
		}
		return true;
	}
	
	public void setzeSchiff(Schiff schiff, Koordinate start, Orientierung orientierung) {
		// Rechteck berechnen und im Schiff setzen
		if(orientierung == Orientierung.HORIZONTAL) {
			schiff.setzePosition(new Rechteck(start.getX(), start.getY(), start.getX() + schiff.getGroesse() - 1, start.getY()));
		} else {
			schiff.setzePosition(new Rechteck(start.getX(), start.getY(), start.getX(), start.getY() + schiff.getGroesse() - 1));
		}
		
		// Schiff darf nicht ueber den Rand ragen
		Rechteck feldMasse = new Rechteck(0, 0, groesse - 1, groesse - 1);
		if(!schiff.getPosition().vollstaendigUeberdecktVon(feldMasse)) {
			throw new IllegalArgumentException("Schiff passt nicht ins Feld");
		}
		
		// Schiff darf nicht mit anderen Schiffen (inkl. Puffer) kollidieren
		for(Schiff bestehendesSchiff : schiffe) {
			if(bestehendesSchiff.kollidiert(schiff)){
				throw new IllegalArgumentException("Ein anderes Schiff blockiert den Platz");
			}
		}
		
		// Schiff auf Feld speichern
		schiffe.add(schiff);
	}
	
	/**
	 * Zaehle alle Treffer der enthaltenen Schiffe
	 * @return
	 */
	public int getAnzahlTreffer() {
		int anzahl = 0;
		for(Schiff schiff : schiffe) {
			for(boolean getroffen : schiff.getTreffer()) {
				if(getroffen == true) anzahl++;
			}
		}
		return anzahl;
	}
	
	@Override
	public String toString() {
		String asciiFeld = "";
		for(int j = 0; j < groesse; j++) {
			for(int i = 0; i < groesse; i++) {
				String nextPos = ".";
				for(Schiff schiff : schiffe) {
					Koordinate testKoordinate = new Koordinate(i,j);
					if(testKoordinate.liegtZwischen(schiff.getPosition().getStart(), schiff.getPosition().getEnde())) {
						if(schiff.isGetroffen(testKoordinate)){
							nextPos = "x";
						} else {
							nextPos = "s";
						}
						break;
					}
				}
				asciiFeld += nextPos + " ";
			}
			asciiFeld += "\n";
		}
		return asciiFeld;
	}
}
