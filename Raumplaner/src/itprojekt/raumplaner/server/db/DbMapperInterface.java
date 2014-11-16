package itprojekt.raumplaner.server.db;

import java.util.List;

import itprojekt.raumplaner.shared.model.RaumPlanerBusinessObject;

/**
 * Interface, dass verschiedene Methoden für alle Mapper bereitstellt.
 * 
 * @author Fabian
 *
 * @param <T>
 */
public interface DbMapperInterface<T extends RaumPlanerBusinessObject> {

	/**
	 * L&auml;dt eine Liste aller Models aus der Datenbank.
	 * 
	 * @return List<T>
	 */
	public List<T> getAll();

	/**
	 * Speichert ein Objekt vom Typ T in der Datenbank.
	 * 
	 * @param model
	 */
	public void updateBo(T bo);

	/**
	 * F&uuml;gt einen neues Objekt vom Typ T in die Datenbank ein.
	 * 
	 * @param bo
	 */
	public void insertBo(T bo);

	/**
	 * L&auml;dt ein Objekt zu einer ID aus der Datenbank
	 * 
	 * @param id
	 */
	public T getBoById(Long id);

	/**
	 * L&ouml;scht ein Model aus der Datenbank
	 * 
	 * @param model
	 */
	public void deleteBo(T bo);

}