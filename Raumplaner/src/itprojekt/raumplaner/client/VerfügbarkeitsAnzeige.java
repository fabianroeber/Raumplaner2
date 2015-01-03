package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerfügbarkeitsAnzeige extends Composite {
	
	private HorizontalPanel verfügbarPanel = new HorizontalPanel();
	
	private int Uhrzeiten;
	
	public VerfügbarkeitsAnzeige() {
		
		initWidget(this.verfügbarPanel);
		
		verfügbarPanel.setPixelSize(1000, 550);
//		verfügbarPanel.setBorderWidth(1);
		verfügbarPanel.getElement().setAttribute("id", "verfügbarPanel-id");
		
		
//		ein VerticalPanel hinzufügen, der die Uhrzeiten enthält. Somit kann der Panel speziell mit css gestyled werden.
		
		VerticalPanel uhrzeitenPanel = new VerticalPanel();
		
//		mit .css den uhrzeitenPanel stylen:
		uhrzeitenPanel.getElement().setAttribute("id", "uhrZeiten-id");
		
		uhrzeitenPanel.setPixelSize(35, 20);
		
		for (Uhrzeiten = 0; Uhrzeiten < 24; Uhrzeiten++) {
			Label Uhr = new Label(Uhrzeiten + ":00");
			
			uhrzeitenPanel.add(Uhr);
			verfügbarPanel.add(uhrzeitenPanel);
			
		}		
		
	}

}
