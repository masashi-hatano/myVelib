package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.User.Observer;
import fr.centralesupelec.is1220.User.User;

class UserTest {

	@Test
	void whenTheStateIsNotCorrectThenAnErrorIsThrown() throws Exception {
		boolean test = false;
		try {
			User user = new User("ddd", null, -1);
		}
		catch (Exception e) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	void when2UsersAreCreatedThenTheirIDAreDifferent() throws Exception {
		User user1 = new User(null, "none", 0);
		User user2 = new User(null, "none", 0);
		assertTrue(user1.getUserID() != user2.getUserID());
	}

	@Test
	void whenTheObserverIsNotTheUserThenTheBalanceDoNotChange() throws Exception {
		User user1 = new User(null, "NONE", 100);
		Observer user2 = new User(null, "NONE", 100);
		user1.update(60, "ELECTRICAL", "PLUS", user2);
		assertTrue(user1.getBalance() == 100);
	}

	@Test
	void whenTheUserHaveAVlibreCardAndUseAPlusStationThenHeEarnedCreditTime() throws Exception {
		User user = new User(null, "VLIBRE", 100);
		user.update(65, "ELECTRICAL", "PLUS", (Observer) user);
		assertTrue(user.getCreditTime() == 5 & user.getNb_rides() == 1 & user.getTotal_charge() == (5.0/60.0)*2+1 & user.getTotal_time() == 65 & user.getTotal_creditTime() == 5);
	}

}
