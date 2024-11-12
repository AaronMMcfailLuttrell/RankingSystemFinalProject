import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        UserEntriesPanel userEntriesPanel = new UserEntriesPanel();
        mainFrame.add(userEntriesPanel);
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();
        mainFrame.add(detailedEntryPanel);
        userEntriesPanel.setVisible(true);
        userEntriesPanel.repaint();
        userEntriesPanel.revalidate();

    }

}

