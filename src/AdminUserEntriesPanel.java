import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminUserEntriesPanel extends JPanel {
    private JScrollPane scrollPane;
    private JPanel entriesListPanel;
    private ArrayList<String> Links;

    public AdminUserEntriesPanel() throws IOException {
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
            addEntryAdmin(new AdminEntrySet(iterator, this));
        }

        // Add scrollPane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }
    //Admin setup
    public void addEntryAdmin(AdminEntrySet entry) {
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

    public void refreshPanel() throws IOException {
        entriesListPanel.removeAll();
        entriesListPanel.revalidate();
        entriesListPanel.repaint();
        Links.clear();
        initializeData();
        for (String iterator : Links) {
            addEntryAdmin(new AdminEntrySet(iterator, this));
        }

    }

}
