// Imports
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

// Class to create a client socket
public class ClientSocket {
	// Variables
	Socket clientSocket;
	int bytesRead;

	public static void main(String[] args) {
		//localhost for testing
		ClientSocket clientSocket = new ClientSocket("localhost");
	}

	// Create the client socket using a host's ip
	// For this project this will be the teachers ip
	public ClientSocket(String host) {
		// Create the client socket
		int maxRetries = 5;
		int attempt = 0;
		boolean connected = false;

		// Try to connect to the server
		while (attempt < maxRetries && !connected) {
			try {
				// Connect to the server
				clientSocket = new Socket(host, 8080);
				// Get the input stream from the server
				InputStream is = clientSocket.getInputStream();
				byte[] fileContent = new byte[1024];
				//This file will probably be called EntryData.txt at the end but for now it is ClientData.txt since we need differentiating names
				FileOutputStream fos = new FileOutputStream("src/ClientData.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bytesRead = is.read(fileContent, 0, fileContent.length);
				// Write the file from the server
				bos.write(fileContent, 0, bytesRead);
				// Close the streams and the socket
				bos.close();
				clientSocket.close();
				connected = true;
			} catch (IOException e) {
				// If the connection fails, try again
				attempt++;
				System.out.println("Connection attempt " + attempt + " failed: " + e.getMessage());
				if (attempt >= maxRetries) {
					// If the max retries are reached, print an error message
					System.out.println("Max retries reached. Could not create client socket.");
				}
			}
		}
	}
}