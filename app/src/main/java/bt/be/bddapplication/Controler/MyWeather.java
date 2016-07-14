package bt.be.bddapplication.Controler;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rome03 on 14/07/2016.
 */
public class MyWeather extends AsyncTask<String, Void, String> {

    public interface IWeather {
        String printValues(String s);
    }

    IWeather callBack;

    public MyWeather(IWeather callback) {
        callBack = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + strings[0] + "," + strings[1] + "&APPID=68e3049bc87bd0a58caf7e50dc4a93a1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream fis = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            fis.close();
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        callBack.printValues(s);
    }
}
