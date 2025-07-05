package io.github.mannam11.internal;

import io.github.mannam11.model.request.PredictionRequest;
import io.github.mannam11.utils.FileUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;

public class RequestBuilder {

    private static final String PREDICTION_BASE_URL = "https://api.replicate.com/v1/models/%s/%s/predictions";
    private static final String IMG_UPLOAD_BASE_URL = "https://api.replicate.com/v1/files";

    public static Request build(PredictionRequest req, String body, String apiKey) {
        String url = String.format(PREDICTION_BASE_URL,
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

    public static Request buildFileUploadRequest(File file, String apiKey) {
        try{
            String mimeType = FileUtil.getMimeType(file);

            MultipartBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                            "content",
                            file.getName(),
                            RequestBody.create(file, MediaType.parse("application/octet-stream"))
                    )
                    .build();

            return new Request.Builder()
                    .url(IMG_UPLOAD_BASE_URL)
                    .post(requestBody)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .build();
        }catch (IOException e) {
            throw new RuntimeException("Building file upload request failed");
        }
    }

}

