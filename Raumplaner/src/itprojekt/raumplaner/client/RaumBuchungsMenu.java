package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RaumBuchungsMenu extends Composite {
	
	VerticalPanel raumMenuPanel = new VerticalPanel();
	
	Anchor neueBuchung;
	Anchor buchungBearbeiten;
	
	public RaumBuchungsMenu() {
		
		initWidget(this.raumMenuPanel);
		
		neueBuchung = new Anchor("Neue Buchung");
		buchungBearbeiten = new Anchor("Buchung bearbeiten");
		
		raumMenuPanel.add(neueBuchung);
		raumMenuPanel.add(buchungBearbeiten);
		
	}

}
