package com.example.webBanXeApi.service;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.*;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ChatBotService {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        List<String> myList = new ArrayList<>();
        myList.add(".");
        myList.add("?");
        myList.add("!");
        myList.add("\n");

        String promptParameters = "Give me a brief and clear answer in 50 words or less: ";


        OpenAiService service = new OpenAiService("YOUR-OPENAI-API");


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
    }

}
