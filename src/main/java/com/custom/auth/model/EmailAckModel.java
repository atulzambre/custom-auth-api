package com.custom.auth.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class EmailAckModel {
    private String emailTo;
    private Boolean isEmailSent;
}
