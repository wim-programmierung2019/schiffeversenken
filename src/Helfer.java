

public class Helfer {

	/**
	 * Gibt eine ganzzahlige Zufallszahl zurueck im Wertebereich zwischen Minimum und Maximum (jeweils inklusive).
	 * @param minimum
	 * @param maximum
	 * @return
	 */
	public static int zufallszahl(int minimum, int maximum) {
		return (int) (Math.random() * (maximum - minimum + 1)) + minimum;
	}
}
