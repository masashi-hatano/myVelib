package fr.centralesupelec.is1220.Station;

import fr.centralesupelec.is1220.User.Observer;

/**
 * <h1>Observable</h1>
 * An interface who implements an observable instance.
 */
public interface Observable {
	/**
	 * Allow to add an observer to the observable.
	 * @param observer the new observer.
	 */
	public void registerObserver(Observer observer);

	/**
	 * Allows to remove an observer to the observable.
	 * @param observer the observer removed.
	 */
	public void removeObserver(Observer observer);

	/**
	 * Allow to notify the observers when an update is done.
	 * @throws Exception thrown when the notification is wrong.
	 */
	public void notifyObservers() throws Exception;
}
