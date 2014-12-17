package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.shared.bo.Belegung;

import java.util.List;

/**
 * Datenbank-Zugriffsklasse f&uuml;r Objekte vom Typ {@link Belegung}.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class BelegungMapper implements DbMapperInterface<Belegung> {

	private static BelegungMapper belegungmapper = null;

	private BelegungMapper() {
	}

	public static BelegungMapper getBelegungMapper() {
		if (belegungmapper == null) {
			belegungmapper = new BelegungMapper();
		}
		return belegungmapper;
	}

	@Override
	public List<Belegung> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBo(Belegung bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertBo(Belegung bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Belegung getBoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBo(Belegung bo) {
		// TODO Auto-generated method stub

	}

}
