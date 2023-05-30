package com.example.webBanXeApi.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ChatBotService {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Demo chatbot!");

        String promptParameters = "Give me a brief and clear answer in 50 words or less: ";

        OpenAiService service = new OpenAiService("");

        // Create a loop to get user input and generate chatbot responses
        Scanner scanner = new Scanner(System.in);
            final List<ChatMessage> messages = new ArrayList<>();
            final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), promptParameters + "should i buy a new car or a used one?");
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

            AtomicBoolean stopCondition = new AtomicBoolean(false);

            service.streamChatCompletion(chatCompletionRequest)
                    .doOnError(throwable -> {
                        throwable.printStackTrace();
                        stopCondition.set(true);
                        service.shutdownExecutor();
                    })
                    .takeWhile(chunk -> {
                        ChatCompletionChoice choice = chunk.getChoices().get(0);
                        String finishReason = choice.getFinishReason();
                        if (finishReason != null && finishReason.equals("length")) {
                            stopCondition.set(true);
                            service.shutdownExecutor();
                            return false;
                        }
                        return !stopCondition.get();
                    })
                    .blockingForEach(chunk -> {
                        ChatMessage message = chunk.getChoices().get(0).getMessage();
                        if (message.getContent() != null) {
                            // Process the content here
                            concatenatedContent.append(message.getContent());
                        }
                    });



            String finalContent = concatenatedContent.toString();
            System.out.println(finalContent);
        }
}
