package itprojekt.raumplaner.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import itprojekt.raumplaner.shared.RaumplanerAdministrationAsync;
import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Hier werden alle verfügbaren Einladugen zur Auswahl angezeigt. Die
 * Einladungen werden in einem Celltable Widget angezeigt. Der User kann hier
 * alle seine Buchungen einsehen und diese bei Bedarf bestätigen
 * 
 * @author Fabian
 *
 */
public class EinladungForm extends VerticalPanel {

	/**
	 * Tabelle mit Einladungen
	 */
	private final CellTable<Einladung> einladungTable = new CellTable<Einladung>();

	/**
	 * Aktueller User
	 */
	private final User actualUser;

	private final RaumplanerAdministrationAsync raumplaneradministration = RpcSettings
			.getRaumplanerAdministration();

	private final Logger logger = RpcSettings.getLogger();

	public EinladungForm(User user) {
		// Format für zeit
		final DateTimeFormat format = DateTimeFormat
				.getFormat("dd.MM.yyyy, HH:mm");

		actualUser = user;

		// Spalte mit Raumanzeige
		Column<Einladung, String> raumColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				return object.getBelegung().getRaum().getBezeichnung();
			}
		};
		einladungTable.addColumn(raumColumn, "Raum");

		Column<Einladung, String> themaColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				return object.getBelegung().getThema();
			}
		};
		einladungTable.addColumn(themaColumn, "Thema");

		Column<Einladung, String> startZeitColum = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				return format.format(object.getBelegung().getStartzeit());
			}
		};
		einladungTable.addColumn(startZeitColum, "Beginn");

		Column<Einladung, String> endZeitColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				return format.format(object.getBelegung().getEndzeit());
			}
		};
		einladungTable.addColumn(endZeitColumn, "Ende");

		Column<Einladung, String> teilnehmerColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				return object.getBelegung().getTeilnehmerAsString();
			}
		};
		// einladungTable.addColumn(teilnehmerColumn, "Teilnehmer");

		Column<Einladung, String> statusColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				if (object.getAkzeptiert()) {
					return "Akzeptiert";
				}
				return "Nicht akzeptiert";
			}
		};
		einladungTable.addColumn(statusColumn);

		Column<Einladung, String> akzeptierenColumn = new Column<Einladung, String>(
				new ButtonCell()) {

			@Override
			public String getValue(Einladung object) {
				return "Status ändern";
			}
		};
		akzeptierenColumn
				.setFieldUpdater(new FieldUpdater<Einladung, String>() {

					@Override
					public void update(int index, Einladung object, String value) {
						if (object.getAkzeptiert()) {
							object.setAkzeptiert(false);
						} else {
							object.setAkzeptiert(true);
						}
						raumplaneradministration.updateEinladung(object,
								new SaveEinladungCallback());
					}
				});
		einladungTable.addColumn(akzeptierenColumn);

		raumplaneradministration.getEinladungenByUser(actualUser,
				new getEinladungenCallback());

		this.add(einladungTable);
	}

	/**
	 * Callback für das Abfragen der Einladungen
	 * 
	 * @author Fabian
	 *
	 */
	class getEinladungenCallback implements AsyncCallback<List<Einladung>> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Einladungen konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(List<Einladung> result) {
			einladungTable.setRowCount(result.size());
			einladungTable.setRowData(result);

		}
	}

	/**
	 * Callback für das Speichern einer Einladung
	 * 
	 * @author Fabian
	 *
	 */
	class SaveEinladungCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			logger.log(Level.WARNING,
					"Einladung konnte nicht gespeichert werden.");

		}

		@Override
		public void onSuccess(Void result) {
			raumplaneradministration.getEinladungenByUser(actualUser,
					new getEinladungenCallback());

		}

	}

}
