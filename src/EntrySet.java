import javax.swing.*;
import java.awt.*;

//Panel that stores the github link and run button
public class EntrySet extends JPanel {
    private static final int CONSTRUCTOR_TEXT_LOC_X = 20;
    private static final int CONSTRUCTOR_TEXT_LOC_Y = 0;
    private static final int CONSTRUCTOR_TEXT_SIZE_X = 100;
    private static final int CONSTRUCTOR_TEXT_SIZE_Y = 25;
    private static final int VOTE_SIZE_X = 70;
    private static final int VOTE_SIZE_Y = 30;

    JLabel TextInstance;
    JButton voteButton;
    JButton runButton;

    public EntrySet() {
        setPreferredSize(new Dimension(Constants.ENTRY_SET_PANEL_WIDTH, Constants.ENTRY_SET_PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);
        setTextInstance("Empty Set");
        add(TextInstance);
        setVoteButton();
        add(voteButton);
        setRunButton();
        add(runButton);

        setVisible(true);
    }

    public EntrySet(String githubLink) {
        setPreferredSize(new Dimension(Constants.ENTRY_SET_PANEL_WIDTH, Constants.ENTRY_SET_PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);

        setTextInstance(githubLink);
        add(TextInstance);
        setVoteButton();
        add(voteButton);
        setRunButton();
        add(runButton);

        setVisible(true);
    }

    private void setVoteButton() {
        //Create Vote button
        voteButton = new JButton("Vote");
        voteButton.setSize(VOTE_SIZE_X,VOTE_SIZE_Y);
        voteButton.setBackground(new Color(144,233,144));
        voteButton.setLocation(CONSTRUCTOR_TEXT_LOC_X, Constants.ENTRY_SET_PANEL_HEIGHT - VOTE_SIZE_Y - 10);
        voteButton.setVisible(true);
    }

    private void setTextInstance(String githubLink) {
        TextInstance = new JLabel();
        TextInstance.setSize(CONSTRUCTOR_TEXT_SIZE_X,CONSTRUCTOR_TEXT_SIZE_Y);
        TextInstance.setLocation(CONSTRUCTOR_TEXT_LOC_X,CONSTRUCTOR_TEXT_LOC_Y);
        TextInstance.setText(githubLink);
    }

    private void setRunButton() {
        runButton = new JButton("Run");
        runButton.setSize(VOTE_SIZE_X, VOTE_SIZE_Y);
        runButton.setBackground(new Color(199, 114, 199, 255));
        runButton.setLocation(Constants.ENTRY_SET_PANEL_WIDTH - VOTE_SIZE_X, Constants.ENTRY_SET_PANEL_HEIGHT - VOTE_SIZE_Y - 10);
        runButton.setVisible(true);
    }

    /*
    Socket to Admin to update vote count on admin end
     */
    private void lambdaFunctionVote() {

    }

}
