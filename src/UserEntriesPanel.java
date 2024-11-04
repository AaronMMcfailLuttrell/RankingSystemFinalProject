import javax.swing.*;
import java.awt.*;

public class UserEntriesPanel extends JPanel {
    private JScrollPane scrollPane;
    private JPanel entriesListPanel;

    public UserEntriesPanel() {
        setLayout(new BorderLayout());

        // Create the panel that will hold the entries, with vertical stacking
        entriesListPanel = new JPanel();
        entriesListPanel.setLayout(new BoxLayout(entriesListPanel, BoxLayout.Y_AXIS));

        // Add entriesListPanel to the scrollPane
        scrollPane = new JScrollPane(entriesListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add scrollPane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add an entry panel to the entriesListPanel
    public void addEntry(JPanel entry) {
        entriesListPanel.add(entry);
        entriesListPanel.revalidate();
        entriesListPanel.repaint();
    }

}
