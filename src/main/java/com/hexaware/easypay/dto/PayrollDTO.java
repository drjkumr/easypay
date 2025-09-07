package com.hexaware.easypay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayrollDTO {

    private Long empId;
    private Boolean active;
    private Long policyId;

}
