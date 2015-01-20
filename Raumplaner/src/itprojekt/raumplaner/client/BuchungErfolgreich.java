package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BuchungErfolgreich extends Composite {
	
	VerticalPanel erfolgreicheBuchung = new VerticalPanel();
	
	Label rueckmeldungPositiv;
	
	public BuchungErfolgreich() {
		
		initWidget(this.erfolgreicheBuchung);
		
		rueckmeldungPositiv = new Label("Die Buchung ist erfolgreich.");
		
		erfolgreicheBuchung.add(rueckmeldungPositiv);
		
	}

}
