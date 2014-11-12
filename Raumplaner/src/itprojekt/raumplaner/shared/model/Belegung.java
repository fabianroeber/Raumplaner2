package itprojekt.raumplaner.shared.model;

import java.util.Date;
import java.util.List;

/**
 * Dieses Model enth&auml;t alle relevanten Informationen zu einer Belegung.
 * 
 * @author Fabian
 *
 */
public class Belegung extends RaumPlanerModel {

	private static final long serialVersionUID = -6185510045881825157L;
	/** Das Thema der Buchung/Veranstaltung */
	private String thema;
	/** Startzeit */
	private Date startzeit;
	/** Endzeit */
	private Date endzeit;
	/** Referenz auf einen Raum */
	private Raum raum = null;
	/** Zeigt, ob die Veranstaltung aktiv ist */
	private boolean aktiv;
	/** Liste mit Einladungen an alle zugehörigen User */
	private List<Einladung> einladungen;

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public Date getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(Date startzeit) {
		this.startzeit = startzeit;
	}

	public Date getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(Date endzeit) {
		this.endzeit = endzeit;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

	public List<Einladung> getEinladungen() {
		return einladungen;
	}

	public void setEinladungen(List<Einladung> einladungen) {
		this.einladungen = einladungen;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

}
