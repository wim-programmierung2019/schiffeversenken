
public class SpielerMitPlan extends Spieler {

	protected TrefferTyp[][] historie = new TrefferTyp[10][10];
	
	public SpielerMitPlan(String name) {
		super(name);
	}

	private Koordinate aktuellerVersuch = new Koordinate(0,0); // Dummy für den Start
	
	@Override
	public Koordinate schuss() {
		do {
			if (historie[aktuellerVersuch.getX()][aktuellerVersuch.getY()]==TrefferTyp.TREFFER) {
				// Wenn der letzte ein Treffer war müssen die Ecken drumherum Wasser sein
				if(aktuellerVersuch.getX() > 0 && aktuellerVersuch.getY() > 0) {
					// Links oben muss Wasser sein
					historie[aktuellerVersuch.getX() - 1][aktuellerVersuch.getY() - 1] = TrefferTyp.WASSER;
				}
				if(aktuellerVersuch.getX() < 9 && aktuellerVersuch.getY() < 9) {
					// Rechts unten muss Wasser sein
					historie[aktuellerVersuch.getX() + 1][aktuellerVersuch.getY() + 1] = TrefferTyp.WASSER;
				}
				if(aktuellerVersuch.getX() > 0 && aktuellerVersuch.getY() < 9) {
					// Links unten muss Wasser sein
					historie[aktuellerVersuch.getX() - 1][aktuellerVersuch.getY() + 1] = TrefferTyp.WASSER;
				}
				if(aktuellerVersuch.getX() < 9 && aktuellerVersuch.getY() > 0) {
					// Rechts oben muss Wasser sein
					historie[aktuellerVersuch.getX() + 1][aktuellerVersuch.getY() - 1] = TrefferTyp.WASSER;
				}
			}

			aktuellerVersuch = new Koordinate(
					Helfer.zufallszahl(0, spiel.getFeldGroesse() - 1), 
					Helfer.zufallszahl(0, spiel.getFeldGroesse() - 1));
			
			
		// So lange neue Versuche bis wir ein bisher unentdecktes Feld haben
		} while (historie[aktuellerVersuch.getX()][aktuellerVersuch.getY()]!=null);

		return aktuellerVersuch;
	}
	
	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
		historie[position.getX()][position.getY()] = ergebnisTyp;
	}

}
