package com.hexaware.easypay.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailsChangeDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String dept;
    @NotBlank
    private String email;

}
