package ua.hillel.javapro.io.serilization.service;

import ua.hillel.javapro.io.serilization.exception.WeatherAppException;
import ua.hillel.javapro.io.serilization.model.CurrentWeather;

public interface WeatherService {
    CurrentWeather getCurrentWeather(String city) throws WeatherAppException;
}
