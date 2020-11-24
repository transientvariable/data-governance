package main

import (
    "encoding/json"
    "fmt"
    "time"
)

func logEvent() *LogEvent {
    return &LogEvent {
        Timestamp: time.Now().Format(time.RFC3339),
        Labels: map[string]interface{} {
            "application": "businessApp",
            "component": "component123",
        },
        Message: "Raw text of the original log message would go here if required",
        Cloud: Cloud{
            Region: "us-east-1",
        },
        Event: Event{
            ID:       "b1694bda-d93b-43f6-9f28-d653fbd7ca18",
            Action:   "ChangeProfile",
            Dataset:  "api.call",
            Kind:     "event",
            Category: []string{ "application", "security" },
            Created: time.Now().Format(time.RFC3339),
        },
        HTTP: HTTP{
            Request: Request{
                ID: "00ecf376-2442-4902-a59d-363a5b176e09",
            },
        },
        Log: Log{
            Level: "info",
        },
        Recipient: Recipient{
            Account: Account{
                ID: "12345678A",
            },
        },
        Source: Source{
            IP: "204.63.40.11",
            Domain: "abc.com",
        },
        UserAgent: UserAgent{
            Name: "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)",
        },
    }
}

func toJson(source interface{}) []byte {
    if source != nil {
        if j, err := json.Marshal(&source); err == nil {
            return j
        }
    }
    return []byte(nil)
}

func log(message interface{}) {
    fmt.Printf("\n%s\n", message)
}

func main() {
    log(toJson(logEvent()))
}
