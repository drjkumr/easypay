package com.hexaware.easypay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssuesDTO {

    private Long issueId;
    private Long empId;
    private String issue;

}
