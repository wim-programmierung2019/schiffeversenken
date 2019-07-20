

public class SpielerComputer2 extends Spieler {
	
	// Merken in welchem Zug der Spieler gerade ist
	protected int zugNummer = 0;

	public SpielerComputer2(String name) {
		super(name);
	}

	@Override
	public Koordinate schuss() {
		// Idee: Schrittweise jede Koordinate abarbeiten
		int x = (zugNummer) / spiel.getFeldGroesse();
		int y = (zugNummer) % spiel.getFeldGroesse();
		zugNummer++;
		return new Koordinate(x, y);
	}

	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
		// Mir ist egal, was die Antwort ist
	}
	
	@Override
	public Feld erzeugeFeld() {
		// 1 Schlachtschiff = 5
		Schiff schlachtschiff = new Schiff(5);
		// 2 Kreuzer = 4
		Schiff kreuzer1 = new Schiff(4);
		Schiff kreuzer2 = new Schiff(4);
		// 3 Zerstoerer = 3
		Schiff zerstoerer1 = new Schiff(3);
		Schiff zerstoerer2 = new Schiff(3);
		Schiff zerstoerer3 = new Schiff(3);
		// 4 Uboote = 2
		Schiff uboot1 = new Schiff(2);
		Schiff uboot2 = new Schiff(2);
		Schiff uboot3 = new Schiff(2);
		Schiff uboot4 = new Schiff(2);
		
		// Neues Feld anlegen
		Feld neuesFeld = new Feld(getSpiel().getFeldGroesse());

		// Schiffe platzieren
		neuesFeld.setzeSchiff(schlachtschiff, new Koordinate(0, 0), Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(kreuzer1, new Koordinate(2, 0),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(kreuzer2, new Koordinate(2, 5),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(zerstoerer1, new Koordinate(4,0),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(zerstoerer2, new Koordinate(4,4),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(zerstoerer3, new Koordinate(6,0), Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(uboot1, new Koordinate(8,3),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(uboot2, new Koordinate(6,4),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(uboot3, new Koordinate(6,7),  Orientierung.VERTIKAL);
		neuesFeld.setzeSchiff(uboot4, new Koordinate(8,0),  Orientierung.VERTIKAL);

		return neuesFeld;
	}
}
