package ua.hillel.javapro.io.serilization.service;

import ua.hillel.javapro.io.serilization.client.WeatherClient;
import ua.hillel.javapro.io.serilization.exception.WeatherAppException;
import ua.hillel.javapro.io.serilization.model.CurrentWeather;
import ua.hillel.javapro.io.serilization.model.GeoLocation;

import java.util.List;

public class WeatherServiceDefault implements WeatherService{

    private WeatherClient client;

    public WeatherServiceDefault(WeatherClient client) {
        this.client = client;
    }

    @Override
    public CurrentWeather getCurrentWeather(String city) throws WeatherAppException {
        List<GeoLocation> locationFromCity = client.getLocationFromCity(city);
        return client.getCurrentWeather(locationFromCity.get(0).getLon(), locationFromCity.get(0).getLat());
    }
}
