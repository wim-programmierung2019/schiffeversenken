import java.util.ArrayList;

public class SchiffeVersenken {
	
	// Feldgroesse des Spiels
	protected int feldGroesse;
	
	// Liste aller Spieler
	protected ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	
	// Marker, welcher Spieler am Zug ist
	protected boolean spieler1amZug = false;
	
	/**
	 * Konstruktor, um ein Spiel anzulegen
	 * @param feldGroesse
	 */
	public SchiffeVersenken(int feldGroesse) {
		this.feldGroesse = feldGroesse;
	}

	public int getFeldGroesse() {
		return feldGroesse;
	}

	public void setFeldGroesse(int feldGroesse) {
		this.feldGroesse = feldGroesse;
	}
	
	/**
	 * Spieler hinzufuegen
	 * @param spieler
	 */
	public void addSpieler(Spieler spieler) {
		this.spieler.add(spieler);
		spieler.setSpiel(this);
	}
	
	/**
	 * Spiel starten
	 */
	public void starten() {
		// Check, dass genau zwei Spieler existieren
		if(spieler.size() != 2) {
			throw new RuntimeException("Es muessen genau zwei Spieler registriert sein!");
		}
		
		// Felder anlegen
		Spieler spieler1 = spieler.get(0);
		spieler1.setFeld(spieler1.erzeugeFeld());
		Spieler spieler2 = spieler.get(1);
		spieler2.setFeld(spieler2.erzeugeFeld());
		
		// Spielschleife starten
		int zugNummer = -1;
		do {
			// Aktiven Spieler tauschen
			spieler1amZug = !spieler1amZug;
			Spieler aktiverSpieler = spieler2;
			Spieler gegnerSpieler = spieler1;
			
			if(spieler1amZug) {
				aktiverSpieler = spieler1;
				gegnerSpieler = spieler2;
			}
		
			// Spieler macht seinen Zug bzw. nach Treffern so oft bis ins Wasser geschossen wird
			TrefferTyp ergebnis;
			do { 
				zugNummer++;
				
				// Zug vorbereiten
				String ausgabe = "#" + zugNummer + "-- Spieler " + aktiverSpieler.getName() + " am Zug: ";
				gegnerSpieler.getFeld().aktiviere();
				
				// Schuss
				Koordinate versuch = aktiverSpieler.schuss();
				ergebnis = gegnerSpieler.getFeld().beschiesse(versuch);
				aktiverSpieler.ergebnis(ergebnis, versuch);
				
				// Status ausgeben 
				ausgabe += "Beschiesse " + versuch.toString() + " --> " + ergebnis.toString() + "!";
				System.out.println(ausgabe);
			} while(ergebnis != TrefferTyp.WASSER && !spieler1.hatVerloren() && !spieler2.hatVerloren());
			
		} while(!spieler1.hatVerloren() && !spieler2.hatVerloren() && zugNummer < 2 * feldGroesse * feldGroesse);
		
		// Spielergebnis bekannt geben
		if(spieler1.hatVerloren()) {
			System.out.println(spieler1.getName() + " verliert!");
		} else if(spieler2.hatVerloren()) {
			System.out.println(spieler2.getName() + " verliert!");
		} else {
			System.out.println("Unentschieden nach " + zugNummer + " Runden!");
			System.out.println(spieler1.getName() + " hat " + spieler2.getFeld().getAnzahlTreffer() + " Treffer gelandet, " +
					spieler2.getName() + " hat " + spieler1.getFeld().getAnzahlTreffer() + " Treffer gelandet.");
		}
		
		// Feldzustaende am Ende ausgeben
		System.out.println("");
		System.out.println("Endzustand von " + spieler1.getName());
		System.out.println(spieler1.getFeld().toString());
		System.out.println("Endzustand von " + spieler2.getName());
		System.out.println(spieler2.getFeld().toString());
	}

}
