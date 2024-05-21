package br.com.ctmgeo.teste.exceptions.handler;

import java.util.Date;

import javax.naming.ServiceUnavailableException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ctmgeo.teste.exceptions.errors.BadRequestException;
import br.com.ctmgeo.teste.exceptions.errors.ConflictException;
import br.com.ctmgeo.teste.exceptions.errors.ForbiddenException;
import br.com.ctmgeo.teste.exceptions.errors.InternalServerErrorException;
import br.com.ctmgeo.teste.exceptions.errors.MethodNotAllowedException;
import br.com.ctmgeo.teste.exceptions.errors.NotFoundException;
import br.com.ctmgeo.teste.exceptions.errors.UnauthorizedException;
import br.com.ctmgeo.teste.exceptions.messages.CustomMessage;

@RestControllerAdvice
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<CustomMessage> badRequestException(BadRequestException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<CustomMessage> unauthorizedException(UnauthorizedException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<CustomMessage> forbiddenException(ForbiddenException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomMessage> notFoundException(NotFoundException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodNotAllowedException.class)
	public ResponseEntity<CustomMessage> methodNotAllowedException(MethodNotAllowedException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<CustomMessage> conflictException(ConflictException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<CustomMessage> internalServerErrorException(InternalServerErrorException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<CustomMessage> serviceUnavailableException(ServiceUnavailableException ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomMessage> allOtherException(Exception ex) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(customMessage, HttpStatus.BAD_REQUEST);
	}

	@Override
	@SuppressWarnings("null")
	protected ResponseEntity<Object> handleHttpMessageNotReadable( HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomMessage customMessage = new CustomMessage(new Date(), ex.getMessage());
    return new ResponseEntity<>(customMessage, HttpStatus.BAD_REQUEST);
	}
}