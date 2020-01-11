package com.timechecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <code>LocaleDateTimeController</code> Class that displays local date time based on passed locale or region code.
 */
@RestController
@RequestMapping("/display")
public class LocaleDateTimeController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleDateTimeController.class);

    /**
     * Method that prints the current date.
     *
     * @return current time.
     */
    @GetMapping(value = "/currentTime")
    public String getCurrentTime() {
        final Date currentDate = new Date();
        return currentDate.toString();
    }


    /**
     * Method that gets the locale and prints in GMT time or UTC.
     * For Eg.,
     * http://localhost:8080/display/getLocaleTime?locale=en_SG -> Saturday, 11 January, 2020 2:05:48 PM SGT
     * http://localhost:8080/display/getLocaleTime?locale=en_JP -> Saturday, January 11, 2020 3:06:10 PM JST
     * http://localhost:8080/display/getLocaleTime?locale=ja_jp -> 2020年1月11日 15時06分36秒 JST
     *
     * @param locale locale
     * @return returns the locale time.
     */
    @GetMapping(value = "/getLocaleTime")
    public String getLocaleTime(@RequestParam(value = "locale") String locale) {
        final Locale currentLocale = StringUtils.parseLocaleString(locale);
        final DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(currentLocale).withZone(TimeZone.getTimeZone(currentLocale.getDisplayCountry()).toZoneId());
        return dateTimeFormatter.format(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()));

    }

    /**
     * Gets the locale specific region time passed in the url.
     *
     * For Eg.,
     * http://localhost:8080/display/getLocaleRegionTime?locale=en_US&zoneId=America/Chicago -> Saturday, January 11, 2020 12:09:43 AM CST
     * http://localhost:8080/display/getLocaleRegionTime?locale=en_US&zoneId=America/Detroit -> Saturday, January 11, 2020 1:10:45 AM EST
     * @param locale locale
     * @param zoneId region id.
     * @return region specific locale time.
     */
    @GetMapping(value = "/getLocaleRegionTime")
    public String getLocaleRegionTime(@RequestParam(value = "locale") String locale,
                                @RequestParam(value = "zoneId") String zoneId) {
        final Locale currentLocale = StringUtils.parseLocaleString(locale);
        final DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(currentLocale).withZone(TimeZone.getTimeZone(zoneId).toZoneId());
        return dateTimeFormatter.format(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of(zoneId)));

    }
}
