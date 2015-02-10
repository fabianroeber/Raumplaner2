package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.builder.shared.TableSectionBuilder;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.HeaderBuilder;
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
 * Hier werden alle verf�gbaren R�ume zur Auswahl angezeigt. Die R�ume werden in
 * einem Celltable Widget angezeigt. Zeilen in der Celltable k�nnen selektiert
 * werden. F�r die selektierte Zeile werden die Belegungen angezeigt (Siehte
 * {@link BelegungForm}). Es k�nnen au�erdem neue R�ume erzeugt werden oder
 * gel�scht werden.
 * 
 * @author Fabian, Alex
 * @author Rathke, Thies
 *
 */
public class RaumForm extends VerticalPanel {

	/**
	 * Aktuell angemeldeter User
	 */
	User actualUser = null;
	/**
	 * Selektierter Raum
	 */
	Raum selectedRaum = null;

	/**
	 * Tabelle mit {@link Raum} Objekten
	 */
	CellTable<Raum> raumTable = new CellTable<Raum>();
	/**
	 * Basispanel
	 */
	HorizontalPanel basePanel = new HorizontalPanel();
	/**
	 * Panel f�r die Raumtabelle
	 */
	VerticalPanel raumPanel = new VerticalPanel();
	/**
	 * Panel, in das die {@link BelegungForm} eingef�gt wird.
	 */
	VerticalPanel buchungsPanel = new VerticalPanel();

	Button button = new Button("Neuen Raum erstellen");
	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	/**
	 * Konstruktor f�r die {@link RaumForm}
	 */
	public RaumForm(User user) {
		// User setzen
		actualUser = user;

		this.add(basePanel);
		basePanel.add(raumPanel);
		basePanel.add(buchungsPanel);
		// Selektion über Tastatur ermöglichen
		raumTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Spalte für die Bezeichnung
		TextColumn<Raum> bezeichnungColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {
				return object.getBezeichnung();
			}

		};
		raumTable.addColumn(bezeichnungColumn, "Bezeichnung");

		// Spalte für das Fassungsverm�gen
		TextColumn<Raum> kapaColumn = new TextColumn<Raum>() {

			@Override
			public String getValue(Raum object) {

				return Integer.toString(object.getFassungsvermoegen());
			}
		};
		raumTable.addColumn(kapaColumn, "Fassungsvermögen");

		// SelectionModel, dass die Selektion eines Raums erm�glicht
		final SingleSelectionModel<Raum> selectionModel = new SingleSelectionModel<Raum>();
		raumTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					// Ändert sich die Selektion, wird einen neue Belegungsform
					// erstellt und der selektierte Raum an diese übergeben.
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						selectedRaum = selectionModel.getSelectedObject();
						if (selectedRaum != null) {
							buchungsPanel.clear();
							buchungsPanel.add(new BelegungForm(selectedRaum,
									actualUser));
							logger.log(Level.INFO,
									"Raum:" + selectedRaum.getBezeichnung()
											+ "wurde ausgew�hlt");
						}
					}
				});

		// Befüllung der Raumtabelle
		raumplanerAdministration.getAllRaums(new GetRaumsCallBack());
		raumTable.setStyleName("raumtable");
		Label tableHeader = new Label("Verfübare Räume");
		tableHeader.setStyleName("h2");
		raumPanel.add(tableHeader);
		raumPanel.add(raumTable);
		raumPanel.add(button);
	}

	class GetRaumsCallBack implements AsyncCallback<List<Raum>> {

		@Override
		public void onFailure(Throwable caught) {

			logger.log(Level.WARNING, "Räume konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(List<Raum> result) {
			raumTable.setRowCount(result.size());
			raumTable.setRowData(0, result);
		}

	}

}
