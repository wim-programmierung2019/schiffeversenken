public enum TrefferTyp {
	WASSER,
	TREFFER,
	VERSENKT;
	
	@Override
	public String toString() {
		switch(this) {
		case WASSER:
			return "Daneben";
		case TREFFER:
			return "Treffer";
		case VERSENKT:
			return "Versenkt";
		}
		return null;
	}
}
