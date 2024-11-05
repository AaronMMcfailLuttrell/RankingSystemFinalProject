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

