package itprojekt.raumplaner.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RaumBuchungsMenu extends Composite {
	
	VerticalPanel raumMenuPanel = new VerticalPanel();
	
	Label raumBuchungHeader;
	
	Anchor neueBuchung;
	Anchor buchungBearbeiten;
	
	NeueBuchung neuBuchen;
	
	BuchungBearbeiten buchungKonstruktor;
	
	public RaumBuchungsMenu() {
		
		initWidget(this.raumMenuPanel);
		
		raumBuchungHeader = new Label("Raumbuchung");
		raumBuchungHeader.setPixelSize(200, 25);
		raumBuchungHeader.getElement().setAttribute("id", "raumBuchungHeader-id");
		
		neueBuchung = new Anchor("Neue Buchung");
		neueBuchung.addClickHandler(new NeueBuchungHandler());
		
		buchungBearbeiten = new Anchor("Buchung bearbeiten");
		buchungBearbeiten.addClickHandler(new BuchungBearbeitenHandler());
		
		raumMenuPanel.add(raumBuchungHeader);
		raumMenuPanel.add(neueBuchung);
		raumMenuPanel.add(buchungBearbeiten);
		
		
		
		
	} private class NeueBuchungHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			neuBuchen = new NeueBuchung();
			
			neueBuchung.setVisible(false);
			
			raumBuchungHeader.setText("Neue Buchung");
			
			raumMenuPanel.add(neuBuchen);
			
		}
		
		
		
	} private class BuchungBearbeitenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			raumBuchungHeader.setText("Buchung bearbeiten");
			
			buchungKonstruktor = new BuchungBearbeiten();
			raumMenuPanel.add(buchungKonstruktor);
			
			neuBuchen.setVisible(false);
			neueBuchung.setVisible(false);
			buchungBearbeiten.setVisible(false);
			
			
			
		}
		
		
		
	}

}
