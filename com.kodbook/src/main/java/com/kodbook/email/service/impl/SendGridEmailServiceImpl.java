package com.kodbook.email.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kodbook.email.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class SendGridEmailServiceImpl implements EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;

    @Override
    public boolean sendEmail(String to, String subject, String content) {
	// SendGrid email sending logic
	Email from = new Email("tech.annotation@gmail.com");
	Email toEmail = new Email(to);
	Content emailContent = new Content("text/plain", content);
	Mail mail = new Mail(from, subject, toEmail, emailContent);

	SendGrid sendGrid = new SendGrid(sendGridApiKey);
	Request request = new Request();

	request.setMethod(Method.POST);
	request.setEndpoint("/mail/send");
	try {
	    request.setBody(mail.build());
	    Response response = sendGrid.api(request);

	    return response.getStatusCode() == 202;

	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	}
    }
}