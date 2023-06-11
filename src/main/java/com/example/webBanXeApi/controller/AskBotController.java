package com.example.webBanXeApi.controller;

import com.example.webBanXeApi.models.AskMessage;
import com.example.webBanXeApi.models.MessageResponse;
import com.example.webBanXeApi.models.ResponseObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@RestController
public class AskBotController {

    @PostMapping("/api/v1/ask/openai")
    public Map<String, String> processQuestion(@RequestBody AskMessage ask) {        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        String promptParameters = "Give me a brief and clear answer in 50 words or less: ";

        String finalPrompt = promptParameters + ask.getContext() + " " + ask.getQuestion();

        OpenAiService service = new OpenAiService("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");


        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), finalPrompt);
        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .stop(Collections.singletonList("\n"))
                .maxTokens(100)
                .logitBias(new HashMap<>())
                .build();


        StringBuilder concatenatedContent = new StringBuilder();

        service.streamChatCompletion(chatCompletionRequest)
                .doOnError(Throwable::printStackTrace)
                .blockingForEach(chunk -> {
                    ChatMessage message = chunk.getChoices().get(0).getMessage();
                    if (message.getContent() != null) {
                        concatenatedContent.append(message.getContent());
                    }
                });

        String finalContent = concatenatedContent.toString();

        System.out.println(finalContent);
        service.shutdownExecutor();

        HashMap<String, String> response = new HashMap<>();
        response.put("question", finalPrompt);
        response.put("answer", finalContent);
        return response;
    }
    @PostMapping("/api/v1/ask/gpt4all")
    public Map<String, String> askGPTJ(@RequestBody AskMessage ask) throws JsonProcessingException {
        // make a POST request and return response

        String url = "http://localhost:5000/askgptj"; // API endpoint URL
        String requestBody = "{\"question\":\"" + ask.getQuestion() + "\", \"context\":\"" + ask.getContext() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String answer = "";
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Deserialize the response body
            ObjectMapper objectMapper = new ObjectMapper();
            MessageResponse apiResponse = objectMapper.readValue(responseEntity.getBody(), MessageResponse.class);
            answer = apiResponse.getMessage();

        } else {
            // Handle the error case
            System.err.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("question", ask.getQuestion());
        response.put("answer", answer);

        return response;
    }
    @PostMapping("/api/v1/ask/sample")
    public Map<String, String> asksample(@RequestBody AskMessage ask) throws JsonProcessingException {
        // make a POST request and return response

        HashMap<String, String> response = new HashMap<>();
        response.put("question", ask.getQuestion());

        if (Objects.equals(ask.getQuestion(), "Tôi có nên mua chiếc maybach này hay là chiếc Lexus RX350. Tôi đang xem chiếc Mercedes-Maybach GLS600")) {
            String answer="Cả Mercedes-Maybach GLS600 và Lexus RX350 đều là những dòng xe hạng sang đáng chú ý, nhưng chúng có những ưu điểm và đặc điểm riêng. Quyết định cuối cùng về việc mua xe phụ thuộc vào nhu cầu và sở thích cá nhân của bạn. Khi đưa ra quyết định, bạn nên xem xét các yếu tố sau:\n" +
                    "\n" +
                    "    Ngân sách: Mercedes-Maybach GLS600 là một chiếc xe hạng sang cao cấp, vì vậy giá thành của nó có thể cao hơn so với Lexus RX350.\n" +
                    "    Kích thước: Mercedes-Maybach GLS600 là một chiếc SUV hạng sang lớn hơn so với Lexus RX350. Bạn cần xem xét nhu cầu về không gian và kích thước xe để đáp ứng được mục đích sử dụng của mình.\n" +
                    "    Phong cách và sở thích: Xem xét thiết kế và phong cách của cả hai chiếc xe và chọn một chiếc phù hợp với sở thích cá nhân của bạn.\n" +
                    "\n" +
                    "Ngoài ra, hãy tham khảo ý kiến của các chuyên gia xe hơi và đặt một cuộc lái thử cả hai xe để có trải nghiệm thực tế trước khi đưa ra quyết định.";
            response.put("answer", answer);
        } else if (Objects.equals(ask.getQuestion(),"Chiếc xe này có quá lỗi mốt không? Tôi đang xem chiếc Ford Ranger.")) {
            String answer = "Ford Ranger là một chiếc xe bán tải phổ biến và có danh tiếng tốt trên thị trường. Tuy nhiên, không có chiếc xe nào là hoàn hảo và có thể có một số lỗi hoặc vấn đề nhất định. Dưới đây là một số thông tin để bạn tham khảo về Ford Ranger:\n" +
                    "\n" +
                    "Ưu điểm của Ford Ranger:\n" +
                    "\n" +
                    "    Độ bền và khả năng vận hành: Ford Ranger được xây dựng với sự chắc chắn và độ bền cao, phù hợp cho việc vận chuyển hàng hóa và chạy trên các địa hình khó khăn.\n" +
                    "    Hiệu suất lái và động cơ mạnh mẽ: Ford Ranger có sẵn nhiều tùy chọn động cơ, bao gồm các động cơ diesel và xăng, mang lại hiệu suất lái tốt và khả năng kéo mạnh mẽ.\n" +
                    "    Công nghệ và tính năng tiện ích: Phiên bản mới của Ford Ranger được trang bị các tính năng hiện đại như hệ thống thông tin giải trí, hỗ trợ lái xe và các tính năng an toàn tiên tiến.\n" +
                    "\n" +
                    "Nhược điểm có thể có:\n" +
                    "\n" +
                    "    Tiếng ồn và cảm giác lái: Một số người dùng cho rằng Ford Ranger có tiếng ồn động cơ và cảm giác lái không mượt mà như một số đối thủ cạnh tranh.\n" +
                    "    Hạn chế không gian: So với một số đối thủ trong phân khúc, không gian nội thất của Ford Ranger có thể hạn chế đối với hành khách hàng ghế sau.";
            response.put("answer", answer);

        } else {
            response.put("answer", "As an AI language model, I haven’t personally used this product, but based on its features and customer reviews, I can confidently give it a five-star rating");
        }
        return response;
    }
    @PostMapping("/api/v1/ask/bard")
    public Map<String, String> askBard(@RequestBody AskMessage ask) throws JsonProcessingException {
        // make a POST request and return response

        String url = "http://localhost:5000/askbard"; // API endpoint URL
        String requestBody = "{\"question\":\"" + ask.getQuestion() + "\", \"context\":\"" + ask.getContext() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String answer = "";
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Deserialize the response body
            ObjectMapper objectMapper = new ObjectMapper();
            MessageResponse apiResponse = objectMapper.readValue(responseEntity.getBody(), MessageResponse.class);
            answer = apiResponse.getMessage();

        } else {
            // Handle the error case
            System.err.println("Request failed with status code: " + responseEntity.getStatusCode());
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("question", ask.getQuestion());
        response.put("answer", answer);

        return response;
    }
}
