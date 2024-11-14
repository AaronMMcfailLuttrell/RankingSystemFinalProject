import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminEntrySet extends EntrySet {
    public AdminEntrySet(String githubLink, AdminUserEntriesPanel referencePanel) {
        super(githubLink);
        JButton deleteEntryButton = new JButton("Delete");
        deleteEntryButton.setLocation(Constants.ENTRY_SET_PANEL_WIDTH + 70, Constants.ENTRY_SET_PANEL_HEIGHT - 40);
        deleteEntryButton.setSize(70, 30);
        deleteEntryButton.setBackground(Color.RED);
        deleteEntryButton.addActionListener(e -> {
            try {
                deleteButtonLambda(githubLink, referencePanel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteEntryButton.setVisible(true);
        add(deleteEntryButton);
    }

    private void deleteButtonLambda(String githubLink, AdminUserEntriesPanel referencePanel) throws IOException {
        File placeholder = new File(Constants.FilePath);
        placeholder.createNewFile();
        Scanner sc = new Scanner(new File(Constants.FilePath));
        ArrayList<String> entries = new ArrayList<>();
        //Read all entries into a temporary list
        while (sc.hasNextLine()) {
            entries.add(sc.nextLine());
        }
        FileWriter fileWriter = new FileWriter(Constants.FilePath);
        //Remove the specific instance that has the github link
        entries.remove(githubLink);

        //Overwrite the original file to correctly delete it
        entries.forEach(entry -> {
			try {
				fileWriter.write(entry + "\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

        fileWriter.close();
        sc.close();

        //refresh panel
        referencePanel.refreshPanel();

    }

}
