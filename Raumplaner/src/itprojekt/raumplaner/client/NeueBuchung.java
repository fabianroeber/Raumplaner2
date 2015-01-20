package itprojekt.raumplaner.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NeueBuchung extends Composite {
	
	VerticalPanel neueBuchungPnl = new VerticalPanel();
	HorizontalPanel pnlMitUser = new HorizontalPanel();
	VerticalPanel eingeladenePnl = new VerticalPanel();
	
	Label raumAuswaehlen;
	ListBox raeumeDropDown; 
	
	Label zeitSlot;
	ListBox ZeitSlotDropDown;
	
	Label personenAuswaehlen;
	SuggestBox personenAuswahl;
	Button personAdden;
	
	Label einladungsThema;
	TextBox themaEintragen;
	
	Button raumBuchen;
	BuchungErfolgreich erfolgreichGebucht;
	
	public NeueBuchung() {
		
		initWidget(this.neueBuchungPnl);
		
		neueBuchungPnl.setSpacing(10);
		
		raumAuswaehlen = new Label("Raum auswählen:");
		
		raeumeDropDown = new ListBox();
		
		raeumeDropDown.addItem("I001");
		raeumeDropDown.addItem("I002");
		raeumeDropDown.addItem("I003");
		raeumeDropDown.addItem("I004");
		raeumeDropDown.addItem("I005");
		raeumeDropDown.addItem("I006");
		
		zeitSlot = new Label("Zeitslot auswählen:");
		
		ZeitSlotDropDown = new ListBox();
		
		ZeitSlotDropDown.addItem("07:00 - 10:00 Uhr");
		ZeitSlotDropDown.addItem("10:15 - 13:15 Uhr");
		ZeitSlotDropDown.addItem("14:15 - 17:15 Uhr");
		ZeitSlotDropDown.addItem("17:15 - 20:15 Uhr");
		
		personenAuswaehlen = new Label("Personen auswählen:");
		
		personenAuswahl = new SuggestBox(Personen.personenEinladen());
		
		personAdden = new Button("OK");
		personAdden.setPixelSize(30, 30);
		personAdden.addClickHandler(new PersonAddenHandler());
		
		pnlMitUser.add(personenAuswahl);
		pnlMitUser.add(personAdden);
		
		einladungsThema = new Label("Tragen Sie das Thema ein:");
		
		themaEintragen = new TextBox();
		
		raumBuchen = new Button("Raum buchen");
		raumBuchen.addClickHandler(new erfolgreicheBuchungHandler());
		
		neueBuchungPnl.add(raumAuswaehlen);
		neueBuchungPnl.add(raeumeDropDown);
		neueBuchungPnl.add(zeitSlot);
		neueBuchungPnl.add(ZeitSlotDropDown);
		neueBuchungPnl.add(personenAuswaehlen);
		neueBuchungPnl.add(eingeladenePnl);
		neueBuchungPnl.add(pnlMitUser);
		neueBuchungPnl.add(einladungsThema);
		neueBuchungPnl.add(themaEintragen);
		neueBuchungPnl.add(raumBuchen);
		
	} public class PersonAddenHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Label personen = new Label("- " + personenAuswahl.getText() + " ist eingeladen");
			
			eingeladenePnl.add(personen);
			
		}
			
	   } public class erfolgreicheBuchungHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				erfolgreichGebucht = new BuchungErfolgreich();
				
				RootPanel.get().add(erfolgreichGebucht);
				
			}
			
			
			
		}
		
		
	}
