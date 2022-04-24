package fr.centralesupelec.is1220.User;

import java.io.Serializable;

/**
 * <h1>NoCard</h1>
 * This class is used when a user do not use a card.
 */
public class NoCard implements Card,Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The type of the card.
	 */
	private String name;

	/**
	 * Creates a new NoCard.
	 */
	public NoCard() {
		super();
		this.name = "NoCard";
	}

	@Override
	public double computePrice(User user, double time, String bikeType) throws Exception {
		if (time <0) {
			throw new Exception("Invalid time");
		}
		if(bikeType.equalsIgnoreCase("MECHANICAL")) {
			double price = (time/60)*1;
			user.setBalance(user.getBalance()-price);
			return price;
		}
		if(bikeType.equalsIgnoreCase("ELECTRICAL")) {
			double price = (time/60)*2;
			user.setBalance(user.getBalance()-price);
			return price;
		}
		else {
			throw new Exception("Bike type is unknown !");
		}
	}

	@Override
	public String getName() {
		return name;
	}


}