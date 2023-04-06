package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    @Test
    void byIpMos() {
        final String MOSCOW_IP = "172.0.32.11";

        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp(MOSCOW_IP);

        Location locationE = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        Assertions.assertEquals(locationE.getCountry(), locationR.getCountry());
    }

    @Test
    void byIpLoc() {
        final String LOCALHOST = "127.0.0.1";

        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp(LOCALHOST);

        Location locationE = new Location(null, null, null, 0);

        Assertions.assertEquals(locationE.getCountry(), locationR.getCountry());
    }

    @Test
    void byIpNew() {
        final String NEW_YORK_IP = "96.44.183.149";

        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp(NEW_YORK_IP);

        Location locationE = new Location("New York", Country.USA, " 10th Avenue", 32);

        Assertions.assertEquals(locationE.getCountry(), locationR.getCountry());
    }

    @Test
    void byIp172() {
        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp("172.0.0.0");

        Location locationE = new Location("Moscow", Country.RUSSIA, null, 0);

        Assertions.assertEquals(locationE.getCountry(), locationR.getCountry());
    }

    @Test
    void byIp96() {
        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp("96.0.0.0");

        Location locationE = new Location("New York", Country.USA, null,  0);

        Assertions.assertEquals(locationE.getCountry(), locationR.getCountry());
    }

    @Test
    void byIpNull() {
        Location locationR;

        GeoService geoService = new GeoServiceImpl();

        locationR = geoService.byIp("0.0.0.0");

        Assertions.assertNull(locationR);
    }
}