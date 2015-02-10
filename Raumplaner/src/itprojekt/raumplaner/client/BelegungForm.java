package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

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
 * Diese Klasse stellt die Buchungen f�r den selektierten Raum da.
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
	 * Aktuell ausgew�hler Raum
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

					// �ndert sich die Selektion, wird einen neue
					// BelegungEditForm
					// erstellt und die selektierte Buchung an diese �bergeben.
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						selectedBelegung = selectionModel.getSelectedObject();
						if (selectedBelegung != null) {
							belegungEditPanel.clear();
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

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				belegungEditPanel.clear();
				belegungEditPanel.add(new BelegungEditForm(true, true,
						new Belegung(), actualUser));

			}
		});

		raumplanerAdministration.getAllBelegungByRaum(actualRaum,
				new GetBelegungCallback());
		Label tableHeader = new Label("Belegungen");
		tableHeader.setStyleName("h2");
		belegunAnsichtPanel.add(tableHeader);
		belegunAnsichtPanel.add(belegungTable);
		belegunAnsichtPanel.add(button);
	}

	class GetBelegungCallback implements AsyncCallback<List<Belegung>> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Belegungen konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(List<Belegung> result) {
			belegungTable.setRowData(result);
			logger.log(Level.INFO, "Buchen f�r Raum" + actualRaum
					+ "wurden erfolgreich geladen");
		}

	}

}
