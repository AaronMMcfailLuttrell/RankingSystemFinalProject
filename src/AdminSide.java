import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminSide {
    static JPanel entryPanel;
    static JButton addEntryButton;
    private static UserEntriesPanel userEntriesPanel;
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2,0));
        entryPanel.setVisible(true);
        mainFrame.add(entryPanel);

        userEntriesPanel = new UserEntriesPanel();
        entryPanel.add(userEntriesPanel);

        JPanel placeholderPanel = new JPanel();
        JButton addEntryButton = new JButton("Add Entry");
        addEntryButton.addActionListener(e -> {
            AdminAddEntry adminAddEntry = new AdminAddEntry(userEntriesPanel);

        });
        placeholderPanel.add(addEntryButton);
        entryPanel.add(placeholderPanel);

        mainFrame.add(entryPanel);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();
        mainFrame.add(detailedEntryPanel);
        userEntriesPanel.setVisible(true);
        userEntriesPanel.repaint();
        userEntriesPanel.revalidate();

    }

}
