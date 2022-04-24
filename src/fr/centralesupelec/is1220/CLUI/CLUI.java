package fr.centralesupelec.is1220.CLUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fr.centralesupelec.is1220.Server.Server;
import fr.centralesupelec.is1220.Station.AbstractStationFactory;

/**
 * <h1>CLUI</h1>
 *This class represents the CLUI
 *which permits the user to enter command
 *to interact with the bike renting system.
 */
public class CLUI {

	/**
	 * Creates a CLUI object.
	 */
	public CLUI() {
		super();
	}

	/**
	 * Main method of the CLUI object which will implements the dialogue with the user of the CLUI.
	 * @param args main function parameters.
	 */
	public static void main(final String[] args){
		// Simple identification
		System.out.println(">> Please enter your name:");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		System.out.println(">> Hello "+username+"!");
		System.out.println(">> Welcome to this Command Line User Interface! You can see the guidline of this CLUI"
				+ " by entering 'help' if you don't know how to use.");
		System.out.println(">> Please enter your command:");

		// Command loop
		boolean commandLoop=true;
		CLUI clui = new CLUI();
		while (commandLoop) {
			String arg;

			String command = sc.nextLine();
			String[] arguments = command.split(" ");

			arg = arguments[0];
			if (arg.equalsIgnoreCase("SETUP")) {
				clui.setup(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("ADDUSER")) {
				clui.addUser(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("OFFLINE")) {
				clui.offline(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("ONLINE")) {
				clui.online(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("RENTBIKE")) {
				clui.rentBike(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("RETURNBIKE")) {
				clui.returnBike(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("DISPLAYSTATION")) {
				clui.displayStation(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("DISPLAYUSER")) {
				clui.displayUser(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("SORTSTATION")) {
				clui.sortStation(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("DISPLAY")) {
				clui.display(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("DELETESERVER")) {
				clui.deleteServer(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("PLANNINGRIDE")) {
				clui.planningRide(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("RUNTEST")) {
				clui.runtest(arguments, clui);
				System.out.println(">> Please enter your command (you can quit by entering 'quit' or 'exit')");
			}

			else if (arg.equalsIgnoreCase("HELP")) {
				System.out.println(">> I'm afraid that I can't help you");

			}

			else if (arg.equalsIgnoreCase("QUIT") || arg.equalsIgnoreCase("EXIT")) {
				commandLoop = false;
			}
		}
		sc.close();
		System.out.println(">> See you again!");
	}

	/**
	 * Creates an new instance of the bike renting system when 
	 * the right command is given. The default are 10 stations,
	 * 10 slots per station, an area of size 4 and 75% 
	 * of slots filled with bikes.
	 * @param arguments the command of the setup function : setup -v (name of the instance) -nstations (the number of stations optional : 10 ) -nslots (the number of slots per station optional : 10) -s (the size of the square representing the area optional : 4) -nbikes (the percentage of slots occupied by bikes optional : 0.75).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] setup(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=false;
		String name=null;
		int nbstations=10;
		int nbslots=10;
		double dimention=4;
		double popRate=0.75;
		int nbbikes;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				name = arguments[i++];
				File file = new File(name+".ser");
				if (name.equals("null")){
					System.err.print(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (file.exists()) {
					System.err.println(">> The Velibnetwork "+"'"+name+"'"+" already exists!");
					flag=false;
					break;
				}
				flag=true;
			}
			else if (arg.equals("-nstations")) {
				try {
					nbstations = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -nstations is supposed to be an integer value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-nslots")) {
				try {
					nbslots = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -nslots is supposed to be an integer value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-s")) {
				try {
					dimention = Double.parseDouble(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -s is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-nbikes")) {
				try {
					popRate = Double.parseDouble(arguments[i++]);
					if (popRate<0 || popRate>1) {
						System.err.println(">> The value of -nbikes is supposed to be between 0 and 1");
					}
				} catch (Exception e) {
					System.err.println(">> The value of -nbikes is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (flag) {
			nbbikes = (int) (nbstations*nbslots*popRate);
			Server server = null;
			try {
				server = new Server(name, nbstations, nbslots, dimention, nbbikes);
			}
			catch (Exception e ) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, name);
			String msg = ">> setup was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, name};
			return objs;
		}
		else {
			String msg = ">> setup has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: setup -velibnetworkName [-nstations] [-nslots] [-s] [-nbikes]");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Add an user to an instance when the right command is given.
	 * @param arguments the command of the addUser function : addUser -userName (name of the user) -cardType (type of the card of the user) -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] addUser(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=false;
		String userName=null;
		String cardType="none";
		String networkname=null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-userName")) {
				userName = arguments[i++];
				flag=true;
			}
			else if (arg.equals("-cardType")) {
				cardType = arguments[i++];
				if (!(cardType.equalsIgnoreCase("NONE") || cardType.equalsIgnoreCase("VLIBRE") || cardType.equalsIgnoreCase("VMAX"))){
					System.err.println("The cardType '"+cardType+"' is not allowed");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || Objects.isNull(userName)) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				server.addUser(userName, cardType, 0);
			}
			catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> addUser was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, networkname};
			return objs;
		}
		else {
			String msg = ">> addUser has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: addUser -userName [-cardType] -velibnetworkName");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Put a station of an instance offline.
	 * @param arguments the command of the offline function : offline -v (name of the instance) -stationID (ID of the station).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] offline(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=true;
		String networkname=null;
		int stationID=-1;
		AbstractStationFactory station;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-stationID")) {
				try {
					stationID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -stationID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
			}
		}
		if (Objects.isNull(networkname) || stationID == -1) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				server.setStateStation("offline", stationID);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> offline was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, networkname};
			return objs;
		}
		else {
			String msg = ">> offline has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: offline -velibnetworkName -stationID");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Put a station of an instance online.
	 * @param arguments the command of the online function : online -v (name of the instance) -stationID (ID of the station).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] online(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=true;
		String networkname=null;
		int stationID=-1;
		AbstractStationFactory station;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-stationID")) {
				try {
					stationID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -stationID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
			}
		}
		if (Objects.isNull(networkname) || stationID == -1) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				station = server.getStations().get(stationID);
			} catch (Exception e) {
				String msg = ">> Invalid Station ID";
				System.err.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			try {
				server.setStateStation("OnService", stationID);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> online was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, networkname};
			return objs;
		}
		else {
			String msg = ">> online has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: online -velibnetworkName -stationID");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Allows an user to rent a bike from a station of an instance.
	 * @param arguments the command of the rentBike function : rentBike -userID (ID of the user) -stationID (ID of the station) -v (name of the instance) -bikeType (type of the bike rented).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] rentBike(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=true;
		String networkname=null;
		int userID = -1;
		int stationID = -1;
		String bikeType=null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-userID")) {
				try {
					userID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -userID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-stationID")) {
				try {
					stationID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -stationID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-bikeType")) {
				bikeType = arguments[i++];
				if (!(bikeType.equalsIgnoreCase("ELECTRICAL") || bikeType.equalsIgnoreCase("MECHANICAL"))){
					System.err.println("The bikeType '"+bikeType+"' is not allowed");
					flag=false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || userID == -1 || stationID == -1 || Objects.isNull(bikeType)) {
			flag=false;
		}
		if (flag) {
			long ms = System.currentTimeMillis();
			double minute = (double) ms;
			Server server = clui.deserializeServer(networkname);
			try {
				server.rentBike(userID, bikeType, stationID, minute);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> rentBike was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, networkname};
			return objs;
		}
		else {
			String msg = ">> rentBike has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: rentBike -velibnetworkName -userID -stationID -bikeType");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Allows an user to return a bike to a station of an instance.
	 * @param arguments the command of the returnBike function : returnBike -userID (ID of the user) -stationID (ID of the station) -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] returnBike(String[] arguments, CLUI clui){
		int i=1;
		boolean flag=true;
		String networkname=null;
		int userID = -1;
		int stationID = -1;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-userID")) {
				try {
					userID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -userID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-stationID")) {
				try {
					stationID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -stationID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || userID == -1 || stationID == -1) {
			flag=false;
		}
		if (flag) {
			long ms = System.currentTimeMillis();
			double minute = (double) ms;
			Server server = clui.deserializeServer(networkname);
			try {
				server.dropBike(userID, stationID, minute);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> returnBike was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, server, networkname};
			return objs;
		}
		else {
			String msg = ">> returnBike has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: returnBike -velibnetworkName -userID -stationID");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Display information on a station of an instance.
	 * @param arguments the command of the displayStation function : displayStation -stationID (ID of the station) -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] displayStation(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		String networkname=null;
		int stationID = -1;
		String display = null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-stationID")) {
				try {
					stationID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -stationID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || stationID == -1) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				long ms = System.currentTimeMillis();
				double minute = (double) ms;
				server.setEndTimeStation(minute, stationID);
				display = server.stationToString(stationID);
				System.out.println(display);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> displayStation was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, display, server, networkname};
			return objs;
		}
		else {
			String msg = ">> displayStation has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: displayStation -velibnetworkName -stationID");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Display information on a user of an instance.
	 * @param arguments the command of the displayUser function : displayUser -userID (ID of the user) -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] displayUser(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		String networkname=null;
		int userID = -1;
		String display = null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-userID")) {
				try {
					userID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -userID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || userID == -1) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				display = server.userToString(userID);
				System.out.println(display);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> displayUser was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, display, server, networkname};
			return objs;
		}
		else {
			String msg = ">> displayUser has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: displayUser -velibnetworkName -userID");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Sort the stations of an instance by a given policy.
	 * @param arguments the command of the sortStation function : sortStation -policy (name of the policy) -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] sortStation(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		String networkname=null;
		String policy = null;
		String display = null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-policy")) {
				policy = arguments[i++];
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || Objects.isNull(policy)) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				for (AbstractStationFactory station:server.getStations().values()) {
					long ms = System.currentTimeMillis();
					double minute = (double) ms;
					server.setEndTimeStation(minute, station.getId());
				}
				display = server.sortStation(policy).toString();
				System.out.println(display);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> sortStation was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, display, server, networkname};
			return objs;
		}
		else {
			String msg = ">> sortStation has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: sortStation -velibnetworkName -policy");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Display information about an instance.
	 * @param arguments the command of the display function : display -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] display(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		String networkname=null;
		String display = null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname)) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				long ms = System.currentTimeMillis();
				double minute = (double) ms;
				for(AbstractStationFactory station: server.getStations().values()) {
					server.setEndTimeStation(minute, station.getId());
				}
				display = server.toString();
				System.out.println(display);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> display was done successfully!";
			System.out.println(msg);
			Object[] objs = {msg, display, server, networkname};
			return objs;
		}
		else {
			String msg = ">> display has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: display -velibnetworkName");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Allows an user to ask a route to go from a point to an other on the map.
	 * @param arguments the command of the rentBike function : rentBike -userID (ID of the user) -bikeType (type of the bike wanted to use) -px (X coordinate of the position of the user) -py (Y coordinate of the position of the user) -dx (X coordinate of the destination of the user) -dy (Y coordinate of the destination of the user) -v (name of the instance)
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] planningRide(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		int userID = -1;
		String bikeType=null;
		double px=-1;
		double py=-1;
		double dx=-1;
		double dy=-1;
		String networkname=null;
		String display=null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-userID")) {
				try {
					userID = Integer.parseInt(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -userID is supposed to be an integer value");
					flag = false;
					break;
				}
			}
			else if (arg.equals("-bikeType")) {
				bikeType = arguments[i++];
				if (!(bikeType.equalsIgnoreCase("ELECTRICAL") || bikeType.equalsIgnoreCase("MECHANICAL"))){
					System.err.println("The bikeType '"+bikeType+"' is not allowed");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-px")) {
				try {
					px = Double.parseDouble(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -px is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-py")) {
				try {
					py = Double.parseDouble(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -py is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-dx")) {
				try {
					dx = Double.parseDouble(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -dx is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-dy")) {
				try {
					dy = Double.parseDouble(arguments[i++]);
				} catch (Exception e) {
					System.err.println(">> The value of -dy is supposed to be an double value");
					flag=false;
					break;
				}
			}
			else if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				File file = new File(networkname+".ser");
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
				if (!file.exists()) {
					System.err.println("The velibnetwork '"+networkname+"' doesn't exist!");
					flag = false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname) || Objects.isNull(bikeType) || px==-1 || py==-1 || dx==-1 || dy==-1) {
			flag=false;
		}
		if (flag) {
			Server server = clui.deserializeServer(networkname);
			try {
				ArrayList<Integer> plan = server.planningRide(userID, bikeType, px, py, dx, dy);
				int depart = plan.get(0);
				int arrive = plan.get(1);
				display = "Your departure station will be at station n°"+depart+server.getStations().get(depart).getCoordinates().toString()
						+"\nYour arrival station will be at station n°"+arrive+server.getStations().get(arrive).getCoordinates().toString();
				System.out.println(display);
			} catch (Exception e) {
				String msg = e.getMessage();
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
			clui.serializeServer(server, networkname);
			String msg = ">> planningRide was done successfully!";
			msg += " "+display;
			Object[] objs = {msg, display, server, networkname};
			return objs;
		}
		else {
			String msg = ">> planningRide has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: planningRide -velibnetworkName -userID -bikeType -px -py -dx -dy");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * Delete an instance.
	 * @param arguments the command of the deleteServer function : deleteServer -v (name of the instance).
	 * @param clui the CLUI associated.
	 * @return the message of success or failure of the operation.
	 */
	public Object[] deleteServer(String[] arguments, CLUI clui) {
		int i=1;
		boolean flag=true;
		String networkname=null;

		if (arguments.length % 2 == 0) {
			flag=false;
		}
		while (i < arguments.length) {
			String arg = arguments[i++];
			if (arg.equals("-velibnetworkName") || arg.equals("-v")) {
				networkname = arguments[i++];
				if (Objects.isNull(networkname)){
					System.err.println(">> 'null' cannot be used as a name of Velibnetwork !");
					flag=false;
					break;
				}
			}
			else {
				flag=false;
				break;
			}
		}
		if (Objects.isNull(networkname)) {
			flag=false;
		}
		if (flag) {
			File file = new File(networkname+".ser");
			if (file.delete()) {
				String msg = ">> Delete was done successfully!";
				System.out.println(msg);
				Object[] objs = {msg, networkname};
				return objs;
			}
			else {
				String msg = ">> No instance to delete!";
				System.out.println(msg);
				Object[] objs = {msg};
				return objs;
			}
		}
		else {
			String msg = ">> Delete has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: display -velibnetworkName");
			Object[] objs = {msg};
			return objs;
		}
	}

	/**
	 * run a test scenario file.
	 * @param arguments the command of the runtest function : runtest (the name of the file to execute).
	 * @param clui the CLUI associated.
	 */
	public void runtest(String[] arguments, CLUI clui) { 
		if(!(arguments.length==1)) {
			String filename = "eval/"+arguments[1];

			File file = new File(filename);
			if (!file.exists()) {
				System.err.println("The testscenario '"+filename+"' doesn't exist!");
			}
			else {
				try {
					ArrayList<String> commandsList = clui.readTextFile(filename);
					String output="";
					for (String command: commandsList) {
						String[] commandArgs = command.split(" ");
						String arg = commandArgs[0];
						Object[] objs=null;

						if (arg.equalsIgnoreCase("SETUP")) {
							objs = clui.setup(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("ADDUSER")) {
							objs= clui.addUser(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("OFFLINE")) {
							objs= clui.offline(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("ONLINE")) {
							objs= clui.online(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("RENTBIKE")) {
							objs= clui.rentBike(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("RETURNBIKE")) {
							objs= clui.returnBike(commandArgs, clui);
							output += (String) objs[0] + "\n";
						}

						else if (arg.equalsIgnoreCase("DISPLAYSTATION")) {
							objs= clui.displayStation(commandArgs, clui);
							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}

						else if (arg.equalsIgnoreCase("DISPLAYUSER")) {
							objs= clui.displayUser(commandArgs, clui);
							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}

						else if (arg.equalsIgnoreCase("DELETESERVER")) {
							objs= clui.deleteServer(commandArgs, clui);
							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}

						else if (arg.equalsIgnoreCase("SORTSTATION")) {
							objs= clui.sortStation(commandArgs, clui);
							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}

						else if (arg.equalsIgnoreCase("DISPLAY")) {
							objs= clui.display(commandArgs, clui);

							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}

						else if (arg.equalsIgnoreCase("PLANNINGRIDE")) {
							objs= clui.planningRide(commandArgs, clui);
							output += (String) (objs[0]+"\n"+objs[1] + "\n");
						}
						else {
							output += "####################  unknown command was detected  ####################";
						}
					}
					String[] name = filename.split(".txt");
					clui.writeTextFile(name[0]+"output.txt", output);
				} catch(Exception e) {
					String msg = e.getMessage();
					System.out.println(msg);
				}

			}
		}
		else {
			String msg = ">> runtest has failed!";
			System.out.println(msg);
			System.err.println(">> Usage: runtest testfile.txt");
		}

	}


	/**
	 * Serialize a server instance.
	 * @param server the server to be serialized.
	 * @param networkName the name of the instance of the system.
	 */
	public void serializeServer(Server server, String networkName) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		String filename = networkName+".ser";
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(server);
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserialize a server instance.
	 * @param networkName the name of the instance of the system.
	 * @return a server instance.
	 */
	public Server deserializeServer(String networkName) {
		String filename = networkName+".ser";
		Server server = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			server = (Server)in.readObject();
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return server;
	}


	/**
	 * Read a file.
	 * @param fileName the name of the file.
	 * @return the content of the file.
	 */
	public ArrayList<String> readTextFile(String fileName) {

		ArrayList<String> returnValue= new ArrayList<String>();
		FileReader file = null;
		BufferedReader reader = null;

		try {
			file = new FileReader(fileName);
			reader = new BufferedReader(file);
			String line = "";

			while ((line = reader.readLine()) != null) {
				returnValue.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (file != null) {
				try {
					file.close();
					reader.close();

				} catch (IOException e) {
					System.out.println("File not found: " + fileName);
				}
			}
		}
		return returnValue;
	} 

	/**
	 * Write a new file.
	 * @param fileName the name of the file.
	 * @param s the content of the file.
	 */
	public void writeTextFile(String fileName, String s) {
		try {
			File myFile = new File(fileName);
			FileOutputStream fos = new FileOutputStream(myFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos);    
			Writer writer = new BufferedWriter(osw); 
			writer.write(s);
			writer.close();
		} 
		catch (IOException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}