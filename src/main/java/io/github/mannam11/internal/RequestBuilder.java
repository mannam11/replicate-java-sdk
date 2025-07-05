package io.github.mannam11.internal;

import io.github.mannam11.model.request.PredictionRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {

    public static Request build(PredictionRequest req, String body, String apiKey, String baseUrl) {
        String url = String.format(baseUrl,
                req.getModelRequest().getOwner(),
                req.getModelRequest().getModel());

        RequestBody requestBody = RequestBody.create(
                body,
                MediaType.parse("application/json")
        );

        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "wait")
                .build();
    }
}

