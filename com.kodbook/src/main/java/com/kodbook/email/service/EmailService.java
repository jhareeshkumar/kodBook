package com.kodbook.email.service;

public interface EmailService {
    boolean sendEmail(String to, String subject, String content);
}
