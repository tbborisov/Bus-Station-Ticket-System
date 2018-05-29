package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import clients.OnlineClient;
import clients.QueueClient;
import server.Server;
import station.Route;
import station.Station;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		Station s = new Station();
		Server server = new Server(s);
		new Thread(server).start();
		String[] d = { "ВеликоТърново", "Варна", "Пловдив", "Благоевград", "Силистра", "София", "Бургас" };
		Thread[] threads = new Thread[40];
		OnlineClient[] oClients = new OnlineClient[30];
		QueueClient[] qClients = new QueueClient[10];
		List<Route> currentRoutes = new ArrayList<Route>();
		currentRoutes = s.getRoutes();
		for (int i = 0; i < oClients.length; i++) {
			int k = i % d.length;
			int numberOfSeats=100;
			for (Route r : currentRoutes) {
				if (r.getDestination().equals(d[k])) {
					numberOfSeats = r.getNumberOfSeats();
				}
			}
			oClients[i] = new OnlineClient(d[k], ThreadLocalRandom.current().nextInt(1, numberOfSeats+1));
			threads[i] = new Thread(oClients[i]);
			threads[i].start();
		}

		for (int i = oClients.length; i < oClients.length + qClients.length; i++) {
			int k = i % d.length;
			int numberOfSeats = 100;
			for (Route r : currentRoutes) {
				if (r.getDestination().equals(d[k])) {
					numberOfSeats = r.getNumberOfSeats();
				}
			}
			qClients[i - oClients.length] = new QueueClient(d[k],
					ThreadLocalRandom.current().nextInt(1, numberOfSeats+1));
			threads[i] = new Thread(qClients[i - oClients.length]);
		}
		
		threads[oClients.length].start();
		threads[oClients.length].join();
		for (int i = oClients.length+1; i < oClients.length+ qClients.length; i++) {
			threads[i].start();
			threads[i-1].join();
		}
		
		System.out.println();
		System.out.println("Доходите от продадени досега билети възлизат на: " + s.getVault());
		System.out.println();
		System.out.println(s.soldTicketsFromEveryRoute());
		System.out.println(s.soldTicketsFromEveryRouteExpanded());
		System.out.println(s.freeSeatsFromEveryRoute());
	
	}
}
