import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserEntriesPanel extends JPanel {
    private JScrollPane scrollPane;
    private JPanel entriesListPanel;
    private ArrayList<String> Links;

    public UserEntriesPanel() throws IOException {
        setLayout(new BorderLayout());
        Links = new ArrayList<>();
        initializeData();

        // Create the panel that will hold the entries, with vertical stacking
        entriesListPanel = new JPanel();
        entriesListPanel.setLayout(new BoxLayout(entriesListPanel, BoxLayout.Y_AXIS));

        // Add entriesListPanel to the scrollPane
        scrollPane = new JScrollPane(entriesListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Add all from ArrayList
        for (String iterator : Links) {
            addEntry(new EntrySet(iterator));
        }

        // Add scrollPane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add an entry panel to the entriesListPanel
    public void addEntry(JPanel entry) {
        entriesListPanel.add(entry);
        entriesListPanel.revalidate();
        entriesListPanel.repaint();
    }

    private void initializeData() throws IOException {
        File placeholder = new File("./src/test.txt");
        placeholder.createNewFile();
        Scanner sc = new Scanner(new File("./src/test.txt"));
        while (sc.hasNextLine()) {
            Links.add(sc.nextLine());
        }
        sc.close();
    }

}
