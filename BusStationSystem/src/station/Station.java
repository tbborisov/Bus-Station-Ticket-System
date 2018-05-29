package station;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Station {
	private List<Route> routes;

	public List<Route> getRoutes() {
		return routes;
	}

	public Station() throws IOException {
		File routeFromTextFile = new File("src/destinations.txt");
		routes = new ArrayList<Route>();
		try (BufferedReader route = new BufferedReader(new FileReader(routeFromTextFile))) {
			String routeLine = null;
			while ((routeLine = route.readLine()) != null) {
				String[] parts = routeLine.split("-");
				routes.add(new Route(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String sellTicket(String destination, int seat, int isOnline) {
		String s = new String();
		for (Route r : routes) {
			if (r.getDestination().equals(destination)) {
				s = r.reserveSeat(seat, isOnline);
				return s;
			}
		}
		return s;
	}

	public double getVault() {
		double vault = 0;
		for (Route r : routes) {
			vault = vault + r.moneyFromRoute();
		}
		return vault;
	}

	public String soldTicketsFromEveryRoute() {
		String s = new String();
		for (Route r : routes) {
			s = s + "За маршрута до " + r.getDestination() + " са продадени " + r.getSoldSeats() + " места.\n";
		}
		return s;
	}

	public String soldTicketsFromEveryRouteExpanded() {
		String s = new String();
		for (Route r : routes) {
			s = s + "За маршрута до " + r.getDestination() + " са продадени " + r.getSoldOnlineSeats()
					+ " онлайн билета и " + r.getSoldOnCasaSeats() + " билета на каса.\n";
		}
		return s;
	}

	public String freeSeatsFromEveryRoute() {
		String s = new String();
		for (Route r : routes) {
			s = s + "Маршрута до " + r.getDestination() + " има " + r.getFreeSeats() + " останали свободни места.\n";
		}
		return s;
	}
}
