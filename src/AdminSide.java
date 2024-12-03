import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

public class AdminSide {
    static JPanel entryPanel;
    static JButton addEntryButton;
    private static AdminUserEntriesPanel userEntriesPanel;

    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2,0));
        entryPanel.setVisible(true);
        mainFrame.add(entryPanel);

        userEntriesPanel = new AdminUserEntriesPanel();
        entryPanel.add(userEntriesPanel);

        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(entryPanel, BorderLayout.CENTER);

        Panel toolBar = new Panel();
        mainFrame.add(toolBar, BorderLayout.NORTH);

        JLabel ipField = new JLabel("IP Address :: " + InetAddress.getLocalHost().getHostAddress());
        toolBar.add(ipField);

        JButton startServerButton = new JButton("Start Server");
        startServerButton.addActionListener(e -> {
            AdminSocket serverSocket = new AdminSocket();
		});
        toolBar.add(startServerButton);

        JButton addEntryButton = new JButton("Add Entry");
        addEntryButton.addActionListener(e -> {
            AdminAddEntry adminAddEntry = new AdminAddEntry(userEntriesPanel);
        });
        toolBar.add(addEntryButton);

        mainFrame.add(entryPanel);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();
        mainFrame.add(detailedEntryPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
