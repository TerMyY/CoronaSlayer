import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountryButton extends JButton {

    Country country;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                if(country.infected > 0)
                    setIcon(new ImageIcon(getClass().getResource("Countries/" + country.name + "Orange.png")));
                if(country.virus != null)
                if(country.virus.lethal && country.dead >= (country.population + country.infected)) { setIcon(new ImageIcon(getClass().getResource("Countries/" + country.name + "Red.png"))); country.isDown = true; }


        }
    });
    ActionListener shopListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> new shopDialog(country));
        }
    };
    CountryButton(Icon icon, Country country) {

        super(icon);
        setBorder(null);
        setContentAreaFilled(false);
        this.country = country;
        timer.start();
        addActionListener(shopListener);


    }


}
