package br.com.openapi.generator.controllers;

import br.com.openapi.generator.models.dto.GeneratorRequestDTO;
import br.com.openapi.generator.services.GeneratorService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/generate")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @PostMapping
    public ResponseEntity<List<String>> generateCode(@RequestBody GeneratorRequestDTO requestDTO){
        return ResponseEntity.ok(generatorService.generateCode(requestDTO));
    }
}
