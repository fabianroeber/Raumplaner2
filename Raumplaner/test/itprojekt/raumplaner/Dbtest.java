package itprojekt.raumplaner;

import itprojekt.raumplaner.server.db.DatabaseConnection;
import itprojekt.raumplaner.server.db.RaumMapper;
import itprojekt.raumplaner.shared.bo.Raum;

import java.util.List;

import org.junit.Test;

public class Dbtest {

	@Test
	public void test() {

		RaumMapper raumMapper = RaumMapper.getRaumMapper();

		DatabaseConnection.isUnitTesting = true;

		List<Raum> raums = raumMapper.getAll();

		for (Raum raum : raums) {
			System.out.println(raum.getBezeichnung());
		}
	}

}
