import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.btnpanel, BorderLayout.SOUTH);
        this.add(this.list, BorderLayout.CENTER);



        addtask = btnpanel.getaddtaskbtn();
        clear = btnpanel.getclearbtn();

        addlistener();
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));

                    Component[] tlist = list.getComponents();
                    for (int i = 0 ; i<tlist.length; i++) {
                        if (tlist[i] instanceof Task) {
                            String msg = "";
                            msg += ((Task) tlist[i]).gettextfieldj().getText() + " ";
                            if (((Task) tlist[i]).gettextfieldj().getBackground() == Color.green) {
                                msg += "1";
                            } else {
                                msg += "0";
                            }
                            bf.write(msg + "\n");
                            System.out.println(msg);
                        }
                    }
                    bf.flush();
                    bf.close();
                } catch (IOException ex) {throw new RuntimeException(ex);}
                Object[] options = {"да", "нет"};
                int n = JOptionPane
                        .showOptionDialog(e.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);

                if (n == 0) {
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
            public void windowOpened(WindowEvent e) {
                try(BufferedReader br = new BufferedReader (new FileReader(file))) {
                    String s;
                    while((s=br.readLine())!=null){
                        String taskname="";
                        int status = 0;
                        String[] subStr;
                        subStr = s.split(" ");
                        taskname = subStr[0];
                        status = Integer.parseInt(subStr[1]);
                        Task task = new Task();
                        task.setTaskname(taskname);
                        if (status == 1){
                            task.donestatus();
                        }
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

                        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                            raf.setLength(0);
                        }catch (IOException ex) {ex.printStackTrace();}


                    }
                    revalidate();
                }catch(IOException ex){System.out.println(ex.getMessage());}
            }
        });
    }
    public void addlistener(){
        addtask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.indexnum();
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
                        System.out.println();
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
