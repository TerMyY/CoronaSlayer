import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyScreen extends JDialog {

    JPanel panel = new JPanel();
    MenuButton easy = new MenuButton("We're responsible citizens");
    MenuButton medium = new MenuButton("Let's do some barbeque");
    MenuButton hard = new MenuButton("Virus is a myth");
    ActionListener difficultyListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == easy) {Game.difficulty = Game.DIFFICULTY.EASY; SwingUtilities.invokeLater(Game::new); setVisible(false); dispose();}
            else if(e.getSource() == medium) {Game.difficulty = Game.DIFFICULTY.MEDIUM; SwingUtilities.invokeLater(Game::new); setVisible(false); dispose();}
            else if(e.getSource() == hard) {Game.difficulty = Game.DIFFICULTY.HARD; SwingUtilities.invokeLater(Game::new); setVisible(false); dispose();}
            else try { throw new Exception(); } catch (Exception exception) { exception.printStackTrace(); }

        }
    };

    DifficultyScreen() {

        easy.setPreferredSize(MenuButton.SIZE_MAX);
        easy.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        medium.setPreferredSize(MenuButton.SIZE_MAX);
        medium.setFont(MenuButton.MONOFONT_THIN);
        hard.setPreferredSize(MenuButton.SIZE_MAX);
        hard.setFont(MenuButton.MONOFONT_THIN);
        panel.add(easy);
        panel.add(Box.createRigidArea(new Dimension(0,175)));
        panel.add(medium);
        panel.add(Box.createRigidArea(new Dimension(0,155)));
        panel.add(hard);
        panel.add(Box.createRigidArea(new Dimension(0,175)));
        easy.addActionListener(difficultyListener);
        medium.addActionListener(difficultyListener);
        hard.addActionListener(difficultyListener);
        add(panel);
        setUndecorated(true);
        panel.setBackground(Main.BACKGROUND_COLOR);
        setResizable(false);
        setModal(true);
        setSize(Main.MENU_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }

}
