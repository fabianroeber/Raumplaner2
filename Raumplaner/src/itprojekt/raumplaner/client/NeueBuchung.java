package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NeueBuchung extends Composite {
	
	VerticalPanel neueBuchungPnl = new VerticalPanel();
	
	Label raumAuswaehlen;
	ListBox raeumeDropDown; 
	
	Label zeitSlot;
	ListBox ZeitSlotDropDown;
	
	Label personenAuswählen;
	SuggestBox personenAuswahl;
	
	Label einladungsThema;
	TextBox themaEintragen;
	
	Button raumBuchen;
	
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
		
		personenAuswählen = new Label("Personen auswählen:");
		
		personenAuswahl = new SuggestBox(Personen.personenEinladen());
		
		
		einladungsThema = new Label("Tragen Sie das Thema ein:");
		
		themaEintragen = new TextBox();
		
		raumBuchen = new Button("Raum buchen");
		
		neueBuchungPnl.add(raumAuswaehlen);
		neueBuchungPnl.add(raeumeDropDown);
		neueBuchungPnl.add(zeitSlot);
		neueBuchungPnl.add(ZeitSlotDropDown);
		neueBuchungPnl.add(personenAuswählen);
		neueBuchungPnl.add(personenAuswahl);
		neueBuchungPnl.add(einladungsThema);
		neueBuchungPnl.add(themaEintragen);
		neueBuchungPnl.add(raumBuchen);
		
		
		
		
		
	}

}
