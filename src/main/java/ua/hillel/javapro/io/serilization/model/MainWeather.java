package ua.hillel.javapro.io.serilization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeather {
    @JsonProperty("temp")
    private double temperature;
    private double humidity;

    public MainWeather() {
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public MainWeather(double humidity) {
        this.humidity = humidity;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainWeather that = (MainWeather) o;
        return Double.compare(that.humidity, humidity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(humidity);
    }

    @Override
    public String toString() {
        return "MainWeather{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
