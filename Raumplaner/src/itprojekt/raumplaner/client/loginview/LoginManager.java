package itprojekt.raumplaner.client.loginview;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// Klasse um die Login-Page anzuzeigen

public class LoginManager implements EntryPoint {

//	private Button anmeldenButton;
	
	@Override
	public void onModuleLoad() {
		

		VerticalPanel vp1 = new VerticalPanel();
		
		RootPanel.get().add(vp1);
		
//		anmeldenButton = new Button();
//		anmeldenButton.setText("Anmelden");
//		anmeldenButton.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent anmeldeEvent) {
//				Window.alert("Sie sind nun angemeldet. Herzlichen Glückwunsch");
//				
//			}
//		});
		
	}

}
