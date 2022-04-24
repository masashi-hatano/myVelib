package fr.centralesupelec.is1220.User;

import java.io.Serializable;

/**
 * <h1>Vlibre</h1>
 * This class represents a Vlibre card.
 */
public class Vlibre implements Card,Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The type of the card.
	 */
	private String name;

	/**
	 * Creates a new Vlibre card.
	 */
	public Vlibre() {
		super();
		this.name = "Vlibre";
	}

	@Override
	public double computePrice(User user, double time, String bikeType) throws Exception {
		if (time <0) {
			throw new Exception("Invalid time");
		}
		if(time >= 60) {
			if(time-user.getCreditTime() > 60) {
				double reduced_time = time-user.getCreditTime()-60;
				user.setCreditTime(0);

				if(bikeType.equalsIgnoreCase("MECHANICAL")) {
					double price = (reduced_time/60)*1;
					user.setBalance(user.getBalance()-price);
					return price;
				}
				if(bikeType.equalsIgnoreCase("ELECTRICAL")) {
					double price = (reduced_time/60)*2+1;
					user.setBalance(user.getBalance()-price);
					return price;
				}
				else {
					throw new Exception("Bike type is unknown !");
				}
			}
			else {
				user.setCreditTime(user.getCreditTime()-time+60);
				if(bikeType.equalsIgnoreCase("MECHANICAL")) {
					return 0;
				}
				if(bikeType.equalsIgnoreCase("ELECTRICAL")) {
					user.setBalance(user.getBalance()-1);
					return 1;
				}
				else {
					throw new Exception("Bike type is unknown !");
				}
			}
		}
		else {
			if(bikeType.equalsIgnoreCase("MECHANICAL")) {
				return 0;
			}
			if(bikeType.equalsIgnoreCase("ELECTRICAL")) {
				double price = (time/60)*1;
				user.setBalance(user.getBalance()-price);
				return price;
			}
			else {
				throw new Exception("Bike type is unknown !");
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
