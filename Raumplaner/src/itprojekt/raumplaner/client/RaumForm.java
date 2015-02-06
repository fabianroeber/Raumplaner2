package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Hier werden alle verfügbaren Räume zur Auswahl angezeigt.
 * 
 * @author Fabian
 *
 */
public class RaumForm extends VerticalPanel {

	List<Raum> raums = new ArrayList<>();

	Raum selectedRaum = null;
	
	 
	
	public RaumForm() {
		
	}

}
