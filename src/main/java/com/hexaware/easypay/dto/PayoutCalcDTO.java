package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayoutCalcDTO {

    private Long payoutId;
    private Long empId;
    private BigDecimal base;
    private BigDecimal hra;
    private BigDecimal ta;
    private BigDecimal oa;
    private BigDecimal pf;
    private BigDecimal profTax;
    private BigDecimal incomeTax;
    private BigDecimal gross;
    private BigDecimal monthly;
    private BigDecimal daily;

}
