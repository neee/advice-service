package ru.netology.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.preference.PreferencesService;
import ru.netology.mockito.external.services.weather.Weather;
import ru.netology.mockito.external.services.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdviceServiceTest {

    @Test
    @DisplayName("should get advice in bad weather")
    void testGetAdviceInBadWeather() {
        WeatherService weatherService = mock(WeatherService.class);
        when(weatherService.currentWeather())
            .thenReturn(Weather.STORMY);

        PreferencesService preferencesService = mock(PreferencesService.class);
        when(preferencesService.get(any()))
            .thenReturn(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS, Preference.READING));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        assertEquals(expected, preferences);
    }
}
