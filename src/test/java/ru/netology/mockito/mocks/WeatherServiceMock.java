package ru.netology.mockito.mocks;

import ru.netology.mockito.external.services.weather.Weather;
import ru.netology.mockito.external.services.weather.WeatherService;

public class WeatherServiceMock implements WeatherService {

    private Weather value;

    @Override
    public Weather currentWeather() {
        return value;
    }

    public void setValue(Weather value) {
        this.value = value;
    }
}
