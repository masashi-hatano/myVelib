package fr.centralesupelec.is1220.Bike;

/**
 * <h1>ElectricalBike</h1>
 * This class represents
 * an Electrical Bike.
 */
public class ElectricalBike extends Bike {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an new Electrical Bike.
	 */
	public ElectricalBike() {
		super(); 
	}

	/**
	 * Gives informations about the bike such as its type or its ID.
	 * @return the informations.
	 */
	@Override
	public String toString() {
		return "ElectricalBike (Bike n°" + this.getId() +")";
	}

	/**
	 * Gives the type of the Bike.
	 * @return the type.
	 */
	@Override
	public String getType() {
		return "ELECTRICAL";
	}

}
