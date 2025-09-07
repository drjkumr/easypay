package com.hexaware.easypay.dto;

import java.math.BigDecimal;

<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SalaryPolicyDTO {

    @NotNull
    @Pattern(regexp = "^\\d+$", message = "Must be a valid ID format")
    private int policyId;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal basePercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal hraPercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal taPercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal oaPercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal pfPercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal incomeTaxPercent;
    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Must be a valid decimal format")
    private BigDecimal profTaxFixed;

    public SalaryPolicyDTO() {
    }

    public SalaryPolicyDTO(int policyId, BigDecimal basePercent, BigDecimal hraPercent, BigDecimal taPercent,
            BigDecimal oaPercent, BigDecimal pfPercent, BigDecimal incomeTaxPercent, BigDecimal profTaxFixed) {
        this.policyId = policyId;
        this.basePercent = basePercent;
        this.hraPercent = hraPercent;
        this.taPercent = taPercent;
        this.oaPercent = oaPercent;
        this.pfPercent = pfPercent;
        this.incomeTaxPercent = incomeTaxPercent;
        this.profTaxFixed = profTaxFixed;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public BigDecimal getBasePercent() {
        return basePercent;
    }

    public void setBasePercent(BigDecimal basePercent) {
        this.basePercent = basePercent;
    }

    public BigDecimal getHraPercent() {
        return hraPercent;
    }

    public void setHraPercent(BigDecimal hraPercent) {
        this.hraPercent = hraPercent;
    }

    public BigDecimal getTaPercent() {
        return taPercent;
    }

    public void setTaPercent(BigDecimal taPercent) {
        this.taPercent = taPercent;
    }

    public BigDecimal getOaPercent() {
        return oaPercent;
    }

    public void setOaPercent(BigDecimal oaPercent) {
        this.oaPercent = oaPercent;
    }

    public BigDecimal getPfPercent() {
        return pfPercent;
    }

    public void setPfPercent(BigDecimal pfPercent) {
        this.pfPercent = pfPercent;
    }

    public BigDecimal getIncomeTaxPercent() {
        return incomeTaxPercent;
    }

    public void setIncomeTaxPercent(BigDecimal incomeTaxPercent) {
        this.incomeTaxPercent = incomeTaxPercent;
    }

    public BigDecimal getProfTaxFixed() {
        return profTaxFixed;
    }

    public void setProfTaxFixed(BigDecimal profTaxFixed) {
        this.profTaxFixed = profTaxFixed;
    }
=======
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaryPolicyDTO {

    @NotBlank
    private Long policyId;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal basePercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal hraPercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal oaPercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal taPercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal pfPercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal incomeTaxPercent;
    @NotBlank
    @DecimalMin(value = "0.0")
    private BigDecimal profTaxFixed;
    @NotBlank
    @DecimalMin(value = "0.0")
    private String policyDesc;
>>>>>>> 314dc4c (Updated latest backend)

}
