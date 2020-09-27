package ru.netology.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.weather.Weather;
import ru.netology.mockito.mocks.PreferencesServiceMock;
import ru.netology.mockito.mocks.WeatherServiceMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdviceServiceTest {

    @Test
    @DisplayName("should get advice in bad weather")
    void testGetAdviceInBadWeather() {
        WeatherServiceMock weatherService = new WeatherServiceMock();
        weatherService.setValue(Weather.STORMY);

        PreferencesServiceMock preferencesService = new PreferencesServiceMock();
        preferencesService.setValue(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS, Preference.READING));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        assertEquals(expected, preferences);
    }
}
