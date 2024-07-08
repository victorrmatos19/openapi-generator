package br.com.openapi.generator.controllers;

import br.com.openapi.generator.models.dto.GeneratorRequestDTO;
import br.com.openapi.generator.services.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generate")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @PostMapping
    public ResponseEntity<String> generateCode(@RequestBody GeneratorRequestDTO requestDTO){
        return ResponseEntity.ok(generatorService.generateCode(requestDTO));
    }
}
