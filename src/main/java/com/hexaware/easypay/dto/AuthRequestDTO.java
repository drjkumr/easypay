package com.hexaware.easypay.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
