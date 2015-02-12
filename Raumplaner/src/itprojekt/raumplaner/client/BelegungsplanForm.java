package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Belegung;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

		Column<Belegung, String> teilnehmerColumn = new Column<Belegung, String>(
				new TextCell()) {

			@Override
			public String getValue(Belegung object) {
				return object.getTeilnehmerAsString();
			}
		};
		belegungTable.addColumn(teilnehmerColumn, "Teilnehmer");

		raumplaneradministration.getAllBelegung(new GetBelegungenCallback());

		this.add(belegungTable);
	}

	/**
	 * Callback für das laden der Belegungen
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
			belegungTable.setRowCount(result.size());
			belegungTable.setRowData(result);
		}

	}

}
