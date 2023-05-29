package com.example.webBanXeApi.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


        String prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly." +
                "The assistant should only answer question in one sentence." +
                "Human: Hello, who are you? " +
                "AI: I am an AI created by OpenAI. How can I help you today? ";

        OpenAiService service = new OpenAiService("");

        // Create a loop to get user input and generate chatbot responses
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Get user input
            System.out.print("Human: ");
            String userInput = scanner.nextLine();

            // Append user input to the prompt
            prompt += "Human: " + userInput + " ";

            // Create a completion request with the prompt and some parameters
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .prompt(prompt)
                    .maxTokens(50)
                    .model("ada")
                    .stop(myList)
                    .build();
            // Call the service to create a completion and
            // Extract the generated text from the response
            String generatedText = service.createCompletion(completionRequest).getChoices().get(0).getText();

            System.out.println(prompt);

            // Append the generated text to the prompt
            prompt += "AI: " + generatedText + "\n";

            // Print the generated text as the chatbot's reply
            System.out.println("AI: " + generatedText);
        }

    }
}