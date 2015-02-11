package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.User;
import itprojekt.raumplaner.shared.bo.Zeitslot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
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
	public BelegungEditForm(final boolean isNew, boolean isEdit,
			Belegung belegung, User user, BelegungForm belegungForm) {

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
		datumLabel.setStyleName("inputLabel");
		final DateBox datebox = new DateBox();

		// Startzeit der aktuellen Belegung zuweisen
		datebox.setValue(selectedBelegung.getStartzeit());
		// Format der Datumsanzeige deffinieren
		datebox.setFormat(new DateBox.DefaultFormat((DateTimeFormat
				.getFormat("dd.MM.yyyy"))));
		// Heutiges Datum ermitteln. Hier müssen veraltete Methoden der
		// Date-Klasse verwendet werden, da die Calendar Klasse von GWT nicht
		// unterstützt wird.
		final Date today = new Date(System.currentTimeMillis());

		// Auswahl der Zeitslots
		HorizontalPanel zeitslotPanel = new HorizontalPanel();
		final Label zeitslotLabel = new Label("Zeitslot: ");

		zeitslotPanel.add(zeitslotLabel);

		// Listbox für Zeitslotauswahl
		final ListBox zeitslotBox = new ListBox();
		List<Zeitslot> zeitslots = new ArrayList<Zeitslot>();
		zeitslots = Arrays.asList(Zeitslot.values());
		for (Zeitslot zeitslot : zeitslots) {
			zeitslotBox.addItem(zeitslot.getText());
		}

		// Hier wird mit Änderungen am Wert des Datums umgegangen
		datebox.getDatePicker().addValueChangeHandler(
				new ValueChangeHandler<Date>() {

					@Override
					public void onValueChange(ValueChangeEvent<Date> event) {
						// Vergangenheitsbuchungen verhindern
						if (CalendarUtil.isSameDate(event.getValue(), today)
								|| !event.getValue().before(today)) {
						} else {
							Window.alert("Räume können nicht in der Vergangenheit gebucht werden!");
							if (isNew) {
								datebox.setValue(today);
							} else {
								datebox.setValue(selectedBelegung
										.getStartzeit());
							}

						}

					}
				});
		datumPanel.add(datumLabel);
		datumPanel.add(datebox);

		bezInput.setStyleName("inputField");
		themaPanel.setStyleName("raumEditPanel");

		// DateFormat zur Anzeige der Zeiten
		final DateTimeFormat format = DateTimeFormat.getFormat("HH:mm");

		// Wenn der User keine Edit Rechte hat, werden die Attribute einer
		// Belegung nur angezeigt
		if (isEdit) {
			themaPanel.add(bezInput);
			zeitslotPanel.add(zeitslotBox);

		} else {
			// Thema wird nur als Label angezeigt
			bezLabel.setText("Thema: " + selectedBelegung.getThema());
			// Deaktieren der Datumsauswahl
			datebox.setEnabled(false);
			// Anzeigen des Zeitslots
			zeitslotLabel.setText("Zeitslot: "
					+ format.format(selectedBelegung.getStartzeit()) + " bis "
					+ format.format(selectedBelegung.getEndzeit()));
		}
		// Anordnung der Panels
		this.add(basePanel);
		basePanel.add(headerPanel);
		basePanel.add(themaPanel);
		basePanel.add(datumPanel);
		basePanel.add(zeitslotPanel);
	}
}
