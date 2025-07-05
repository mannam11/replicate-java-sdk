package io.github.mannam11.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadResponse {
    private String id;
    private Urls urls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Urls {
        private String get;

        public String getGet() { return get; }
        public void setGet(String get) { this.get = get; }
    }
}