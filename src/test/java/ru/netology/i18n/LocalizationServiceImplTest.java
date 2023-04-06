package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void localeRus() {
        Country country = Country.RUSSIA;

        LocalizationService localizationService = new LocalizationServiceImpl();
        String receive = localizationService.locale(country);

        String expect = "Добро пожаловать";

        Assertions.assertEquals(expect, receive);
    }

    @Test
    void localeDef() {
        Country country = Country.BRAZIL;

        LocalizationService localizationService = new LocalizationServiceImpl();
        String receive = localizationService.locale(country);

        String expect = "Welcome";

        Assertions.assertEquals(expect, receive);
    }
}