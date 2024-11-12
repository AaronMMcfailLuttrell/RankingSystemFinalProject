import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AdminAddEntry extends JDialog {
    private JTextField nameField;
    private UserEntriesPanel userEntriesPanel;
    public AdminAddEntry(UserEntriesPanel userPanel) {
        userEntriesPanel = userPanel;
        this.setTitle("Add Event");
        this.setModal(true);
        this.setResizable(false);
        this.setSize(800,300);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        //Where the user inserts the link
        nameField = new JTextField();
        nameField.setEditable(true);
        nameField.setSize(400,30);
        nameField.setBackground(Color.WHITE);
        nameField.setLocation(100,100);
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameField.setVisible(true);
        add(nameField);

        //Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setLocation(550,100);
        submitButton.setSize(100,30);
        submitButton.setBackground(new Color(61, 239, 79, 255));
        submitButton.setVisible(true);
        submitButton.addActionListener(e -> {
            try {
                submitLambda();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(submitButton);



        setVisible(true);
        repaint();
        revalidate();

    }

    private void submitLambda() throws IOException {
        EntrySet entrySet = new EntrySet(nameField.getText());
        userEntriesPanel.addEntry(entrySet);
        FileWriter fileWriter = new FileWriter("./src/test.txt");
        fileWriter.write(nameField.getText());
        fileWriter.close();
        dispose();
    }

}
