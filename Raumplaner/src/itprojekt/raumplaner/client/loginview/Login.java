package itprojekt.raumplaner.client.loginview;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Login extends Composite {

	/*
	 * VORLÄUFIGE TESTKLASSE FÜR LOGINMANAGER BITTE NICHT LÖSCHEN
	 */

	private Label anmeldeBegruessung;
	private TextBox benutzerName;
	private PasswordTextBox benutzerPasswort;
	private Label nameEingeben;
	private Label pwEingeben;
	private Button anmeldenButton;

	HorizontalPanel LogPanel = new HorizontalPanel();

	public Login() {

		initWidget(this.LogPanel);

		// Einen Label, um den User zur Anmeldung aufzufordern

		anmeldeBegruessung = new HTML("Bitte melden Sie sich an");
		anmeldeBegruessung.setVisible(true);

		// TextBox und PasswortBox um den User zu ermöglichen, sich anzumelden

		nameEingeben = new HTML("<br><br>" + "Username:");
		benutzerName = new TextBox();

		pwEingeben = new HTML("<br><br>" + "Password:");
		benutzerPasswort = new PasswordTextBox();

		// Button zum Anmelden: mit einem ClickHandler

		anmeldenButton = new Button();
		anmeldenButton.setText("Anmelden");
		anmeldenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent anmeldeEvent) {
				Window.alert("Sie sind nun angemeldet. Herzlichen Glückwunsch");

			}
		});

		// Add-Methoden zum LogPanel:

		LogPanel.add(anmeldeBegruessung);
		LogPanel.add(nameEingeben);
		LogPanel.add(benutzerName);
		LogPanel.add(pwEingeben);
		LogPanel.add(benutzerPasswort);
		LogPanel.add(anmeldenButton);

	}

}
