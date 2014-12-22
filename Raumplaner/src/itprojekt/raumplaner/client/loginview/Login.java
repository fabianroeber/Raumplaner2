package itprojekt.raumplaner.client.loginview;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Login extends Composite {
	
	private Label anmeldeBegrüßung;
	private TextBox benutzerName;
	private PasswordTextBox benutzerPasswort;
	private Label nameEingeben;
	private Label pwEingeben;
	private Button anmeldenButton;
	
	public Login() {
		
		VerticalPanel LogPanel = new VerticalPanel();
		
//		Einen Label, um den User zur Anmeldung aufzufordern 
		
		anmeldeBegrüßung = new Label("Bitte melden Sie sich an");
		anmeldeBegrüßung.setVisible(true);
		
//		TextBox und PasswortBox um den User zu ermöglichen, sich anzumelden
		
		nameEingeben = new Label("Bitte geben Sie Ihren Nutzernamen ein:");
		benutzerName = new TextBox();
		
		pwEingeben = new Label("Bitte geben Sie Ihr Passwort ein:");
		benutzerPasswort = new PasswordTextBox();
		
//		Button zum Anmelden: mit einem ClickHandler
		
		anmeldenButton = new Button();
		anmeldenButton.setText("Anmelden");
		anmeldenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent anmeldeEvent) {
				Window.alert("Sie sind nun angemeldet. Herzlichen Glückwunsch");
				
			}
		});
		
		
//		Add-Methoden zum LogPanel:
		
		LogPanel.add(anmeldeBegrüßung);
		LogPanel.add(nameEingeben);
		LogPanel.add(benutzerName);
		LogPanel.add(pwEingeben);
		LogPanel.add(benutzerPasswort);
		LogPanel.add(anmeldenButton);
		
		
		
		
	}

}
