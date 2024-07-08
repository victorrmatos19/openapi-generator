package br.com.openapi.generator.services;

import br.com.openapi.generator.models.dto.GeneratorRequestDTO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeneratorService {

    @Value("${openai.key}")
    private String key;

    private static final String GPT_VERSION = "gpt-4";

    public List<String> generateCode(GeneratorRequestDTO requestDTO){
        var completionRequest = ChatCompletionRequest
                .builder()
                .model(GPT_VERSION)
                .messages(Arrays.asList(
                        new ChatMessage(ChatMessageRole.USER.value(), requestDTO.getUserPrompt()),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), requestDTO.getSystemPrompt())
                ))
                .build();
        var service = new OpenAiService(key);

        List<String> products = new ArrayList<>();
        service
                .createChatCompletion(completionRequest)
                .getChoices()
                .forEach(c -> products.add(c.getMessage().getContent()));

        return products;
    }
}
