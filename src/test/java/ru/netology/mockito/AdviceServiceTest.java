package ru.netology.mockito;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Set;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.preference.PreferencesService;
import ru.netology.mockito.external.services.weather.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdviceServiceTest {

    @Test
    @DisplayName("should get advice in bad weather")
    void testGetAdviceInBadWeather() {
        WeatherService weatherService = mock(WeatherService.class);
        when(weatherService.currentWeather()).thenReturn(Weather.STORMY);

        PreferencesService preferencesService = mock(PreferencesService.class);
        when(preferencesService.get(any())).thenReturn(Set.of(Preference.FOOTBALL));
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        adviceService.getAdvice("user1");

        verify(preferencesService).get(argumentCaptor.capture());
        assertEquals("user1", argumentCaptor.getValue());
    }

    @Test
    @DisplayName("should call spy weather service")
    void testSpyWeatherService() {
        WeatherService weatherService = spy(WeatherServiceImpl.class);
        assertEquals(Weather.SUNNY, weatherService.currentWeather());
    }
}
