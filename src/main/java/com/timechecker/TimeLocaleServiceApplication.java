package com.timechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Spring BootStrap Application.
 */
@SpringBootApplication
public class TimeLocaleServiceApplication {

    /**
     * Main method.
     * @param args runtime arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(TimeLocaleServiceApplication.class, args);

    }



}
