package ru.netology.mockito.external.services.weather;

public class WeatherServiceImpl implements WeatherService {

    @Override
    public Weather currentWeather() {
        return Weather.SUNNY;
    }
}
