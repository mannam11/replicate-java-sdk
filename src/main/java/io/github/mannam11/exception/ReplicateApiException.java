package io.github.mannam11.exception;

public class ReplicateApiException extends RuntimeException {
    private final int statusCode;
    private final String title;
    private final String detail;

    public ReplicateApiException(int statusCode, String title, String detail) {
        super("Replicate API Error (" + statusCode + "): " + title + " - " + detail);
        this.statusCode = statusCode;
        this.title = title;
        this.detail = detail;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}

