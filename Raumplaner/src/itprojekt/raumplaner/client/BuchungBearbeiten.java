package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BuchungBearbeiten extends Composite {
	
	VerticalPanel BuchungBearbeitenPnl = new VerticalPanel();
	
	Label raumAuswaehlen;
	ListBox raeumeDropDown; 
	
	Label zeitSlot;
	ListBox zeitSlotDropDown;
	
	public BuchungBearbeiten() {
		
		initWidget(this.BuchungBearbeitenPnl);
		
		BuchungBearbeitenPnl.setSpacing(10);
		
//		Nur vorläufig hier werden die benutzerabhängige oder Raumabhängige Buchungen aufgelistet
		
		raumAuswaehlen = new Label("Raum auswählen:");
		
		raeumeDropDown = new ListBox();
		
		raeumeDropDown.addItem("I001");
		raeumeDropDown.addItem("I002");
		raeumeDropDown.addItem("I003");
		raeumeDropDown.addItem("I004");
		raeumeDropDown.addItem("I005");
		raeumeDropDown.addItem("I006");
		
		zeitSlot = new Label("Zeitslot auswählen:");
		
		zeitSlotDropDown = new ListBox();
		
		zeitSlotDropDown.addItem("07:00 - 10:00 Uhr");
		zeitSlotDropDown.addItem("10:15 - 13:15 Uhr");
		zeitSlotDropDown.addItem("14:15 - 17:15 Uhr");
		zeitSlotDropDown.addItem("17:15 - 20:15 Uhr");
		
		BuchungBearbeitenPnl.add(raumAuswaehlen);
		BuchungBearbeitenPnl.add(raeumeDropDown);
		BuchungBearbeitenPnl.add(zeitSlot);
		BuchungBearbeitenPnl.add(zeitSlotDropDown);
		
	}

}
