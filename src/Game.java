import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;


public class Game extends JFrame {

        enum DIFFICULTY {

            EASY,
            MEDIUM,
            HARD

        }

        static boolean isPaused;
        static double vaccine;

        Country russia = new Country("Russia", Country.EconomyType.MEDIUM, Country.DensityType.LOW, Country.ClimateType.COLD, Country.SizeType.LARGE,17130000, Country.HumidityType.MEDIUM, Country.TourismType.LOW);
        Country usa = new Country("USA", Country.EconomyType.RICH, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.LARGE,9834000, Country.HumidityType.MEDIUM, Country.TourismType.MEDIUM);
        Country china = new Country("China", Country.EconomyType.MEDIUM, Country.DensityType.HIGH, Country.ClimateType.MEDIUM, Country.SizeType.LARGE, 9597000, Country.HumidityType.MEDIUM, Country.TourismType.MEDIUM);
        Country poland = new Country("Poland", Country.EconomyType.MEDIUM, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.MEDIUM, 312679, Country.HumidityType.WET, Country.TourismType.MEDIUM);
        Country belarus = new Country("Belarus", Country.EconomyType.MEDIUM, Country.DensityType.LOW, Country.ClimateType.MEDIUM, Country.SizeType.SMALL, 207600, Country.HumidityType.MEDIUM, Country.TourismType.LOW);
        Country ukraine = new Country("Ukraine", Country.EconomyType.MEDIUM, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.MEDIUM, 603628, Country.HumidityType.MEDIUM, Country.TourismType.MEDIUM);
        Country germany = new Country("Germany", Country.EconomyType.RICH, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.MEDIUM, 357386, Country.HumidityType.DRY, Country.TourismType.HIGH);
        Country greenland = new Country("Greenland", Country.EconomyType.POOR, Country.DensityType.LOW, Country.ClimateType.COLD, Country.SizeType.LARGE, 2131000, Country.HumidityType.DRY, Country.TourismType.LOW);
        Country japan = new Country("Japan", Country.EconomyType.RICH, Country.DensityType.HIGH, Country.ClimateType.MEDIUM, Country.SizeType.SMALL, 377975, Country.HumidityType.MEDIUM, Country.TourismType.HIGH);
        Country southKorea = new Country("SouthKorea", Country.EconomyType.RICH, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.SMALL, 100210, Country.HumidityType.MEDIUM, Country.TourismType.HIGH);
        Country northKorea = new Country("NorthKorea", Country.EconomyType.MEDIUM, Country.DensityType.MEDIUM, Country.ClimateType.MEDIUM, Country.SizeType.SMALL, 120540, Country.HumidityType.MEDIUM, Country.TourismType.LOW);
        static DIFFICULTY difficulty;
        static int score;
        static double population;
        JPanel mainPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        JPanel populationPanel = new JPanel();
        MenuButton pause = new MenuButton("⏸");
        ActionListener pauseListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              pause();

            }
        };
        FlowLayout flowLayout = new FlowLayout();
        ImageIcon russiaIcon = new ImageIcon(getClass().getResource("Countries/Russia.png"));
        CountryButton russiaButton = new CountryButton(russiaIcon, russia);
        ImageIcon usaIcon = new ImageIcon(getClass().getResource("Countries/USA.png"));
        CountryButton usaButton = new CountryButton(usaIcon, usa);
        ImageIcon northKoreaIcon = new ImageIcon(getClass().getResource("Countries/NorthKorea.png"));
        CountryButton northKoreaButton = new CountryButton(northKoreaIcon, northKorea);
        ImageIcon southKoreaIcon = new ImageIcon(getClass().getResource("Countries/SouthKorea.png"));
        CountryButton southKoreaButton = new CountryButton(southKoreaIcon, southKorea);
        ImageIcon chinaIcon = new ImageIcon(getClass().getResource("Countries/China.png"));
        CountryButton chinaButton = new CountryButton(chinaIcon, china);
        ImageIcon germanyIcon = new ImageIcon(getClass().getResource("Countries/Germany.png"));
        CountryButton germanyButton = new CountryButton(germanyIcon, germany);
        ImageIcon polandIcon = new ImageIcon(getClass().getResource("Countries/Poland.png"));
        CountryButton polandButton = new CountryButton(polandIcon, poland);
        ImageIcon ukraineIcon = new ImageIcon(getClass().getResource("Countries/Ukraine.png"));
        CountryButton ukraineButton = new CountryButton(ukraineIcon, ukraine);
        ImageIcon belarusIcon = new ImageIcon(getClass().getResource("Countries/Belarus.png"));
        CountryButton belarusButton = new CountryButton(belarusIcon, belarus);
        ImageIcon greenlandIcon = new ImageIcon(getClass().getResource("Countries/Greenland.png"));
        CountryButton greenlandButton = new CountryButton(greenlandIcon, greenland);
        ImageIcon japanIcon = new ImageIcon(getClass().getResource("Countries/Japan.png"));
        CountryButton japanButton = new CountryButton(japanIcon, japan);
        JLabel scoreLabel = new JLabel("Score: " + score + "♛");
        JLabel populationLabel = new JLabel("Healthy people: " + (int)population);
        JProgressBar vaccineBar = new JProgressBar(0, 55000);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                timer.start();
            }

        });

        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                score += difficulty == DIFFICULTY.EASY ? 7 : difficulty == DIFFICULTY.MEDIUM ? 5 : 3;
                populationLabel.setText("Healthy people: " + (int)population);
                vaccineBar.setValue((int)vaccine);
                scoreLabel.setText("Score: " + score + "♛");
                if(population == 0) {
                    SwingUtilities.invokeLater(() -> new GameOver(false, score));
                    timer.stop();
                    setVisible(false);
                    dispose();
                }

            }
        });
        KeyListener pauseKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                if(key == VK_PAUSE || key == VK_P)
                    pause();

            }

            @Override
            public void keyReleased(KeyEvent e) {



            }
        };
        KeyListener backKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == VK_BACK_SPACE || e.getKeyCode() == VK_ESCAPE) {

                   back();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        ChangeListener vaccineListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(vaccineBar.getValue() >= vaccineBar.getMaximum()) {

                    score += difficulty == DIFFICULTY.EASY ? 1000 : difficulty == DIFFICULTY.MEDIUM ? 3500 : 5500;
                    SwingUtilities.invokeLater(() -> new GameOver(true, score));
                    timer.stop();
                    setVisible(false);
                    dispose();

                }
            }
};



        Game() {

            china.setInfected();
            thread.start();
            controlPanel.setBackground(Main.BACKGROUND_COLOR);
            flowLayout.setVgap(75);
            flowLayout.setHgap(45);
            mainPanel.setLayout(flowLayout);
            mainPanel.add(russiaButton);
            mainPanel.add(usaButton);
            mainPanel.add(northKoreaButton);
            mainPanel.add(southKoreaButton);
            mainPanel.add(chinaButton);
            mainPanel.add(germanyButton);
            mainPanel.add(polandButton);
            mainPanel.add(ukraineButton);
            mainPanel.add(belarusButton);
            mainPanel.add(greenlandButton);
            mainPanel.add(japanButton);
            mainPanel.setBackground(Main.BACKGROUND_COLOR);
            add(mainPanel);
            controlPanel.add(pause);
            pause.addActionListener(pauseListener);
            controlPanel.add(vaccineBar);
            vaccineBar.setBackground(Main.BACKGROUND_COLOR);
            vaccineBar.setForeground(MenuButton.CRIMSON);
            vaccineBar.setBorder(MenuButton.BORDER);
            vaccineBar.setPreferredSize(MenuButton.SIZE_MAX);
            vaccineBar.addChangeListener(vaccineListener);
            controlPanel.add(scoreLabel);
            scoreLabel.setForeground(MenuButton.CRIMSON);
            scoreLabel.setFont(MenuButton.MONOFONT);
            populationPanel.add(populationLabel);
            populationPanel.setBackground(Main.BACKGROUND_COLOR);
            populationLabel.setForeground(MenuButton.CRIMSON);
            populationLabel.setFont(MenuButton.MONOFONT_THIN);
            mainPanel.add(controlPanel);
            mainPanel.add(populationPanel);
            Main.setIcons(this);
            setUndecorated(true);
            setResizable(false);
            setSize(Main.SCREEN_SIZE);
            setTitle("Corona Slayer");
            setLocationRelativeTo(null);
            addKeyListener(pauseKeyListener);
            addKeyListener(backKeyListener);
            setFocusable(true);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {

                    Main.confirmExit();

                }
            });
            setVisible(true);


        }


       void pause() {

           if(timer.isRunning())
           {
               timer.stop();
               isPaused = true;
               pause.setText("▶");
           }
           else
           {
               timer.start();
               isPaused = false;
               pause.setText("⏸");
           }

       }

       void back() {

           JDialog backDialog = new JDialog();
           MenuButton yes = new MenuButton("Yes");
           MenuButton no = new MenuButton("No");
           JLabel exitMessage = new JLabel("To menu?");
           JPanel panel = new JPanel();
           ActionListener exitListener = new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if(e.getSource() == yes) { Game.super.setVisible(false); Game.super.dispose(); }
                   backDialog.setVisible(false); backDialog.dispose();
               }
           };


               panel.add(exitMessage);
               panel.add(Box.createRigidArea(new Dimension(0,195)));
               panel.add(yes);
               yes.setPreferredSize(MenuButton.SIZE_MAX);
               panel.add(no);
               no.setPreferredSize(MenuButton.SIZE_MAX);
               exitMessage.setMaximumSize(MenuButton.SIZE_MAX);
               exitMessage.setForeground(Main.TEXT_COLOR);
               exitMessage.setFont(MenuButton.MONOFONT);
               yes.addActionListener(exitListener);
               no.addActionListener(exitListener);
               backDialog.add(panel);
               backDialog.setUndecorated(true);
               panel.setBackground(Main.BACKGROUND_COLOR);
               backDialog.setResizable(false);
               backDialog.setModal(true);
               backDialog.setSize(Main.MENU_SIZE);
               backDialog.setLocationRelativeTo(null);
               backDialog.setVisible(true);
               backDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

           }


}
