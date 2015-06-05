package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import mahli.MAHLIClient;
import mahli.MAHLIDummyClient;
import mahli.MAHLIServer;
import module1.ModuleOneClient;
import module1.ModuleOneServer;
import module2.ModuleTwoClient;
import module2.ModuleTwoServer;

public class MasterMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = 9897;
		int port_two = 9898;
		int port_mahli = 9010;
		int port_power = 9013;
		
		try {
			
			// create a thread for module one
			ModuleOneServer serverOne = new ModuleOneServer(port_power);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
			
			// create a thread for module two
			ModuleTwoServer serverTwo = new ModuleTwoServer(port_two);
			Thread server_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverTwo);
			
			//create a thread for module mahli
			MAHLIServer serverMahli = new MAHLIServer(port_mahli);
			Thread server_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverMahli);
			
			MAHLIDummyClient clientMahli = new MAHLIDummyClient(port_mahli, null);
			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
			
			// each server begins listening
			server_1.start();
			//server_2.start();
			server_3.start();
			client_3.start();
			
			// The following commands are examples of sending data: 
			// from module 1 client to module 2 server
			// and from module 2 client to module 2 server
			
			// client one server sending messages to server two
			ModuleOneClient clientOne = new ModuleOneClient(port_two, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			
			// client two server sending messages to server one
			ModuleTwoClient clientTwo = new ModuleTwoClient(port_one, null); // notice port_one
			Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientTwo);
			
			// client two server sending messages to server Mahli
			//MAHLIClient clientMahli = new MAHLIClient(port_mahli, null); // notice port_one
			//Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
			
			// start the thread which communicates through sockets
			//client_1.start();
			//client_2.start();
			//client_3.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
