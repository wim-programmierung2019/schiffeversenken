
public class SpielerBarracuda extends Spieler {


	public SpielerBarracuda(String name) {
		super(name);
	}
    
    TrefferTyp[][]Trefferhistorie=new TrefferTyp [10][10];
    

	@Override
	public Koordinate schuss() {
		// Idee: Zuf�llig eine Position w�hlen
		Koordinate Versuch;
		do {Versuch = new Koordinate(Helfer.zufallszahl(0, spiel.getFeldGroesse()), 
							  Helfer.zufallszahl(0, spiel.getFeldGroesse()));}
		while (Trefferhistorie[Versuch.getX()][Versuch.getY()]!=null);
		return Versuch;
	
	}

	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
	Trefferhistorie[position.getX()][position.getY()] = ergebnisTyp;
		// Mir ist egal, was die Antwort ist
	}
}
