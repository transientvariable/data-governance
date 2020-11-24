package com.github.transientvariable.logevent;

import static java.lang.System.out;
import static com.github.transientvariable.logevent.LogEvent.Category.APPLICATION;
import static ccom.github.transientvariable.logevent.LogEvent.Category.SECURITY;
import static com.github.transientvariable.logevent.LogEvent.Kind.EVENT;
import static com.github.transientvariable.logevent.LogEvent.LogLevel.INFO;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.github.transientvariable.logevent.LogEvent.Account;
import com.github.transientvariable.logevent.LogEvent.Cloud;
import com.github.transientvariable.logevent.LogEvent.Event;
import com.github.transientvariable.logevent.LogEvent.Http;
import com.github.transientvariable.logevent.LogEvent.Log;
import com.github.transientvariable.logevent.LogEvent.Recipient;
import com.github.transientvariable.logevent.LogEvent.Request;
import com.github.transientvariable.logevent.LogEvent.Source;
import com.github.transientvariable.logevent.LogEvent.UserAgent;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper()
            .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(FAIL_ON_EMPTY_BEANS, false)
            .configure(WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private static LogEvent logEvent() {
        return LogEvent.builder()
                .timestamp(new Date())
                .labels(Map.of(
                        "application", "businessApp",
                        "component", "component123"))
                .message("Raw text of the original log message would go here if required")
                .cloud(Cloud.builder()
                        .region("us-east-1")
                        .build())
                .event(Event.builder()
                        .id("b1694bda-d93b-43f6-9f28-d653fbd7ca18")
                        .action("ChangeProfile")
                        .dataset("api.call")
                        .kind(EVENT.getValue())
                        .category(List.of(APPLICATION.getValue(), SECURITY.getValue()))
                        .created(new Date())
                        .build())
                .http(Http.builder()
                        .request(Request.builder()
                                .id("00ecf376-2442-4902-a59d-363a5b176e09")
                                .build())
                        .build())
                .log(Log.builder()
                        .level(INFO.getValue())
                        .build())
                .recipient(Recipient.builder()
                        .account(Account.builder()
                                .id("123456789")
                                .build())
                        .build())
                .source(Source.builder()
                        .domain("abc.com")
                        .ip("204.63.40.11")
                        .build())
                .userAgent(UserAgent.builder()
                        .name("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)")
                        .build())
                .build();
    }

    private static <I> Function<I, String> toJson() {
        return object -> {
            try {
                return OBJECT_MAPPER.writeValueAsString(object);
            }
            catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        };
    }

    private static Function<String, Void> log() {
        return message -> { out.println(message); return null; };
    }

    public static void main(String... args) throws Exception {
        toJson()
            .andThen(log())
            .apply(logEvent());
    }
}
