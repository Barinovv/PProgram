import javax.swing.*;
import java.awt.*;

public class List extends JPanel {
    public List(){
        GridLayout Layout = new GridLayout(10, 1); // 10 на 1, строчек на колонку
        Layout.setVgap(5); // межстрочный интервал
        this.setLayout(Layout);

    }
    public void indexnum(){ // массив со всеми тасками, получает и индексирует таски
        Component[] listcomp = this.getComponents();
        for (int i = 0; i < listcomp.length; i++){
            if(listcomp[i] instanceof Task){
                ((Task)listcomp[i]).writeindexjl(i+1);
            }
        }
    }
}
