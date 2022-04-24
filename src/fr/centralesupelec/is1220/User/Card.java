package fr.centralesupelec.is1220.User;

/**
 * <h1>Card</h1>
 * This interface is a model
 * for the Item Card.
 */
public interface Card {
	/**
	 * Compute the price of the ride which was on a given Bike during a given time of an user who uses this card. 
	 * @param user the user of this card.
	 * @param time the duration of the ride.
	 * @param bikeType the type of the bike used during the ride.
	 * @return the price of the ride
	 * @throws Exception thrown when the time is misspelled or when the type of the bike is unknown.
	 */
	public double computePrice(User user, double time, String bikeType) throws Exception;

	/**
	 * Get the name of the card.
	 * @return the name of the card.
	 */
	public String getName();

}
