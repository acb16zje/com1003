package assignment2017;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * A class that produce a GUI for the game mode selector
 *
 * @author Zer Jun Eng
 */
public class Connect4GameModeFrame extends JFrame {

    private final Connect4 game;

    public static String[] gameModeLists = {
            "Human vs Random Player",
            "Human vs Intelligent Player",
            "Human vs Human"
    };

    /**
     * Constructor for main menu (game mode frame)
     *
     * @param game The current Connect 4 game
     */
    public Connect4GameModeFrame(final Connect4 game) {
        this.game = game;

        final JPanel gameModePanel = new JPanel();
        gameModePanel.add(createGameModePanel());

        final Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(gameModePanel, BorderLayout.CENTER);

        setTitle("Welcome to Connect 4");
        pack(); // Automatically setSize the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates a combo box of game modes
     *
     * @return The JPanel containing the game mode combo box
     */
    private JPanel createGameModePanel() {
        final JPanel gameMode = new JPanel();
        gameMode.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10),
                new TitledBorder("Please Select a Game Mode")
            )
        );

        final JComboBox gameModeOptions = new JComboBox(gameModeLists);
        final JButton startGame = new JButton("Start");
        startGame.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playGUI(gameModeOptions.getSelectedIndex());
                dispose();
            }
        });

        gameMode.add(gameModeOptions);
        gameMode.add(startGame);

        return gameMode;
    }
}
