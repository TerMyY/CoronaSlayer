import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {

    MenuButton play = new MenuButton("Play");
    MenuButton scores = new MenuButton("Scores");
    MenuButton exit = new MenuButton("Exit");
    JPanel mainPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
    GridBagLayout gridBagLayout = new GridBagLayout();
    ActionListener exitAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

           Main.confirmExit();

        }
    };
    ActionListener scoresAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(Scores::new);

        }
    };
    ActionListener playAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(DifficultyScreen::new);

        }
    };

    Menu() {

    mainPanel.setLayout(gridBagLayout);
    controlPanel.setLayout(boxLayout);
    controlPanel.add(play);
    controlPanel.add(Box.createRigidArea(new Dimension(0, 35)));
    controlPanel.add(scores);
    controlPanel.add(Box.createRigidArea(new Dimension(0, 35)));
    controlPanel.add(exit);
    play.setPreferredSize(MenuButton.SIZE_MAX);
    scores.setPreferredSize(MenuButton.SIZE_MAX);
    exit.setPreferredSize(MenuButton.SIZE_MAX);
    play.addActionListener(playAction);
    scores.addActionListener(scoresAction);
    exit.addActionListener(exitAction);
    mainPanel.add(controlPanel);
    add(mainPanel);
    mainPanel.setBackground(Main.BACKGROUND_COLOR);
    controlPanel.setBackground(Main.BACKGROUND_COLOR);
    Main.setIcons(this);
    setUndecorated(true);
    setResizable(false);
    setSize(Main.MENU_SIZE);
    setTitle("Corona Slayer");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {

            Main.confirmExit();

        }
    });

    setVisible(true);

    }



}
