import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLayout(new BorderLayout());

        Panel toolBar = new Panel();
        mainFrame.add(toolBar, BorderLayout.NORTH);

        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(1, 2));
        mainFrame.add(centerPanel, BorderLayout.CENTER);

        UserEntriesPanel userEntriesPanel = new UserEntriesPanel();
        DetailedEntryPanel detailedEntryPanel = new DetailedEntryPanel();

        centerPanel.add(userEntriesPanel);
        centerPanel.add(detailedEntryPanel);

        centerPanel.repaint();
        centerPanel.revalidate();

    }

}

