package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;
import itprojekt.raumplaner.shared.bo.Zeitslot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
	 * Aktueller Raum
	 */
	final Raum actualRaum;

	/**
	 * Listbox für Zeitslotauswahl
	 */
	final ListBox zeitslotBox = new ListBox();

	/**
	 * Hier werden alle Möglichen Werte des Enums Zeitslot der Listbox zu //
	 * Verfügung gestellt.
	 */
	final List<Zeitslot> zeitslots = Arrays.asList(Zeitslot.values());

	Logger logger = RpcSettings.getLogger();
	RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
			.getRaumplanerAdministration();

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
	 * 
	 * @param belegungForm
	 *            - Zum Zugriff auf die untergeordnete BelegungForm, um z.B. die
	 *            Buchungstabelle zu aktualisieren
	 * 
	 * @param raum
	 *            - Der aktuelle Raum
	 */
	public BelegungEditForm(final boolean isNew, boolean isEdit,
			Belegung belegung, final User user, BelegungForm belegungForm,
			Raum raum) {

		actualBelegungForm = belegungForm;
		selectedBelegung = belegung;
		actualRaum = raum;

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

		for (Zeitslot zeitslot : zeitslots) {
			zeitslotBox.addItem(zeitslot.getText());
		}
		// Suchen des aktuellen Zeitslots, wenn keiner ausgwählt wurde, dann
		// wird der Dummy Slot eingefügt
		int index = zeitslots
				.indexOf(Zeitslot.getZeitSlotForStart(selectedBelegung
						.getStartzeit().getHours()));
		zeitslotBox.setSelectedIndex(index);

		zeitslotBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Zeitslot zeitslot = zeitslots.get(zeitslotBox
						.getSelectedIndex());
				// RPC Aufruf, der den Raum nach bereits belegten Buchungen
				// durchsucht
				raumplanerAdministration.isRaumBelegt(actualRaum,
						selectedBelegung.getStartzeit(), zeitslot.getStart(),
						zeitslot.getEnd(), new IsRaumBelegtCallback());

			}
		});

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

		// User zur Belegung einladen
		HorizontalPanel userPanel = new HorizontalPanel();

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

		// Buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();
		Button speichernButton = new Button("Speichern");
		Button abbrechenButton = new Button("Abbrechen");
		speichernButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Der Dummmy Zeitslot darf nicht ausgewählt sein (=kein
				// Zeitslot ausgewählt)
				if (zeitslotBox.getSelectedIndex() == 0) {
					Window.alert("Bitte legen Sie einen Zeitslot fest.");

				} else {
					// Zeiten setzen
					selectedBelegung.setStartzeit(datebox.getValue());
					selectedBelegung.setEndzeit(datebox.getValue());
					selectedBelegung.getStartzeit().setHours(
							zeitslots.get(zeitslotBox.getSelectedIndex())
									.getStart());
					selectedBelegung.getStartzeit().setHours(
							zeitslots.get(zeitslotBox.getSelectedIndex())
									.getEnd());

					selectedBelegung.setThema(bezInput.getValue());
					selectedBelegung.setErsteller(user);
					selectedBelegung.setRaum(actualRaum);

					// Neue Belegung speichern
					if (isNew) {
						raumplanerAdministration.saveNewBelegung(
								selectedBelegung, new SaveBelegungCallBack());
					}// Belegung editieren
					else {
						raumplanerAdministration.updateBelegung(
								selectedBelegung, new SaveBelegungCallBack());
					}
				}

			}
		});
		// Bei Abbruch wird das Panel resettet!
		abbrechenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				actualBelegungForm.clearBelegungsEditPanel();
			}
		});

		if (isEdit) {
			buttonPanel.add(speichernButton);
		}
		buttonPanel.add(abbrechenButton);

		// Anordnung der Panels
		this.add(basePanel);
		basePanel.add(headerPanel);
		basePanel.add(themaPanel);
		basePanel.add(datumPanel);
		basePanel.add(zeitslotPanel);
		basePanel.add(userPanel);
		basePanel.add(buttonPanel);
	}

	/**
	 * Nachdem geprüft wurde, ob ein Raum bereits belegt ist, wird hier mit dem
	 * Ergebnis umgegangen.
	 * 
	 * @author Fabian
	 *
	 */
	class IsRaumBelegtCallback implements AsyncCallback<Boolean> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Fehler beim Überprüfen der Raumbelegungen");

		}

		@Override
		public void onSuccess(Boolean result) {
			if (result == true) {
				if (Zeitslot.getZeitSlotForStart(
						selectedBelegung.getStartzeit().getHours()).getStart() != zeitslots
						.get(zeitslotBox.getSelectedIndex()).getStart()) {
					Window.alert("Der Raum ist zu diesem Zeitpunkt bereits belegt");
					// Im index 0 befindet sich ein Dummy Zeitslot, mit dem die
					// Belegung nicht gespeichert werden kann
					zeitslotBox.setSelectedIndex(0);
				}

			}

		}
	}

	/**
	 * Callback für das Speichern einer Belegung
	 * 
	 * @author Fabian
	 *
	 */
	class SaveBelegungCallBack implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING, "Fehler beim Speichern der Raumbelegung");

		}

		@Override
		public void onSuccess(Void result) {
			actualBelegungForm.updateBelegungen();
			actualBelegungForm.clearBelegungsEditPanel();
		}

	}
}
