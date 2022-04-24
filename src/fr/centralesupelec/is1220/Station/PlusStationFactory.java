package fr.centralesupelec.is1220.Station;

import fr.centralesupelec.is1220.Server.Point;
import fr.centralesupelec.is1220.User.Observer;

/**
 * <h1>PlusStationFactory</h1>
 * This class represents a plus 
 * station which parks bikes and interact with users.
 */
public class PlusStationFactory extends AbstractStationFactory {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new plus station with a new ID, a given state, at a given time and a given point on the map
	 * @param state the state of the station : "ONSERVICE" for activate the station or "OFFLINE" for shut it down.
	 * @param time the time of beginning of service of the station.
	 * @param coordinates the coordinates of the station.
	 * @throws Exception thrown when the begin time or the state is misspelled. 
	 */
	public PlusStationFactory(String state, double time, Point coordinates) throws Exception {
		super(state, time,coordinates);
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
			return "PlusStationFactory n°" + this.getId()+", state = "+this.getState()+", number of free slots = " + nbFreeSlots+", number of occupied slots = " + nbOccupiedSlots+", occupation = "+ this.computeStationBalance(this.getTimeStart(), this.getTimeEnd());
		}
		catch (Exception e) {
			return "TimeEnd not defined";
		}
	}

	@Override
	public void notifyObservers() throws Exception {
		if (super.isChanged()) {
			for (Observer ob : this.getObservers()) {
				ob.update(this.getTimeRanted(),this.getBikeRented().getType(),"PLUS",this.getObserverRanter());
			}
			this.setChanged(false);
		}

	}

}
