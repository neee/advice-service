package ru.netology.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.preference.PreferencesService;
import ru.netology.mockito.external.services.weather.Weather;
import ru.netology.mockito.external.services.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdviceServiceTest {

    @Test
    @DisplayName("should get advice in bad weather")
    void testGetAdviceInBadWeather() {
        WeatherService weatherService = new WeatherService() {
            @Override
            public Weather currentWeather() {
                return Weather.RAINY;
            }
        };

        PreferencesService preferencesService = new PreferencesService() {
            @Override
            public Set<Preference> get(String userId) {
                return Set.of(Preference.READING, Preference.WATCHING_FILMS);
            }
        };

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        assertEquals(expected, preferences);
    }
}
