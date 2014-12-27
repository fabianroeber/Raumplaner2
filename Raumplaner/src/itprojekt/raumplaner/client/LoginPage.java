package itprojekt.raumplaner.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPage extends Composite {
	
	DecoratorPanel loginPanel = new DecoratorPanel();
	
	private Label anmelden;
	private Label userName;
	private Label password;
	
//	private Button anmeldeButton;
	
	Image powerIcon;
	Image hdmLogo;
	
	TextBox eingabeBenutzerName;
	PasswordTextBox eingabeBenutzerPasswort;
	
	HauptLayout hl1;
	
	
	public LoginPage()  {
		
		initWidget(loginPanel);
		
//		Zuerst eine FlexTable für LoginPage erstellen
		
		FlexTable flex = new FlexTable();
		flex.setCellSpacing(10);
		flex.setCellPadding(2);
		
		FlexCellFormatter formatter = flex.getFlexCellFormatter();
		
//		Versuch die Fotos von Neubau auf der LoginSeite zu benutzen
		
//		FlexTable flexFotos = new FlexTable();
//		flexFotos.setCellSpacing(10);
//		flexFotos.setCellPadding(2);
//		
//		flexFotos.getElement().setAttribute("id", "flexFotos-id");
//		
//		Image neubau1 = new Image("images/Neubau 1.jpg");
//		neubau1.setPixelSize(200, 200);
//		Image neubau2 = new Image("images/Neubau 2.jpg");
//		neubau2.setPixelSize(200, 200);
//		Image neubau3 = new Image("images/Neubau 3.jpg");
//		neubau3.setPixelSize(200, 200);
//		
//		flexFotos.setWidget(0, 0, neubau1);
//		flexFotos.setWidget(0, 1, neubau2);
//		flexFotos.setWidget(0, 2, neubau3);
		
//		Titel der FlexTable hinzufügen
		
		anmelden = new Label("Bitte melden Sie sich an");
		anmelden.getElement().setAttribute("id", "headerAnmelden-id");
		
		flex.setWidget(0, 0, anmelden);
		formatter.setColSpan(0, 0, 4);
		formatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
//		Username und Passwort zur Eingabe
		
		userName = new Label("Username:");
		eingabeBenutzerName = new TextBox();
		eingabeBenutzerName.setPixelSize(125, 15);
		
		flex.setWidget(1, 0, userName);
		flex.setWidget(1, 1, eingabeBenutzerName);
		
		
		password = new Label("Password:");
		eingabeBenutzerPasswort = new PasswordTextBox();
		eingabeBenutzerPasswort.setPixelSize(125, 15);
		
		flex.setWidget(2, 0, password);
		flex.setWidget(2, 1, eingabeBenutzerPasswort);
		
//		Einen Button hinzufügen
		
//		anmeldeButton = new Button("Anmelden");
		
//		flex.setWidget(2, 4, anmeldeButton);
		
//		Power-Button Icon neben dem anmeldeButton hinzufügen
		
		powerIcon = new Image("images/Power-Button.jpg");
		powerIcon.setPixelSize(30, 30);
		
		powerIcon.addClickHandler(new PowerButtonHandler());
		
		flex.setWidget(2, 3, powerIcon);
		
		Label anmeldeText = new Label("Anmelden");
		flex.setWidget(2, 4, anmeldeText);
		
		
//		Versuch HdM-Logo der LoginSeite zu hinzufügen - VORLÄUFIG
		
		hdmLogo = new Image("images/HdM Logo.png");
		hdmLogo.setPixelSize(150, 70);
		
		flex.setWidget(4, 4, hdmLogo);
		
		
		loginPanel.setWidget(flex);
//		loginPanel.setWidget(flexFotos);
		
		
		
	}
	
//	Versuch, dem PowerIcon einen Handler zuzuweisen

	private class PowerButtonHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
//			Window.alert("Sie sind nun angemeldet");

//			Versuch den Konstruktor von HauptLayout hier aufzurufen, so dass beim Klicken auf anmelden der Raumplaner geöffnet wird
			hl1 = new HauptLayout();
			RootPanel.get().add(hl1);
			
//			Funktioniert gerade noch auf der LoginPage. Dieser Bug wird demnächst behoben
			
		}
		
	}

}
