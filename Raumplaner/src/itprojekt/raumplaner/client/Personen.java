package itprojekt.raumplaner.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

public class Personen extends Composite {
	
	static MultiWordSuggestOracle personenMail;
	
	public static MultiWordSuggestOracle personenEinladen() {
	
		
		personenMail = new MultiWordSuggestOracle();
		
		personenMail.add("fried.zwilling@gmail.com");
		
		return personenMail;
		
	}
	
}
