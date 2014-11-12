package itprojekt.raumplaner.shared.model;

import java.util.Date;

/**
 * Dieses Model enth&auml;lt alle f&uuml;r einen Raum relevanten Informationen.
 * 
 * @author Fabian
 *
 */
public class Raum extends RaumPlanerModel {

	private static final long serialVersionUID = 5344416189299259996L;
	
	/** Bezeichnung */
	private String bezeichnung;
	/** Fassungsverm&ouml;gen */
	private Long fassungsvermoegen;

	public Raum(Long id, Date date, String bezeichnung, Long fassungsvermoegen) {
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

	public Long getFassungsvermoegen() {
		return fassungsvermoegen;
	}

	public void setFassungsvermoegen(Long fassungsvermoegen) {
		this.fassungsvermoegen = fassungsvermoegen;
	}

}
