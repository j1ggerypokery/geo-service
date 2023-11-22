package ru.netology.i18n;

import ru.netology.entity.Country;

import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceImpl implements LocalizationService {

    public String locale(Country country) {

        switch (country) {
            case RUSSIA:
                return "Добро пожаловать";
            default:
                return "Welcome";
        }
    }
}
