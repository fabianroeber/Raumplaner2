package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPage extends Composite {
	
	DecoratorPanel loginPanel = new DecoratorPanel();
	
	private Label anmelden;
	private Label userName;
	private Label password;
	
	TextBox eingabeBenutzerName;
	PasswordTextBox eingabeBenutzerPasswort;
	
	
	public LoginPage()  {
		
		initWidget(loginPanel);
		
//		Zuerst eine FlexTable für LoginPage erstellen
		
		FlexTable flex = new FlexTable();
		flex.setCellSpacing(10);
		flex.setCellPadding(2);
		
		FlexCellFormatter formatter = flex.getFlexCellFormatter();
		
//		Titel der FlexTable hinzufügen
		
		anmelden = new Label("Bitte melden Sie sich an");
		
		flex.setWidget(0, 0, anmelden);
		formatter.setColSpan(0, 0, 4);
		formatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
//		Username und Passwort zur Eingabe
		
		userName = new Label("Username:");
		eingabeBenutzerName = new TextBox();
		
		flex.setWidget(1, 0, userName);
		flex.setWidget(1, 1, eingabeBenutzerName);
		
		
		password = new Label("Password:");
		eingabeBenutzerPasswort = new PasswordTextBox();
		
		flex.setWidget(2, 0, password);
		flex.setWidget(2, 1, eingabeBenutzerPasswort);
		
		
		
		loginPanel.setWidget(flex);
		
		
		
	}

}
