package br.com.openapi.generator.models.dto;

public class GeneratorRequestDTO {

    private String userPrompt;
    private String systemPrompt;

    public GeneratorRequestDTO(String userPrompt, String systemPrompt) {
        this.userPrompt = userPrompt;
        this.systemPrompt = systemPrompt;
    }

    public String getUserPrompt() {
        return userPrompt;
    }

    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }
}
