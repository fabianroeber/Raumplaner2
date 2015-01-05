package itprojekt.raumplaner.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class HauptLayout extends Composite {

	private DockPanel dockPanel;
	Label begruessung;
	private Label copyRight;

	// private Button raumAuswählen;
	// private Button datumAuswählen;
	// private Button raumVerfügbarkeit;

	Image wiLogo;

	HorizontalPanel vp = new HorizontalPanel();

	String copyRightZeichen = "\u00a9";
	Label mitteVorlaeufig;

	public HauptLayout() {

		initWidget(this.vp);
		vp.getElement().setAttribute("id", "hauptLayoutVp-id"); // Den
																// Raumplaner in
																// der .css
																// einzeln
																// stylen

		Menu men = new Menu();
		VerfuegbarkeitsAnzeige verfuegbarkeiten = new VerfuegbarkeitsAnzeige();

		dockPanel = new DockPanel();
		dockPanel.setSpacing(20);
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

		// Bild hinzufügen:

		wiLogo = new Image();
		wiLogo.setUrl("https://pbs.twimg.com/profile_images/2032975755/wi7_Logo_rgb_t.png");
		wiLogo.setPixelSize(200, 200);
		wiLogo.setVisible(true);

		// Label hinzufügen

		begruessung = new Label("Raumreservierungssystem");
		begruessung.setPixelSize(500, 20);
		DOM.setElementAttribute(begruessung.getElement(), "id", "RRS-id");

		copyRight = new Label(copyRightZeichen + " Hochschule der Medien");
		copyRight.setPixelSize(500, 30);

		mitteVorlaeufig = new Label(
				"Dieser Bereich wird noch aufgebaut... ( Die Verfügbarkeitsanzeige kommt hier rein)");
		mitteVorlaeufig.setPixelSize(750, 500);

		// Buttons hinzufügen
		//
		// raumAuswählen = new Button("Raum auswählen");
		// raumAuswählen.setPixelSize(200, 30);
		// datumAuswählen = new Button("Datum auswählen");
		// datumAuswählen.setPixelSize(200, 30);
		// raumVerfügbarkeit = new Button("Raumverfügbarkeit überprüfen");
		// raumVerfügbarkeit.setPixelSize(200, 30);

		// Dem DockPanel alle Widgets hinzufügen

		dockPanel.add(begruessung, DockPanel.NORTH);
		dockPanel.add(copyRight, DockPanel.SOUTH);
		dockPanel.add(men, DockPanel.WEST);
		// dockPanel.add(datumAuswählen, DockPanel.WEST);
		// dockPanel.add(raumVerfügbarkeit, DockPanel.WEST);
		dockPanel.add(wiLogo, DockPanel.EAST);
		dockPanel.add(verfuegbarkeiten, DockPanel.CENTER);

		vp.add(dockPanel);

	}

}