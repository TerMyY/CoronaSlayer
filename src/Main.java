import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.SwingUtilities.*;

public class Main {

    static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static final Dimension MENU_SIZE = new Dimension(MenuButton.SIZE_MAX.width + 115, ( MenuButton.SIZE_MAX.height * 3) + 155 );
    static final Color BACKGROUND_COLOR = new Color(33,33,33);
    static final Color TEXT_COLOR = new Color(205,200,200);


    public static void main(String[] args) {

        invokeLater(Menu::new);

    }

    static void setIcons(JFrame frame) {

        final ImageIcon icon_x16 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x16.png"));
        final ImageIcon icon_x20 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x20.png"));
        final ImageIcon icon_x32 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x32.png"));
        final ImageIcon icon_x40 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x40.png"));
        final ImageIcon icon_x64 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x64.png"));
        final ImageIcon icon_x256 = new ImageIcon( Main.class.getResource("Icons/CoronaSlayer_x256.png"));
        final List<Image> icons = new ArrayList<Image>();
        icons.add(icon_x16.getImage());
        icons.add(icon_x20.getImage());
        icons.add(icon_x32.getImage());
        icons.add(icon_x40.getImage());
        icons.add(icon_x64.getImage());
        icons.add(icon_x256.getImage());
        frame.setIconImages(icons);

    }

    static void confirmExit() {

        SwingUtilities.invokeLater(ExitDialog::new);

    }

}
