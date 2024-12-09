import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        ClientSocket clientSocket = new ClientSocket("localhost");

        MainFrame mainFrame = new MainFrame();
        mainFrame.setLayout(new BorderLayout());

        Panel toolBar = new Panel();
        mainFrame.add(toolBar, BorderLayout.NORTH);

        //connection to host
        JTextField ipField = new JTextField();
        ipField.setPreferredSize(new Dimension(100, 30));
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(e -> {
            clientSocket.setHost(ipField.getText());
            new Thread(clientSocket::connectSocket).start();
        });

        mainFrame.setUserSocket(clientSocket);

        toolBar.add(ipField);
        toolBar.add(connectButton);

        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(1, 2));
        mainFrame.add(centerPanel, BorderLayout.CENTER);

        UserEntriesPanel userEntriesPanel = new UserEntriesPanel(clientSocket);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();

        centerPanel.add(userEntriesPanel);
        centerPanel.add(detailedEntryPanel);

        while (true) {
            userEntriesPanel.initializeData();
            userEntriesPanel.refreshPanel();
            centerPanel.repaint();
            centerPanel.revalidate();

            try {
                System.out.println("Refreshing");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

