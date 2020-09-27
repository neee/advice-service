package ru.netology.mockito.external.services.weather;

public enum Weather {
    RAINY("Дождливо"),
    STORMY("Сильный ветер"),
    SUNNY("Солнечно"),
    CLOUDY("Облачно");

    private String weather;

    Weather(String weather) {
        this.weather = weather;
    }
}
