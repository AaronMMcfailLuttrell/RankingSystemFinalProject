import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminSocket {
	private final int numClients = 20;
	private final int PORT = 8080;
	private final String PATH = "src/EntryData.txt";
	ServerSocket serverSocket;
	private Socket[] clientSockets;

	public AdminSocket() {
		try {
			// Create the server socket
			serverSocket = new ServerSocket(PORT);

			// Accept the client sockets
			clientSockets = new Socket[numClients];
			for (int i = 0; i < numClients; i++) {
				clientSockets[i] = serverSocket.accept();
			}
		} catch (IOException e) {
			System.out.println("Could not create server socket");
		}
	}

	public void closeSockets() throws IOException {
		// Close the sockets
		for (Socket client : clientSockets) {
			client.close();
		}

		serverSocket.close();
	}

	// Send the file to the client
	public void sendFile() throws IOException {
		// Create the things needed to read the file
		File file = new File(PATH);
		byte[] fileContent = new byte[(int) file.length()];

		// Create stream for reading file
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);

		// Read the file into the byte array
		bis.read(fileContent, 0, fileContent.length);

		// Send the file to the client
		for (Socket client : clientSockets) {
			OutputStream os = client.getOutputStream();
			os.write(fileContent, 0, fileContent.length);
			os.flush();
		}
	}
}
