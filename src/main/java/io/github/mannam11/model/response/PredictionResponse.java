package io.github.mannam11.model.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResponse {
    private String id;
    private String version;
    private Map<String, Object> input;
    private Object output;
    private String error;
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("completed_at")
    private String completedAt;

    private Urls urls;

    // Getters and setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public Map<String, Object> getInput() { return input; }
    public void setInput(Map<String, Object> input) { this.input = input; }

    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getStartedAt() { return startedAt; }
    public void setStartedAt(String startedAt) { this.startedAt = startedAt; }

    public String getCompletedAt() { return completedAt; }
    public void setCompletedAt(String completedAt) { this.completedAt = completedAt; }

    public Urls getUrls() { return urls; }
    public void setUrls(Urls urls) { this.urls = urls; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Urls {
        private String get;
        private String cancel;

        public String getGet() { return get; }
        public void setGet(String get) { this.get = get; }

        public String getCancel() { return cancel; }
        public void setCancel(String cancel) { this.cancel = cancel; }
    }
}

