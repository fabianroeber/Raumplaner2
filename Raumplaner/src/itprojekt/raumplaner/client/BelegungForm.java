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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
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

		TextColumn<Belegung> themaColumn = new TextColumn<Belegung>() {

			@Override
			public String getValue(Belegung object) {
				return object.getThema();
			}

		};
		belegungTable.addColumn(themaColumn, "Thema");

		// SelectionModel, dass die Selektion einer Belegung erm�glicht
		final SingleSelectionModel<Belegung> selectionModel = new SingleSelectionModel<Belegung>();
		belegungTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					// Ändert sich die Selektion, wird einen neue
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
							if (selectedBelegung.getErsteller().equals(
									actualUser.getEmail())) {
								belegungEditPanel.add(new BelegungEditForm(
										false, true, selectedBelegung,
										actualUser));
							} else {
								belegungEditPanel.add(new BelegungEditForm(
										false, false, selectedBelegung,
										actualUser));
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
						new Belegung(), actualUser));

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

}
