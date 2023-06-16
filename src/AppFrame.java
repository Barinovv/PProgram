import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppFrame extends JFrame { //отрисовывает все элементы которые мы создали
    private JButton addtask;
    private JButton clear;
    TitleBar title = new TitleBar();
    BtnPanel btnpanel = new BtnPanel();
    List list = new List();
    public AppFrame(){
        this.setSize(400, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(this.title, BorderLayout.NORTH); //отрисовка надписи todolist
        this.add(this.btnpanel, BorderLayout.SOUTH); //add task clear task
        this.add(this.list, BorderLayout.CENTER); // список всех тасков

        addtask = btnpanel.getaddtaskbtn(); //добавление функии
        clear = btnpanel.getclearbtn();
        addlistener(); // метод добавляющий ивенты на кнопки

    }

    public void addlistener(){
        addtask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.indexnum();
                revalidate();

                JButton done = task.getdonej(); // это если таск выполнил
                done.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.donestatus();
                        revalidate();
                    }
                });
                JButton remove = task.getremovej(); // удалние таска
                    remove.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            list.remove(task);
                            list.indexnum();
                            revalidate();
                            repaint();
                        }
                    });
                }

        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component[] tasklist = list.getComponents();
                for (int i = 0 ; i<tasklist.length; i++){
                    if(tasklist[i] instanceof Task){
                        list.remove((Task)tasklist[i]);
                    }
                }
                revalidate();
                repaint();
            }
        });

    }
}
