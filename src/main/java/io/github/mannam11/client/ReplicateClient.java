package io.github.mannam11.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mannam11.exception.ReplicateException;
import io.github.mannam11.internal.RequestBuilder;
import io.github.mannam11.internal.ResponseHandler;
import io.github.mannam11.internal.Validator;
import io.github.mannam11.model.request.PredictionRequest;
import io.github.mannam11.model.response.FileUploadResponse;
import io.github.mannam11.model.response.PredictionResponse;
import io.github.mannam11.utils.UrlValidationUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReplicateClient {
    private final String apiKey;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

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

        if (requiresFileUpload(predictionRequest)) {
            uploadInputImage(predictionRequest);
        }

        return sendPredictionRequest(predictionRequest);
    }

    private boolean requiresFileUpload(PredictionRequest request) {
        Map<String, Object> input = request.getInput();

        if (!input.containsKey("input_image")) {
            return false;
        }

        return !checkForInputImage(input);
    }

    private void uploadInputImage(PredictionRequest request) {
        Object imageValue = request.getInput().get("input_image");

        if (imageValue == null) {
            throw new ReplicateException("Missing input_image value for upload.");
        }

        File file = new File(imageValue.toString());

        Request uploadRequest = RequestBuilder.buildFileUploadRequest(file, apiKey);
        Call call = okHttpClient.newCall(uploadRequest);

        try (Response response = call.execute()) {
            FileUploadResponse uploadResponse = ResponseHandler.handleResponse(response, FileUploadResponse.class);
            request.getInput().put("input_image", uploadResponse.getUrls().getGet());
        } catch (IOException e) {
            throw new ReplicateException("Failed to upload input image: " + e.getMessage(), e);
        }
    }

    private PredictionResponse sendPredictionRequest(PredictionRequest request) {
        try {
            String requestBody = objectMapper.writeValueAsString(request);
            Request predictionHttpRequest = RequestBuilder.build(request, requestBody, apiKey);

            Call call = okHttpClient.newCall(predictionHttpRequest);
            try (Response response = call.execute()) {
                return ResponseHandler.handleResponse(response, PredictionResponse.class);
            }
        } catch (IOException e) {
            throw new ReplicateException("Prediction request failed: " + e.getMessage(), e);
        }
    }

    private boolean checkForInputImage(Map<String,Object> input){

        boolean isValid = UrlValidationUtil.isValidUrl(input.get("input_image").toString());

        return input.containsKey("input_image") && isValid;
    }

}


