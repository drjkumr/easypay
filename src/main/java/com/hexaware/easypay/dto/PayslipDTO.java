package com.hexaware.easypay.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayslipDTO {

    private Long payslipId;
    private Long empId;
    private BigDecimal actual_monthly_payout;
    private BigDecimal benefits;

}
