package io.github.mannam11.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;


public class PredictionRequest {

    @JsonIgnore
    private final ModelRequest modelRequest;
    private final Map<String,Object> input;

    private PredictionRequest(Builder builder){
        this.modelRequest=builder.modelRequest;
        this.input=builder.input;
    }

    public ModelRequest getModelRequest() {
        return modelRequest;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public static class Builder{
        private ModelRequest modelRequest;
        private Map<String,Object> input;

        public Builder model(ModelRequest modelRequest){
            this.modelRequest=modelRequest;
            return this;
        }

        public Builder input(Map<String,Object> input){
            this.input=input;
            return this;
        }

        public PredictionRequest build(){
            return new PredictionRequest(this);
        }
    }
}
