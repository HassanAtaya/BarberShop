package com.barbershop.util;

import java.util.Locale;

public class LanguageUtil {
    public static Locale getLocale(String languageCode) {
        return new Locale(languageCode);
    }
}