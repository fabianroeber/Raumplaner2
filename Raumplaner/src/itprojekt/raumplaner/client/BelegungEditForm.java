package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Dies ist der Bereich in dem Buchungen bearbeitet oder eingesehen werden
 * können.
 * 
 * @author Fabian, Alex, Simon, Feridun
 *
 */
public class BelegungEditForm extends VerticalPanel {

	public BelegungEditForm(boolean isNew, boolean isEdit, Belegung belegung,
			User user) {

		if (isEdit) {
			// HIER BEARBEITEN

		} else {
			// HIER NUR ANZEIGE
		}
	}
}
