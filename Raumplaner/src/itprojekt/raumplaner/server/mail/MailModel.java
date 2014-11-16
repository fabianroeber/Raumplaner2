package itprojekt.raumplaner.server.mail;

/**
 * Ein Model für eine Mail, in der ein Link zur Best&auml;tigung einer Einladung
 * verschickt wird.
 * 
 * @author Fabian
 *
 */
public class MailModel {

	private String empfaenger;

	private String text;

	private String activationLink;

	public String getEmpfaenger() {
		return empfaenger;
	}

	public void setEmpfaenger(String empfaenger) {
		this.empfaenger = empfaenger;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getActivationLink() {
		return activationLink;
	}

	public void setActivationLink(String activationLink) {
		this.activationLink = activationLink;
	}

}
