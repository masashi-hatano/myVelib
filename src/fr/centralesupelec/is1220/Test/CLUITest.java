package fr.centralesupelec.is1220.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import fr.centralesupelec.is1220.CLUI.CLUI;
import fr.centralesupelec.is1220.Server.Server;
import fr.centralesupelec.is1220.Station.AbstractStationFactory;

class CLUITest {

	@Test
	void whenSetupAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer","-nstations","2","-nslots","1","-s","10","-nbikes","1"};
		Object[] obj = clui.setup(arg, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj[1] instanceof Server);
	}

	@Test
	void whenSetupWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-nstations","2","-v","testServer","-nslots","1","-s","10","-nbikes","1"};
		Object[] obj = clui.setup(arg, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj[1] instanceof Server);
	}


	@Test
	void whenSetupSomeArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer","-nstations","2"};
		Object[] obj = clui.setup(arg, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj[1] instanceof Server);
	}

	@Test
	void whenSetupAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-viro","testServer"};
		Object[] obj = clui.setup(arg, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenSetupAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer","-nstations","dde"};
		Object[] obj = clui.setup(arg, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenUserAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getUsers().size() == 1);
	}

	@Test
	void whenUserWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.addUser(new String[] {"adduser","-userName","name","-cardType","vlibre","-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getUsers().size() == 1);
	}


	@Test
	void whenUserSomeArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getUsers().size() == 1);
	}

	@Test
	void whenUserAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.addUser(new String[] {"adduser","-viro","testServer","-userName","name","-cardType","vlibre"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenUserAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibdqc"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenOfflineAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		int id = (int) ((Server) set[1]).getStations().keySet().toArray()[0];
		Object[] obj = clui.offline(new String[] {"offline","-v","testServer","-stationID",""+id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((AbstractStationFactory) ((Server) obj[1]).getStations().get(id)).getState().equalsIgnoreCase("OFFLINE"));
	}

	@Test
	void whenOfflineWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		int id = (int) ((Server) set[1]).getStations().keySet().toArray()[0];
		Object[] obj = clui.offline(new String[] {"offline","-stationID",""+id,"-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((AbstractStationFactory) ((Server) obj[1]).getStations().get(id)).getState().equalsIgnoreCase("OFFLINE"));
	}

	@Test
	void whenOfflineAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] obj = clui.offline(new String[] {"offline","-v","testServer","-stationIDh",id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenOfflineAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.offline(new String[] {"offline","-v","testServer","-stationID","0"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenOnlineAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		int id = (int) ((Server) set[1]).getStations().keySet().toArray()[0];
		clui.offline(new String[] {"offline","-v","testServer","-stationID",""+id}, clui);
		Object[] obj = clui.online(new String[] {"online","-v","testServer","-stationID",""+id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((AbstractStationFactory) ((Server) obj[1]).getStations().get(id)).getState().equalsIgnoreCase("ONSERVICE"));
	}

	@Test
	void whenOnlineWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		int id = (int) ((Server) set[1]).getStations().keySet().toArray()[0];
		clui.offline(new String[] {"offline","-v","testServer","-stationID",""+id}, clui);
		Object[] obj = clui.online(new String[] {"online","-stationID",""+id,"-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((AbstractStationFactory) ((Server) obj[1]).getStations().get(id)).getState().equalsIgnoreCase("ONSERVICE"));
	}

	@Test
	void whenOnlineAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		clui.offline(new String[] {"offline","-v","testServer","-stationID",id}, clui);
		Object[] obj = clui.online(new String[] {"online","-vilo","testServer","-stationID",id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenOnlineAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		clui.offline(new String[] {"offline","-v","testServer","-stationID",id}, clui);
		Object[] obj = clui.online(new String[] {"online","-v","testServer","-stationID","0"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenRentAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getBikeOnRide().size() == 1);
	}

	@Test
	void whenRentWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.rentBike(new String[] {"rentbike","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL","-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getBikeOnRide().size() == 1);
	}

	@Test
	void whenRentAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.rentBike(new String[] {"rentbike","-vilo","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenRentAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","jsejf"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenRentAnArgumentHasNotTheGoodValueThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		Object[] obj = clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID","0","-bikeType","MECHANICAL"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenReturnAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		Object[] obj = clui.returnBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getBikeOnRide().size() == 0);
	}

	@Test
	void whenReturnWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		Object[] obj = clui.returnBike(new String[] {"rentbike","-stationID",id,"-userID",idu,"-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(((Server) obj[1]).getBikeOnRide().size() == 0);
	}

	@Test
	void whenReturnAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		Object[] obj = clui.returnBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userIDed",idu}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenReturnAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		clui.rentBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID",idu,"-bikeType","MECHANICAL"}, clui);
		Object[] obj = clui.returnBike(new String[] {"rentbike","-v","testServer","-stationID",id,"-userID","0"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplaySAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] obj = clui.displayStation(new String[] {"displayStation","-v","testServer","-stationID",id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenDisplaySWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] obj = clui.displayStation(new String[] {"displayStation","-stationID",id,"-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenDisplaySAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] set = clui.setup(arg, clui);
		String id = ((Server) set[1]).getStations().keySet().toArray()[0].toString();
		Object[] obj = clui.displayStation(new String[] {"displayStation","-vilo","testServer","-stationID",id}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplaySAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.displayStation(new String[] {"displayStation","-v","testServer","-stationID","0"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplayUAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.displayUser(new String[] {"displayStation","-v","testServer","-userID",idu}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenDisplayUWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.displayUser(new String[] {"displayStation","-userID",idu,"-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenDisplayUAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.displayUser(new String[] {"displayStation","-vilo","testServer","-userID",idu}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplayUAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		Object[] obj = clui.displayUser(new String[] {"displayStation","-v","testServer","-userID","0"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenSortAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.sortStation(new String[] {"displayStation","-v","testServer","-policy","used"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenSortWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.sortStation(new String[] {"displayStation","-policy","used","-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenSortAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.sortStation(new String[] {"displayStation","-v","testServer","-policies","used"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenSortAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.sortStation(new String[] {"displayStation","-v","testServer","-policy","hdhbh"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplayAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.display(new String[] {"displayStation","-v","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenDisplayAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.display(new String[] {"displayStation","-vilo","testServer"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDisplayAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] obj = clui.display(new String[] {"displayStation","-v","testServerjij"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenPlanAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.planningRide(new String[] {"displayStation","-v","testServer","-userID",idu,"-bikeType","MECHANICAL","-px","1","-py","1","-dx","2","-dy","2"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenPlanWeChangeTheOrderOfArgumentsThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.planningRide(new String[] {"displayStation","-userID",idu,"-v","testServer","-bikeType","MECHANICAL","-px","1","-py","1","-dx","2","-dy","2"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length > 1);
	}

	@Test
	void whenPlanAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.planningRide(new String[] {"displayStation","-v","testServer","-userIDftyft",idu,"-bikeType","MECHANICAL","-px","1","-py","1","-dx","2","-dy","2"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenPlanAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		Object[] setu = clui.addUser(new String[] {"adduser","-v","testServer","-userName","name","-cardType","vlibre"}, clui);
		String idu = ((Server) setu[1]).getUsers().keySet().toArray()[0].toString();
		Object[] obj = clui.planningRide(new String[] {"displayStation","-v","testServer","-userID",idu,"-bikeType","MECHANICAL","-px","-1","-py","1","-dx","2","-dy","2"}, clui);
		File file = new File("testServer.ser");
		file.delete();
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDeleteAllTheWrightArgumentsAreThereThenAServerIsCreated() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		clui.setup(arg, clui);
		clui.deleteServer(arg, clui);
		Object[] obj = clui.display(arg, clui);
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDeleteAnArgumentIsWrongThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-vilo","testServer"};
		Object[] obj = clui.deleteServer(arg, clui);
		assertTrue(obj.length == 1);
	}

	@Test
	void whenDeleteAnArgumentHasNotTheGoodTypeThenErrorIsReturned() throws Exception {
		CLUI clui = new CLUI();
		String[] arg = {"setup","-v","testServer"};
		Object[] obj = clui.deleteServer(arg, clui);
		assertTrue(obj.length == 1);
	}

}
