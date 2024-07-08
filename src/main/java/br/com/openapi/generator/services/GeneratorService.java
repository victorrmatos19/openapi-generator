package br.com.openapi.generator.services;

import br.com.openapi.generator.models.dto.GeneratorRequestDTO;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class GeneratorService {

    @Value("${openai.key}")
    private String KEY;

    private static final String GPT_VERSION = "gpt-4";

    public String generateCode(GeneratorRequestDTO requestDTO){
        var completionRequest = ChatCompletionRequest
                .builder()
                .model(GPT_VERSION)
                .messages(Arrays.asList(
                        new ChatMessage(ChatMessageRole.USER.value(), requestDTO.getUserPrompt()),
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), requestDTO.getSystemPrompt())
                ))
                .build();

        var service = new OpenAiService(KEY);

        AtomicReference<String> response = new AtomicReference<>("");
        service
                .createChatCompletion(completionRequest)
                .getChoices()
                .forEach(c -> response.set(c.getMessage().getContent()));

        return response.get();
    }
}
