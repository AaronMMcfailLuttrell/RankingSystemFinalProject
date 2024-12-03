// Imports
import java.io.*;
import java.net.Socket;

// Class to handle the client
public class ClientHandler implements Runnable {
	// Variables
	private final Socket clientSocket;
	private final String filePath;

	// Constructor
	public ClientHandler(Socket clientSocket, String filePath) {
		this.clientSocket = clientSocket;
		this.filePath = filePath;
	}

	// Run method
	@Override
	public void run() {
		try {
			// Send the file to the client
			sendFile();



		} catch (IOException e) {
			// If an error occurs, print the error message
			System.out.println("Error handling client: " + e.getMessage());
		}

		// Close the client socket
		try {
			clientSocket.close();
		} catch (IOException e) {
			// If an error occurs, print the error message
			System.out.println("Error closing client socket: " + e.getMessage());
		}
	}

	// Method to send the file to the client
	private void sendFile() throws IOException {
		// Create the things needed to read the file
		File file = new File(filePath);
		byte[] fileContent = new byte[(int) file.length()];

		// Create stream for reading file
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);

		// Read the file into the byte array
		bis.read(fileContent, 0, fileContent.length);

		// Send the file to the client
		OutputStream os = clientSocket.getOutputStream();
		os.write(fileContent, 0, fileContent.length);
		os.flush();
	}
}