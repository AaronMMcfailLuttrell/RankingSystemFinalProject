// Imports
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Class to create the server socket
public class AdminSocket {
	// Variables
	private final int PORT = 65535;
	private final String PATH = "src/EntryData.txt";
	private ServerSocket serverSocket;
	private ExecutorService executorService;

	// Main test method
	public static void main(String[] args) {
		AdminSocket adminSocket = new AdminSocket();
		adminSocket.startServer();
	}

	// Constructor
	public AdminSocket() {
		try {
			// Create the server socket
			serverSocket = new ServerSocket(PORT);
			// Create a thread pool with a fixed number of threads
			executorService = Executors.newFixedThreadPool(20);
		} catch (IOException e) {
			// If the server socket cannot be created, print an error message
			System.out.println("Could not create server socket: " + e.getMessage());
		}
	}

	// Method to start the server
	public void startServer() {
		System.out.println("Server started. Waiting for clients...");
		// Wait for clients to connect
		while (true) {
			try {
				// Accept a new client and handle it in a separate thread
				Socket clientSocket = serverSocket.accept();
				executorService.submit(new ClientHandler(clientSocket, PATH));
			} catch (IOException e) {
				// If an error occurs, print the error message
				System.out.println("Error accepting client connection: " + e.getMessage());
			}
		}
	}

	public void stopServer() {
		try {
			serverSocket.close();
			executorService.shutdown();
		} catch (IOException e) {
			System.out.println("Error closing server socket: " + e.getMessage());
		}
	}
}