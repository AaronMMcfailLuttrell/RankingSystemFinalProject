import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ClientSocket userSocket;

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

    public void windowClosing() {
        userSocket.closeSocket();
        System.exit(0);
    }
}
