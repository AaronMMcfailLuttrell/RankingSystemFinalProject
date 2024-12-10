import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class UserEntriesPanel extends JPanel implements EntriesPanel {
    private JScrollPane scrollPane;
    private JPanel entriesListPanel;
    private ArrayList<String> Links;
    private ClientSocket userSocket;
    private Consumer<EntrySet> detailsListenerSetter;

    public UserEntriesPanel(ClientSocket userSocket) throws IOException {
        this.userSocket = userSocket;

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
            addEntry(new EntrySet(iterator, userSocket));
        }

        // Add scrollPane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add an entry panel to the entriesListPanel
    public void addEntry(EntrySet entry) {
        entriesListPanel.add(entry);
        entriesListPanel.revalidate();
        entriesListPanel.repaint();
        if (this.detailsListenerSetter != null) {
            this.detailsListenerSetter.accept(entry);
        }
    }

    public void refreshPanel() {
        entriesListPanel.removeAll();
        //Add all from ArrayList
        for (String iterator : Links) {
            addEntry(new EntrySet(iterator, userSocket));
        }
    }

    public void initializeData() throws IOException {
        Links.clear();
        File placeholder = new File(Constants.FilePath);
        placeholder.createNewFile();
        Scanner sc = new Scanner(new File(Constants.FilePath));
        while (sc.hasNextLine()) {
            Links.add(sc.nextLine());
        }
        sc.close();
    }

    @Override
    public void setDetailsListenerSetter(Consumer<EntrySet> consumer) {
        this.detailsListenerSetter = consumer;
    }
}
