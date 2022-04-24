package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.Bike.ElectricalBike;
import fr.centralesupelec.is1220.Bike.MechanicalBike;

class BikeTest {

	@Test
	void when2BikesOfDifferentClassesAreCreatingThenTheirIDIsDifferent() {
		MechanicalBike bike1 = new MechanicalBike();
		ElectricalBike bike2 = new ElectricalBike();
		assertTrue(bike1.getId() == 1 & bike2.getId() == 2);
	}

	@Test
	void when2BikesOfSameClassesAreCreatingThenTheirIDIsDifferent() {
		MechanicalBike bike1 = new MechanicalBike();
		MechanicalBike bike2 = new MechanicalBike();
		assertTrue(bike1.getId() == 3 & bike2.getId() == 4);
	}

}
