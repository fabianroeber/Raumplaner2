package itprojekt.raumplaner.shared.bo;

import java.util.Arrays;
import java.util.List;

/**
 * Dieses Enum legt fest, zu welchen Uhrzeiten Räume gebucht werden können.
 * 
 * @author Fabian
 *
 */
public enum Zeitslot {

	NOSLOT(0, 0, "Kein Zeitslot ausgewählt"), FIRST(8, 10, "8:00 - 10:00"), SECOND(
			10, 12, "10:00 - 12:00"), THIRD(13, 15, "13:00 - 15:00"), FOURTH(
			15, 18, "15:00 - 18:00");

	private Zeitslot(int i, int i2, String text) {
		start = i;
		end = i2;
		this.text = text;
	}

	/**
	 * Beschreibung des Zeitraums als Anzeige
	 */
	private String text;

	/**
	 * Startzeit (Stunde)
	 */
	private int start;

	/**
	 * Endzeit (Stunde)
	 */
	private int end;

	/**
	 * Gibt einen Zeitslot zu einer Startzeit zurück
	 * 
	 * @param startzeit
	 * @return {@link Zeitslot}
	 */
	public static Zeitslot getZeitSlotForStart(int start) {
		List<Zeitslot> zeitslots = Arrays.asList(Zeitslot.values());
		for (Zeitslot zeitslot : zeitslots) {
			if (zeitslot.getStart() == start) {
				return zeitslot;
			}
		}
		return Zeitslot.NOSLOT;

	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {

		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
