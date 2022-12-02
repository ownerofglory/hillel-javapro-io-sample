package ua.hillel.javapro.io.serilization.ui;

import ua.hillel.javapro.io.serilization.exception.WeatherAppException;
import ua.hillel.javapro.io.serilization.model.CurrentWeather;
import ua.hillel.javapro.io.serilization.service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WeatherUi extends JFrame {
    private WeatherService service;

    private JPanel cityPanel = new JPanel();
    private JPanel resultPanel = new JPanel();
    private JLabel cityLabel = new JLabel("Enter city name");
    private JTextField cityInput = new JTextField();
    private JLabel resultIconLabel = new JLabel();
    private JButton searchButton = new JButton("Get weather");

    private JLabel weatherLabel = new JLabel();

    {
        setSize(400, 200);
        setTitle("Hillel Weather App");
        setLayout(new BorderLayout());

        cityPanel.setSize(400, 100);
        cityPanel.setLayout(new GridLayout(2, 2));
        cityPanel.setBackground(Color.DARK_GRAY);
        resultIconLabel.setForeground(Color.WHITE);
        cityLabel.setForeground(Color.WHITE);

        cityPanel.add(cityLabel);
        cityPanel.add(cityInput);
        cityPanel.add(resultIconLabel);
        cityPanel.add(searchButton);


        resultPanel.setSize(400, 100);
        resultPanel.add(weatherLabel);

        add(cityPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public WeatherUi(WeatherService service) throws HeadlessException {
        this.service = service;
        searchButton.addActionListener(e -> {
            try {
                CurrentWeather currentWeather = service.getCurrentWeather(cityInput.getText());
                System.out.println(currentWeather);
                resultIconLabel.setIcon(new ImageIcon(new URL("http://openweathermap.org/img/wn/"+ currentWeather.getWeatherList().get(0).getIcon() +".png")));

                weatherLabel.setText(String.format("temperature: %d°C | humidity: %d%% | %s",
                        (int) Math.round(currentWeather.getMain().getTemperature()),
                        (int) Math.round(currentWeather.getMain().getHumidity()),
                        currentWeather.getWeatherList().get(0).getDescription()));
            } catch (MalformedURLException ex) {
                resultIconLabel.setText("--icon--");
                ex.printStackTrace();
            } catch (WeatherAppException ex) {
                resultIconLabel.setText(ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
