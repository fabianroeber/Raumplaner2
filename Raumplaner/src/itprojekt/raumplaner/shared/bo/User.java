package itprojekt.raumplaner.shared.bo;

/**
 * Dieses Model enth&auml;t alle relevanten Informationen zu einem User.
 * 
 * @author Fabian
 *
 */
public class User extends RaumPlanerBusinessObject {
	
	private static final long serialVersionUID = 1917108383838779183L;
	
	/** Vorname */
	private String vorname;
	/** Nachname */
	private String nachname;
	/** Email-Adresse */
	private String email;

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
