package com.dev.lima.cdc.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.dev.lima.cdc.site.carrinho.Carrinho;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Cookies {

	/**
	 * 
	 * @param key chave que vai ser gerada para o cookie
	 * @param carrinho carrinho de compras que vai ser serializado
	 * @param response
	 */
	public void writeAsJson(String key, Carrinho carrinho, HttpServletResponse response) {		
		try {
			Cookie cookie = new Cookie(key, new ObjectMapper().writeValueAsString(carrinho));
			cookie.setPath("/");
			cookie.setHttpOnly(false);
			response.addCookie(cookie);		
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
