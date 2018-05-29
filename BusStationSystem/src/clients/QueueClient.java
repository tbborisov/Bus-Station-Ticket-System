package clients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class QueueClient implements Runnable {
	private String destination;
	private int seatNumber;

	public QueueClient() {
		destination = new String();
		seatNumber = 0;
	}

	public QueueClient(String destination, int seatNumber) {
		this.destination = destination;
		this.seatNumber = seatNumber;
	}

	public String getDestination() {
		return destination;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	private void buyTicket() {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 4242);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String destination = getDestination();
			int seatNumber = getSeatNumber();
			String ticketDescription = new String();
			ticketDescription = ticketDescription + destination + "-" + seatNumber + "-" + 0;
			bw.write(ticketDescription);
			bw.newLine();
			bw.flush();
			System.out.println(br.readLine());
			br.close();
			bw.close();
			socket.close();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		buyTicket();
	}
}
