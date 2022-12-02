package ua.hillel.javapro.io.serilization.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.hillel.javapro.io.serilization.exception.WeatherAppException;
import ua.hillel.javapro.io.serilization.model.CurrentWeather;
import ua.hillel.javapro.io.serilization.model.GeoLocation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");
    private static final String BASE_URL = "https://api.openweathermap.org";
    private static final String DATA_PATH = "/data/2.5/weather";
    private static final String GEO_PATH = "/geo/1.0/direct";
    private final HttpClient httpClient;

    public OpenWeatherMapClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public CurrentWeather getCurrentWeather(double lon, double lat) throws WeatherAppException {
        try {
            String params = String.format("?lat=%f&lon=%f&appid=%s&units=metric", lat, lon, API_KEY);
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(BASE_URL + DATA_PATH + params))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), CurrentWeather.class);

        } catch (URISyntaxException e) {
            throw new WeatherAppException("Current weather: Wrong URI syntax", e);
        } catch (JsonProcessingException e) {
            throw new WeatherAppException("Current weather: Error processing JSON", e);
        } catch (IOException | InterruptedException e) {
            throw new WeatherAppException("Current weather: Error when sending request to OpenWeather", e);
        }
    }

    @Override
    public List<GeoLocation> getLocationFromCity(String city) throws WeatherAppException {
        String params = String.format("?q=%s&limit=1&appid=%s", city, API_KEY);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(BASE_URL + GEO_PATH + params))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (URISyntaxException e) {
            throw new WeatherAppException("Current weather: Wrong URI syntax", e);
        } catch (JsonProcessingException e) {
            throw new WeatherAppException("Current weather: Error processing JSON", e);
        } catch (IOException | InterruptedException e) {
            throw new WeatherAppException("Current weather: Error when sending request to OpenWeather", e);
        }
    }
}
