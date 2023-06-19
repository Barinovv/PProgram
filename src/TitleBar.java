import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {

    JLabel TitleText = new JLabel("ToDoList");
    public TitleBar(){

        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(new Color(90, 143,123));

        TitleText.setPreferredSize(new Dimension(200, 80));
        TitleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        TitleText.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.TitleText);
    }

}
