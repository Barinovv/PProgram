import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class weather extends Task {
    public static void main(String[] args) throws JSONException {
        String urlAdress = "http://api.openweathermap.org/data/2.5/weather?q=Пенза&mode=json&units=metric&cnt=7&appid=72c0575116da5cde81871658faeded31";
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

        JSONObject obj = new JSONObject(content.toString());
        String msg = "Температура: " + obj.get("main");
        System.out.println(msg);


// System.out.println("Температура: " + obj.getJSONObject("main").getDouble("temp"));
// System.out.println("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
// System.out.println("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
// System.out.println("Давлениеи: " + obj.getJSONObject("main").getDouble("pressure"));
        }

    }



        /**if(!content.isEmpty()){
            JSONArray obj = new JSONArray(content);
            JSONArray arr = obj.getJSONArray("wind").getJSONArray("speed");
            String blurb = "";

            // Loop through the docs array
            for (int i = 0; i < arr.length(); i++)
            {
                blurb += arr.getJSONObject(i).getString("web_url");
            }

            System.out.println(blurb);**/
            //String msg = "Температура: " + obj.getJSONObject("main").getDouble("temp");
            //System.out.println(arr);
//            System.out.println("Температура: " + obj.getJSONObject("main").getDouble("temp"));
//            System.out.println("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
//            System.out.println("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
//            System.out.println("Давлениеи: " + obj.getJSONObject("main").getDouble("pressure"));



