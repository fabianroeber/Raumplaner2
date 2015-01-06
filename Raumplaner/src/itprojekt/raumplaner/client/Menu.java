package itprojekt.raumplaner.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Menu extends Composite {

	private VerticalPanel menuPanel = new VerticalPanel();

	Button raumAuswaehlen;

	Label nav;

	Button datumAuswaehlen;

	Button raumVerfuegbarkeit;

	Label Meldung;

	public Menu() {

		initWidget(this.menuPanel);

		// Navi Label hinzufügen:

		nav = new Label("Navigation");
		// DOM.setElementAttribute(nav.getElement(), "id", "Navigation-id"); //
		// DEPRECATED
		nav.getElement().setAttribute("id", "Navigation-id");
		nav.setPixelSize(200, 30);
		nav.setVisible(true);

		raumAuswaehlen = new Button("Raum auswählen");
		datumAuswaehlen = new Button("Datum auswählen");
		raumVerfuegbarkeit = new Button("Raumverfügbarkeit überprüfen");

		raumAuswaehlen.setPixelSize(200, 30);
		datumAuswaehlen.setPixelSize(200, 30);
		raumVerfuegbarkeit.setPixelSize(200, 30);

		// Listener hinzufügen

		raumAuswaehlen.addClickHandler(new RaumAuswaehlenHandler());
		datumAuswaehlen.addClickHandler(new DatumAuswaehlenHandler());
		raumVerfuegbarkeit.addClickHandler(new RaumVerfuegbarkeitHandler());

		// add-Methoden:

		menuPanel.add(nav);
		menuPanel.add(raumAuswaehlen);
		menuPanel.add(datumAuswaehlen);
		menuPanel.add(raumVerfuegbarkeit);

	}

	public class DatumAuswaehlenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			DatePicker datumBox = new DatePicker();

			menuPanel.add(datumBox);

			datumAuswaehlen.setEnabled(false); // Nach dem 1-maligen Click darf
												// man nicht nochmal auf
												// datumAuswählen klicken
		}

	}

	private class RaumAuswaehlenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			ListBox raums = new ListBox(); // ListBox erzeugen
			raums.addItem("I001"); // Inhalt des Listbox füllen
			raums.addItem("I002");
			raums.addItem("I003");
			raums.addItem("I004");
			raums.addItem("I005");
			raums.addItem("I006");
			raums.addItem("I007");
			raums.addItem("I008");
			raums.addItem("I009");
			raums.addItem("I010");
			raums.addItem("I011");

			raums.setVisibleItemCount(5); // Wie viele Einträge sollen
											// angezeigt
											// werden?

			menuPanel.add(raums);

			raumAuswaehlen.setEnabled(false); // Nach dem 1-maligen Click darf
												// man nicht nochmal auf
												// raumAuswählen klicken
		}

	}

	private class RaumVerfuegbarkeitHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			// Hier wird eine if-Abfrage kommen, die überprüft ob das Datum
			// und der Raum ausgewählt ist.

			if (raumAuswaehlen.isAttached()) {

				Window.alert("Sie haben noch keinen Raum oder kein Datum ausgewählt");

				raumVerfuegbarkeit.setEnabled(true);

				// Grid verfügbarkeiten = new Grid(25, 25);

				// Meldung = new
				// Label("Bitte wählen Sie einen Raum und ein Datum aus");

				// verfügbarkeiten.setText(0, 0, "Raum: ");
				// verfügbarkeiten.setText(1, 0, "00:00");
				// verfügbarkeiten.setText(2, 0, "01:00");
				// verfügbarkeiten.setText(3, 0, "02:00");
				// verfügbarkeiten.setText(4, 0, "03:00");
				// verfügbarkeiten.setText(5, 0, "04:00");
				// verfügbarkeiten.setText(6, 0, "05:00");
				// verfügbarkeiten.setText(7, 0, "07:00");
				// verfügbarkeiten.setText(8, 0, "08:00");
				// verfügbarkeiten.setText(9, 0, "09:00");
				// verfügbarkeiten.setText(10, 0, "10:00");
				// verfügbarkeiten.setText(11, 0, "11:00");
				// verfügbarkeiten.setText(12, 0, "12:00");
				// verfügbarkeiten.setText(13, 0, "13:00");
				// verfügbarkeiten.setText(14, 0, "14:00");
				// verfügbarkeiten.setText(15, 0, "15:00");
				// verfügbarkeiten.setText(16, 0, "16:00");
				// verfügbarkeiten.setText(17, 0, "17:00");
				// verfügbarkeiten.setText(18, 0, "18:00");
				// verfügbarkeiten.setText(19, 0, "19:00");
				// verfügbarkeiten.setText(20, 0, "20:00");
				// verfügbarkeiten.setText(21, 0, "21:00");
				// verfügbarkeiten.setText(22, 0, "22:00");
				// verfügbarkeiten.setText(23, 0, "23:00");
				// verfügbarkeiten.setText(24, 0, "00:00");
				//
				// verfügbarkeiten.setBorderWidth(1);

				// menuPanel.add(Meldung);

				// raumVerfügbarkeit.setEnabled(false); // Nach dem 1-maligen
				// Click
				// // darf man nicht nochmal
				// // auf datumAuswählen
				// // klicken

			} else {

				raumVerfuegbarkeit.setEnabled(false); // Nach dem 1-maligen
														// Click
														// darf man nicht
														// nochmal
														// auf datumAuswählen
														// klicken
			}
		}
	}

}