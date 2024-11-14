import javax.swing.*;
import java.awt.*;

public class DetailedEntryPanel extends JPanel {
    public DetailedEntryPanel() {
        setBackground(Color.white);
    }

    public void showDetails(Repo repo) {
        if (!repo.isDownloaded()) {
            repo.downloadRepo();
        }

        this.add(new JLabel(repo.getUser() + " " + repo.getTitle()));
        revalidate();
        repaint();
    }
}
