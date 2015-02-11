package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Raum;
import itprojekt.raumplaner.shared.bo.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Hier werden alle verfügbaren Räume zur Auswahl angezeigt. Die Räume werden in
 * einem Celltable Widget angezeigt. Zeilen in der Celltable können selektiert
 * werden. Für die selektierte Zeile werden die Belegungen angezeigt (Siehte
 * {@link BelegungForm}). Es können außerdem neue Räume erzeugt werden oder
 * gelöscht werden.
 * 
 * @author Fabian, Alex
 * @author Rathke, Thies
 *
 */
public class RaumForm extends VerticalPanel {

	/**
	 * Referenz auf diese Klasse, um diese an die Editierungs-Form zu übergeben
	 */
	final RaumForm raumform = this;
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
	 * Panel, in das die {@link BelegungForm} eingefügt wird.
	 */
	VerticalPanel belegungsPanel = new VerticalPanel();

	Button button = new Button("Neuen Raum erstellen");
	Logger logger = RpcSettings.getLogger();

	RaumplanerAdministrationAsync raumplanerAdministration = RpcSettings
			.getRaumplanerAdministration();

	/**
	 * Konstruktor für die {@link RaumForm}
	 */
	public RaumForm(User user) {

		// User setzen
		actualUser = user;

		this.add(basePanel);
		basePanel.add(raumPanel);
		basePanel.add(belegungsPanel);
		// Selektion über Tastatur ermöglichen

		// Spalte für die Bezeichnung
		Column<Raum, String> bezeichnungColumn = new Column<Raum, String>(
				new TextCell()) {

			@Override
			public String getValue(Raum object) {
				return object.getBezeichnung();
			}
		};
		raumTable.addColumn(bezeichnungColumn, "Bezeichnung");

		// Spalte für das Fassungsvermögen
		Column<Raum, Number> kapaColumn = new Column<Raum, Number>(
				new NumberCell()) {
			@Override
			public Number getValue(Raum object) {
				return object.getFassungsvermoegen();
			}
		};
		raumTable.addColumn(kapaColumn, "Fassungsvermögen");

		// Spalte, mit Bearbeiten-Button
		Column<Raum, String> editColumn = new Column<Raum, String>(
				new ButtonCell()) {

			@Override
			public String getValue(Raum object) {
				return "Bearbeiten";
			}
		};
		// Der Fieldupdater wird aufgerufen, wenn der Bearbeiten-Butten gedrückt
		// wurde.
		editColumn.setFieldUpdater(new FieldUpdater<Raum, String>() {

			@Override
			public void update(int index, Raum object, String value) {
				belegungsPanel.clear();
				// Das Buchungspanel wird hier auch für das Bearbeiten von
				// Räumen benutzt.
				belegungsPanel.add(new RaumEditForm(object, false, raumform));
			}
		});

		raumTable.addColumn(editColumn);

		// Diese Spalte dient zum Löschen des in der Spalte enthaltenen
		// Raumobjekts
		Column<Raum, String> deleteColumn = new Column<Raum, String>(
				new ButtonCell()) {

			@Override
			public String getValue(Raum object) {
				return "Löschen";
			}
		};

		// Der Fieldupdater wird aufgerufen, wenn der Anwender auf den "Löschen"
		// - Button clickt
		deleteColumn.setFieldUpdater(new FieldUpdater<Raum, String>() {

			@Override
			public void update(int index, Raum object, String value) {
				raumplanerAdministration.deleteRaum(object,
						new DeleteRaumCallback());
			}
		});
		raumTable.addColumn(deleteColumn);

		// SelectionModel, dass die Selektion eines Raums ermöglicht
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
							belegungsPanel.clear();
							belegungsPanel.add(new BelegungForm(selectedRaum,
									actualUser));
							logger.log(Level.INFO,
									"Raum:" + selectedRaum.getBezeichnung()
											+ "wurde ausgewählt");
						}
					}
				});

		// Clickhandler, um einen neuen Raum zu erstellen
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// Neues Raumobjekt an RaumEditForm übergeben
				belegungsPanel.clear();
				belegungsPanel
						.add(new RaumEditForm(new Raum(), true, raumform));
			}
		});

		// Befüllung der Raumtabelle
		updateTableRaumForm();
		raumTable.setStyleName("raumtable");
		Label tableHeader = new Label("Verfügbare Räume");
		tableHeader.setStyleName("h2");
		raumPanel.add(tableHeader);
		raumPanel.add(raumTable);
		raumPanel.add(button);
	}

	/**
	 * Nachdem alle Räume aus der Datenbank geladen wurden, werder sie der
	 * Tabelle zugewiesen.
	 * 
	 * @author Fabian
	 *
	 */
	class GetRaumsCallBack implements AsyncCallback<List<Raum>> {

		@Override
		public void onFailure(Throwable caught) {

			logger.log(Level.WARNING, "Räume konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(List<Raum> result) {
			raumTable.setRowCount(result.size());
			raumTable.setRowData(0, result);
			logger.log(Level.INFO, "Räume wurden erforlgreich geladen");
		}

	}

	/**
	 * Nachdem ein Raum gelöscht wurde, werden die Daten neu in die Tablle
	 * geladen
	 * 
	 * @author Fabian, Alex, Simon
	 *
	 */
	class DeleteRaumCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING, "Raum konnte nicht gelöscht werden");

		}

		@Override
		public void onSuccess(Void result) {
			updateTableRaumForm();
			logger.log(Level.INFO, "Raum wurde erforlgreich gelöscht");

		}

	}

	/**
	 * Methode um Tabelle zu aktualisieren
	 */
	public void updateTableRaumForm() {
		raumplanerAdministration.getAllRaums(new GetRaumsCallBack());
	}

	/**
	 * Methode um das Belegungspanel zurückzusetzen
	 */
	public void clearBelegungPanel() {
		belegungsPanel.clear();
	}

}
