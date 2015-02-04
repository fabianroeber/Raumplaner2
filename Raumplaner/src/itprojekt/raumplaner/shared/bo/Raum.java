package itprojekt.raumplaner.shared.bo;

import java.util.Date;

/**
 * Dieses Model enth&auml;lt alle f&uuml;r einen Raum relevanten Informationen.
 * 
 * @author Fabian
 *
 */
public class Raum extends RaumPlanerBusinessObject {

	private static final long serialVersionUID = 5344416189299259996L;

	/** Bezeichnung */
	private String bezeichnung;
	/** Fassungsverm&ouml;gen */
	private int fassungsvermoegen;

	public Raum() {

	}

	public Raum(int id, Date date, String bezeichnung, int fassungsvermoegen) {
		this.setCreated(date);
		this.setId(id);
		this.bezeichnung = bezeichnung;
		this.fassungsvermoegen = fassungsvermoegen;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getFassungsvermoegen() {
		return fassungsvermoegen;
	}

	public void setFassungsvermoegen(int fassungsvermoegen) {
		this.fassungsvermoegen = fassungsvermoegen;
	}

}
