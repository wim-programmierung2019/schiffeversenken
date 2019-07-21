public class Main {

	protected static final int GROESSE = 10;
	
	public static void main(String[] args) {
		// Spiel anlegen
		SchiffeVersenken spiel = new SchiffeVersenken(GROESSE);
		
		// Zwei Spieler anlegen
		spiel.addSpieler(new SpielerMitPlan("Christoph"));
		spiel.addSpieler(new SpielerBarracuda("Barracuda"));
		
		// Los gehts
		spiel.starten();
	}
}
