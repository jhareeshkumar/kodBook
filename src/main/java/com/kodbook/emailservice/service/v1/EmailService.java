package com.kodbook.emailservice.service.v1;

public interface EmailService {
    boolean sendEmail(String to, String subject, String content);
}
