import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AdminAddEntry extends JDialog {
    private JTextField nameField;
    private AdminUserEntriesPanel userEntriesPanel;
    public AdminAddEntry(AdminUserEntriesPanel userPanel) {
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
        AdminEntrySet entrySet = new AdminEntrySet(nameField.getText(), userEntriesPanel);
        userEntriesPanel.addEntryAdmin(entrySet);
        FileWriter fileWriter = new FileWriter(Constants.FilePath, true);
        fileWriter.write(nameField.getText() + "\n");
        fileWriter.close();
        dispose();
    }

}
