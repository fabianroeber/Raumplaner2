package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * Hier werden alle Belegungen angezeigt. Die Belegungen werden in einem
 * Celltable Widget angezeigt. Der User kann hier alle Buchungen einsehen.
 * 
 * 
 * @author Fabian
 *
 * 
 */
public class BelegungsplanForm extends VerticalPanel {

	/**
	 * Tabelle mit Belegungen
	 */
	private final CellTable<Belegung> belegungTable = new CellTable<Belegung>();

	private final Logger logger = RpcSettings.getLogger();

	private final RaumplanerAdministrationAsync raumplaneradministration = RpcSettings
			.getRaumplanerAdministration();
	private final ListBox raumbox = new ListBox();

	private final List<Raum> raums = new ArrayList<Raum>();

	private final List<Belegung> belegunen = new ArrayList<Belegung>();

	/**
	 * Konstruktor für einen Raumbelegungsplan
	 */
	public BelegungsplanForm() {

		// Format für zeit
		final DateTimeFormat format = DateTimeFormat
				.getFormat("dd.MM.yyyy, HH:mm");

		// Spalte mit Raumanzeige
		Column<Belegung, String> raumColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return object.getRaum().getBezeichnung();
			}
		};
		belegungTable.addColumn(raumColumn, "Raum");

		Column<Belegung, String> themaColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return object.getThema();
			}
		};
		belegungTable.addColumn(themaColumn, "Thema");

		Column<Belegung, String> startZeitColum = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return format.format(object.getStartzeit());
			}
		};
		belegungTable.addColumn(startZeitColum, "Beginn");

		Column<Belegung, String> endZeitColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return format.format(object.getEndzeit());
			}
		};
		belegungTable.addColumn(endZeitColumn, "Ende");

		Column<Belegung, String> erstellerColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return object.getErsteller().getEmail();
			}
		};
		belegungTable.addColumn(erstellerColumn, "Ersteller");

		Column<Belegung, String> teilnehmerColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return object.getTeilnehmerAsString();
			}
		};
		belegungTable.addColumn(teilnehmerColumn, "Teilnehmer");

		raumbox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				raumplaneradministration.getAllBelegung(new GetBelegungenCallback());
			}
		});

		this.add(raumbox);

		raumplaneradministration.getAllBelegung(new GetBelegungenCallback());
		raumplaneradministration.getAllRaums(new GetRaumsCallBack());

		this.add(belegungTable);
	}

	/**
	 * Callback für das laden der Belegungen Filtert nach gewähltem Raum
	 * 
	 * @author Fabian
	 *
	 */
	class GetBelegungenCallback implements AsyncCallback<List<Belegung>> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Belegungen konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(List<Belegung> result) {

			if (raumbox.getSelectedIndex() == 0) {
				belegungTable.setRowCount(result.size());
				belegungTable.setRowData(result);
			} else {
				belegunen.clear();
				for (Belegung belegung : result) {
					if (belegung.getRaum().equals(
							raums.get(raumbox.getSelectedIndex()))) {
						belegunen.add(belegung);
					}
				}
				belegungTable.setRowCount(belegunen.size());
				belegungTable.setRowData(belegunen);
			}

		}
	}

	/**
	 * Nachdem alle Räume aus der Datenbank geladen wurden, werder sie dem
	 * Dropdown hinzugefügt
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
			raums.clear();
			raums.add(new Raum("Alle", 20));
			raums.addAll(result);
			for (Raum raum : raums) {
				raumbox.addItem(raum.getBezeichnung());
			}
		}
	}

}
