package fr.centralesupelec.is1220.Station;

import fr.centralesupelec.is1220.Server.Point;
import fr.centralesupelec.is1220.User.Observer;

/**
 * <h1>StandardStationFactory</h1>
 * This class represents a standard
 * station which parks bikes and interact with users.
 */
public class StandardStationFactory extends AbstractStationFactory {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new standard station with a new ID, a given state, at a given time and a given point on the map
	 * @param state the state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @param time the time of beginning of service of the station.
	 * @param coordinates the coordinates of the station.
	 * @throws Exception thrown when the begin time or the state is misspelled. 
	 */
	public StandardStationFactory(String state, double time, Point coordinates) throws Exception {
		super(state, time, coordinates);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		int nbFreeSlots = 0;
		int nbOccupiedSlots =0;
		for (ParkingSlot slot : this.getParking()) {
			if (slot.getState().equalsIgnoreCase("FREE")) {
				nbFreeSlots++;
			}
			if (slot.getState().equalsIgnoreCase("OCCUPIED")) {
				nbOccupiedSlots++;
			} 
		}
		try {
			return "StandardStationFactory n°" + this.getId()+", state = "+this.getState()+", number of free slots = " + nbFreeSlots+", number of occupied slots = " + nbOccupiedSlots+", occupation = "+this.computeStationBalance(this.getTimeStart(), this.getTimeEnd());
		}
		catch (Exception e) {
			return "TimeEnd not defined";
		}
	}

	@Override
	public void notifyObservers() throws Exception {
		if (this.isChanged()) {
			for (Observer ob : this.getObservers()) {
				ob.update(this.getTimeRanted(),this.getBikeRented().getType(),"STANDARD",this.getObserverRanter());
			}
			this.setChanged(false);
		}

	}


}
