package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Diese Form dient der Bearbeitung der Eigenschaften eines Raums.
 * 
 * @author Fabian
 *
 */
public class RaumEditForm extends VerticalPanel {

	/**
	 * Kennzeichner, ob es ein neues, nicht persistierter Raum ist
	 */
	final boolean isNewRaum;

	/**
	 * Aktelle Raumform mit der Raumtablle
	 */
	RaumForm actualRaumform;
	/**
	 * Selektierter Raum
	 */
	final Raum actualRaum;

	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumPlanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	/**
	 * @param raum
	 *            - Raum der zu bearbeiten ist
	 * @param raumform
	 *            - Die RaumForm wird mit übergeben, um sie von dieser Stelle
	 *            aus aktualisieren zu können
	 * @param isNew
	 *            - Kennzeichnung, ob der Raum ein neues, nicht persisitiertes
	 *            Objekt ist
	 */
	public RaumEditForm(Raum raum, boolean isNew, RaumForm raumform) {
		// Setzen der aktuellen Referenzen
		isNewRaum = isNew;
		actualRaum = raum;
		actualRaumform = raumform;

		// Panel für Raumbezeichnung
		VerticalPanel basePanel = new VerticalPanel();
		HorizontalPanel raumbezeichnung = new HorizontalPanel();

		HorizontalPanel raumkapa = new HorizontalPanel();

		HorizontalPanel buttonPanel = new HorizontalPanel();
		Button speichern = new Button("Speichern");
		speichern.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (isNewRaum) {
					raumPlanerAdministration.saveNewRaum(actualRaum,
							new SaveRaumCallback());
				} else
					raumPlanerAdministration.updateRaum(actualRaum,
							new SaveRaumCallback());
			}
		});

		Button abbrechen = new Button("Speichern");
		buttonPanel.add(speichern);
		buttonPanel.add(abbrechen);

		this.add(basePanel);
		basePanel.add(raumbezeichnung);
		basePanel.add(raumkapa);
		basePanel.add(buttonPanel);
	}

	/**
	 * Nachdem ein neuer Raum gespeichert wurde, wird die Tabelle neu aus der
	 * Datenbank geladen.
	 * 
	 * @author Fabian, Alex, Simon
	 *
	 */
	class SaveRaumCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING, "Raum konnte nicht gespeichert werden");
		}

		@Override
		public void onSuccess(Void result) {
			actualRaumform.updateTableRaumForm();
			logger.log(Level.INFO, "Raum wurde erforlgreich gespeichert");
		}
	}
}
