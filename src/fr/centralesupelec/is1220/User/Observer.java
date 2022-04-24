package fr.centralesupelec.is1220.User;

/**
 * <h1>Observer</h1>
 * An interface who implements an observer instance.
 */
public interface Observer {

	/**
	 * Allow to update the price of an observer when a ride is done.
	 * @param time the duration of the ride.
	 * @param bikeType the type of the bike used : "ELECTRICAL" for an electrical bike or "MECHANICAL" for a mechanical bike.
	 * @param stationType the type of the station where the bike was dropped : "PLUS" for a plus station or "STANDARD" for a standard station.
	 * @param observer the observer who dropped the bike.
	 * @throws Exception thrown when the bike's type is unknown.
	 */
	void update(double time, String bikeType, String stationType, Observer observer) throws Exception;
}
