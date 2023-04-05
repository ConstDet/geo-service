package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    @Test
    void test_always_sender_russian_if_IP_rus() {
        Location locationMock = Mockito.mock(Location.class);
        Mockito.when(locationMock.getCountry()).thenReturn(Country.RUSSIA);

        GeoService geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("172.123.12.19")).thenReturn(locationMock);

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "172.123.12.19");

        MessageSender messageSenderMock = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        String preference = messageSenderMock.send(headers);

        String expect = "Добро пожаловать";

        Assertions.assertEquals(expect, preference);
    }
}