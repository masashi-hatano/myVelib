package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.Bike.Bike;
import fr.centralesupelec.is1220.Bike.ElectricalBike;
import fr.centralesupelec.is1220.Bike.MechanicalBike;
import fr.centralesupelec.is1220.Station.ParkingSlot;
import fr.centralesupelec.is1220.Station.PlusStationFactory;
import fr.centralesupelec.is1220.Station.StandardStationFactory;
import fr.centralesupelec.is1220.User.User;

class AbstractStationFactoryTest {

	@Test
	void whenTheStateIsNotCorrectThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		try {
			StandardStationFactory station = new StandardStationFactory("zfs", 0,null);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenTheTimeIsNotCorrectThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		try {
			StandardStationFactory station = new StandardStationFactory("ONSERVICE", -1,null);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void when2StationsOfDifferentClassesAreCreatingThenTheirIDAreDifferent() throws Exception {
		StandardStationFactory station1 = new StandardStationFactory("ONSERVICE",0,null);
		PlusStationFactory station2 = new PlusStationFactory("ONSERVICE",0,null);
		assertTrue(station1.getId() != station2.getId());
	}

	@Test
	void when2StationsOfSameClassesAreCreatingThenTheirIDIsDifferent() throws Exception {
		StandardStationFactory station1 = new StandardStationFactory("ONSERVICE",0,null);
		StandardStationFactory station2 = new StandardStationFactory("ONSERVICE",0,null);
		assertTrue(station1.getId() != station2.getId());
	}

	@Test
	void whenTheEndTimeIsSetLowerThanTheLastUpdateOfAParkingSlotThenAnErrorisThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station1 = new StandardStationFactory("ONSERVICE",0,null);
		station1.addParkingSlot("OOO",1);
		try {
			station1.setTimeEnd(0);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenTheStateIsNotCorrectlyUpdateThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		try {
			station.setState("ede");
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenASlotOccupiedIsAddedThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		try {
			station.addParkingSlot("OCCUPIED", 0);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void when2PArkingSlotsAreAddedToTheSameStationThenTheirIDAreDifferent() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("OOO", 0);
		ParkingSlot parkingSlot1 = station.getParking().get(0);
		ParkingSlot parkingSlot2 = station.getParking().get(1);
		assertTrue(parkingSlot1.getId()==1 & parkingSlot2.getId()==2);
	}

	@Test
	void when2PArkingSlotsAreAddedToDifferentStationThenTheirIDAreTheSame() throws Exception {
		StandardStationFactory station1 = new StandardStationFactory("ONSERVICE", 0,null);
		StandardStationFactory station2 = new StandardStationFactory("ONSERVICE", 0,null);
		station1.addParkingSlot("OOO", 0);
		station2.addParkingSlot("OOO", 0);
		ParkingSlot parkingSlot1 = station1.getParking().get(0);
		ParkingSlot parkingSlot2 = station2.getParking().get(0);
		assertTrue(parkingSlot1.getId()==1 & parkingSlot2.getId()==1);
	}

	@Test
	void whenThereIsNoSlotAvailableAndYouWantToAddABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		try {
			station.addBike("ELECTRICAL", 0);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenTheTypeOfTheBikeWhenAddedIsWrightThenTheBikeIsOfTheRightTypeAndTheSlotsAreOccupied() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("FREE", 0);
		station.addParkingSlot("FREE", 0);
		station.addParkingSlot("FREE", 0);
		station.addBike("ELECTRICAL", 0);
		station.addBike("MECHANICAL", 0);
		ParkingSlot slot1 = station.getParking().get(0);
		ParkingSlot slot2 = station.getParking().get(1);
		ParkingSlot slot3 = station.getParking().get(2);
		Bike bike1 = station.getBikes().get(0);
		Bike bike2 = station.getBikes().get(1);
		Bike bike3 = station.getBikes().get(2);
		assertTrue(slot1.getState() == "OCCUPIED" & slot2.getState() == "OCCUPIED" & slot3.getState() == "FREE" & bike1 instanceof ElectricalBike & bike2 instanceof MechanicalBike & bike3 == null);
	}

	@Test
	void whenThereIsNoSlotAvailableAndYouWantToRentABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("FREE",0);
		try {
			station.rentBike("ELECTRICAL", 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsNoBikeOfTheRightTypeAndYouWantToRentABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("FREE",0);
		station.addBike("MECHANICAL", 0);
		try {
			station.rentBike("ELECTRICAL", 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsABikeOfTheRightTypeAndYouWantToRentABikeThenYouGetTheRightBikeAndTheSlotIsSetFreeAndNbRentGrows() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("FREE",0);
		station.addBike("ELECTRICAL", 0);
		Bike bike = station.rentBike("ELECTRICAL", 1);
		ParkingSlot slot = station.getParking().get(1);
		assertTrue(slot.getState() == "FREE" & bike instanceof ElectricalBike & station.getNbRents() == 1);
	}

	@Test
	void whenThereIsNoFreeSlotAndYouWantToDropABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("FREE",0);
		station.addBike("ELECTRICAL", 0);
		ElectricalBike bike = new ElectricalBike();
		User user = new User(null, "none", 0);
		station.registerObserver(user);
		try {
			station.dropBike(bike, 1, user,1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenThereIsAFreeSlotAndYouWantToDropABikeThenTheBikeIsDroppedTheSlotBecomeOccupiedNbDropsGrowsAndUsesrAreNotified() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		station.addParkingSlot("FREE",0);
		ElectricalBike bike = new ElectricalBike();
		User user = new User(null, "NONE", 100);
		station.registerObserver(user);
		station.dropBike(bike, 1, user,1);
		ParkingSlot slot = station.getParking().get(1);
		Bike newbike = station.getBikes().get(1);
		assertTrue(newbike.getId() == bike.getId() & slot.getState() == "OCCUPIED" & station.getNbReturns() == 1 & user.getBalance() < 100);
	}

	@Test
	void whenTheStationIsOfflineAndYouWantToRentABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("OFFLINE", 0,null);
		station.addParkingSlot("FREE",0);
		station.addBike("ELECTRICAL", 0);
		try {
			station.rentBike("ELECTRICAL", 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenTheStationIsOfflineAndYouWantToDropABikeThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("OFFLINE", 0,null);
		station.addParkingSlot("FREE",0);
		ElectricalBike bike = new ElectricalBike();
		try {
			station.dropBike(bike, 1, null,1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenYouWantToChangeTheStateOfASlotNotInTheStationThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("OFFLINE", 0,null);
		station.addParkingSlot("FREE",0);
		try {
			station.setSlotState(2, "OOO", 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenYouWantToChangeTheStateOfASlotByOccupiedThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		StandardStationFactory station = new StandardStationFactory("OFFLINE", 0,null);
		station.addParkingSlot("FREE",0);
		try {
			station.setSlotState(1, "OCCUPIED", 1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void whenYouWantToChangeTheStateOfASlotThenTheStateIsChanged() throws Exception {
		StandardStationFactory station = new StandardStationFactory("OFFLINE", 0,null);
		station.addParkingSlot("FREE",0);
		station.setSlotState(1, "OOO", 1);
		ParkingSlot slot1 = station.getParking().get(0);
		assertTrue(slot1.getState() == "OOO");
	}

	@Test
	void WhenThereIsNoSlotsThenComputeSationBalanceReturns0() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		double balance = station.computeStationBalance(0, 1);
		assertTrue(balance == 0);
	}

	@Test
	void WhenThereAreSlotsThenComputeSationBalanceReturnsTheMeanValueOfOccupation() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("FREE", 0);
		station.addParkingSlot("OOO", 0);
		ElectricalBike bike = new ElectricalBike();
		station.dropBike(bike, 1, null,1000000);
		station.setSlotState(2, "FREE", 2000000);
		double balance = station.computeStationBalance(0, 3000000);
		ParkingSlot slot1 = station.getParking().get(0); 
		ParkingSlot slot2 = station.getParking().get(1); 
		double balance1 = slot1.computeOccupation(0, 3000000);
		double balance2 = slot2.computeOccupation(0, 3000000);
		assertTrue(balance == (balance1 + balance2)/2);
	}

	@Test
	void WhenThereIsNoSlotAvailableIsFreeReturnsFalse() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("OOO", 0);
		assertTrue(!station.isFree());
	}

	@Test
	void WhenThereIsASlotAvailableIsFreeReturnsTrue() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("FREE", 0);
		assertTrue(station.isFree());
	}

	@Test
	void WhenThereIsAElectricalBikeAvailableIsThereBikeReturnsTrue() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("FREE", 0);
		station.addBike("ELECTRICAL", 0);
		assertTrue(station.isThereBike("ELECTRICAL"));
	}

	@Test
	void WhenThereIsAnMechanicalBikeAvailableIsThereBikeReturnsTrue() throws Exception {
		StandardStationFactory station = new StandardStationFactory("ONSERVICE", 0,null);
		station.addParkingSlot("FREE", 0);
		station.addBike("MECHANICAL", 0);
		assertTrue(station.isThereBike("MECHANICAL"));
	}

}
