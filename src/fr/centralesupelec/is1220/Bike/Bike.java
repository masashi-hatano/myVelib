package fr.centralesupelec.is1220.Bike;

import java.io.Serializable;

/**
 * <h1>Bike</h1>
 * This class is an abstract model
 * for the Item Bike.
 */
public abstract class Bike implements Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A static counter for calculating the ID of the bikes.
	 */
	private static int counter = 0;

	/**
	 * The ID of the Bike.
	 */
	private int id;

	/**
	 * Create a new Bike with a new ID.
	 */
	public Bike() {
		super();
		counter ++;
		this.id = counter;
	}

	/**
	 * Get the ID of the Bike.
	 * @return the Bike's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Give the type of the Bike.
	 * @return the Bike's Type.
	 */
	public abstract String getType();

	/**
	 * Give information about the Bike.
	 * @return the informations.
	 */
	@Override
	public String toString() {
		return "Bike n°" + id;
	}


}
