import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ClientSocket userSocket;
    AdminSocket serverSocket;

    public MainFrame() {
        setTitle("Ranking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridLayout(1, 2));
        setVisible(true);
    }

    public void setUserSocket(ClientSocket userSocket) {
        this.userSocket = userSocket;
    }

    public void setServerSocket(AdminSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void windowClosing() {
        if (serverSocket != null)
            serverSocket.stopServer();

        if (userSocket != null)
            userSocket.closeSocket();

        System.exit(0);
    }
}
