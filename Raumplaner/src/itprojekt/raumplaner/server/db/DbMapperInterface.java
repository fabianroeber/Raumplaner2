package itprojekt.raumplaner.server.db;

import java.util.List;

import itprojekt.raumplaner.shared.model.RaumPlanerModel;

public interface DbMapperInterface<T extends RaumPlanerModel> {

	/**
	 * L&auml;dt eine Liste aller Models aus der Datenbank.
	 * 
	 * @return List<T>
	 */
	public List<T> getAllModels();

	/**
	 * Speichert ein Objekt vom Typ T in der Datenbank.
	 * 
	 * @param model
	 */
	public void saveModel(T model);

	/**
	 * L&auml;dt ein Objekt zu einer ID aus der Datenbank
	 * 
	 * @param id
	 */
	public T getModelById(Long id);

	/**
	 * L&ouml;scht ein Model aus der Datenbank
	 * 
	 * @param model
	 */
	public void deleteModel(T model);

}