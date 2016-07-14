package bt.be.bddapplication.Utilitaires;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Main {
    private Double temp;
    private Integer humidity;
    private Integer pressure;
    private Double tempMin;
    private Double tempMax;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Main() {
        Log.i("TEST", "je suis la");
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = Double.parseDouble(temp);
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = Integer.parseInt(humidity);
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = Integer.parseInt(pressure);
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = Double.parseDouble(tempMin);
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = Double.parseDouble(tempMax);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    private Double convertF(Double temp) {
        temp = (temp - 32) / 1.8;
        return temp;
    }

    public static Main hydrate(JSONObject jsonObject) { //Permet de creer un oibjet avec les attribut du "main" de mon JSON
        Main obj = new Main();
        try {
            obj.setTemp(jsonObject.getString("temp"));
            obj.setPressure(jsonObject.getString("pressure"));
            obj.setHumidity(jsonObject.getString("humidity"));
            obj.setTempMin(jsonObject.getString("temp_min"));
            obj.setTempMax(jsonObject.getString("temp_min"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public String toString() {
        return "MainWeather" +
                "temp=" + temp;

    }
}
