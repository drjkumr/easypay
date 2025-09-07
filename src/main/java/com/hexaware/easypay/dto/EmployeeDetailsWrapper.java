package com.hexaware.easypay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDetailsWrapper {

    private DetailsChangeDTO details;
    private DetailsChangePswDTO password;

}
