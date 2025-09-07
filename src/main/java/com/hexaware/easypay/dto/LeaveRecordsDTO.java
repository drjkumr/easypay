package com.hexaware.easypay.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeaveRecordsDTO {

    private Long leaveId;
    private Long empId;
    private LocalDate leaveDate;
    private LocalDateTime createdAt;

}
