package fr.centralesupelec.is1220.User;

import java.io.Serializable;

/**
 * <h1>Vmax</h1>
 * This class represents a Vmax card.
 */
public class Vmax implements Card,Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The type of the card.
	 */
	private String name;

	/**
	 * Creates a new Vmax card.
	 */
	public Vmax() {
		super();
		this.name = "Vmax";
	}

	@Override
	public double computePrice(User user, double time, String bikeType) throws Exception {
		if (time <0) {
			throw new Exception("Invalid time");
		}
		if(time>60) {
			double price = ((time-60)/60)*1;
			user.setBalance(user.getBalance()-price);
			return price;
		}
		else {
			return 0;
		}
	}

	@Override
	public String getName() {
		return name;
	}

}