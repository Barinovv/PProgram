import javax.swing.*;
import java.awt.*;

public class Task extends JPanel {
    private JLabel index;
    private JTextField taskname;
    private JButton done;
    private JButton remove;

    public Task(){
        GridLayout Layouttask = new GridLayout(1, 4);
        Layouttask.setHgap(5);
        this.setPreferredSize(new Dimension(400, 20));
        this.setBackground(new Color(255, 234, 17));
        this.setLayout(Layouttask);

        index = new JLabel("");
        index.setPreferredSize(new Dimension(10, 20));
        index.setHorizontalAlignment(JLabel.LEFT);
        index.setBackground(new Color(255, 234, 17));
        this.add(this.index);

        taskname = new JTextField("Новая задача");
        taskname.setPreferredSize(new Dimension(10, 20));
        taskname.setBorder(BorderFactory.createEmptyBorder());
        taskname.setBackground(new Color(255, 234, 17));
        this.add(this.taskname);

        done = new JButton("Готово");
        done.setPreferredSize(new Dimension(10, 20));
        this.add(this.done);

        remove = new JButton("Удалить");
        remove.setPreferredSize(new Dimension(10, 20));
        this.add(this.remove);


    }

    public void writeindexjl(int n) { // отправляет индекс таска в метод indexnum
        this.index.setText(String.valueOf(n));
        this.revalidate();
    }
    public JButton getdonej(){
        return this.done;
    }
    public JButton getremovej(){
        return this.remove;
    }
    public void donestatus(){
        this.taskname.setBackground(Color.green);
        this.index.setBackground(Color.green);
        this.remove.setBackground(Color.green);
        this.setBackground(Color.green);
        revalidate();
    }
}
