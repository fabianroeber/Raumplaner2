package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NeueBuchung extends Composite {
	
	VerticalPanel neueBuchungPnl = new VerticalPanel();
	
	ListBox räumeDropDown; 
	
	public NeueBuchung() {
		
		initWidget(this.neueBuchungPnl);
		
		neueBuchungPnl.setSpacing(10);
		
		räumeDropDown = new ListBox();
		räumeDropDown.setTitle("Raumauswahl:");
		
		räumeDropDown.addItem("I001");
		räumeDropDown.addItem("I002");
		räumeDropDown.addItem("I003");
		räumeDropDown.addItem("I004");
		räumeDropDown.addItem("I005");
		räumeDropDown.addItem("I006");
		
		neueBuchungPnl.add(räumeDropDown);
			
		
		
	}

}
