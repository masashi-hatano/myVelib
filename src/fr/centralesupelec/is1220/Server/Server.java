package fr.centralesupelec.is1220.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fr.centralesupelec.is1220.Bike.Bike;
import fr.centralesupelec.is1220.Station.AbstractStationFactory;
import fr.centralesupelec.is1220.Station.OccupiedStationComparator;
import fr.centralesupelec.is1220.Station.StationFactoryFactory;
import fr.centralesupelec.is1220.Station.UsedStationComparator;
import fr.centralesupelec.is1220.User.Observer;
import fr.centralesupelec.is1220.User.User;

/**
 * <h1>Server</h1>
 *This class represents the server
 *which acts as the interface with the user
 *and creates the instance of the problem.
 */
public class Server implements Serializable{
	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Dictionary of the users of the instance.
	 */
	private Map<Integer,User> users;
	/**
	 * Dictionary containing true if the users is on a ride and false if he is not.
	 */
	private Map<Integer,Boolean> onRide;
	/**
	 * Dictionary containing the times the users began their rides.
	 */
	private Map<Integer,Double> rideStart;
	/**
	 * Dictionary of the stations of the instance.
	 */
	private Map<Integer,AbstractStationFactory> stations;
	/**
	 * Dictionary containing the bikes gone on ride.
	 */
	private Map<Integer,Bike> bikeOnRide;
	/**
	 * Name of the instance.
	 */
	private String name;
	/**
	 * Length of the side of the square representing the area of the instance.
	 */
	private double dimension;

	/**
	 * Creates an instance of the problem composed of N stations (with 50% of them being plus stations), M slots per station, Nb Bikes (30% of them are electrical) in a square of side of a given length.
	 * @param name the name of the instance.
	 * @param nbStations the number of stations in the instance.
	 * @param nbSlots the number of slots per stations.
	 * @param dimension length of the side of the square.
	 * @param nbBikes the number of bikes in the instance.
	 * @throws Exception thrown if the configuration of stations, slots and bikes is impossible or if the dimension is negative.
	 */
	public Server(String name, int nbStations, int nbSlots,double dimension, int nbBikes) throws Exception {
		super();
		this.name =name;
		this.dimension = dimension;
		users = new HashMap<Integer, User>();
		stations = new HashMap<Integer,AbstractStationFactory>();
		bikeOnRide = new HashMap<Integer, Bike>();
		onRide = new HashMap<Integer, Boolean>();
		rideStart = new HashMap<Integer, Double>(); 
		if (nbStations < 0 || nbSlots < 0 || nbBikes < 0 || (nbStations == 0 & (nbSlots >0 || nbBikes >0)) || (dimension <= 0) || (nbBikes > nbSlots*nbStations) ) {
			throw new Exception("Invalid synthaxe");
		}
		Random random = new Random();

		for (int i =0;i< nbStations;i++) {
			Point coordinates = new Point(dimension*random.nextDouble(),dimension*random.nextDouble());
			double testPlus = random.nextDouble();
			StationFactoryFactory factory = new StationFactoryFactory();
			if (testPlus < 0.5) {
				AbstractStationFactory station = factory.createStation("PLUS","ONSERVICE", 0, coordinates);
				this.stations.put(station.getId(),station);
			}
			else {
				AbstractStationFactory station = factory.createStation("STANDARD","ONSERVICE", 0, coordinates);
				this.stations.put(station.getId(),station);
			}
		}
		for (AbstractStationFactory station : this.stations.values()) {
			for (int i =0;i< nbSlots;i++) {
				station.addParkingSlot("FREE", 0);
			}
		}

		ArrayList<String> bikeTypes= new ArrayList<String>();
		int nbElectrical = (int) Math.floor(nbBikes*0.3);
		for (int i =0;i< nbElectrical;i++) {
			bikeTypes.add("ELECTRICAL");
		}
		for (int i =0;i< nbBikes - nbElectrical;i++) {
			bikeTypes.add("MECHANICAL");
		}
		Collections.shuffle(bikeTypes);
		for (int i =0;i< nbBikes;i++) {
			int indice = random.nextInt(nbStations);
			while (!this.stations.get(stations.keySet().toArray()[indice]).isFree()) {
				indice = random.nextInt(nbStations);
			}
			this.stations.get(stations.keySet().toArray()[indice]).addBike(bikeTypes.get(i), 0);
		}
	}

	/**
	 * Gives the users of the instance.
	 * @return the users of the instance.
	 */
	public Map<Integer, User> getUsers() {
		return users;
	}

	/**
	 * Gives the users on ride of the instance.
	 * @return the users on ride of the instance.
	 */
	public Map<Integer, Boolean> getOnRide() {
		return onRide;
	}

	/**
	 * Gives the time of the start of the ride of the users of the instance.
	 * @return the time of the start of the ride of the users of the instance.
	 */
	public Map<Integer, Double> getRideStart() {
		return rideStart;
	}

	/**
	 * Gives the stations of the instance.
	 * @return the stations of the instance.
	 */
	public Map<Integer, AbstractStationFactory> getStations() {
		return stations;
	}

	/**
	 * Gives the bikes on ride of the instance.
	 * @return the bikes on ride of the instance.
	 */
	public Map<Integer, Bike> getBikeOnRide() {
		return bikeOnRide;
	}

	/**
	 * Gives the name of the instance.
	 * @return the name of the instance.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gives the length of the side of the square representing the area of the instance.
	 * @return the length of the side of the square representing the area of the instance.
	 */
	public double getDimension() {
		return dimension;
	}

	/**
	 * Add an user to the instance.
	 * @param name name of the user.
	 * @param cardType type of the card of the user : "NONE" for no card, "VLIBRE" for a vlibre card or "VMAX" for a vmax card.
	 * @param balance balance of the credit card of the user.
	 * @throws Exception thrown when the type of card of the user is misspelled.
	 */
	public void addUser(String name, String cardType,double balance) throws Exception {
		User user = new User(name,cardType,balance);
		this.users.put(user.getUserID(),user);
		this.onRide.put(user.getUserID(),false);
		this.rideStart.put(user.getUserID(),null);
		for (AbstractStationFactory station : this.stations.values()) {
			station.registerObserver(user);
		}
	}
	/**
	 * Allows an User to rent a Bike with a given type from a given station at a given time.
	 * @param userID the ID if the user who wants to rent a bike.
	 * @param bikeType the type of the bike rent : "MECHANICAL" for a mechanical bike or "ELECTRICAL" for an electrical bike.
	 * @param stationID the ID of the station where the bike is rent.
	 * @param time the time when the bike is rent.
	 * @return the ID of the bike rent.
	 * @throws Exception thrown when the bike's type, the user's ID, the station's ID or the time is misspelled or when the User have already a bike.
	 */
	public int rentBike(int userID,String bikeType,int stationID,double time) throws Exception {
		if (this.stations.containsKey(stationID)) {
			if (this.users.containsKey(userID)) {
				if (!this.onRide.get(userID)) {
					this.onRide.put(userID,true);
					this.rideStart.put(userID, time);
					Bike bike = this.stations.get(stationID).rentBike(bikeType, time);
					this.bikeOnRide.put(userID,bike);
					return bike.getId();
				}
				else {
					throw new Exception("User On Ride");
				}
			}
			else {
				throw new Exception("Invalid User ID");
			}
		}
		else {
			throw new Exception("Invalid Station ID");
		}
	}

	/**
	 * Allows an user to drop a bike he has rent, in an given station at a given time.
	 * @param userID the ID if the user who wants to drop a bike.
	 * @param stationID the ID of the station where the bike is dropped.
	 * @param time the time when the bike is dropped.
	 * @throws Exception thrown when the user's ID, the station's ID or the time is misspelled or when the User did not rent a bike.
	 */
	public void dropBike(int userID,int stationID,double time) throws Exception {
		if (this.stations.containsKey(stationID)) {
			if (this.users.containsKey(userID)) {
				if (this.bikeOnRide.containsKey(userID)) {
					this.onRide.put(userID, false);
					this.stations.get(stationID).dropBike(this.bikeOnRide.get(userID), time, (Observer) this.users.get(userID) , time - this.rideStart.get(userID));
					this.bikeOnRide.remove(userID);
				}
				else {
					throw new Exception("Bike not used");
				}
			}
			else {
				throw new Exception("Invalid User ID");
			}
		}
		else {
			throw new Exception("Invalid Station ID");
		}
	}

	/**
	 * Give information about an user.
	 * @param userID the ID of the user.
	 * @return information about the user.
	 * @throws Exception thrown when the ID of the user is misspelled.
	 */
	public String userToString(int userID) throws Exception {
		if (this.users.containsKey(userID)) {
			return this.users.get(userID).toString();
		}
		else {
			throw new Exception("Invalid User ID");
		}
	}
	/**
	 * Give information about a station.
	 * @param stationID the ID of the station.
	 * @return information about the station.
	 * @throws Exception thrown when the ID of the station is misspelled.
	 */
	public String stationToString(int stationID) throws Exception {
		if (this.stations.containsKey(stationID)) {
			return this.stations.get(stationID).toString();
		}
		else {
			throw new Exception("Invalid Station ID");
		}
	}

	/**
	 * Compute the distance between two Points.
	 * @param p1 first point.
	 * @param p2 second point.
	 * @return the distance.
	 */
	public double distance(Point p1,Point p2) {
		return Math.pow((p1.getX() - p2.getX()),2) + Math.pow((p1.getY()-p2.getY()), 2);
	}
	/**
	 * Compute a ride plan for an user at a given position who wants to go to a given destination with a given type of bike.
	 * @param userID the ID of the user.
	 * @param bikeType the type of the bike used : "MECHANICAL" for a mechanical bike or "ELECTRICAL" for an electrical bike.
	 * @param positionX the X coordinate of the position of the user.
	 * @param positionY the Y coordinate of the position of the user.
	 * @param destinationX the X coordinate of the destination of the user.
	 * @param destinationY the Y coordinate of the destination of the user.
	 * @return the ID of the station of depart of the user and the ID of the station of arrival of the user.
	 * @throws Exception thrown when the user'ID or the bike's type is misspelled or when the position or the destination of the user is unreachable.
	 */
	public ArrayList<Integer> planningRide(int userID,String bikeType,double positionX,double positionY,double destinationX,double destinationY) throws Exception {
		if (!this.users.containsKey(userID)) {
			throw new Exception("Invalid User ID");
		}
		if (positionX <0 || positionX > this.dimension || positionY <0 || positionY > this.dimension || destinationX <0 || destinationX > this.dimension || destinationY <0 || destinationY > this.dimension) {
			throw new Exception("Invalid coordonnees");
		}
		Point position = new Point(positionX,positionY);
		Point destination = new Point(destinationX,destinationY);
		double miniPosition = Double.POSITIVE_INFINITY;
		double miniDestination = Double.POSITIVE_INFINITY;
		ArrayList<Integer> plan = new ArrayList<Integer>();
		plan.add(null);
		plan.add(null);
		for (AbstractStationFactory station : this.stations.values()) {
			double distancePosition = distance(station.getCoordinates(),position);
			double distanceDestination = distance(station.getCoordinates(),destination);
			if (station.isThereBike(bikeType) & distancePosition < miniPosition & station.getState().equalsIgnoreCase("ONSERVICE")) {
				miniPosition = distancePosition;
				plan.set(0,station.getId());
			}
			if (station.isFree() & distanceDestination < miniDestination & station.getState().equalsIgnoreCase("ONSERVICE")) {
				miniDestination = distanceDestination;
				plan.set(1,station.getId());
			}
		}
		if (plan.get(0) == null || plan.get(1) == null) {
			throw new Exception("no right plan");
		}
		return plan;
	}

	/**
	 * Compute a sorted list of the station's ID of the instance given an certain sort policy.
	 * @param policy the sort policy used : "USED" for sorting the station by the most used and "OCCUPATION" for sorting the station by the most occupied.
	 * @return the sorted list of IDs.
	 * @throws Exception thrown when the policy is misspelled or when the end time of each station is not compute. 
	 */
	public ArrayList<Integer> sortStation(String policy) throws Exception {
		if (!(policy.equalsIgnoreCase("USED") || policy.equalsIgnoreCase("OCCUPATION"))) {
			throw new Exception("Invalid synthaxe");
		}
		ArrayList<AbstractStationFactory> sorted = new ArrayList<AbstractStationFactory>();
		for (AbstractStationFactory station : this.stations.values()) {
			sorted.add(station);
		}
		Comparator<AbstractStationFactory> comparator = null;
		if (policy.equalsIgnoreCase("USED")) {
			comparator = new UsedStationComparator();
		}
		else {
			comparator = new OccupiedStationComparator();
		}
		Collections.sort(sorted,comparator);
		ArrayList<Integer> plan = new ArrayList<Integer>();
		for (AbstractStationFactory station : sorted) {
			plan.add(station.getId());
		}
		return plan;
	}

	/**
	 * Set the time of end of service of a station which allowed to compute its occupation.
	 * @param time the end time of the station.
	 * @param stationID the ID of the station.
	 * @throws Exception thrown when the end time or the station's ID is misspelled.
	 */
	public void setEndTimeStation(double time,int stationID) throws Exception {
		if (this.stations.containsKey(stationID)) {
			this.stations.get(stationID).setTimeEnd(time);
		}
		else {
			throw new Exception("Invalid Station ID");
		}
	}
	/**
	 * Allow to change the state of the station.
	 * @param state the new state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @param stationID the ID of the station.
	 * @throws Exception thrown when the station's ID or the state is misspelled or when the station is already in this state.
	 */
	public void setStateStation(String state,int stationID) throws Exception {
		if (this.stations.containsKey(stationID)) {
			if (!((AbstractStationFactory) this.stations.get(stationID)).getState().equalsIgnoreCase(state)) {
				this.stations.get(stationID).setState(state);
			}
			else {
				throw new Exception("Station already in this state");
			}
		}
		else {
			throw new Exception("Invalid Station ID");
		}
	}

	/**
	 * Give information about the current state of the instance.
	 * @return information about the current state of the instance.
	 */
	@Override
	public String toString() {
		return "Server " + name +", dimension=" + dimension + ", users=" + users + ", stations=" + stations;
	}

}
