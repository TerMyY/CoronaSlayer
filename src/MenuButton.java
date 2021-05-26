import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenuButton extends JButton {

    static final Color CRIMSON = new Color(153,0,0);
    static final Border BORDER = BorderFactory.createMatteBorder(5,5,5,5,CRIMSON);
    static final Font MONOFONT_THIN = new Font(Font.MONOSPACED, Font.BOLD, 33);
    static final Font MONOFONT = new Font(Font.MONOSPACED, Font.BOLD, 99);
    static final Dimension SIZE_MAX = new Dimension(Main.SCREEN_SIZE.width / 3, Main.SCREEN_SIZE.height / 7);

    MenuButton(String text) {

        super(text);
        setFont(MONOFONT);
        setForeground(Main.TEXT_COLOR);
        setFocusable(false);
        setPreferredSize(getPreferredSize());
        setMaximumSize(SIZE_MAX);
        setBorder(BORDER);
        setContentAreaFilled(false);

    }


}
