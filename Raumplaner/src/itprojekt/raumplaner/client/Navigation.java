package itprojekt.raumplaner.client;

import org.apache.tools.ant.taskdefs.Get;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Navigation extends Composite {
	
	VerticalPanel navPanel = new VerticalPanel();
	HorizontalPanel zusammenPanel = new HorizontalPanel();
	
	Label navHeader;
	Anchor raumBuchung;
	Anchor raumVerfuegbarkeit;
	
	Raumverfuegabarkeit verfuegbareRaeume;
	
	RaumBuchungsMenu raumBuchenMenu;
	
	public Navigation() {
		
		initWidget(this.zusammenPanel);
		
		navHeader = new Label("Navigation");
		navHeader.getElement().setAttribute("id", "navigation");
		navHeader.setPixelSize(200, 25);
		
		raumBuchung = new Anchor("Raumbuchung");
		raumBuchung.addClickHandler(new raumBuchungHandler());
		
		raumVerfuegbarkeit = new Anchor("Raumverfügbarkeit überprüfen");
		raumVerfuegbarkeit.addClickHandler(new verfuegbarkeitHandler());
		
		zusammenPanel.setSpacing(20);

		navPanel.add(navHeader);
		navPanel.add(raumBuchung);
		navPanel.add(raumVerfuegbarkeit);
		
		zusammenPanel.add(navPanel);
		
	} private class raumBuchungHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			raumBuchenMenu = new RaumBuchungsMenu();
			
			
			zusammenPanel.add(raumBuchenMenu);
			raumBuchung.setVisible(false);
			
			
		}
		
		
	} private class verfuegbarkeitHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			verfuegbareRaeume = new Raumverfuegabarkeit();
			
			zusammenPanel.add(verfuegbareRaeume);
			
			raumBuchung.setVisible(true);
			raumVerfuegbarkeit.setVisible(false);
			
		}
		
		
		
	}
	

}
