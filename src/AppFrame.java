import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.io.BufferedWriter;


public class AppFrame extends JFrame {
    private JButton addtask;
    private JButton clear;
    File file = new File("text.txt");

    TitleBar title = new TitleBar();
    BtnPanel btnpanel = new BtnPanel();
    List list = new List();

    public AppFrame(){
        this.setSize(400, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.btnpanel, BorderLayout.SOUTH);
        this.add(this.list, BorderLayout.CENTER);

        addtask = btnpanel.getaddtaskbtn();
        clear = btnpanel.getclearbtn();

        try(BufferedReader br = new BufferedReader (new FileReader(file))) {
            String s;
            while((s=br.readLine())!=null){
                Task task = new Task();
                list.add(task);
                list.indexnum();

                JButton done = task.getdonej();
                done.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.donestatus();
                        revalidate();
                    }
                });
                JButton remove = task.getremovej();
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
            revalidate();
        }catch(IOException ex){System.out.println(ex.getMessage());}
        addlistener();
    }
    public void addlistener(){
        addtask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.indexnum();

                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
                    String msg="";
                    msg += task.gettextfieldj().getText();
                    bf.write(msg + "\n");
                    bf.flush();
                    bf.close();
                } catch (IOException ex) {throw new RuntimeException(ex);}
                revalidate();

                JButton done = task.getdonej();
                done.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.donestatus();
                        revalidate();
                    }
                });
                JButton remove = task.getremovej();
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

                try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                    raf.setLength(0);
                    }catch (IOException ex) {ex.printStackTrace();}

                revalidate();
                repaint();
            }
        });

    }
}
