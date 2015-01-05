package itprojekt.raumplaner.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class Menu extends Composite {

	private VerticalPanel menuPanel = new VerticalPanel();

	Button raumAuswählen;

	Label nav;

	Button datumAuswählen;

	Button raumVerfügbarkeit;

	Label Meldung;

	public Menu() {

		initWidget(this.menuPanel);

		// Navi Label hinzufügen:

		nav = new Label("Navigation");
//		DOM.setElementAttribute(nav.getElement(), "id", "Navigation-id"); // DEPRECATED
		nav.getElement().setAttribute("id", "Navigation-id");
		nav.setPixelSize(200, 30);
		nav.setVisible(true);

		raumAuswählen = new Button("Raum auswählen");
		datumAuswählen = new Button("Datum auswählen");
		raumVerfügbarkeit = new Button("Raumverfügbarkeit überprüfen");

		raumAuswählen.setPixelSize(200, 30);
		datumAuswählen.setPixelSize(200, 30);
		raumVerfügbarkeit.setPixelSize(200, 30);

		// Listener hinzufügen

		raumAuswählen.addClickHandler(new RaumAuswählenHandler());
		datumAuswählen.addClickHandler(new DatumAuswählenHandler());
		raumVerfügbarkeit.addClickHandler(new RaumVerfügbarkeitHandler());

		// add-Methoden:

		menuPanel.add(nav);
		menuPanel.add(raumAuswählen);
		menuPanel.add(datumAuswählen);
		menuPanel.add(raumVerfügbarkeit);

	}

	public class DatumAuswählenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			DatePicker datumBox = new DatePicker();

			menuPanel.add(datumBox);

			datumAuswählen.setEnabled(false); // Nach dem 1-maligen Click darf
												// man nicht nochmal auf
												// datumAuswählen klicken
		}

	}

	private class RaumAuswählenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			ListBox räume = new ListBox(); // ListBox erzeugen
			räume.addItem("I001"); // Inhalt des Listbox füllen
			räume.addItem("I002");
			räume.addItem("I003");
			räume.addItem("I004");
			räume.addItem("I005");
			räume.addItem("I006");
			räume.addItem("I007");
			räume.addItem("I008");
			räume.addItem("I009");
			räume.addItem("I010");
			räume.addItem("I011");

			räume.setVisibleItemCount(5); // Wie viele Einträge sollen angezeigt
											// werden?

			menuPanel.add(räume);

			raumAuswählen.setEnabled(false); // Nach dem 1-maligen Click darf
												// man nicht nochmal auf
												// raumAuswählen klicken
		}

	}

	private class RaumVerfügbarkeitHandler implements ClickHandler {

		@Override
public void onClick(ClickEvent event) {
			
//			Hier wird eine if-Abfrage kommen, die überprüft ob das Datum und der Raum ausgewählt ist.
			
			if (raumAuswählen.isAttached()) {
			
			Window.alert("Sie haben noch keinen Raum oder kein Datum ausgewählt");
			
			raumVerfügbarkeit.setEnabled(true);
			
			// Grid verfügbarkeiten = new Grid(25, 25);

//			Meldung = new Label("Bitte wählen Sie einen Raum und ein Datum aus");

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

//			menuPanel.add(Meldung);

//			raumVerfügbarkeit.setEnabled(false); // Nach dem 1-maligen Click
//													// darf man nicht nochmal
//													// auf datumAuswählen
//													// klicken

			}
			else {
				
				raumVerfügbarkeit.setEnabled(false); // Nach dem 1-maligen Click
													 // darf man nicht nochmal
							  						 // auf datumAuswählen
													 // klicken
				}
			}
		}

	}