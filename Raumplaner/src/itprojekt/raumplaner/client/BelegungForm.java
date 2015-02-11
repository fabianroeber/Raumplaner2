package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Diese Klasse stellt die Buchungen für den selektierten Raum da.
 * 
 * @author Fabian
 * @author Alex
 *
 */
public class BelegungForm extends VerticalPanel {

	final BelegungForm belegungForm = this;

	/**
	 * Aktuell eingeloggter User
	 */
	User actualUser = null;

	/**
	 * Selektierte Belegung
	 */
	Belegung selectedBelegung = null;

	/**
	 * Aktuell ausgewähler Raum
	 */
	Raum actualRaum = null;

	VerticalPanel belegunAnsichtPanel = new VerticalPanel();
	VerticalPanel belegungEditPanel = new VerticalPanel();
	HorizontalPanel basePanel = new HorizontalPanel();

	Button button = new Button("Neue Belegung erstellen");

	CellTable<Belegung> belegungTable = new CellTable<Belegung>();
	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	public BelegungForm(Raum selectedRaum, User user) {
		// User und Raum setzen
		actualUser = user;
		actualRaum = selectedRaum;
		this.add(basePanel);
		basePanel.add(belegunAnsichtPanel);
		basePanel.add(belegungEditPanel);

		belegungTable
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Spalte für Thema
		TextColumn<Belegung> themaColumn = new TextColumn<Belegung>() {

			@Override
			public String getValue(Belegung object) {
				return object.getThema();
			}

		};
		belegungTable.addColumn(themaColumn, "Thema");

		// DateFormat zur Anzeige der Zeiten
		final DateTimeFormat format = DateTimeFormat
				.getFormat("EEEE, MMMM dd, yyyy HH:mm");

		// Spalte Startzeit
		Column<Belegung, String> startColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return format.format(object.getStartzeit());
			}
		};
		belegungTable.addColumn(startColumn, "Beginn");
		// Spalte Endzeit
		Column<Belegung, String> endColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return format.format(object.getEndzeit());
			}
		};
		belegungTable.addColumn(endColumn, "Ende");

		// Diese Spalte dient zum Löschen des in der Spalte enthaltenen
		// Belegungsobjekts
		Column<Belegung, String> deleteColumn = new Column<Belegung, String>(
				new ButtonCell()) {

			@Override
			public String getValue(Belegung object) {
				return "Löschen";
			}
		};

		// Der Fieldupdater wird aufgerufen, wenn der Anwender auf den "Löschen"
		// - Button clickt
		deleteColumn.setFieldUpdater(new FieldUpdater<Belegung, String>() {

			@Override
			public void update(int index, Belegung object, String value) {
				if (hasUserEditRights(object)) {
					raumplanerAdministration.deleteBelegung(object,
							new DeleteBelegungCallBack());
				} else {
					Window.alert("Sie haben keine Berechtigung, diese Belegung zu löschen! Dies kann nur durch den Ersteller erfolgen");
				}
			}
		});

		belegungTable.addColumn(deleteColumn);

		// SelectionModel, dass die Selektion einer Belegung erm�glicht
		final SingleSelectionModel<Belegung> selectionModel = new SingleSelectionModel<Belegung>();
		belegungTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					// Ändert sich die Selektion, wird eine neue
					// BelegungEditForm
					// erstellt und die selektierte Buchung an diese übergeben.
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						selectedBelegung = selectionModel.getSelectedObject();
						if (selectedBelegung != null) {
							belegungEditPanel.clear();
							// Prüfung, ob der angemeldete User der Ersteller
							// der Belegung ist. Wenn nicht, wird die
							// Bearbeitungsansicht zur reinen Anzeige, in der
							// nichts bearbeitet werden kann.
							if (hasUserEditRights(selectedBelegung)) {
								// editierbare Edit-Form wird geöffnet
								belegungEditPanel.add(new BelegungEditForm(
										false, true, selectedBelegung,
										actualUser, belegungForm));
							} else {
								// nicht editierbare Edit-Form wird geöffnet
								belegungEditPanel.add(new BelegungEditForm(
										false, false, selectedBelegung,
										actualUser, belegungForm));
							}
						}
					}
				});

		// Neue Belegung erstellen
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				belegungEditPanel.clear();
				belegungEditPanel.add(new BelegungEditForm(true, true,
						new Belegung(), actualUser, belegungForm));

			}
		});
		// Alle Belegunen des Raums laden und der Tabelle zuweisen
		raumplanerAdministration.getAllBelegungByRaum(actualRaum,
				new GetBelegungCallback());

		// Tabellenüberschrift
		Label tableHeader = new Label("Belegungen");
		tableHeader.setStyleName("h2");

		// Widgets der Form hinzufügen
		belegunAnsichtPanel.add(tableHeader);
		belegunAnsichtPanel.add(belegungTable);
		belegunAnsichtPanel.add(button);
	}

	/**
	 * Diese Methode prüft, ob ein Nutzer das Recht hat, eine Belegung zu
	 * editieren.
	 * 
	 * @param Belegung
	 * @return boolean
	 */
	private boolean hasUserEditRights(Belegung belegung) {
		if (actualUser.equals(belegung.getErsteller())) {
			return true;
		} else
			return false;
	}

	/**
	 * Dieses Callback befüllt die Tabelle mit den aus der Datenbank geladenen
	 * Buchungen. Es wird geprüft, ob die Belegungen bereits in der
	 * Vergangenheit liegen. Die vergangenen Belegungen werden herausgefiltert.
	 * 
	 * @author Fabian, Alex
	 * 
	 */
	class GetBelegungCallback implements AsyncCallback<List<Belegung>> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Belegungen konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(List<Belegung> result) {
			List<Belegung> aktuelleBelegunen = new ArrayList<Belegung>();
			// Vergleiche alle Belegunen mit der aktuellen Serverzeit
			for (Belegung belegung : result) {
				if (belegung.getEndzeit().after(
						new Date(System.currentTimeMillis()))) {
					aktuelleBelegunen.add(belegung);
				}
			}
			// Gefilterte Buchungen in die Tabelle laden.
			belegungTable.setRowData(aktuelleBelegunen);
			logger.log(Level.INFO, "Belegungen für Raum" + actualRaum
					+ "wurden erfolgreich geladen");
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
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Callback für das Löschen einer Belegung
	 * 
	 * @author Fabian
	 *
	 */
	class DeleteBelegungCallBack implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Belegungen konnten nicht gelöscht werden.");

		}

		@Override
		public void onSuccess(Void result) {
			raumplanerAdministration.getAllBelegungByRaum(actualRaum,
					new GetBelegungCallback());
		}

	}
}
