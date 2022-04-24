package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.User.Card;
import fr.centralesupelec.is1220.User.User;

class NoCardTest {

	@Test
	void whenTheBikeIsMechanicalThenTheBalanceAndThePriceAreWright() throws Exception {
		User user = new User(null, "NONE", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 60, "MECHANICAL");
		assertTrue(price == 1.0 & user.getBalance() == 99.0);
	}

	@Test
	void whenTheBikeIsElectricalThenTheBalanceAndThePriceAreWright() throws Exception {
		User user = new User(null, "NONE", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 60, "ELECTRICAL");
		assertTrue(price == 2.0 & user.getBalance() == 98.0);
	}

	@Test
	void whenTheTimeIsWrongAnErrorIsTrown() throws Exception {
		boolean test = false;
		User user = new User(null, "NONE", 100);
		Card card = user.getCard();
		try {
			double price = card.computePrice(user, -1, "ELECTRICAL");
		}catch (Exception e ) {
			test = true;
		}
		assertTrue(test);
	}

}
