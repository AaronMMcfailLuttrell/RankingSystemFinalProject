import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminSide {
    static JPanel entryPanel;
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2,0));
        entryPanel.setVisible(true);
        mainFrame.add(entryPanel);

        UserEntriesPanel userEntriesPanel = new UserEntriesPanel();
        entryPanel.add(userEntriesPanel);

        JPanel placeholderPanel = new JPanel();
        entryPanel.add(placeholderPanel);

        mainFrame.add(entryPanel);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();
        mainFrame.add(detailedEntryPanel);
        userEntriesPanel.setVisible(true);
        userEntriesPanel.repaint();
        userEntriesPanel.revalidate();

    }

}
