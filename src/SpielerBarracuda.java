
public class SpielerBarracuda extends Spieler {


	public SpielerBarracuda(String name) {
		super(name);
	}
    
    TrefferTyp[][]Trefferhistorie=new TrefferTyp [10][10];
    Koordinate Versuch= new Koordinate(0,0);

	@Override
	public Koordinate schuss() {
		// Idee: Zuf�llig eine Position w�hlen
		do {
		// Vorher ein Treffer?
		if (Trefferhistorie[Versuch.getX()][Versuch.getY()]==TrefferTyp.TREFFER) {
		// Liegt der Versuch nicht in Zeile und Spalte 0
		      if (Versuch.getX()!=0 && Versuch.getY()!=0){ 
		          Versuch =new Koordinate(Versuch.getX()-1,Versuch.getY());}
		          
		      else    
		          {Versuch =new Koordinate(Versuch.getX()+1,Versuch.getY());}
		}
		else{
		Versuch = new Koordinate(Helfer.zufallszahl(0, spiel.getFeldGroesse()-1), 
							  Helfer.zufallszahl(0, spiel.getFeldGroesse()-1));}}
		while (Trefferhistorie[Versuch.getX()][Versuch.getY()]!=null);
   


		return Versuch;
    

	}

	@Override
	public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {
	Trefferhistorie[position.getX()][position.getY()] = ergebnisTyp;
	//System.out.println(Trefferhistorie[Versuch.getX()][Versuch.getY()]);
		// Mir ist egal, was die Antwort ist
	}
}
