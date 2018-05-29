package station;

import java.util.concurrent.ThreadLocalRandom;

public class Route {
	private String destination;
	private int numberOfSeats;
	private double ticketPrice;
	private boolean seats[];
	private int soldSeats;
	private int soldOnline;

	public Route() {
		destination = new String();
	}

	public Route(String destination, int numberOfSeats, double ticketPrice) {
		this.destination = destination;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
		seats = new boolean[numberOfSeats];
		for (int i = 0; i < numberOfSeats; i++) {
			seats[i] = false;
		}
		soldSeats = 0;
	}

	public String getDestination() {
		return destination;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public double ticketPrice() {
		return ticketPrice;
	}

	public int getSoldSeats() {
		return soldSeats;
	}

	public int getFreeSeats() {
		return numberOfSeats - soldSeats;
	}

	public int getSoldOnlineSeats() {
		return soldOnline;
	}

	public int getSoldOnCasaSeats() {
		return getSoldSeats() - getSoldOnlineSeats();
	}

	public boolean hasFreeSeat() {
		for (int i = 0; i < numberOfSeats; i++) {
			if (!seats[i])
				return true;
		}
		return false;
	}

	public double moneyFromRoute() {
		return soldSeats * ticketPrice;
	}

	public synchronized String reserveSeat(int seatNumber, int isOnline) {
		String s = new String();
		if (hasFreeSeat()) {
			if (seatNumber > 0 && seatNumber <= numberOfSeats && !seats[seatNumber - 1]) {
				seats[seatNumber - 1] = true;
				soldSeats++;
				if (isOnline == 1) {
					soldOnline++;
					s = s + "Успешна продажба на място " + seatNumber + " до " + destination + " на цена " + ticketPrice
							+ " лева.  Билетът е закупен онлайн.";
				} else {
					s = s + "Успешна продажба на място " + seatNumber + " до " + destination + " на цена " + ticketPrice
							+ " лева.  Билетът е закупен на каса.";
				}
			} else if (seatNumber > 0 && seatNumber <= numberOfSeats) {
				int randomNum = ThreadLocalRandom.current().nextInt(1, numberOfSeats+1);
				while (seats[randomNum - 1] == true) {
					randomNum = ThreadLocalRandom.current().nextInt(1, numberOfSeats+1);
				}
				seats[randomNum - 1] = true;
				soldSeats++;
				if (isOnline == 1) {
					soldOnline++;
					s = s + "Успешна продажба на място " + randomNum + " до " + destination + " на цена " + ticketPrice
							+ " лева. Билетът е закупен онлайн.";
				} else {
					s = s + "Успешна продажба на място " + seatNumber + " до " + destination + " на цена " + ticketPrice
							+ " лева.  Билетът е закупен на каса.";
				}
			}
		}
		return s;
	}
}
