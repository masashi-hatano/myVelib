package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.User.Card;
import fr.centralesupelec.is1220.User.User;

class VlibreTest {

	@Test
	void whenTheBikeIsMechanicalAndTheTimeLessThanAnHourThenThePriceIs0() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 35, "MECHANICAL");
		assertTrue(price == 0 & user.getBalance() == 100);
	}

	@Test
	void whenTheBikeIsMechanicalAndTheTimeIsHigherThanAnHourWithNotEnoughCreditTimeThenThePriceIsReduced() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		user.setCreditTime(20);
		double price = card.computePrice(user, 135, "MECHANICAL");
		assertTrue(price == (55.0/60.0) & user.getBalance() == 100-(55.0/60.0) & user.getCreditTime() == 0);
	}

	@Test
	void whenTheBikeIsMechanicalAndTheTimeIsHigherThanAnHourWithEnoughCreditTimeThenThePriceIs0() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		user.setCreditTime(20);
		double price = card.computePrice(user, 75, "MECHANICAL");
		assertTrue(price == 0 & user.getBalance() == 100 & user.getCreditTime() == 5);
	}

	@Test
	void whenTheBikeIsElectricalAndTheTimeLessThanAnHourThenThePriceIsNormal() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		double price = card.computePrice(user, 35, "ELECTRICAL");
		assertTrue(price == 35.0/60.0 & user.getBalance() == 100-(35.0/60.0));
	}

	@Test
	void whenTheBikeIsElectricalAndTheTimeIsHigherThanAnHourWithNotEnoughCreditTimeThenThePriceIsReduced() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		user.setCreditTime(20);
		double price = card.computePrice(user, 135, "ELECTRICAL");
		assertTrue(price == (55.0/60.0)*2+1 & user.getBalance() == 100-(55.0/60.0)*2-1 & user.getCreditTime() == 0);
	}

	@Test
	void whenTheBikeIsElectricalAndTheTimeIsHigherThanAnHourWithEnoughCreditTimeThenThePriceIs1() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		user.setCreditTime(20);
		double price = card.computePrice(user, 75, "ELECTRICAL");
		assertTrue(price == 1 & user.getBalance() == 99 & user.getCreditTime() == 5);
	}

	@Test
	void whenTheTimeIsWrongAnErrorIsTrown() throws Exception {
		boolean test = false;
		User user = new User(null, "VLIBRE", 100);
		Card card = user.getCard();
		try {
			double price = card.computePrice(user, -1, "ELECTRICAL");
		}catch (Exception e ) {
			test = true;
		}
		assertTrue(test);
	}

}
