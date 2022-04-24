package fr.centralesupelec.is1220.User;

import java.io.Serializable;

import fr.centralesupelec.is1220.Server.Point;

/**
 * <h1>User</h1>
 * This class represents an user
 * of the instance who will interact with
 * the server to rent and drop bikes.
 */
public class User implements Observer,Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the user.
	 */
	private String name;
	/**
	 * A counter which will help to compute the ID of the user.
	 */
	private static int counterID=0;
	/**
	 * The ID of the user.
	 */
	private int userID;
	/**
	 * The coordinates of the user.
	 */
	private Point point=null;
	/**
	 * The Card of the user.
	 */
	private Card card;
	/**
	 * The credit time of the user.
	 */
	private double creditTime;
	/**
	 * The balance of the user.
	 */
	private double balance;
	/**
	 * The number of rides done by the user.
	 */
	private int nb_rides;
	/**
	 * The total time ride by the user.
	 */
	private double total_time;
	/**
	 * The total charges the user payed.
	 */
	private double total_charge;
	/**
	 * The total credit time the user earned.
	 */
	private double total_creditTime;

	/**
	 * Creates a new user with a given name, card and balance.
	 * @param name the name of the user.
	 * @param cardType the type of the card of the user : "NONE" for no card, "VLIBRE" for a vlibre card or "VMAX" for a vmax card.
	 * @param balance the balance of the user.
	 * @throws Exception thrown when the balance is negative.
	 */
	public User(String name, String cardType, double balance) throws Exception {
		super();
		if (!(cardType.equalsIgnoreCase("NONE") || cardType.equalsIgnoreCase("VLIBRE") || cardType.equalsIgnoreCase("VMAX"))) {
			throw new Exception("Invalid synthaxe");
		}
		if (balance <0) {
			throw new Exception("Invalid Synthaxe");
		}
		this.name = name;
		counterID++;
		this.userID = counterID;
		Card card = null;
		if (cardType.equalsIgnoreCase("NONE")) {
			card = new NoCard();
		} 
		if (cardType.equalsIgnoreCase("VLIBRE")) {
			card = new Vlibre();
		}
		if (cardType.equalsIgnoreCase("VMAX")) {
			card = new Vmax();
		}
		this.card = card;
		this.creditTime = 0;
		this.balance = balance;
		this.nb_rides = 0;
		this.total_time = 0;
		this.total_charge = 0;
		this.total_creditTime = 0;
	}

	@Override
	public void update(double time, String bikeType, String stationType,Observer observer) throws Exception {
		if (!(stationType.equalsIgnoreCase("PLUS") || stationType.equalsIgnoreCase("STANDARD") || bikeType.equalsIgnoreCase("ELECTRICAL") || bikeType.equalsIgnoreCase("MECHANICAL"))) {
			throw new Exception("Invalid Synthaxe");
		}
		if (observer instanceof User) {
			User user = (User) observer;
			if (user.getUserID() == this.userID) {
				double price = this.card.computePrice(this, time, bikeType);
				double earnedcredit = 0;

				if(this.card instanceof Vlibre && stationType.equalsIgnoreCase("PLUS")) {
					this.creditTime += 5;
					earnedcredit = 5;
				}

				this.nb_rides += 1;
				this.total_time += time;
				this.total_charge += price;
				this.total_creditTime += earnedcredit;
			}
		}
	}

	/**
	 * Give information about the user.
	 * @return information about the user.
	 */
	@Override 
	public String toString() {
		return "[Username: "+this.name+"]" + "[UserID: "+this.userID+"]" + "[Card type : "+this.card.getName()+"]" 
				+ "[Credit time : "+this.creditTime+"]" + "[Balance : "+this.balance+"]" + "[Number of rides : "+this.getNb_rides()+"]"
				+ "[Total ride time : "+this.getTotal_time()+"]" + "[Total amount of charge : "+this.getTotal_charge()+"]"
				+ "[Total amount of credit time : "+this.getTotal_creditTime()+"]";
	}

	/**
	 * Get the name of the user.
	 * @return the name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the user.
	 * @param name the name of the user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the ID of the user.
	 * @return the ID of the user.
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Set the ID of the user.
	 * @param userID the ID of the user.
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Get the coordinates of the user.
	 * @return the coordinates of the user.
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * Set the coordinates of the user.
	 * @param point the coordinates of the user.
	 */
	public void setPosition(Point point) {
		this.point = point;
	}

	/**
	 * Get the Card of the user.
	 * @return the Card of the user.
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Set the Card of the user.
	 * @param card the Card of the user.
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Get the credit time of the user.
	 * @return the credit time of the user.
	 */
	public double getCreditTime() {
		return creditTime;
	}

	/**
	 * Set the credit time of the user.
	 * @param creditTime the credit time of the user.
	 */
	public void setCreditTime(double creditTime) {
		this.creditTime = creditTime;
	}

	/**
	 * Get the balance of the user.
	 * @return the balance of the user.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Set the balance of the user.
	 * @param balance the balance of the user.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Get the number of rides done by the user.
	 * @return the number of rides done by the user.
	 */
	public int getNb_rides() {
		return nb_rides;
	}

	/**
	 * Set the number of rides done by the user.
	 * @param nb_rides the number of rides done by the user.
	 */
	public void setNb_rides(int nb_rides) {
		this.nb_rides = nb_rides;
	}

	/**
	 * Get the total time ride by the user.
	 * @return the total time ride by the user.
	 */
	public double getTotal_time() {
		return total_time;
	}

	/**
	 * Set the total time ride by the user.
	 * @param total_time the total time ride by the user.
	 */
	public void setTotal_time(double total_time) {
		this.total_time = total_time;
	}

	/**
	 * Get the total charges the user payed.
	 * @return the total charges the user payed.
	 */
	public double getTotal_charge() {
		return total_charge;
	}

	/**
	 * Set the total charges the user payed.
	 * @param total_charge the total charges the user payed.
	 */
	public void setTotal_charge(double total_charge) {
		this.total_charge = total_charge;
	}

	/**
	 * Get the total credit time the user earned.
	 * @return the total credit time the user earned.
	 */
	public double getTotal_creditTime() {
		return total_creditTime;
	}

	/**
	 * Set the total credit time the user earned.
	 * @param total_creditTime the total credit time the user earned.
	 */
	public void setTotal_creditTime(double total_creditTime) {
		this.total_creditTime = total_creditTime;
	}

}