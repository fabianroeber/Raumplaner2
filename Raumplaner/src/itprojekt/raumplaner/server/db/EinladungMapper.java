package itprojekt.raumplaner.server.db;

import itprojekt.raumplaner.shared.bo.Einladung;

import java.util.List;

/**
 * Datenbank-Zugriffsklasse f&uuml;r {@link Einladung} Objekte.
 * 
 * @author Fabian
 * @author Thies
 *
 */
public class EinladungMapper implements DbMapperInterface<Einladung> {

	private static EinladungMapper einladungmapper = null;

	private EinladungMapper() {
	}

	public static EinladungMapper getEinladungMapper() {
		if (einladungmapper == null) {
			einladungmapper = new EinladungMapper();
		}
		return einladungmapper;
	}

	@Override
	public List<Einladung> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBo(Einladung bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertBo(Einladung bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Einladung getBoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBo(Einladung bo) {
		// TODO Auto-generated method stub

	}

}
