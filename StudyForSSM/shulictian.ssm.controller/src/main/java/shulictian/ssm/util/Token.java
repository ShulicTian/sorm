package shulictian.ssm.util;

import java.util.Date;

public class Token {

	private Token() {

	}

	private static Token token = new Token();

	public static Token getToken() {

		return token;
	}

	public String makeToken() {
		
		String token = Math.random()*10000+":"+new Date();

		return token;
	}
}
