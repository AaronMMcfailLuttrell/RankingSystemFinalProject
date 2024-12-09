import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AdminSide {
    static JPanel entryPanel;
    static JButton addEntryButton;
    private static AdminUserEntriesPanel userEntriesPanel;
    private static AdminSocket serverSocket;

    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        entryPanel = new JPanel();
        entryPanel.setLayout(new BorderLayout());
        entryPanel.setVisible(true);

        userEntriesPanel = new AdminUserEntriesPanel();
        entryPanel.add(userEntriesPanel);

        mainFrame.setLayout(new BorderLayout());

        Panel toolBar = getPanel();

        mainFrame.add(toolBar, BorderLayout.NORTH);
        mainFrame.add(entryPanel, BorderLayout.CENTER);

        mainFrame.setServerSocket(serverSocket);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private static Panel getPanel() throws UnknownHostException {
        Panel toolBar = new Panel();

        JLabel ipField = new JLabel("IP Address :: " + InetAddress.getLocalHost().getHostAddress());
        toolBar.add(ipField);

        JButton startServerButton = new JButton("Start Server");
        startServerButton.addActionListener(e -> {
            serverSocket = new AdminSocket();
		});
        toolBar.add(startServerButton);

        JButton addEntryButton = new JButton("Add Entry");
        addEntryButton.addActionListener(e -> {
            AdminAddEntry adminAddEntry = new AdminAddEntry(userEntriesPanel);
        });
        toolBar.add(addEntryButton);
        return toolBar;
    }
}
