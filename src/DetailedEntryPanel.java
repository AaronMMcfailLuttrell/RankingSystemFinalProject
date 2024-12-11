import javax.swing.*;
import java.awt.*;

public class DetailedEntryPanel extends JPanel implements DetailsListener {
    public DetailedEntryPanel() {
        super();
        setBackground(Color.white);
        this.setLayout(new GridBagLayout());
    }

    public void showDetails(Repo repo) {
        if (!repo.isDownloaded()) {
            repo.downloadRepo();
        }

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.PAGE_START;
        labelConstraints.gridy = 0;

        GridBagConstraints panelConstainsts = new GridBagConstraints();
        panelConstainsts.anchor = GridBagConstraints.CENTER;
        panelConstainsts.fill = GridBagConstraints.BOTH;
        panelConstainsts.weightx = 1.0;
        panelConstainsts.weighty = 1.0;
        panelConstainsts.gridy = 1;

        this.add(new JLabel(repo.getUser() + " " + repo.getTitle()), labelConstraints);
        this.add(new JScrollPane(new JTree(repo.walkDirectory())), panelConstainsts);
        revalidate();
        repaint();
    }

    @Override
    public void onDetailsClicked(Repo repo) {
        showDetails(repo);
    }
}
