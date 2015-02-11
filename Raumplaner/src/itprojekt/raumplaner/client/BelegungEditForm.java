package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Dies ist der Bereich in dem Buchungen bearbeitet oder eingesehen werden
 * k�nnen.
 * 
 * @author Fabian, Alex, Simon, Feridun
 *
 */
public class BelegungEditForm extends VerticalPanel {

	/**
	 * Selektierte Belegung
	 */
	Belegung selectedBelegung;

	/**
	 * Aktuelle Belegunsform, von der aus diese Form geöffnet wird
	 */
	BelegungForm actualBelegungForm;

	/**
	 * 
	 * Dieser Konstruktor erstellt eine neue Bearbeitungsansicht für eine
	 * Buchung. Je nach Übergabeparamenter kann diese Ansicht anders aussehen.
	 * 
	 * @param isNew
	 *            - Kennzeichnung, dass es sich um ein neues, noch nicht
	 *            persistiertes Objekt handelt.
	 * @param isEdit
	 *            - Kennzeichnung, ob der User das Recht hat, die Belegung zu
	 *            bearbeiten.
	 * @param belegung
	 *            - Die Belegung als Objekt
	 * @param user
	 *            - Der aktuell eingeloggte User als Objekt
	 */
	public BelegungEditForm(boolean isNew, boolean isEdit, Belegung belegung,
			User user, BelegungForm belegungForm) {

		actualBelegungForm = belegungForm;
		selectedBelegung = belegung;
		
		if (isEdit) {
			// HIER BEARBEITEN

		} else {
			// HIER NUR ANZEIGE
		}
	}
}
