import javax.swing.*;
import java.awt.*;

public class AdminSide {
    static JPanel entryPanel;
    public static void main(String[] args) {
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


        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < 20; i++) {
            String placeholder = "";
            for (int j = 0; j < 10; j++) {
                placeholder += alphabet[(int) (Math.random() * alphabet.length)];
            }
            placeholder += ".exe";
            userEntriesPanel.addEntry(new EntrySet(placeholder));
        }



    }
}
