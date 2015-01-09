package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerfuegbarkeitsAnzeige extends Composite {
	
	private HorizontalPanel verfuegbarPanel = new HorizontalPanel();
	
	private int Uhrzeiten;
	
	public VerfuegbarkeitsAnzeige() {
		
		initWidget(this.verfuegbarPanel);
		
		verfuegbarPanel.setPixelSize(1000, 550);
//		verfügbarPanel.setBorderWidth(1);
		verfuegbarPanel.getElement().setAttribute("id", "verfügbarPanel-id");
		
		
//		ein VerticalPanel hinzufügen, der die Uhrzeiten enthält. Somit kann der Panel speziell mit css gestyled werden.
		
		VerticalPanel uhrzeitenPanel = new VerticalPanel();
		
//		ein VerticalPanel für die VerfügbarkeitsListe
		
		VerticalPanel verfuegbarListe = new VerticalPanel();
		verfuegbarListe.getElement().setAttribute("id", "verfuegbarListe-id");
		
//		Header für die Uhrzeiten hinzufügen
		
		Label raumHeader = new Label("Raum: "/*Hier wird der Raum abgerufen: Getter von Räume in der Klasse Menu*/);
		raumHeader.setPixelSize(65,  30);
		raumHeader.getElement().setAttribute("id", "raumHeader-id");
		
		uhrzeitenPanel.add(raumHeader);
		verfuegbarPanel.add(uhrzeitenPanel);	
		
//		mit .css den uhrzeitenPanel stylen:
		
		uhrzeitenPanel.getElement().setAttribute("id", "uhrZeiten-id");
		
		uhrzeitenPanel.setPixelSize(65, 20);
		
		for (Uhrzeiten = 0; Uhrzeiten < 24; Uhrzeiten++) {
			Label Uhr = new Label(Uhrzeiten + ":00");
			
			uhrzeitenPanel.add(Uhr);
			verfuegbarPanel.add(uhrzeitenPanel);
			
		}
		
		
//		Header für die Verfügbarkeit
		
		Label verfuegbarHeader = new Label("Verfügbarkeit (das Ausgewählte Datum)");
		verfuegbarHeader.setPixelSize(750, 30);
		verfuegbarHeader.getElement().setAttribute("id", "verfuegbarHeader-id");
		
		verfuegbarListe.add(verfuegbarHeader);
		
//		Für gebuchte Uhrzeiten
		
		Label gebucht = new Label("gebucht"); // Test -- nichts ändern
		gebucht.getElement().setAttribute("id", "geBucht-id");
		
		verfuegbarListe.add(gebucht);
		
//		Für freie / buchbare Uhrzeiten (bis 7:00 Uhr und ab 20:00 Uhr dunkel)
		
		Label freiDunkel = new Label("frei");
		
		verfuegbarListe.add(freiDunkel);
		
//		Für freue / buchbare Uhrzeiten (ab 7:00 Uhr bis 20:00 Uhr hell)
		
		Label freiNormal = new Label("frei");
		freiNormal.getElement().setAttribute("id", "freiNormal-id");
		
		verfuegbarListe.add(freiNormal);
		
		verfuegbarPanel.add(verfuegbarListe);

	}

}
