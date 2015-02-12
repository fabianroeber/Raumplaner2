package itprojekt.raumplaner.client;

import itprojekt.raumplaner.shared.bo.Einladung;
import itprojekt.raumplaner.shared.bo.User;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
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
	private final CellTable<Einladung> einladungen = new CellTable<Einladung>();

	/**
	 * Aktueller User
	 */
	private final User actualUser;

	public EinladungForm(User user) {

		actualUser = user;

		VerticalPanel basePanel = new VerticalPanel();

		// Spalte mit Raumanzeige
		Column<Einladung, String> raumColumn = new Column<Einladung, String>(
				new TextCell()) {

			@Override
			public String getValue(Einladung object) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		einladungen.addColumn(raumColumn);

		this.add(basePanel);
	}

}
