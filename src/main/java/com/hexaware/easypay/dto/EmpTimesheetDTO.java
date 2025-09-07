package com.hexaware.easypay.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpTimesheetDTO {

    @NotBlank
    @Min(value = 1)
    private Integer noDaysWorked;

}
