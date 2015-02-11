package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

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

		// Basis-Panel
		VerticalPanel basePanel = new VerticalPanel();
		// Header-Panel (dynamisch, je nach Berechtigung oder neues/editiertes
		// Objekt)
		HorizontalPanel headerPanel = new HorizontalPanel();
		Label pageheader = new Label();
		pageheader.setStyleName("h2");
		if (isNew) {
			pageheader.setText("Neue Belegung");
		} else if (!isNew && isEdit) {
			pageheader.setText("Belegung bearbeiten");
		} else {
			pageheader.setText("Details der Belegung");
		}
		headerPanel.add(pageheader);

		// Bereich für Thema der Belegung
		HorizontalPanel themaPanel = new HorizontalPanel();
		Label bezLabel = new Label("Thema: ");
		bezLabel.setStyleName("inputlabel");
		themaPanel.add(bezLabel);
		final TextBox bezInput = new TextBox();
		bezInput.setValue(selectedBelegung.getThema());

		// Bereich für Datum
		HorizontalPanel datumPanel = new HorizontalPanel();

		Label datumLabel = new Label("Datum: ");
		final DateBox datebox = new DateBox();
		// Startzeit der aktuellen Belegung zuweisen
		datebox.setValue(selectedBelegung.getStartzeit());
		// Format der Datumsanzeige deffinieren
		datebox.setFormat(new DateBox.DefaultFormat((DateTimeFormat
				.getFormat("dd.MM.yyyy"))));
		datumPanel.add(datumLabel);
		datumPanel.add(datebox);

		bezInput.setStyleName("inputField");
		themaPanel.setStyleName("raumEditPanel");

		// Wenn der User keine Edit Rechte hat, werden die Attribute einer
		// Belegung nur angezeigt
		if (isEdit) {
			themaPanel.add(bezInput);
		} else {
			// Thema wird nur als Label angezeigt
			bezLabel.setText("Thema: " + selectedBelegung.getThema());
			// Deaktieren der Datumsauswahl
			datebox.setEnabled(false);
		}

		this.add(basePanel);
		basePanel.add(headerPanel);
		basePanel.add(themaPanel);
		basePanel.add(datumPanel);
	}
}
