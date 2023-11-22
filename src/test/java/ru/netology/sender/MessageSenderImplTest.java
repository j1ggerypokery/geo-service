package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class MessageSenderImplTest {

    @Test
    void getIsRussian() {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        GeoService geoService = Mockito.mock(GeoService.class);

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn((Location) Set.of(GeoServiceImpl.LOCALHOST, GeoServiceImpl.NEW_YORK_IP, GeoServiceImpl.MOSCOW_IP));

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> message = new HashMap<>();

        String expected = GeoServiceImpl.MOSCOW_IP;
        String actual = String.valueOf(messageSenderImpl.send(message));
        Assertions.assertEquals(expected, actual);
    }
}