package ua.hillel.javapro.io.serilization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    @JsonProperty("coord")
    private Coordinates coordinates;
    @JsonProperty("weather")
    private List<Weather> weatherList;
    private String name;
    private MainWeather main;

    public CurrentWeather() {
    }

    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather that = (CurrentWeather) o;
        return Objects.equals(coordinates, that.coordinates) && Objects.equals(weatherList, that.weatherList) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, weatherList, name);
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "coordinates=" + coordinates +
                ", weatherList=" + weatherList +
                ", name='" + name + '\'' +
                ", main=" + main +
                '}';
    }
}
