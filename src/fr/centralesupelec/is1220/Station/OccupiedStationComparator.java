package fr.centralesupelec.is1220.Station;	

import java.util.Comparator;

/**
 * <h1>OccupiedStationComparator</h1>
 * Comparator for sorting the station by the most occupied.
 */
public class OccupiedStationComparator implements Comparator<AbstractStationFactory> {

	@Override
	public int compare(AbstractStationFactory o1, AbstractStationFactory o2) {
		// TODO Auto-generated method stub
		try {
			return ((Double) o2.computeStationBalance(o2.getTimeStart(), o2.getTimeEnd())).compareTo((Double) o1.computeStationBalance(o1.getTimeStart(), o1.getTimeEnd()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

}
