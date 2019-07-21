

import java.util.ArrayList;

public abstract class Spieler {

	// Das Spiel
	protected SchiffeVersenken spiel;
	// Spielername
	protected String name;
	// Spielfeld fuer diesen Spieler
	private Feld feld;

	/**
	 * Konstruktor zum Anlegen eines Spielers
	 * @param name
	 */
	public Spieler(String name) {
		this.name = name;
	}

	/**
	 * Zu implementierende Methode, um eine Koordinate zu bestimmen auf die geschossen wird.
	 * @return
	 */
	public abstract Koordinate schuss();
	
	/**
	 * Mehode, um das Ergebnis des Schusses weiter zu verarbeiten.
	 * @param ergebnisTyp
	 * @param position
	 */
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {}

	/**
	 * Methode, um ein Feld anzulegen und mit den Schiffen zu befuellen.
	 * @return
	 */
	public Feld erzeugeFeld() {
		ArrayList<Schiff> schiffe = new ArrayList<Schiff>();
		
		// 1 Schlachtschiff = 5
		schiffe.add(new Schiff(5));
		// 2 Kreuzer = 4
		schiffe.add(new Schiff(4));
		schiffe.add(new Schiff(4));
		// 3 Zerstoerer = 3
		schiffe.add(new Schiff(3));
		schiffe.add(new Schiff(3));
		schiffe.add(new Schiff(3));
		// 4 Uboote = 2
		schiffe.add(new Schiff(2));
		schiffe.add(new Schiff(2));
		schiffe.add(new Schiff(2));
		schiffe.add(new Schiff(2));
		
		Feld neuesFeld = new Feld(getSpiel().getFeldGroesse());
		
		for(int s = 0; s<schiffe.size(); s++) {
			Schiff schiff = schiffe.get(s);
			// Jeweils maximal 2*n^2 Versuche das Schiff zu positionieren
			for(int i = 0; i < getSpiel().getFeldGroesse() * getSpiel().getFeldGroesse() * 2; i++) {
				// Zufallsorientierung
				Orientierung orientierung = Orientierung.HORIZONTAL;
				if(Math.random() * 2 > 1) {
					orientierung = Orientierung.VERTIKAL;
				}
				
				// Zufallskoordinate
				Koordinate koordinate = new Koordinate(
					(int) (Helfer.zufallszahl(0, getSpiel().getFeldGroesse() - 1)), 
					(int) (Helfer.zufallszahl(0, getSpiel().getFeldGroesse() - 1)));
				
				try{
					neuesFeld.setzeSchiff(schiff, koordinate, orientierung);
					break;
				} catch(Exception e) { }
			}
		}
		
		if(neuesFeld.getSchiffe().size() != 10) throw new RuntimeException("Schiffe konnten nicht gesetzt werden!");
			
		return neuesFeld;
	}
	
	/**
	 * Test, ob der Spieler verloren hat, indem alle Schiffe getroffen sind.
	 * @return
	 */
	public final boolean hatVerloren() {
		return feld.hatVerloren();
	}
	
	public SchiffeVersenken getSpiel() {
		return spiel;
	}

	public void setSpiel(SchiffeVersenken spiel) {
		this.spiel = spiel;
	}

	public Feld getFeld() {
		return feld;
	}

	public void setFeld(Feld feld) {
		this.feld = feld;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
