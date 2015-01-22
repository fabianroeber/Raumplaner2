package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Raumverfuegabarkeit extends Composite {
	
	VerticalPanel verfuegbarPnl1 = new VerticalPanel();
	
	Label raumverfuegbarkeitHeader;
	
	Label raumAuswaehlen;
	ListBox raeumeDropDown;
	
	Label zeitSlot;
	ListBox zeitSlotDropDown;
	
	public Raumverfuegabarkeit() {
		
		initWidget(this.verfuegbarPnl1);
		
		raumverfuegbarkeitHeader = new Label("Raumverfügbarkeit");
		raumverfuegbarkeitHeader.setPixelSize(200, 25);
		raumverfuegbarkeitHeader.getElement().setAttribute("id", "raumBuchungHeader-id");
		
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
		
		
		verfuegbarPnl1.add(raumverfuegbarkeitHeader);
		verfuegbarPnl1.add(raumAuswaehlen);
		verfuegbarPnl1.add(raeumeDropDown);
		verfuegbarPnl1.add(zeitSlot);
		verfuegbarPnl1.add(zeitSlotDropDown);
		
		
		
		
	}

}
