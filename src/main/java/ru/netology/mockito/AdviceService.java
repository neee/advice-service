package ru.netology.mockito;

import java.util.Set;
import java.util.stream.Collectors;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.preference.PreferencesService;
import ru.netology.mockito.external.services.weather.Weather;
import ru.netology.mockito.external.services.weather.WeatherService;

public class AdviceService {

    private final PreferencesService preferencesService;
    private final WeatherService weatherService;

    public AdviceService(PreferencesService preferencesService, WeatherService weatherService) {
        this.preferencesService = preferencesService;
        this.weatherService = weatherService;
    }

    public Set<Preference> getAdvice(String userId) {
        Weather weather = weatherService.currentWeather();
        Set<Preference> preferences = preferencesService.get(userId);
        if (Weather.RAINY == weather || Weather.STORMY == weather) {
            return preferences.stream()
                .filter(p -> p != Preference.FOOTBALL)
                .collect(Collectors.toSet());
        } else if (Weather.SUNNY == weather) {
            return preferences.stream()
                .filter(p -> p != Preference.READING)
                .collect(Collectors.toSet());
        }
        return preferences;
    }
}
