package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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
	final Raum selectedRaum;

	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumPlanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	/**
	 * Dieser Konstruktor erstellt die Editierungs-Form für einen Raum in
	 * Abhängigkeit der übergebenen Werte.
	 * 
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
		selectedRaum = raum;
		actualRaumform = raumform;

		// Basis-Panel
		VerticalPanel basePanel = new VerticalPanel();

		// Überschrift der Bearbeitungsansicht
		HorizontalPanel headerPanel = new HorizontalPanel();
		Label pageheader = new Label();
		pageheader.setStyleName("h2");
		if (isNewRaum) {
			pageheader.setText("Neuer Raum");
		} else {
			pageheader.setText("Raum bearbeiten");
		}
		headerPanel.add(pageheader);

		// Bereich für Raumbezeichnung
		HorizontalPanel raumbezeichnungPanel = new HorizontalPanel();
		Label bezLabel = new Label("Bezeichnung: ");
		bezLabel.setStyleName("inputlabel");
		raumbezeichnungPanel.add(bezLabel);
		final TextBox bezInput = new TextBox();
		bezInput.setStyleName("inputField");
		raumbezeichnungPanel.setStyleName("raumEditPanel");
		raumbezeichnungPanel.add(bezInput);

		// Bereich für das Fassungsvermögen
		HorizontalPanel raumkapaPanel = new HorizontalPanel();
		Label kapaLabel = new Label("Fassungsvermögen: ");
		kapaLabel.setStyleName("inputlabel");
		raumkapaPanel.add(kapaLabel);
		final IntegerBox kapaInput = new IntegerBox();
		kapaInput.setStyleName("inputField");
		raumkapaPanel.add(kapaInput);

		// Speichern Button + Clickhandler
		HorizontalPanel buttonPanel = new HorizontalPanel();
		Button speichernButton = new Button("Speichern");
		speichernButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Eingaben des Benutzers verwerten. Vorher Prüfen, ob beide
				// Felder ausgefüllt sind
				if (bezInput.getValue() == null || kapaInput.getValue() == null) {
					Window.alert("Bitte geben Sie eine Bezeichnung und ein Fassungsvermögen an!");
				} else {
					selectedRaum.setBezeichnung(bezInput.getValue());
					selectedRaum.setFassungsvermoegen((int) kapaInput
							.getValue());
					if (isNewRaum) {
						raumPlanerAdministration.saveNewRaum(selectedRaum,
								new SaveRaumCallback());
					} else
						raumPlanerAdministration.updateRaum(selectedRaum,
								new SaveRaumCallback());
				}
			}

		});
		// Bei Abbruch wird das Panel zurückgesetzt
		Button abbrechenButton = new Button("Abbrechen");
		abbrechenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				actualRaumform.clearBelegungPanel();
			}
		});
		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);

		// Anordnung der Widgets
		this.add(basePanel);
		basePanel.add(headerPanel);
		basePanel.add(raumbezeichnungPanel);
		basePanel.add(raumkapaPanel);
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
			actualRaumform.clearBelegungPanel();
			logger.log(Level.INFO, "Raum wurde erforlgreich gespeichert");
		}
	}
}
