import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        UserEntriesPanel userEntriesPanel = new UserEntriesPanel();
        mainFrame.add(userEntriesPanel);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();
        mainFrame.add(detailedEntryPanel);


        for (int i = 0; i < 60; i++) {
            JPanel entry = new JPanel();
            entry.setPreferredSize(new Dimension(250, 50));
            entry.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            entry.add(new JLabel("Entry " + (i + 1)));
            entry.setVisible(true);
            userEntriesPanel.addEntry(entry);
        }

    }

}

