package com.github.transientvariable.logevent;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(NON_EMPTY)
public class LogEvent {

    private Date                timestamp;
    private Map<String, Object> labels;
    private String              message;

    private Cloud               cloud;
    private Event               event;
    private Http                http;
    private Log                 log;
    private Recipient           recipient;
    private Source              source;
    private Trace               trace;

    @JsonProperty("user_agent")
    private UserAgent           userAgent;

    @Data
    @Builder
    public static class Cloud {
        private String region;
    }

    @Data
    @Builder
    public static class Event {
        private String       id;
        private String       action;
        private String       dataset;
        private String       kind;
        private List<String> category;
        private Date         created;
    }

    @Data
    @Builder
    public static class Http {
        private Request request;
    }

    @Data
    @Builder
    public static class Request {
        private String id;
    }

    @Data
    @Builder
    public static class Log {
        private String level;
    }

    @Data
    @Builder
    public static class Recipient {
        private Account account;
    }

    @Data
    @Builder
    public static class Account {
        private String id;
    }

    @Data
    @Builder
    public static class Source {
        private String ip;
        private String domain;
    }

    @Data
    @Builder
    public static class Trace {
        private String      id;
        private Transaction transaction;
    }

    @Data
    @Builder
    public static class Transaction {
        private String id;
    }

    @Data
    @Builder
    public static class UserAgent {
        private String name;
    }

    public static enum Kind {
        ALERT("alert"),
        EVENT("event"),
        METRIC("metric"),
        PIPELINE_ERROR("pipeline_error"),
        SIGNAL("signal"),
        STATE("state");

        private String value;

        Kind(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum Category {
        AUTHENTICATION("authentication"),
        APPLICATION("application"),
        DATABASE("database"),
        DRIVER("driver"),
        FILE("file"),
        HOST("host"),
        IAM("iam"),
        INSTRUSION_DETECTION("intrusion_detection"),
        MALWARE("malware"),
        NETWORK("network"),
        PACKAGE("package"),
        PROCESS("process"),
        SECURITY("security"),
        WEB("web");

        private String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum LogLevel {
        INFO("info"),
        DEBUG("debug"),
        ERROR("error"),
        TRACE("trace"),
        WARN("warn");

        private String value;

        LogLevel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
