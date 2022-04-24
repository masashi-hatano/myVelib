package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.Station.AbstractStationFactory;
import fr.centralesupelec.is1220.Station.PlusStationFactory;
import fr.centralesupelec.is1220.Station.StationFactoryFactory;

class StationFactoryFactoryTest {

	@Test
	void whenAPlusStationIsAskedThenAPlusStationIsReturned() throws Exception {
		StationFactoryFactory factory = new StationFactoryFactory();
		AbstractStationFactory station = factory.createStation("PLUS", "ONSERVICE", 0, null);
		assertTrue(station instanceof PlusStationFactory);
	}

	@Test
	void whenAStandardStationIsAskedThenAStandardStationIsReturned() throws Exception {
		StationFactoryFactory factory = new StationFactoryFactory();
		AbstractStationFactory station = factory.createStation("PLUS", "ONSERVICE", 0, null);
		assertTrue(station instanceof PlusStationFactory);
	}

	@Test
	void whenTheStationTypeIsWrongThenAnExceptionIsThrown() throws Exception {
		StationFactoryFactory factory = new StationFactoryFactory();
		boolean test = false;
		try {
			AbstractStationFactory station = factory.createStation("saas", "ONSERVICE", 0, null);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

}
