package fr.centralesupelec.is1220.Station;

import java.io.Serializable;

import fr.centralesupelec.is1220.Server.Point;

/**
 * <h1>StationFactoryFactory</h1>
 * This class permits to construct stations.
 */
public class StationFactoryFactory implements Serializable{

	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a station constructor.
	 */
	public StationFactoryFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a station of a given type with a given state at a given place annd at a given Time.
	 * @param stationType the type of the station : "PLUS" for a plus station or "STANDARD" for a standard station.
	 * @param state the state of the station : "ONSERVICE" for a working station or "OFFLINE" for a closed station.
	 * @param time the time of opening of the station.
	 * @param coordinates the coordinates of the station on the map.
	 * @return the station.
	 * @throws Exception thrown when the station's type, the station's state or the time is misspelled.
	 */
	public AbstractStationFactory createStation(String stationType, String state,double time,Point coordinates) throws Exception {
		if (stationType.equalsIgnoreCase("PLUS")) {
			return new PlusStationFactory(state, time, coordinates);
		}
		if (stationType.equalsIgnoreCase("STANDARD")) {
			return new StandardStationFactory(state, time, coordinates);
		}
		throw new Exception("Invalid Synthaxe");
	}
}
