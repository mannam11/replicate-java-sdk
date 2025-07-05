package io.github.mannam11.internal;

import io.github.mannam11.model.request.PredictionRequest;

public class Validator {

    public static void validatePredictionRequest(PredictionRequest req) {

        if (req == null) throw new IllegalArgumentException("PredictionRequest cannot be null");
        if (req.getModelRequest() == null) throw new IllegalArgumentException("ModelRequest cannot be null");

        if (req.getModelRequest().getModel() == null || req.getModelRequest().getModel().trim().isEmpty()){
            throw new IllegalArgumentException("ModelRequest MODEL cannot be null");
        }

        if (req.getModelRequest().getOwner() == null || req.getModelRequest().getOwner().trim().isEmpty()){
            throw new IllegalArgumentException("ModelRequest OWNER cannot be null");
        }
    }
}

