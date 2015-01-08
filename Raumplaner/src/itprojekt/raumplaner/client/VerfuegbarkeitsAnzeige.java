package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerfuegbarkeitsAnzeige extends Composite {
	
	private HorizontalPanel verfügbarPanel = new HorizontalPanel();
	
	private int Uhrzeiten;
	
	public VerfuegbarkeitsAnzeige() {
		
		initWidget(this.verfügbarPanel);
		
		verfügbarPanel.setPixelSize(1000, 550);
//		verfügbarPanel.setBorderWidth(1);
		verfügbarPanel.getElement().setAttribute("id", "verfügbarPanel-id");
		
		
//		ein VerticalPanel hinzufügen, der die Uhrzeiten enthält. Somit kann der Panel speziell mit css gestyled werden.
		
		VerticalPanel uhrzeitenPanel = new VerticalPanel();
		
//		ein VerticalPanel für die VerfügbarkeitsListe
		
		VerticalPanel verfügbarListe = new VerticalPanel();
		verfügbarListe.getElement().setAttribute("id", "verfuegbarListe-id");
		
//		Header für die Uhrzeiten hinzufügen
		
		Label raumHeader = new Label("Raum: "/*Hier wird der Raum abgerufen: Getter von Räume in der Klasse Menu*/);
		raumHeader.setPixelSize(65,  20);
		
		uhrzeitenPanel.add(raumHeader);
		verfügbarPanel.add(uhrzeitenPanel);	
		
//		mit .css den uhrzeitenPanel stylen:
		
		uhrzeitenPanel.getElement().setAttribute("id", "uhrZeiten-id");
		
		uhrzeitenPanel.setPixelSize(65, 20);
		
		for (Uhrzeiten = 0; Uhrzeiten < 24; Uhrzeiten++) {
			Label Uhr = new Label(Uhrzeiten + ":00");
			
			uhrzeitenPanel.add(Uhr);
			verfügbarPanel.add(uhrzeitenPanel);
			
		}
		
//		Header für die Verfügbarkeit
		
		Label verfügbarHeader = new Label("Verfügbarkeit");
		verfügbarHeader.setPixelSize(750, 450);
//		verfügbarHeader.getElement().setAttribute("id", "verfügbarListe-id");
		
		Label gebucht = new Label("gebucht"); // Test -- nichts ändern
		
		verfügbarListe.add(verfügbarHeader);
		verfügbarListe.add(gebucht);
		verfügbarPanel.add(verfügbarListe);

	}

}
