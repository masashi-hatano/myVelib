package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.Station.ParkingSlot;

class ParkingSlotTest {

	@Test
	void whenTheStateGivenIsTheSameAsBeforeThenTheListsDoNotGrow() throws Exception {
		ParkingSlot parking = new ParkingSlot(1,"FREE",0);
		parking.setState("FREE", 1);
		assertTrue(parking.getHistoryState().size() == 1 & parking.getHistoryTime().size() == 1);
	}

	@Test
	void whenTheStateGivenIsNotTheSameAsBeforeThenTheListsGrow() throws Exception {
		ParkingSlot parking = new ParkingSlot(1,"FREE",0);
		parking.setState("OCCUPIED", 1);
		assertTrue(parking.getHistoryState().size() == 2 & parking.getHistoryTime().size() == 2);
	}

	@Test
	void whenTheStateIsIncorrectThenAnExceptionIsThrown() {
		boolean thrown = false;

		try {
			ParkingSlot parking = new ParkingSlot(1,"hgqdfu",0);
		} catch (Exception e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	void whenTheTimeIsIncorrectThenAnExceptionIsThrown() {
		boolean thrown = false;

		try {
			ParkingSlot parking = new ParkingSlot(1,"FREE",-1);
		} catch (Exception e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	void whenTheStateUpdateIsIncorrectThenAnExceptionIsThrown() {
		boolean thrown = false;

		try {
			ParkingSlot parking = new ParkingSlot(1,"FREE",0);
			parking.setState("sffs", 1);
		} catch (Exception e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	void whenTheTimeUpdateIsIncorrectThenAnExceptionIsThrown() {
		boolean thrown = false;

		try {
			ParkingSlot parking = new ParkingSlot(1,"FREE",1);
			parking.setState("FREE", 0);
		} catch (Exception e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	void whenTheTimeWindowIsIncorrectThenAnExceptionIsThrown() {
		boolean thrown = false;

		try {
			ParkingSlot parking = new ParkingSlot(1,"FREE",0);
			parking.setState("OCCUPIED", 1);
			parking.setState("OOO", 2);
			parking.setState("FREE", 3);
			parking.computeOccupation(1, 0);
		} catch (Exception e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	void whenTheTimeWindowIsCorrectThenCorrectIntersectionIsGiven() throws Exception {

		ParkingSlot parking = new ParkingSlot(1,"FREE",0);
		parking.setState("OCCUPIED", 1);
		parking.setState("OOO", 2);
		parking.setState("FREE", 3);
		double time1 = parking.computeOccupation(0, 0);
		double time2 = parking.computeOccupation(0, 1000000);
		double time3 = parking.computeOccupation(5000000, 15000000);
		double time4 = parking.computeOccupation(15000000, 25000000);
		double time5 = parking.computeOccupation(25000000, 35000000);
		double time6 = parking.computeOccupation(35000000, 40000000);

		assertTrue(time1 == 0 & time2 == 0 & time3 == 0.5 & time4 == 1 & time5 == 0.5 & time6 == 0);
	}

}
