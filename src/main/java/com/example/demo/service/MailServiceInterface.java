package com.example.demo.service;

import com.example.demo.DTO.MailInfoDTO;

import jakarta.mail.MessagingException;

public interface MailServiceInterface {

	void sendMaild(String to, String subject, String body) throws MessagingException;

	void sendMaild(MailInfoDTO mail) throws MessagingException;

}
