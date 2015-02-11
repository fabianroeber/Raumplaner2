package itprojekt.raumplaner.shared.bo;

import java.sql.Date;

/**
 * Dieses Model enth&auml;lt alle relevanten Informationen zu einer Einladung.
 * 
 * @author Fabian
 *
 */
public class Einladung extends RaumPlanerBusinessObject {

	private static final long serialVersionUID = 8105987472322808899L;

	/** Nutzer */
	User user;
	
	/** Zeigt an, ob die Einladung akzeptiert */
	Boolean akzeptiert;
	
	/** Zeigt, an welche Belegung zugehörig ist */
	Belegung belegung;
	



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getAkzeptiert() {
		return akzeptiert;
	}

	public void setAkzeptiert(Boolean akzeptiert) {
		this.akzeptiert = akzeptiert;
	}

	public void setBelegung(Belegung belegung) {
		this.belegung = belegung;
	}
	
	public Belegung getBelegung() {
		return belegung;
	}

}
