package com.hexaware.easypay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimesheetDTO {

    private Long timesheetId;
    private Long empId;
    private Integer noDaysWorked;
    private Boolean approval;

}
