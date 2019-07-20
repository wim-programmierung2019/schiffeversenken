

public class SpielerComputer extends Spieler {

	public SpielerComputer(String name) {
		super(name);
	}

	@Override
	public Koordinate schuss() {
		// Idee: Zuf�llig eine Position w�hlen
		return new Koordinate(Helfer.zufallszahl(0, spiel.getFeldGroesse()), 
							  Helfer.zufallszahl(0, spiel.getFeldGroesse()));
	}

	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
		// Mir ist egal, was die Antwort ist
	}
}
