package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MailInfoDTO {

	private String from;
	private String to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private String body;
	private String[] attachments;
	
	
	public MailInfoDTO(String to, String subject, String body) {
		this.from = "phannhatlong44@gmail.com";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	
	
	
	
}
