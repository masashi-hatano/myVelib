package fr.centralesupelec.is1220.Station;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h1>PakingSlot</h1>
 * This class represents the slots within 
 * a station which will welcome bikes.
 */
public class ParkingSlot implements Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the slot.
	 */
	private int id;
	/**
	 * The state of the slot : "FREE" for a free slot, "OCCUPIED" for an occupied slot or "OOO" for an out-of-order slot.
	 */
	private String state;
	/**
	 * An ArrayList representing the key times of the history of the slot.
	 */
	private ArrayList<Double> historyTime;
	/**
	 * An ArrayList representing the key states of the history of the slot.
	 */
	private ArrayList<String> historyState;

	/**
	 * Creates a new slot with a given ID, state and time of creation.
	 * @param id the ID of the slot.
	 * @param state the state of the slot : "FREE" for a free slot, "OCCUPIED" for an occupied slot or "OOO" for an out-of-order slot.
	 * @param time the time of creation of the slot.
	 * @throws Exception thrown when the state or the time is misspelled.
	 */
	public ParkingSlot(int id,String state,double time) throws Exception {
		super();
		if (time <0 || !(state.equalsIgnoreCase("FREE") || state.equalsIgnoreCase("OCCUPIED") || state.equalsIgnoreCase("OOO"))) {
			throw new Exception("Invalid syntaxe");
		}
		this.id = id;
		this.state = state;
		this.historyTime = new ArrayList<Double>();
		this.historyTime.add((Double) time);
		this.historyState = new ArrayList<String>();
		this.historyState.add(state);
	}

	/**
	 * Get the ID of the slot.
	 * @return the ID of the slot.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the state of the slot if and only if the state change.
	 * @param state the new state of the slot.
	 * @param time the time of the update.
	 * @throws Exception thrown when the state or the time is misspelled.
	 */
	public void setState(String state, double time) throws Exception {
		if (time <0 || time < this.historyTime.get(this.historyTime.size()-1) || !(state.equalsIgnoreCase("FREE") || state.equalsIgnoreCase("OCCUPIED") || state.equalsIgnoreCase("OOO"))) {
			throw new Exception("Invalid syntaxe");
		}
		if (this.state != state) {
			this.state = state;
			this.historyState.add(state);
			this.historyTime.add((Double) time);
		}
	}

	/**
	 * Get the ArrayList representing the key times of the history of the slot.
	 * @return the ArrayList representing the key times of the history of the slot.
	 */
	public ArrayList<Double> getHistoryTime() {
		return historyTime;
	}

	/**	
	 * Get the ArrayList representing the key states of the history of the slot.
	 * @return the ArrayList representing the key states of the history of the slot.
	 */
	public ArrayList<String> getHistoryState() {
		return historyState;
	}

	/**
	 * Get the state of the slot : "FREE" for a free slot, "OCCUPIED" for an occupied slot or "OOO" for an out-of-order slot.
	 * @return the state of the slot : "FREE" for a free slot, "OCCUPIED" for an occupied slot or "OOO" for an out-of-order slot.
	 */
	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "ParkingSlot n°" + id + "is" + state;
	}

	/**
	 * Compute the occupation of the slot in a given time window.
	 * @param timeStart the beginning of the window.
	 * @param timeEnd the end of the window.
	 * @return the occupation of the slot in a given time window.
	 * @throws Exception thrown when the window is wrongly define.
	 */
	public double computeOccupation(double timeStart,double timeEnd) throws Exception {
		if (timeStart <0 || timeEnd <0 || timeStart > timeEnd) {
			throw new Exception("Invalid syntaxe");
		}
		double deltaTime =0;
		for (int i = 0;i < this.historyTime.size();i++) {
			double time1 = this.historyTime.get(i);
			String state1 = this.historyState.get(i);
			if (!state1.equalsIgnoreCase("FREE")) {
				if (i < this.historyTime.size() -1) {
					double time2 = this.historyTime.get(i+1);
					double timeOpen = Math.max(time1,timeStart);
					double timeClose = Math.min(time2, timeEnd);
					deltaTime += Math.max(0, timeClose/10000000-timeOpen/10000000);
				}
				else {
					double timeOpen = Math.max(time1,timeStart);
					deltaTime += Math.max(0, timeEnd/10000000-timeOpen/10000000);
				}
			}
		}
		return deltaTime;
	}



}
