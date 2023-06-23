import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task extends JPanel {
    private JLabel index;
    private JTextField taskname;
    private JButton done;
    private JButton remove;
    private JLabel weather;



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

        taskname = new JTextField("kapibara");
        taskname.setPreferredSize(new Dimension(10, 20));
        taskname.setBorder(BorderFactory.createEmptyBorder());
        taskname.setBackground(new Color(255, 234, 17));
        this.add(this.taskname);

        done = new JButton("done");
        done.setPreferredSize(new Dimension(10, 20));
        this.add(this.done);

        remove = new JButton("remove");
        remove.setPreferredSize(new Dimension(10, 20));
        this.add(this.remove);

        weather = new JLabel(getWeather());
        weather.setPreferredSize(new Dimension(10, 20));
        weather.setHorizontalAlignment(JLabel.LEFT);
        weather.setBackground(new Color(255, 234, 17));
        this.add(this.weather);
    }

    public String getWeather() {
        String urlAdress =" http://api.openweathermap.org/data/2.5/weather?q=penza&mode=json&units=metric&cnt=7&appid=3ce9ea94136b1a3424e7f503c5355391";
        StringBuffer content = new StringBuffer();
        String msg = "";

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                content.append(line + "\n");
            }
            br.close();}catch (Exception e){System.out.println("kapibara vret!!!");}

        System.out.println();
        if(!content.isEmpty()){
            JSONObject obj = new JSONObject(content.toString());
            msg += "temp: " + obj.getJSONObject("main").getDouble("temp");
        }
        return msg;
    }

    public void writeindexjl(int n) {
        this.index.setText(String.valueOf(n));
        this.revalidate();
    }
    public JTextField gettextfieldj(){ return this.taskname; }
    public JButton getdonej(){
        return this.done;
    }
    public void setTaskname(String taskname) {
        this.taskname.setText(taskname);
    }
    public JButton getremovej(){
        return this.remove;
    }
    public void donestatus(){
        if (this.taskname.getBackground() == Color.green){
            this.taskname.setBackground(new Color(255, 234, 17));
            this.index.setBackground(new Color(255, 234, 17));
            this.remove.setBackground(new Color(255, 234, 17));
            this.weather.setBackground(new Color(255, 234, 17));
            this.setBackground(new Color(255, 234, 17));
        } else {
            this.taskname.setBackground(Color.green);
            this.index.setBackground(Color.green);
            this.remove.setBackground(Color.green);
            this.weather.setBackground(Color.green);
            this.setBackground(Color.green);
        }

        revalidate();
    }
}
