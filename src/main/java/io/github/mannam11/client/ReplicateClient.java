package io.github.mannam11.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mannam11.internal.RequestBuilder;
import io.github.mannam11.internal.ResponseHandler;
import io.github.mannam11.internal.Validator;
import io.github.mannam11.exception.ReplicateException;
import io.github.mannam11.model.request.PredictionRequest;
import io.github.mannam11.model.response.PredictionResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReplicateClient {
    private final String apiKey;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private static final String BASE_URL = "https://api.replicate.com/v1/models/%s/%s/predictions";

    private ReplicateClient(Builder builder){
        if(builder.apiKey == null || builder.apiKey.trim().isEmpty()){
            throw new IllegalArgumentException("API key must not be null or empty");
        }
        this.apiKey= builder.apiKey;
        this.okHttpClient = builder.okHttpClient != null
                ? builder.okHttpClient
                : new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public static class Builder{
        private String apiKey;
        private OkHttpClient okHttpClient;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public ReplicateClient build(){
            return new ReplicateClient(this);
        }
    }

    public PredictionResponse predict(PredictionRequest predictionRequest) {

        Validator.validatePredictionRequest(predictionRequest);

        try {
            String requestBody = objectMapper.writeValueAsString(predictionRequest);
            Request httpRequest = RequestBuilder.build(predictionRequest, requestBody, apiKey, BASE_URL);

            System.out.println("httpRequest : " + httpRequest.toString());

            Call call = okHttpClient.newCall(httpRequest);
            try (Response response = call.execute()) {
                return ResponseHandler.handle(response);
            }
        } catch (IOException e) {
            throw new ReplicateException("Network error occurred: " + e.getMessage(), e);
        }
    }

}


