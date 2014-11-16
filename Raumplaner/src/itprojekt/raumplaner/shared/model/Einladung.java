package itprojekt.raumplaner.shared.model;

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

}
