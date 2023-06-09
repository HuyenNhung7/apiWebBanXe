package com.example.webBanXeApi.controller;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@RestController
public class AskBotController {

    @PostMapping("/api/v1/ask")
    public Map<String, String> processQuestion(@RequestBody String question, @RequestParam(defaultValue = "") String context) {        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        String promptParameters = "Give me a brief and clear answer in 50 words or less: ";


        OpenAiService service = new OpenAiService("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");


        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), promptParameters + context + " " + question);
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
        response.put("answer", finalContent);
        return response;
    }

}
