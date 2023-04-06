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
        String receive = messageSenderMock.send(headers);

        String expect = "Добро пожаловать";

        Assertions.assertEquals(expect, receive);
    }

    @Test
    void test_always_sender_english_if_IP_eng() {
        Location locationMock = Mockito.mock(Location.class);
        Mockito.when(locationMock.getCountry()).thenReturn(Country.USA);

        GeoService geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("96.44.183.149")).thenReturn(locationMock);

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "96.44.183.149");

        MessageSender messageSenderMock = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        String receive = messageSenderMock.send(headers);

        String expect = "Welcome";

        Assertions.assertEquals(expect, receive);
    }

    @Test
    void test_IP_address_is_null() {
        Location locationMock = Mockito.mock(Location.class);
        Mockito.when(locationMock.getCountry()).thenReturn(Country.USA);

        GeoService geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp("")).thenReturn(locationMock);

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "");

        MessageSender messageSenderMock = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        String receive = messageSenderMock.send(headers);

        String expect = "Welcome";

        Assertions.assertEquals(expect, receive);
    }
}