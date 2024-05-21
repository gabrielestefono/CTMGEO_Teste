package br.com.ctmgeo.teste.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HttpMessageNotReadableCustomException extends RuntimeException {
	public HttpMessageNotReadableCustomException(String string) {
		super(string);
	}
}
