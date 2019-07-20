public class Main {

	protected static final int GROESSE = 10;
	
	public static void main(String[] args) {
		// Spiel anlegen
		SchiffeVersenken spiel = new SchiffeVersenken(GROESSE);
		
		// Zwei Spieler anlegen
		spiel.addSpieler(new SpielerComputer("Spieler 1"));
		spiel.addSpieler(new SpielerComputer2("Spieler 2"));
		
		// Los gehts
		spiel.starten();
	}
}
