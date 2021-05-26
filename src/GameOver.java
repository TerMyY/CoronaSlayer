import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameOver extends JDialog {

    File scores = new File("Scores.txt");
    BufferedWriter scoresWriter;

    {
        try { scoresWriter = new BufferedWriter(new FileWriter(scores, true)); }
        catch (IOException e) { JOptionPane.showMessageDialog(this, e, "IO Exception", JOptionPane.ERROR_MESSAGE); }
    }

    int score;
    String[] scoresLines;

    JPanel panel = new JPanel();
    JLabel gameOverLabel = new JLabel();
    JTextArea nameArea = new JTextArea();
    MenuButton okButton = new MenuButton("OK!");
    ActionListener okListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                    try { scoresWriter.append(nameArea.getText()).append(" - ").append(String.valueOf(score)).append("\n"); }
                    catch (IOException ioException) { JOptionPane.showMessageDialog(panel, ioException, "IO Exception", JOptionPane.ERROR_MESSAGE); }


            setVisible(false); dispose();
            try { scoresWriter.close(); } catch (IOException ioException) { ioException.printStackTrace(); }

        }
    };

    GameOver(boolean isWin, int score) {

        this.score = score;
        gameOverLabel.setFont(MenuButton.MONOFONT);
        gameOverLabel.setForeground(Main.TEXT_COLOR);
        gameOverLabel.setText(isWin ? "You won! Your initials: " : "You lost. Your initials: ");
        nameArea.setBackground(Main.BACKGROUND_COLOR);
        nameArea.setForeground(Main.TEXT_COLOR);
        nameArea.setFont(MenuButton.MONOFONT);
        nameArea.setFocusable(true);
        nameArea.setBorder(MenuButton.BORDER);
        panel.add(gameOverLabel);
        panel.add(nameArea);
        panel.add(okButton);
        panel.setBackground(Main.BACKGROUND_COLOR);
        okButton.addActionListener(okListener);
        add(panel);
        setUndecorated(true);
        setResizable(false);
        setModal(true);
        setTitle("Corona Slayer");
        setSize(Main.SCREEN_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }
}




