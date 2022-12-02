package ua.hillel.javapro.io.serilization.client;

import ua.hillel.javapro.io.serilization.exception.WeatherAppException;
import ua.hillel.javapro.io.serilization.model.CurrentWeather;
import ua.hillel.javapro.io.serilization.model.GeoLocation;

import java.util.List;

public interface WeatherClient {
    CurrentWeather getCurrentWeather(double lon, double lat) throws WeatherAppException;
    List<GeoLocation> getLocationFromCity(String city) throws WeatherAppException;
}
