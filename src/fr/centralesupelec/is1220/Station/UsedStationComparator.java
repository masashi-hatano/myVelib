package fr.centralesupelec.is1220.Station;

import java.util.Comparator;

/**
 * <h1>UsedStationComparator</h1>
 * Comparator for sorting the station by the most used.
 */
public class UsedStationComparator implements Comparator<AbstractStationFactory> {

	@Override
	public int compare(AbstractStationFactory o1, AbstractStationFactory o2) {
		// TODO Auto-generated method stub
		return o2.getNbRents()+o2.getNbReturns() - o1.getNbRents() - o1.getNbReturns();
	}


}
