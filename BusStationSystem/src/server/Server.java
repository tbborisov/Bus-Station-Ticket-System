package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import station.Station;

public class Server implements Runnable {
	public static int ticketNumber = 1000;
	Station station;

	public Server(Station s) {
		station = s;
	}

	public void start() throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(4242);
			while (true) {
				Socket socket = serverSocket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedWriter messageToTextFile = new BufferedWriter(
						new FileWriter("src/transactions.txt", true));
				String line = null;
				while ((line = br.readLine()) != null) {
					String s = new String();
					String[] parts = line.split("-");
					s = station.sellTicket(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					if (!s.isEmpty()) {
						messageToTextFile.write(s);
						messageToTextFile.newLine();
						messageToTextFile.flush();
						bw.write("Успешно закупихте билет с номер: " + ticketNumber+ "!");
						ticketNumber++;
					}
					else{
						bw.write("Закупуването на билет беше неуспешно, местата са свършили!");
					}
					bw.newLine();
					bw.flush();
				}
				messageToTextFile.close();
				br.close();
				bw.close();

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
