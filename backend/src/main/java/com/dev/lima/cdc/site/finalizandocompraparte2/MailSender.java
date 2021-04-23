package com.dev.lima.cdc.site.finalizandocompraparte2;

import org.springframework.stereotype.Component;

@Component
public class MailSender implements EmailService {

	@Override
	public void enviarEmail() {
		System.out.println("Comunicando servidor email...");
		try {
			Thread.sleep(5000);
			System.out.println("Email enviado!");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
