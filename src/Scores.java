import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Scores extends JDialog {

    JPanel mainPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JPanel listPanel = new JPanel();
    BorderLayout controlLayout= new BorderLayout();
    BorderLayout borderLayout = new BorderLayout();
    BoxLayout boxLayout = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
    MenuButton back = new MenuButton("Back");
    MenuButton up = new MenuButton("▲");
    MenuButton down = new MenuButton("▼");
    JLabel first = new JLabel("");
    JLabel second = new JLabel("");
    JLabel third = new JLabel("");
    ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    };

    File scoresFile = new File("Scores.txt");
    BufferedReader scoresReader;

    {
        try {
            scoresReader = new BufferedReader(new FileReader(scoresFile));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e, "File not found", JOptionPane.ERROR_MESSAGE);
        }
    }



    Scores() {

        listPanel.setLayout(boxLayout);
        try {
            for (String line; (line = scoresReader.readLine()) != null;) {
                if(first.getText().equals("")) { first.setText(scoresReader.readLine()); continue; }
                if (second.getText().equals("")) { second.setText(scoresReader.readLine()); continue; }
                if (third.getText().equals("")) { third.setText(scoresReader.readLine()); continue; }
            }
        }
        catch (IOException e) { JOptionPane.showMessageDialog(this, e, "IO Exception", JOptionPane.ERROR_MESSAGE); }
        listPanel.add(first);
        listPanel.add(second);
        listPanel.add(third);
        listPanel.setBackground(Main.BACKGROUND_COLOR);
        first.setFont(MenuButton.MONOFONT_THIN);
        first.setForeground(Main.TEXT_COLOR);
        second.setFont(MenuButton.MONOFONT_THIN);
        second.setForeground(Main.TEXT_COLOR);
        third.setFont(MenuButton.MONOFONT_THIN);
        third.setForeground(Main.TEXT_COLOR);
        back.setPreferredSize(MenuButton.SIZE_MAX);
        setSize(Main.MENU_SIZE);
        back.setMaximumSize(new Dimension(getBounds().width, MenuButton.SIZE_MAX.height));
        back.addActionListener(backListener);
        mainPanel.setLayout(borderLayout);
        mainPanel.add(back, BorderLayout.PAGE_END);
        controlPanel.setLayout(controlLayout);
        //controlPanel.add(up, BorderLayout.PAGE_START);
        //controlPanel.add(down, BorderLayout.PAGE_END);
        controlPanel.setBackground(Main.BACKGROUND_COLOR);
        mainPanel.add(listPanel);
        mainPanel.add(controlPanel, BorderLayout.LINE_END);
        mainPanel.setBackground(Main.BACKGROUND_COLOR);
        //up.setFont(MenuButton.MONOFONT_THIN);
        //down.setFont(MenuButton.MONOFONT_THIN);
        add(mainPanel);
        setUndecorated(true);
        setResizable(false);
        setModal(true);
        setTitle("Scores");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }

    }

