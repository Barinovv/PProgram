import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class weather {
    public static void main(String[] args) {
        String urlAdress =" http://api.openweathermap.org/data/2.5/weather?q=Пенза&mode=json&units=metric&cnt=7&appid=72c0575116da5cde81871658faeded31";
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = br.readLine()) != null){
                content.append(line + "\n");
            }
            br.close();
        }catch (Exception e){
            System.out.println("Город не был найден");
        }

        System.out.println(content);

        if(!content.isEmpty()){
            JSONObject obj = new JSONObject(content);
            String msg = "Температура: " + obj.getJSONObject("main").getDouble("temp");
            System.out.println(msg);
//            System.out.println("Температура: " + obj.getJSONObject("main").getDouble("temp"));
//            System.out.println("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
//            System.out.println("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
//            System.out.println("Давлениеи: " + obj.getJSONObject("main").getDouble("pressure"));
        }

    }
}