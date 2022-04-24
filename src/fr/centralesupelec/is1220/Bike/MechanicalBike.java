package fr.centralesupelec.is1220.Bike;

/**
 * <h1>ElectricalBike</h1>
 * This class represents
 * a Mechanical Bike.
 */
public class MechanicalBike extends Bike {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new Mechanical Bike.
	 */
	public MechanicalBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gives informations about the bike such as its type or its ID.
	 * @return the informations.
	 */
	@Override
	public String toString() {
		return "MechanicalBike (Bike n°" + this.getId() +")";
	}

	/**
	 * Gives the type of the Bike.
	 * @return the type.
	 */
	@Override
	public String getType() {
		return "MECHANICAL";
	}


}
