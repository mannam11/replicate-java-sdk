package io.github.mannam11.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mannam11.exception.ReplicateApiException;
import io.github.mannam11.exception.ReplicateException;
import io.github.mannam11.model.response.PredictionResponse;
import okhttp3.Response;

import java.io.IOException;

public class ResponseHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PredictionResponse handle(Response response) throws IOException {
        String responseBody = response.body() != null ? response.body().string() : "";
        if (!response.isSuccessful()) {
            handleError(response.code(), responseBody);
        }

        try {
            return objectMapper.readValue(responseBody, PredictionResponse.class);
        } catch (JsonProcessingException e) {
            throw new ReplicateException("Failed to parse API response", e);
        }
    }

    private static void handleError(int code, String responseBody) {
        try {
            JsonNode errorJson = objectMapper.readTree(responseBody);
            String title = errorJson.path("title").asText("Unknown error");
            String detail = errorJson.path("detail").asText("No details provided");
            throw new ReplicateApiException(code, title, detail);
        } catch (JsonProcessingException e) {
            throw new ReplicateApiException(code, "API Error", responseBody);
        }
    }
}

