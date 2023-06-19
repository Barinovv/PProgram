import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BtnPanel extends JPanel {

    private JButton addtask;
    private JButton clear;

    Border emptyborder = BorderFactory.createEmptyBorder();

    public  BtnPanel(){
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(new Color(129, 202, 207));

        addtask = new JButton("add task");
        addtask.setBorder(emptyborder);
        addtask.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        this.add(addtask);

        this.add(Box.createHorizontalStrut(20));

        clear = new JButton("clear all task");
        clear.setBorder(emptyborder);
        addtask.setFont(new Font("Sans-serif", Font.PLAIN, 14));

        this.add(clear);
    }
    public JButton getaddtaskbtn(){
        return addtask;
    }
    public JButton getclearbtn(){
        return clear;
    }

}
