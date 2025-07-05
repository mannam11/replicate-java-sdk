package io.github.mannam11.model.request;

public class ModelRequest {
    private final String owner;
    private final String model;

    private ModelRequest(Builder builder){
        this.owner= builder.owner;
        this.model= builder.model;
    }

    public String getOwner() {
        return owner;
    }

    public String getModel() {
        return model;
    }

    public static class Builder{
        private String owner;
        private String model;

        public Builder owner(String owner){
            this.owner=owner;
            return this;
        }

        public Builder model(String model){
            this.model=model;
            return this;
        }

        public ModelRequest build(){
            return new ModelRequest(this);
        }
    }
}
