package itprojekt.raumplaner.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class HauptLayout extends Composite {
	
	private DockPanel dockPanel;
	Label begrüßung;
	private Label copyRight;
	
//	private Button raumAuswählen;
//	private Button datumAuswählen;
//	private Button raumVerfügbarkeit;
	
	Image wiLogo;
	
	HorizontalPanel vp = new HorizontalPanel();
	
	String copyRightZeichen = "\u00a9";
	Label mitteVorläufig;
	
	
	
	
	
	public HauptLayout() {
		
		initWidget(this.vp);
		
		Menu men = new Menu();
		
		dockPanel = new DockPanel();
		dockPanel.setSpacing(20);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		
		// Bild hinzufügen:
		
		wiLogo = new Image();
		wiLogo.setUrl("https://pbs.twimg.com/profile_images/2032975755/wi7_Logo_rgb_t.png");
		wiLogo.setPixelSize(200, 200);
		wiLogo.setVisible(true);
		
		
		// Label hinzufügen
		
		begrüßung = new Label("Raumreservierungssystem");
		begrüßung.setPixelSize(500, 20);
		DOM.setElementAttribute(begrüßung.getElement(), "id", "RRS-id");
		
		copyRight = new Label(copyRightZeichen +" Hochschule der Medien");
		copyRight.setPixelSize(500, 30);
		
		mitteVorläufig = new Label("Dieser Bereich wird noch aufgebaut... ( Die Verfügbarkeitsanzeige kommt hier rein)");
		mitteVorläufig.setPixelSize(750, 500);
		
		// Buttons hinzufügen
//		
//		raumAuswählen = new Button("Raum auswählen");
//		raumAuswählen.setPixelSize(200, 30);
//		datumAuswählen = new Button("Datum auswählen");
//		datumAuswählen.setPixelSize(200, 30);
//		raumVerfügbarkeit = new Button("Raumverfügbarkeit überprüfen");
//		raumVerfügbarkeit.setPixelSize(200, 30);
		

		
		// Dem DockPanel alle Widgets hinzufügen
		
		dockPanel.add(begrüßung, DockPanel.NORTH);
		dockPanel.add(copyRight, DockPanel.SOUTH);
		dockPanel.add(men, DockPanel.WEST);
//		dockPanel.add(datumAuswählen, DockPanel.WEST);
//		dockPanel.add(raumVerfügbarkeit, DockPanel.WEST);
		dockPanel.add(wiLogo, DockPanel.EAST);
		dockPanel.add(mitteVorläufig, DockPanel.CENTER);
		
		vp.add(dockPanel);
				
	}
	
}