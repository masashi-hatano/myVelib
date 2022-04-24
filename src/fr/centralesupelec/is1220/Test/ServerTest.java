package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.Bike.Bike;
import fr.centralesupelec.is1220.Bike.ElectricalBike;
import fr.centralesupelec.is1220.Server.Server;
import fr.centralesupelec.is1220.Station.AbstractStationFactory;
import fr.centralesupelec.is1220.User.User;
import fr.centralesupelec.is1220.User.Vlibre;

class ServerTest {

	@Test
	void whenThereIsSlotsAndNoStationThenAnErrorIsThrown() {
		boolean test = false;
		try {
			Server server = new Server(null, 0, 1, 4, 0);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsBikesAndNoStationThenAnErrorIsThrown() {
		boolean test = false;
		try {
			Server server = new Server(null, 0, 0, 4, 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsToMuchBikesThenAnErrorIsThrown() {
		boolean test = false;
		try {
			Server server = new Server(null, 2, 1, 4, 3);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsAWrongDimensionThenAnErrorIsThrown() {
		boolean test = false;
		try {
			Server server = new Server(null, 1, 1, -1, 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAServerIsCreatedThenAllTheVariablesAreWright() throws Exception {
		Server server = new Server(null, 10, 10, 10, 40);
		int nbStation = server.getStations().size();
		boolean nbSlots = true;
		int nbBikes = 0;
		int nbBikeElectric = 0;
		boolean dimension = true;
		for (AbstractStationFactory station: server.getStations().values()) {
			if (station.getParking().size() != 10) {
				nbSlots = false;
			}
			if (station.getCoordinates().getX() > 10 || station.getCoordinates().getY() > 10) {
				dimension = false;
			}
			for (Bike bike : station.getBikes()) {
				if (bike != null) {
					nbBikes += 1;
				}
				if (bike instanceof ElectricalBike) {
					nbBikeElectric += 1;
				}
			}
		}
		assertTrue(nbSlots & dimension & nbStation == 10 & nbBikes == 40 & nbBikeElectric == (int) Math.floor(nbBikes*0.3));
	}

	@Test
	void whenYouAddAnUserWithTheWrongCardTypeThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		try {
			server.addUser(null, "adad", 10);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenYouAddAnUserThenHeHasTheRightCard() throws Exception {
		Server server = new Server(null, 1, 1, 4, 1);
		server.addUser(null, "VLIBRE", 10);
		boolean test = false;
		for (User user : server.getUsers().values()) {
			test = user.getCard() instanceof Vlibre;
		}
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		assertTrue(test & station.getObservers().size() == 1);
	}

	@Test
	void whenAWrongUserTryToRentABikeThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		try {
			server.rentBike(0, "MECHANICAL", station.getId(),1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAUserTryToRentABikeFromAWrongStationThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		try {
			server.rentBike(user.getUserID(), "MECHANICAL", 0,1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAUserTryToRentABikeThenTheBikeIsGone() throws Exception {
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		Bike bike = station.getBikes().get(0);
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(), 1);
		assertTrue(bikeID == bike.getId() & station.getParking().get(0).getState().equalsIgnoreCase("FREE") & ((Bike) server.getBikeOnRide().values().toArray()[0]).getId() == bikeID & ((boolean) server.getOnRide().values().toArray()[0]) == true & ((double) server.getRideStart().values().toArray()[0]) == 1);
	}

	@Test
	void whenAnUserOnRideTryToRentASecondBikeThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 2, 4, 2);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		server.rentBike(user.getUserID(), "MECHANICAL", station.getId(),1);
		try {
			server.rentBike(user.getUserID(), "MECHANICAL", station.getId(),2);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAWrongUserTryToDropABikeThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(),1);
		try {
			server.dropBike(0, station.getId(),2);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAUserTryToDropABikeToAWrongStationThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(),1);
		try {
			server.dropBike(user.getUserID(), 0,2);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAUserTryToDropABikeWithoutRentingOneThenAnErrorIsTrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		server.addUser(null, "VLIBRE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		try {
			server.dropBike(user.getUserID(), station.getId(),2);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenAUserTryToDropABikeThenTheBikeIsThere() throws Exception {
		Server server = new Server(null, 1, 1, 4, 1);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		Bike bike = station.getBikes().get(0);
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(), 1);
		server.dropBike(user.getUserID(), station.getId(),100);
		assertTrue(bikeID == bike.getId() & station.getParking().get(0).getState().equalsIgnoreCase("OCCUPIED") & server.getBikeOnRide().isEmpty() & ((boolean) server.getOnRide().values().toArray()[0]) == false & user.getBalance() < 10);
	}

	@Test
	void whenThereIsOnlyOneFreeSlotAndOneOccupiedSlotThenTheRightStationIsReturned() throws Exception {
		Server server = new Server(null, 2, 1, 10, 1);
		AbstractStationFactory station1 = (AbstractStationFactory) server.getStations().values().toArray()[0];
		AbstractStationFactory station2 = (AbstractStationFactory) server.getStations().values().toArray()[1];
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		ArrayList<Integer> plan = server.planningRide(user.getUserID(), "MECHANICAL", 0, 0, 10, 10);
		assertTrue((station1.getParking().get(0).getState().equalsIgnoreCase("OCCUPIED") & plan.get(0) == station1.getId() & plan.get(1) == station2.getId()) || (station2.getParking().get(0).getState().equalsIgnoreCase("OCCUPIED") & plan.get(0) == station2.getId() & plan.get(1) == station1.getId()));
	}

	@Test
	void whenThereAreFreeSlotsAndOccupiedSlotsThenTheRightStationsAreReturned() throws Exception {
		Server server = new Server(null, 10, 10, 4, 75);
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		ArrayList<Integer> plan = server.planningRide(user.getUserID(), "ELECTRICAL", 1, 1, 2, 2);
		assertTrue(plan.get(0) != null & plan.get(1) != null);
	}

	@Test
	void whenThereIsNoAvailableBikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 2, 1, 10, 1);
		AbstractStationFactory station1 = (AbstractStationFactory) server.getStations().values().toArray()[0];
		AbstractStationFactory station2 = (AbstractStationFactory) server.getStations().values().toArray()[1];
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		try {
			ArrayList<Integer> plan = server.planningRide(user.getUserID(), "ELECTRICAL", 0, 0, 10, 10);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsNoFreeSlotThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		Server server = new Server(null, 2, 1, 10, 2);
		AbstractStationFactory station1 = (AbstractStationFactory) server.getStations().values().toArray()[0];
		AbstractStationFactory station2 = (AbstractStationFactory) server.getStations().values().toArray()[1];
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		try {
			ArrayList<Integer> plan = server.planningRide(user.getUserID(), "MECHANICAL", 0, 0, 10, 10);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenYouWantToSortTheStationByUsedThenTheCorrectListIsReturned() throws Exception {
		Server server = new Server(null, 2, 1, 4, 2);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		AbstractStationFactory station2 = (AbstractStationFactory) server.getStations().values().toArray()[1];
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(), 1);
		ArrayList<Integer> sortedList = server.sortStation("USED");
		assertTrue(sortedList.get(0) == station.getId() & sortedList.get(1) == station2.getId());
	}

	@Test
	void whenYouWantToSortTheStationByOccupationThenTheCorrectListIsReturned() throws Exception {
		Server server = new Server(null, 2, 1, 4, 2);
		AbstractStationFactory station = (AbstractStationFactory) server.getStations().values().toArray()[0];
		AbstractStationFactory station2 = (AbstractStationFactory) server.getStations().values().toArray()[1];
		server.addUser(null, "NONE", 10);
		User user = (User) server.getUsers().values().toArray()[0];
		int bikeID = server.rentBike(user.getUserID(), "MECHANICAL", station.getId(), 1);
		server.setEndTimeStation(2, station.getId());
		server.setEndTimeStation(2, station2.getId());
		ArrayList<Integer> sortedList = server.sortStation("OCCUPATION");
		assertTrue(sortedList.get(0) == station2.getId() & sortedList.get(1) == station.getId());
	}


}
