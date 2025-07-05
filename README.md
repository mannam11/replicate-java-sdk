# Replicate Java SDK

A Java SDK for interacting with the Replicate API, enabling seamless integration of machine learning models into your Java applications.

## Features

- ✅ **Text input ➝ Text output** - Generate text responses from text prompts
- ✅ **Text input ➝ Image output** - Generate images from text descriptions
- Simple and intuitive API design
- Fluent builder pattern for easy configuration
- Type-safe request/response handling

## Quick Start

### 1. Get your API Key
First, sign up at [Replicate](https://replicate.com) and get your API key from your account dashboard.

### 2. Initialize the Client
```java
ReplicateClient client = new ReplicateClient.Builder()
    .apiKey("YOUR_API_KEY_HERE")
    .build();
```

### 3. Make Your First Prediction
```java
import java.util.Map;

// Create input parameters
Map<String, Object> input = Map.of(
    "prompt", "A highly detailed, realistic Tyrannosaurus rex standing in a lush, prehistoric Cretaceous forest at sunset, with dramatic lighting highlighting its textured scales and powerful build. The background features dense ferns and towering ancient trees, with mist rising from the forest floor, creating a cinematic and lifelike atmosphere."
);

// Configure the model
ModelRequest model = new ModelRequest.Builder()
    .owner("google")
    .model("imagen-4")
    .build();

// Create prediction request
PredictionRequest request = new PredictionRequest.Builder()
    .input(input)
    .model(model)
    .build();

// Execute prediction
PredictionResponse response = client.predict(request);
```

## Usage Examples

### Text Generation
```java
// Example with a text-to-text model
Map<String, Object> input = Map.of(
    "prompt", "Write a short story about a robot learning to paint"
);

ModelRequest model = new ModelRequest.Builder()
    .owner("meta")
    .model("llama-2-70b-chat")
    .build();

PredictionRequest request = new PredictionRequest.Builder()
    .input(input)
    .model(model)
    .build();

PredictionResponse response = client.predict(request);
```

### Image Generation
```java
// Example with image generation
Map<String, Object> input = Map.of(
    "prompt", "A serene mountain landscape at dawn with reflection in a crystal clear lake"
);

ModelRequest model = new ModelRequest.Builder()
    .owner("stability-ai")
    .model("stable-diffusion-xl")
    .build();

PredictionRequest request = new PredictionRequest.Builder()
    .input(input)
    .model(model)
    .build();

PredictionResponse response = client.predict(request);
```

## Requirements

- Java 8 or higher
- Internet connection for API calls
- Valid Replicate API key

## Contributing

We welcome contributions! Please feel free to submit issues and pull requests.

### Development Setup

1. Clone the repository:
```bash
git clone https://github.com/mannam11/replicate-java-sdk.git
cd replicate-java-sdk
```

2. Build the project:
```bash
./mvnw clean compile
```

3. Create a Main class to test the SDK, Add the following code to Main.java:
```java

public class Main {
    public static void main(String[] args) {
        // Initialize the client
        ReplicateClient client = new ReplicateClient.Builder()
                .apiKey("YOUR_API_KEY_HERE") // Replace with your actual API key
                .build();
        
        // Example 1: Image Generation
        Map<String, Object> input = Map.of(
                "prompt", "A highly detailed, realistic Tyrannosaurus rex standing in a lush, prehistoric Cretaceous forest at sunset, with dramatic lighting highlighting its textured scales and powerful build. The background features dense ferns and towering ancient trees, with mist rising from the forest floor, creating a cinematic and lifelike atmosphere."
        );
        
        ModelRequest model = new ModelRequest.Builder()
                .owner("google")
                .model("imagen-4")
                .build();
                
        PredictionRequest request = new PredictionRequest.Builder()
                .input(input)
                .model(model)
                .build();

        PredictionResponse response = client.predict(request);
    }
}
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Made with ❤️ for the Java community