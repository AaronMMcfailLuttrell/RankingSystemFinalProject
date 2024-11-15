import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientSocket {
	Socket clientSocket;
	int bytesRead;

	public static void main(String[] args) {
		ClientSocket clientSocket = new ClientSocket();
	}

	public ClientSocket() {
		// Create the client socket
		int maxRetries = 5;
		int attempt = 0;
		boolean connected = false;

		while (attempt < maxRetries && !connected) {
			try {
				clientSocket = new Socket("localhost", 8080);
				InputStream is = clientSocket.getInputStream();
				byte[] fileContent = new byte[1024];
				FileOutputStream fos = new FileOutputStream("src/ClientData.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bytesRead = is.read(fileContent, 0, fileContent.length);
				bos.write(fileContent, 0, bytesRead);
				bos.close();
				clientSocket.close();
				connected = true;
			} catch (IOException e) {
				attempt++;
				System.out.println("Connection attempt " + attempt + " failed: " + e.getMessage());
				if (attempt >= maxRetries) {
					System.out.println("Max retries reached. Could not create client socket.");
				}
			}
		}
	}
}
