package com.custom.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailModel {
    private String emailTo;
    private String emailFrom;
    private String emailCc;
    private String emailBody;
    private String emailSubject;
}
