package fr.centralesupelec.is1220.Station;

import java.io.Serializable;
import java.util.ArrayList;

import fr.centralesupelec.is1220.Bike.Bike;
import fr.centralesupelec.is1220.Bike.ElectricalBike;
import fr.centralesupelec.is1220.Bike.MechanicalBike;
import fr.centralesupelec.is1220.Server.Point;
import fr.centralesupelec.is1220.User.Observer;

/**
 * <h1>AbstractStationFactory</h1>
 * This class is an abstract model
 * for the Factory station which parks bikes and interact with users.
 */
public abstract class AbstractStationFactory implements Observable,Serializable {
	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ArrayList of the slots in the parking.
	 */
	private ArrayList<ParkingSlot> parking;
	/**
	 * The ArrayList of the bikes in each slots of the parking.
	 */
	private ArrayList<Bike> bikes;
	/**
	 * The ArrayList of the observers of the station.
	 */
	private ArrayList<Observer> observers;
	/**
	 * The boolean representing an update in the station.
	 */
	private boolean changed;
	/**
	 * The last bike returned.
	 */
	private Bike bikeRented;
	/**
	 * The last observer who returned a bike.
	 */
	private Observer observerRanter;
	/**
	 * The ride time of the last observer who returned a bike.
	 */
	private double TimeRanted;
	/**
	 * The number of rents done in the station.
	 */
	private int nbRents;
	/**
	 * The number of returns done in the station.
	 */
	private int nbReturns;
	/**
	 * The state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 */
	private String state;
	/**
	 * The counter which allowed to compute the id of the station.
	 */
	private static int counterStations = 0;
	/**
	 * The ID of the station.
	 */
	private int id;
	/**
	 * The counter which allowed to compute the id of the slots in the station.
	 */
	private int counterSlots;
	/**
	 * The time of beginning of service of the station.
	 */
	private double timeStart;
	/**
	 * The time of ending of service of the station.
	 */
	private double timeEnd;
	/**
	 * The coordinates of the station.
	 */
	private Point coordinates;

	/**
	 * Creates a new Factory with a new ID, a given state, at a given time and a given point on the map
	 * @param state the state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @param time the time of beginning of service of the station.
	 * @param coordinates the coordinates of the station.
	 * @throws Exception thrown when the begin time or the state is misspelled. 
	 */
	public AbstractStationFactory(String state,double time,Point coordinates) throws Exception {
		super();
		if (time <0 || !(state.equalsIgnoreCase("ONSERVICE") || state.equalsIgnoreCase("OFFLINE"))) {
			throw new Exception("Invalid syntaxe");
		}
		this.parking = new ArrayList<ParkingSlot>();
		this.state = state;
		this.bikes = new ArrayList<Bike>();
		this.observers = new ArrayList<Observer>();
		this.changed = false;
		this.nbRents = 0;
		this.nbReturns =0;
		counterStations ++;
		this.id = counterStations;
		this.counterSlots = 0;
		this.timeStart = time;
		this.timeEnd = -1;
		this.coordinates = coordinates;
	}

	/**
	 * Get the coordinates of the station.
	 * @return the coordinates of the station.
	 */
	public Point getCoordinates() {
		return coordinates;
	}

	/**
	 * Set the coordinates of the station.
	 * @param coordinates the coordinates of the station.
	 */
	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Get the time of beginning of service of the station.
	 * @return the time of beginning of service of the station.
	 */
	public double getTimeStart() {
		return timeStart;
	}

	/**
	 * Get the time of ending of service of the station.
	 * @return the time of ending of service of the station.
	 */
	public double getTimeEnd() {
		return timeEnd;
	}

	/**
	 * Set the time of ending of service of the station.
	 * @param timeEnd the time of ending of service of the station.
	 * @throws Exception thrown when the time of ending is impossible.
	 */
	public void setTimeEnd(double timeEnd) throws Exception {
		if (timeEnd < 0) {
			throw new Exception("Invalid syntaxe");
		}
		for (ParkingSlot parkingSlot : this.parking )
			if (parkingSlot.getHistoryTime().get(parkingSlot.getHistoryTime().size() -1) > timeEnd) {
				throw new Exception("Invalid syntaxe");
			}
		this.timeEnd = timeEnd;
	}

	/**
	 * Get the ArrayList of the slots in the parking.
	 * @return the ArrayList of the slots in the parking.
	 */
	public ArrayList<ParkingSlot> getParking() {
		return parking;
	}

	/**
	 * Get the ArrayList of the bikes in the parking.
	 * @return the ArrayList of the bikes in the parking.
	 */
	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	/**
	 * Get the ArrayList of the observers of the station.
	 * @return the ArrayList of the observers of the station.
	 */
	public ArrayList<Observer> getObservers() {
		return observers;
	}

	/**
	 * Get the number of rents done in the station.
	 * @return the number of rents done in the station.
	 */
	public int getNbRents() {
		return nbRents;
	}

	/**
	 * Get the number of returns done in the station.
	 * @return the number of returns done in the station.
	 */
	public int getNbReturns() {
		return nbReturns;
	}

	/**
	 * Get the state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @return the state of the station.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Get the ID of the station.
	 * @return the ID of the station.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the boolean representing an update in the station.
	 * @return the boolean representing an update in the station.
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Set the boolean representing an update in the station.
	 * @param changed the boolean representing an update in the station.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Get the last bike returned.
	 * @return the last bike returned.
	 */
	public Bike getBikeRented() {
		return bikeRented;
	}

	/**
	 * Get the last observer who returned a bike.
	 * @return the last observer who returned a bike.
	 */
	public Observer getObserverRanter() {
		return observerRanter;
	}

	/**
	 * Get the ride time of the last observer who returned a bike.
	 * @return the ride time of the last observer who returned a bike.
	 */
	public double getTimeRanted() {
		return TimeRanted;
	}

	/**
	 * Set the state of the station.
	 * @param state the state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @throws Exception thrown when the station id misspelled.
	 */
	public void setState(String state) throws Exception {
		if (!(state.equalsIgnoreCase("ONSERVICE") || state.equalsIgnoreCase("OFFLINE"))){
			throw new Exception("Invalid syntaxe");
		}
		this.state = state;
	}
	/**
	 * Allow to add a slot to the parking with a given state at a given time. 
	 * @param state the state of the new slot : "FREE" for a free slot or "OOO" for an out-of-order slot.
	 * @param time the time of adding.
	 * @throws Exception thrown when the state or the time is misspelled.
	 */
	public void addParkingSlot(String state,double time) throws Exception {
		if (state.equalsIgnoreCase("OCCUPIED")) {
			throw new Exception("Invalid State Occupied");
		}
		this.counterSlots++;
		ParkingSlot parkingSlot = new ParkingSlot(this.counterSlots, state, time);
		this.parking.add(parkingSlot);
		this.bikes.add(null);
	}

	/**
	 * Allow to add a bike with a given type in the parking at a given time. 
	 * @param bikeType the type of the new bike : "MECHANICAL" for a mechanical bike or "ELECTRICAL" for an electrical bike.
	 * @param time the time of adding.
	 * @throws Exception thrown when the type or the time is misspelled or when there is no slot available.
	 */
	public void addBike(String bikeType,double time) throws Exception {
		if (!(bikeType.equalsIgnoreCase("ELECTRICAL") || bikeType.equalsIgnoreCase("MECHANICAL"))){
			throw new Exception("Invalid syntaxe");
		}
		boolean testSlots = true;
		for (int i=0; i < this.parking.size(); i++) {
			if (this.parking.get(i).getState().equalsIgnoreCase("FREE")) {
				testSlots = false;
				this.parking.get(i).setState("OCCUPIED", time);
				if (bikeType.equalsIgnoreCase("ELECTRICAL")) {
					ElectricalBike bike = new ElectricalBike();
					this.bikes.set(i,bike);
				}
				else {
					MechanicalBike bike = new MechanicalBike();
					this.bikes.set(i,bike);
				}
				break;
			}
		}
		if (testSlots) {
			throw new Exception("No Slot Available");
		}
	}

	/**
	 * Allow to rent a bike with a given type from the parking at a given time.  
	 * @param type the type of the new bike : "MECHANICAL" for a mechanical bike or "ELECTRICAL" for an electrical bike.
	 * @param time the time of renting.
	 * @return the ID of the bike rent.
	 * @throws Exception thrown when the time or the bike's type is misspelled or when there is no available bike.
	 */
	public Bike rentBike(String type,double time) throws Exception {
		if (this.getState().equalsIgnoreCase("OFFLINE")) {
			throw new Exception("Offline");
		}
		if (!(type.equalsIgnoreCase("MECHANICAL") || type.equalsIgnoreCase("ELECTRICAL"))){
			throw new Exception("Invalid syntaxe");
		}
		boolean testBike = true;
		for (int i=0;i<this.parking.size();i++) {
			if (this.parking.get(i).getState().equalsIgnoreCase("OCCUPIED"))
				if (this.bikes.get(i) instanceof MechanicalBike) {
					MechanicalBike bike = (MechanicalBike) this.bikes.get(i);

					if (type.equalsIgnoreCase(bike.getType())) {
						this.bikes.set(i,null);
						this.parking.get(i).setState("FREE", time);
						testBike = false;
						this.nbRents ++;
						return bike;
					}
				}
			if (this.bikes.get(i) instanceof ElectricalBike) {
				ElectricalBike bike = (ElectricalBike) this.bikes.get(i);

				if (type.equalsIgnoreCase(bike.getType())) {
					this.bikes.set(i,null);
					this.parking.get(i).setState("FREE", time);
					testBike = false;
					this.nbRents ++;
					return bike;
				}
			}
		}
		if (testBike) {
			throw new Exception("No Bike Available");
		}
		return null;
	}

	/**
	 * Allow a given observer to drop a bike in the parking at a given time. 
	 * @param bike the bike dropped.
	 * @param time the time of dropping.
	 * @param observer the observer who wants to drop a bike.
	 * @param timeRide the time of ride of the observer before renting.
	 * @throws Exception thrown when the station is offline, when the time of dropping or the time of ride is misspelled or when there is no slot available.   
	 */
	public void dropBike(Bike bike,double time,Observer observer,double timeRide) throws Exception {
		if (this.getState().equalsIgnoreCase("OFFLINE")) {
			throw new Exception("Offline");
		}
		if (timeRide <0) {
			throw new Exception("Invalid Synthaxe");
		}
		boolean testSlots = true;
		for (int i=0; i < this.parking.size(); i++) {
			if (this.parking.get(i).getState().equalsIgnoreCase("FREE")) {
				testSlots = false;
				this.parking.get(i).setState("OCCUPIED", time);
				this.bikes.set(i,bike);
				this.nbReturns++;
				this.changed = true;
				this.bikeRented = bike;
				this.observerRanter = observer;
				this.TimeRanted = timeRide;
				this.notifyObservers();
				break;
			}
		}
		if (testSlots) {
			throw new Exception("No Slot Available");
		}
	}

	/**
	 * Allow to set the state of a slot at a given time.
	 * @param slotID the ID of the slot.
	 * @param state the state of the slot: "FREE" for free to used or "OOO" for out-of-order. 
	 * @param time the time the state is change.
	 * @throws Exception thrown when the state, the slot's ID or the time is misspelled.
	 */
	public void setSlotState(int slotID,String state,double time) throws Exception {
		boolean testSlot = true;
		if (state.equalsIgnoreCase("OCCUPIED")) {
			throw new Exception("Invalid State Occupied");
		}
		for (ParkingSlot slot: this.getParking()) {
			if (slot.getId() == slotID) {
				slot.setState(state, time);
				testSlot = false;
			}
		}
		if (testSlot) {
			throw new Exception("No Slot With That ID");
		}
	}
	/**
	 * Computes the Occupation of the station on a given time window.
	 * @param timeStart the beginning of the window.
	 * @param timeEnd the end of the window.
	 * @return the occupation of the station.
	 * @throws Exception thrown when the time window is badly define.
	 */
	public double computeStationBalance(double timeStart,double timeEnd) throws Exception {
		double deltaTime =0;
		for (ParkingSlot parkingSlot : this.parking) {
			deltaTime += parkingSlot.computeOccupation(timeStart, timeEnd);
		}
		if (this.parking.size() >0) {
			deltaTime = deltaTime / this.parking.size();
		}
		return deltaTime;
	}
	/**
	 * Tests if the station have a free slot.
	 * @return a boolean representing if the station have a free slot.
	 */
	public boolean isFree() {
		for (ParkingSlot slot :this.parking) {
			if (slot.getState().equalsIgnoreCase("FREE")) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Tests if the station have a bike of a given type.
	 * @param bikeType the type of the bike : "MECHANICAL" for a mechanical bike or "ELECTRICAL" for an electrical bike.
	 * @return a boolean representing if the station have a bike of a given type.
	 */
	public boolean isThereBike(String bikeType) {
		for (Bike bike :this.bikes) {
			if ((bikeType.equalsIgnoreCase("ELECTRICAL") & bike instanceof ElectricalBike) || (bikeType.equalsIgnoreCase("MECHANICAL") & bike instanceof MechanicalBike)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);	
	}

	@Override
	public abstract void notifyObservers() throws Exception;

	/**
	 * Give information about the station.
	 * @return information about the station.
	 */
	@Override
	public String toString() {
		return "AbstractStationFactory state=" + state;
	}

}
