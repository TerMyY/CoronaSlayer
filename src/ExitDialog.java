import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitDialog extends JDialog {

    MenuButton yes = new MenuButton("Yes");
    MenuButton no = new MenuButton("No");
    JLabel exitMessage = new JLabel("Exit?");
    JPanel panel = new JPanel();
    ActionListener exitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == yes) { System.exit(0); }
            else { setVisible(false); dispose(); }
        }
    };

    ExitDialog() {

        panel.add(exitMessage);
        panel.add(Box.createRigidArea(new Dimension(0, 195)));
        panel.add(yes);
        yes.setPreferredSize(MenuButton.SIZE_MAX);
        panel.add(no);
        no.setPreferredSize(MenuButton.SIZE_MAX);
        exitMessage.setForeground(Main.TEXT_COLOR);
        exitMessage.setFont(MenuButton.MONOFONT);
        yes.addActionListener(exitListener);
        no.addActionListener(exitListener);
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
