package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.User.Card;
import fr.centralesupelec.is1220.User.User;

class VmaxTest {

	@Test
	void whenTheBikeIsMechanicalOrElectricalAndTheTimeIsLowerThan1HourThenThePriceIs0() throws Exception {
		User user = new User(null, "VMAX", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 35, "MECHANICAL");
		assertTrue(price == 0 & user.getBalance() == 100);
	}

	@Test
	void whenTheBikeIsMechanicalOrElectricalAndTheTimeGreaterThan1HourThenThePriceIsReduced() throws Exception {
		User user = new User(null, "VMAX", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 75, "ELECTRICAL");
		assertTrue(price == 15.0/60.0 & user.getBalance() == 100 - (15.0/60.0));
	}

	@Test
	void whenTheTimeIsWrongAnErrorIsTrown() throws Exception {
		boolean test = false;
		User user = new User(null, "VMAX", 100);
		Card card = user.getCard();
		try {
			double price = card.computePrice(user, -1, "ELECTRICAL");
		}catch (Exception e ) {
			test = true;
		}
		assertTrue(test);
	}

}
