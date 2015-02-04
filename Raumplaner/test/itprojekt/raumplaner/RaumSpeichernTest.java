package itprojekt.raumplaner;

import itprojekt.raumplaner.server.db.DatabaseConnection;
import itprojekt.raumplaner.server.db.RaumMapper;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Test zum Raum Speichern
 * 
 * @author Fabian
 *
 */
public class RaumSpeichernTest {

	@Test
	public void test() {

		Date date = new Date(System.currentTimeMillis());

		RaumMapper raumMapper = RaumMapper.getRaumMapper();

		DatabaseConnection.isUnitTesting = true;

		Raum raum = new Raum();
		raum.setBezeichnung("I001");
		raum.setCreated(date);
		raum.setFassungsvermoegen(10);

		raumMapper.insert(raum);

	}
}
