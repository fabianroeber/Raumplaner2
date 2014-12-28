package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class LoginFotos extends Composite {
	
	HorizontalPanel hpLogin = new HorizontalPanel();
	
	public LoginFotos() {
		
		initWidget(hpLogin);
		
		Image neubau1 = new Image("images/Neubau 1.jpg");
		neubau1.setPixelSize(190, 190);
		
		Image neubau2 = new Image("images/Neubau 2.jpg");
		neubau2.setPixelSize(190, 190);
		
		Image neubau3 = new Image("images/Neubau 3.jpg");
		neubau3.setPixelSize(190, 190);
		
		Image neubau4 = new Image("images/Neubau 4.jpg");
		neubau4.setPixelSize(190, 190);
		
		Image neubau5 = new Image("images/Neubau 5.jpg");
		neubau5.setPixelSize(190, 190);
		
		Image neubau6 = new Image("images/Neubau 6.jpg");
		neubau6.setPixelSize(190, 190);
		
//		Image hdm1 = new Image("images/hdm 1.jpg");
//		hdm1.setPixelSize(190, 190);
//		
//		Image hdm2 = new Image("images/hdm 2.jpg");
//		hdm2.setPixelSize(190, 190);
		
		hpLogin.add(neubau1);
		hpLogin.add(neubau2);
		hpLogin.add(neubau3);
		hpLogin.add(neubau4);
		hpLogin.add(neubau6);
		hpLogin.add(neubau5);
		
//		hpLogin.add(hdm1);
//		hpLogin.add(hdm2);
		
	}

}
