package tech.builrun.springsecurity.controllers.dto;

public record LoginResponse(String acessToken, Long expiresIn) {
}
