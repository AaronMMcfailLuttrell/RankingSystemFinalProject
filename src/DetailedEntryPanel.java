import javax.swing.*;
import java.awt.*;

public class DetailedEntryPanel extends JPanel implements DetailsListener {
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

    @Override
    public void onDetailsClicked(Repo repo) {
        showDetails(repo);
    }
}
