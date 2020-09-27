package ru.netology.mockito.mocks;

import java.util.Set;

import ru.netology.mockito.external.services.preference.Preference;
import ru.netology.mockito.external.services.preference.PreferencesService;

public class PreferencesServiceMock implements PreferencesService {

    public PreferencesServiceMock() {
        this.value = Set.of(Preference.READING, Preference.FOOTBALL);
    }

    private Set<Preference> value;

    @Override
    public Set<Preference> get(String userId) {
        return value;
    }

    public void setValue(Set<Preference> value) {
        this.value = value;
    }
}
