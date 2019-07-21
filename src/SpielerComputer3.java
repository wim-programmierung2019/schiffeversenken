

public class SpielerComputer3 extends Spieler {
	
	// Merken in welchem Zug der Spieler gerade ist
	protected int zugNummer = 0;
	protected int koordinateX = 0;
	protected int koordinateY = 0;
	protected boolean getroffen = false;
	protected int speicherX = 0;
	protected int speicherY = 0;

	public SpielerComputer3(String name) {
		super(name);
	}

	@Override
	public Koordinate schuss() {
		// Idee: Schrittweise jede Koordinate abarbeiten
		
		int x = koordinateX;
		int y = koordinateY;
		zugNummer++;
		return new Koordinate(x, y);
	}

	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
        TrefferTyp treffer = TrefferTyp.TREFFER;
        TrefferTyp wasser = TrefferTyp.WASSER;
        TrefferTyp versenkt = TrefferTyp.VERSENKT;
        if(ergebnisTyp == treffer){
            speicherX = koordinateX;
            speicherY = koordinateY;
            treffer(position.getX(), position.getY());
        }
        if(ergebnisTyp == wasser){
            if(position.getY() == 9 || position.getX() == 9)
            {
                if(position.getY() == 9 && position.getX() == 9){
                    koordinateY = 0;
                    koordinateX = 2;
                    }
                if(position.getY() == 9){
                    koordinateY = 0;                   
                }
                if(position.getX() == 9){
                    koordinateX = 0;
                }
                koordinateY++;
                koordinateX++;
            }
        }

        if(ergebnisTyp == versenkt){
            getroffen = false;
            returnTaktik();
            //rückkehr
        }
        //Taktik umstellen
		// Mir ist egal, was die Antwort ist
		//hier muss geklärt werden ob getroffen
		//dann anpassung der Taktik
		//gleichzeitig abspeichern des wertes
		//ggf. einen boolean setzen o.ä.
	}
	
	public void treffer(int x, int y){
	    getroffen = true;
	    koordinateY++;
	}
	
	public void returnTaktik(){
	    koordinateX = speicherX;
	    koordinateY = speicherY;
	    if(koordinateY == 9 || koordinateX == 9)
            {
                if(koordinateY == 9 && koordinateX == 9){
                    koordinateY = 0;
                    koordinateX = 2;
                    }
                if(koordinateY == 9){
                    koordinateY = 0;                   
                }
                if(koordinateX == 9){
                    koordinateX = 0;
                }
                koordinateY++;
                koordinateX++;
            }
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
