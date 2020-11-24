package main

type LogEvent struct {
    Timestamp string                 `json:"timestamp"`
    Labels    map[string]interface{} `json:"labels"`
    Message   string                 `json:"message"`
    Cloud     Cloud                  `json:"cloud"`
    Event     Event                  `json:"event"`
    HTTP      HTTP                   `json:"http"`
    Log       Log                    `json:"log"`
    Recipient Recipient              `json:"recipient"`
    Source    Source                 `json:"source"`
    UserAgent UserAgent              `json:"user_agent"`
}

type Cloud struct {
    Region string `json:"region"`
}

type Event struct {
    ID       string   `json:"id"`
    Action   string   `json:"action"`
    Dataset  string   `json:"dataset"`
    Kind     string   `json:"kind"`
    Category []string `json:"category"`
    Created  string   `json:"created"`
}

type Request struct {
    ID string `json:"id"`
}

type HTTP struct {
    Request Request `json:"request"`
}

type Log struct {
    Level string `json:"level"`
}

type Account struct {
    ID string `json:"id"`
}

type Recipient struct {
    Account Account `json:"account"`
}

type Source struct {
    IP     string `json:"ip"`
    Domain string `json:"domain"`
}

type UserAgent struct {
    Name string `json:"name"`
}
