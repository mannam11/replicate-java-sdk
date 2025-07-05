# Replicate Java SDK

A Java SDK for interacting with the Replicate API, enabling seamless integration of machine learning models into your Java applications.

## Features

- ✅ **Text input ➝ Text output** - Generate text responses from text prompts
- ✅ **Text input ➝ Image output** - Generate images from text descriptions

## Installation

### Gradle
Add the following dependency to your `build.gradle` file:

```gradle
implementation group: 'io.github.mannam11', name: 'replicate-java-sdk', version: '1.0.1'
```

### Maven
Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>io.github.mannam11</groupId>
    <artifactId>replicate-java-sdk</artifactId>
    <version>1.0.1</version>
</dependency>
```


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

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Made with ❤️ for the Java community